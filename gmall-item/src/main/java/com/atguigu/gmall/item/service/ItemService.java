package com.atguigu.gmall.item.service;

import com.atguigu.gmall.common.bean.ResponseVo;
import com.atguigu.gmall.item.feign.GmallPmsClient;
import com.atguigu.gmall.item.feign.GmallSmsClient;
import com.atguigu.gmall.item.feign.GmallWmsClient;
import com.atguigu.gmall.item.vo.ItemVo;
import com.atguigu.gmall.pms.entity.*;
import com.atguigu.gmall.pms.vo.ItemGroupVo;
import com.atguigu.gmall.pms.vo.SaleAttrValueVo;
import com.atguigu.gmall.sms.vo.ItemSaleVo;
import com.atguigu.gmall.wms.entity.WareSkuEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author chao
 * @create 2020-06-04
 */

@Service
public class ItemService {

    @Autowired
    private GmallPmsClient pmsClient;

    @Autowired
    private GmallSmsClient smsClient;

     @Autowired
     private GmallWmsClient wmsClient;

    public ItemVo queryItemBySkuId(Long skuId) {

        // sku的基本信息
        ResponseVo<SkuEntity> skuEntityResponseVo = this.pmsClient.querySkuById(skuId);
        SkuEntity skuEntity = skuEntityResponseVo.getData();
        if (skuEntity == null) {
            return null;
        }
        ItemVo itemVo = new ItemVo();
        itemVo.setSkuId(skuId);
        itemVo.setTitle(skuEntity.getTitle());
        itemVo.setSubTitle(skuEntity.getSubtitle());
        itemVo.setPrice(skuEntity.getPrice());
        itemVo.setWeight(skuEntity.getWeight());
        itemVo.setDefaultImage(skuEntity.getDefaultImage());

        // 三级分类
        ResponseVo<List<CategoryEntity>> categoryResponseVo = this.pmsClient.queryCategoriesByCid3(skuEntity.getCatagoryId());
        List<CategoryEntity> categoryEntities = categoryResponseVo.getData();
        if (!CollectionUtils.isEmpty(categoryEntities)) {
            itemVo.setCategories(categoryEntities);
        }

        // 品牌信息
        ResponseVo<BrandEntity> brandEntityResponseVo = this.pmsClient.queryBrandById(skuEntity.getBrandId());
        BrandEntity brandEntity = brandEntityResponseVo.getData();
        if (brandEntity != null) {
            itemVo.setBrandId(brandEntity.getId());
            itemVo.setBrandName(brandEntity.getName());
        }


        // spu信息
        ResponseVo<SpuEntity> spuEntityResponseVo = this.pmsClient.querySpuById(skuEntity.getSpuId());
        SpuEntity spuEntity = spuEntityResponseVo.getData();
        if (spuEntity != null) {
            itemVo.setSpuId(spuEntity.getId());
            itemVo.setSpuName(spuEntity.getName());
        }


        // spu 的图片列表
        ResponseVo<List<SkuImagesEntity>> imagesResponseVo = this.pmsClient.queryImagesBySkuId(skuId);
        List<SkuImagesEntity> skuImagesEntities = imagesResponseVo.getData();
        if (!CollectionUtils.isEmpty(skuImagesEntities)) {
            itemVo.setImages(skuImagesEntities);
        }

        // 促销信息
        ResponseVo<List<ItemSaleVo>> salesResponseVo = this.smsClient.querySaleVosBySkuId(skuId);
        List<ItemSaleVo> itemSaleVos = salesResponseVo.getData();
        if (!CollectionUtils.isEmpty(itemSaleVos)) {
            itemVo.setSales(itemSaleVos);
        }

        // 库存信息
        ResponseVo<List<WareSkuEntity>> wareResponseVo = this.wmsClient.queryWareSkuBySkuId(skuId);
        List<WareSkuEntity> wareSkuEntities = wareResponseVo.getData();
        if (!CollectionUtils.isEmpty(wareSkuEntities)) {
            itemVo.setStore(wareSkuEntities.stream().anyMatch(wareSkuEntity -> wareSkuEntity.getStock() - wareSkuEntity.getStockLocked() > 0));
        }

        // spu所有销售属性
        ResponseVo<List<SaleAttrValueVo>> saleAttrResponseVo = this.pmsClient.querySaleAttrValuesBySpuId(skuEntity.getSpuId());
        List<SaleAttrValueVo> saleAttrValueVos = saleAttrResponseVo.getData();
        if (!CollectionUtils.isEmpty(saleAttrValueVos)) {
            itemVo.setSaleAttrs(saleAttrValueVos);
        }

        // 当前sku的销售属性
        ResponseVo<List<SkuAttrValueEntity>> skuAttrValueResponseVo = this.pmsClient.querySaleAttrValueBySkuId(skuId);
        // [{attrId: 8, attrName: 颜色, attrValue: 黑色}, {attrId: 9, attrName: 内存, attrValue: 12G}, {attrId: 10, attrName: 存储, attrValue: 128G}]
        List<SkuAttrValueEntity> skuAttrValueEntities = skuAttrValueResponseVo.getData();
        // {8: 黑色, 9: 12G, 10: 256G}
        if (!CollectionUtils.isEmpty(skuAttrValueEntities)) {
            Map<Long, String> map = skuAttrValueEntities.stream().collect(Collectors.toMap(SkuAttrValueEntity::getAttrId, SkuAttrValueEntity::getAttrValue));
            itemVo.setSaleAttr(map);
        }

        // spu所有销售属性和sku的对应关系
        ResponseVo<String> skuJsonResponseVo = this.pmsClient.querySkuJsonsBySpuId(skuEntity.getSpuId());
        String skuJsons = skuJsonResponseVo.getData();
        itemVo.setSkuJsons(skuJsons);

        // spk的海报信息
        ResponseVo<SpuDescEntity> spuDescEntityResponseVo = this.pmsClient.querySpuDescById(skuEntity.getSpuId());
        SpuDescEntity spuDescEntity = spuDescEntityResponseVo.getData();
        if (spuDescEntity != null) {
            String decript = spuDescEntity.getDecript();
            itemVo.setSpuImages(Arrays.asList(StringUtils.split(decript,",")));
        }

        // 分组及组下属性信息
        ResponseVo<List<ItemGroupVo>> groupResponseVo = this.pmsClient.queryItemGroupVoByCidAndSpuIdAndSkuId(skuEntity.getCatagoryId(), skuEntity.getSpuId(), skuId);
        List<ItemGroupVo> itemGroupVos = groupResponseVo.getData();
        if (!CollectionUtils.isEmpty(itemGroupVos)) {
            itemVo.setGroups(itemGroupVos);
        }


        return itemVo;
    }
}
