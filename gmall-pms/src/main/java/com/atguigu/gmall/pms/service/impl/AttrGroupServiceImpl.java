package com.atguigu.gmall.pms.service.impl;

import com.atguigu.gmall.pms.entity.AttrEntity;
import com.atguigu.gmall.pms.entity.SkuAttrValueEntity;
import com.atguigu.gmall.pms.entity.SpuAttrValueEntity;
import com.atguigu.gmall.pms.mapper.AttrMapper;
import com.atguigu.gmall.pms.mapper.SkuAttrValueMapper;
import com.atguigu.gmall.pms.mapper.SpuAttrValueMapper;
import com.atguigu.gmall.pms.vo.AttrValueVo;
import com.atguigu.gmall.pms.vo.GroupVo;
import com.atguigu.gmall.pms.vo.ItemGroupVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.gmall.common.bean.PageResultVo;
import com.atguigu.gmall.common.bean.PageParamVo;

import com.atguigu.gmall.pms.mapper.AttrGroupMapper;
import com.atguigu.gmall.pms.entity.AttrGroupEntity;
import com.atguigu.gmall.pms.service.AttrGroupService;
import org.springframework.util.CollectionUtils;


@Service("attrGroupService")
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupMapper, AttrGroupEntity> implements AttrGroupService {

    @Autowired
    private AttrMapper attrMapper;

    @Override
    public PageResultVo queryPage(PageParamVo paramVo) {
        IPage<AttrGroupEntity> page = this.page(
                paramVo.getPage(),
                new QueryWrapper<AttrGroupEntity>()
        );

        return new PageResultVo(page);
    }

    /**
     * 根据三级分类id查询分组及组下的规格参数
     *
     * @param catId
     * @return
     */
    @Override
    public List<GroupVo> queryByCatId(Long catId) {
        // 查询所有的分组
        List<AttrGroupEntity> attrGroupEntities = this.list(new QueryWrapper<AttrGroupEntity>().eq("category_id", catId));

        // 查询出每组下的规格参数
        return attrGroupEntities.stream().map(attrGroupEntity -> {
            GroupVo groupVo = new GroupVo();
            BeanUtils.copyProperties(attrGroupEntity, groupVo);
            // 查询规格参数，只需查询出每个分组下的通用属性就可以了（不需要销售属性）
            List<AttrEntity> attrEntities = this.attrMapper.selectList(new QueryWrapper<AttrEntity>().eq("group_id", attrGroupEntity.getId()).eq("type", 1));
            groupVo.setAttrEntities(attrEntities);
            return groupVo;
        }).collect(Collectors.toList());
    }

    @Autowired
    private SpuAttrValueMapper spuAttrValueMapper;
    @Autowired
    private SkuAttrValueMapper skuAttrValueMapper;

    @Override
    public List<ItemGroupVo> queryItemGroupVoByCidAndSpuIdAndSkuId(Long cid,
                                                                   Long spuId,
                                                                   Long skuId) {
        List<AttrGroupEntity> attrGroupEntities = this.list(new QueryWrapper<AttrGroupEntity>().eq("category_id", cid));
        if (CollectionUtils.isEmpty(attrGroupEntities)) {
            return null;
        }

        return attrGroupEntities.stream().map(attrGroupEntity -> {
            ItemGroupVo itemGroupVo = new ItemGroupVo();
            // 设置分组名称
            itemGroupVo.setGroupName(attrGroupEntity.getName());

            List<AttrValueVo> attrValueVos = new ArrayList<>();
            // 查询当前组下的基本属性及值
            List<SpuAttrValueEntity> spuAttrValueEntities = this.spuAttrValueMapper.querySpuAttrValuesBySpuIdAndGId(spuId, attrGroupEntity.getId());
            if (!CollectionUtils.isEmpty(spuAttrValueEntities)) {
                attrValueVos.addAll(spuAttrValueEntities.stream().map(spuAttrValueEntity -> {
                    AttrValueVo attrValueVo = new AttrValueVo();
                    BeanUtils.copyProperties(spuAttrValueEntity,attrValueVo);
                    return attrValueVo;
                }).collect(Collectors.toList()));
            }

            // 查询当前组下的销售属性及值
            List<SkuAttrValueEntity> skuAttrValueEntities = this.skuAttrValueMapper.querySkuAttrValuesBySkuIdAndGId(skuId, attrGroupEntity.getId());
            if (!CollectionUtils.isEmpty(skuAttrValueEntities)) {
                attrValueVos.addAll(skuAttrValueEntities.stream().map(skuAttrValueEntity -> {
                    AttrValueVo attrValueVo = new AttrValueVo();
                    BeanUtils.copyProperties(skuAttrValueEntity,attrValueVo);
                    return attrValueVo;
                }).collect(Collectors.toList()));
            }

            itemGroupVo.setAttrValues(attrValueVos);
            return itemGroupVo;
        }).collect(Collectors.toList());
    }


}