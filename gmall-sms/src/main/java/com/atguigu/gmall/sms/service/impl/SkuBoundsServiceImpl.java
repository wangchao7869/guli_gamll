package com.atguigu.gmall.sms.service.impl;

import com.atguigu.gmall.sms.entity.SkuFullReductionEntity;
import com.atguigu.gmall.sms.entity.SkuLadderEntity;
import com.atguigu.gmall.sms.mapper.SkuFullReductionMapper;
import com.atguigu.gmall.sms.mapper.SkuLadderMapper;
import com.atguigu.gmall.sms.vo.ItemSaleVo;
import com.atguigu.gmall.sms.vo.SkuSaleVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.gmall.common.bean.PageResultVo;
import com.atguigu.gmall.common.bean.PageParamVo;

import com.atguigu.gmall.sms.mapper.SkuBoundsMapper;
import com.atguigu.gmall.sms.entity.SkuBoundsEntity;
import com.atguigu.gmall.sms.service.SkuBoundsService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;


@Service("skuBoundsService")
public class SkuBoundsServiceImpl extends ServiceImpl<SkuBoundsMapper, SkuBoundsEntity> implements SkuBoundsService {

    @Autowired
    private SkuFullReductionMapper skuFullReductionMapper;

    @Autowired
    private SkuLadderMapper skuLadderMapper;

    @Override
    public PageResultVo queryPage(PageParamVo paramVo) {
        IPage<SkuBoundsEntity> page = this.page(
                paramVo.getPage(),
                new QueryWrapper<SkuBoundsEntity>()
        );

        return new PageResultVo(page);
    }

    /**
     * 新增sku的营销信息
     *
     * @param skuSaleVo
     */
    @Transactional
    @Override
    public void saveSkuSaleInfo(SkuSaleVo skuSaleVo) {
        // 3.1. 积分优惠
        SkuBoundsEntity skuBoundsEntity = new SkuBoundsEntity();
        BeanUtils.copyProperties(skuSaleVo, skuBoundsEntity);
        // 数据库保存的是整数0-15，页面绑定的是0000-1111
        List<Integer> work = skuSaleVo.getWork();
        if (!CollectionUtils.isEmpty(work)) {
            skuBoundsEntity.setWork(work.get(0) * 8 + work.get(1) * 4 + work.get(2) * 2 + work.get(3));
        }
        this.save(skuBoundsEntity);

        // 3.2. 满减优惠
        SkuFullReductionEntity skuFullReductionEntity = new SkuFullReductionEntity();
        BeanUtils.copyProperties(skuSaleVo, skuFullReductionEntity);
        skuFullReductionEntity.setAddOther(skuSaleVo.getAddOther());
        this.skuFullReductionMapper.insert(skuFullReductionEntity);

        // 3.3. 数量折扣
        SkuLadderEntity skuLadderEntity = new SkuLadderEntity();
        BeanUtils.copyProperties(skuSaleVo, skuLadderEntity);
        this.skuLadderMapper.insert(skuLadderEntity);
    }

    @Override
    public List<ItemSaleVo> querySaleVosBySkuId(Long skuId) {

        List<ItemSaleVo> itemSaleVos = new ArrayList<>();

        // 积分
        SkuBoundsEntity boundsEntity = this.getOne(new QueryWrapper<SkuBoundsEntity>().eq("sku_id", skuId));
        if (boundsEntity != null) {
            ItemSaleVo boundSaleVo = new ItemSaleVo();
            boundSaleVo.setType("积分");
            boundSaleVo.setDesc("送" + boundsEntity.getGrowBounds() + "成长积分，送"+ boundsEntity.getBuyBounds() + "购物积分");
            itemSaleVos.add(boundSaleVo);
        }

        // 满减
        SkuFullReductionEntity reductionEntity = this.skuFullReductionMapper.selectOne(new QueryWrapper<SkuFullReductionEntity>().eq("sku_id", skuId));
        if (reductionEntity != null) {
            ItemSaleVo reductionSaleVo = new ItemSaleVo();
            reductionSaleVo.setType("满减");
            reductionSaleVo.setDesc("满" + reductionEntity.getFullPrice() + "减" +reductionEntity.getReducePrice());
            itemSaleVos.add(reductionSaleVo);
        }
        // 打折
        SkuLadderEntity skuLadderEntity = this.skuLadderMapper.selectOne(new QueryWrapper<SkuLadderEntity>().eq("sku_id", skuId));
        if (skuLadderEntity != null) {
            ItemSaleVo ladderSaleVo = new ItemSaleVo();
            ladderSaleVo.setType("打折");
            ladderSaleVo.setDesc("满" + skuLadderEntity.getFullCount() + "件打" + skuLadderEntity.getDiscount().divide(new BigDecimal(10)) + "折");
            itemSaleVos.add(ladderSaleVo);
        }

        return itemSaleVos;
    }


}