package com.atguigu.gmall.sms.service;

import com.atguigu.gmall.sms.vo.SkuSaleVo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.atguigu.gmall.common.bean.PageResultVo;
import com.atguigu.gmall.common.bean.PageParamVo;
import com.atguigu.gmall.sms.entity.SkuBoundsEntity;

import java.util.Map;

/**
 * 商品spu积分设置
 *
 * @author chao
 * @email c15071211749@163.com
 * @date 2020-05-22 22:05:26
 */
public interface SkuBoundsService extends IService<SkuBoundsEntity> {

    PageResultVo queryPage(PageParamVo paramVo);

    /**
     * 新增sku的营销信息
     * @param skuSaleVo
     */
    void saveSkuSaleInfo(SkuSaleVo skuSaleVo);

}

