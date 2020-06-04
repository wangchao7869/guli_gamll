package com.atguigu.gmall.item.vo;

import com.atguigu.gmall.pms.entity.CategoryEntity;
import com.atguigu.gmall.pms.vo.ItemGroupVo;
import com.atguigu.gmall.pms.vo.SaleAttrValueVo;
import com.atguigu.gmall.pms.entity.SkuImagesEntity;
import com.atguigu.gmall.sms.vo.ItemSaleVo;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author chao
 * @create 2020-06-03
 */

@Data
public class ItemVo {
    // 面包屑所需的三级分类
    private List<CategoryEntity> categories;

    // 面包屑所需的品牌信息
    private Long brandId;
    private String brandName;

    // 面包屑所需的spu信息
    private Long spuId;
    private String spuName;

    // 中间模块： sku一些信息
    private Long skuId;
    private String title;
    private String subTitle;
    private BigDecimal price;
    private Integer weight;
    private String defaultImage;

    // 中间模块： sku的图片列表
    private List<SkuImagesEntity> images;

    // 中间模块： sku的促销信息
    private List<ItemSaleVo> sales;

    // 中间模块： sku的库存信息
    private Boolean store = false;

    // 中间模块： spu所有的销售属性组合
    private List<SaleAttrValueVo> saleAttrs;
    // 中间模块：当前sku的销售属性
    // {8: 黑色, 9: 12G, 10: 256G}
    private Map<Long ,String > saleAttr;
    // 销售属性组合和skuId的对应关系
    // {'黑色, 8G, 256G': 30, '黑色, 12G, 512G': 32}
    private String skuJsons;

    // 商品详情：spu的图片列表
    private List<String> spuImages;

    // 规格参数组及租下的规格参数值
    private List<ItemGroupVo> groups;

}
