package com.atguigu.gmall.pms.api;

import com.atguigu.gmall.common.bean.PageParamVo;
import com.atguigu.gmall.common.bean.ResponseVo;
import com.atguigu.gmall.pms.entity.*;
import com.atguigu.gmall.pms.vo.ItemGroupVo;
import com.atguigu.gmall.pms.vo.SaleAttrValueVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author chao
 * @create 2020-05-27
 */

public interface GmallPmsApi {
    /**
     * 分页查询spu
     * @param pageParamVo
     * @return
     */
    @PostMapping("pms/spu/page")
    public ResponseVo<List<SpuEntity>> querySpusByPage(@RequestBody PageParamVo pageParamVo);

    /**
     * 根据spuId查询sku
     * @param spuId
     * @return
     */
    @GetMapping("pms/sku/spu/{spuId}")
    public ResponseVo<List<SkuEntity>> querySkusBySpuId(@PathVariable("spuId")Long spuId);

    /**
     * 根据分类Id查询分类
     * @param id
     * @return
     */
    @GetMapping("pms/category/{id}")
    public ResponseVo<CategoryEntity> queryCategoryById(@PathVariable("id") Long id);

    /**
     * 根据品牌Id查询品牌
     * @param id
     * @return
     */
    @GetMapping("pms/brand/{id}")
    public ResponseVo<BrandEntity> queryBrandById(@PathVariable("id") Long id);

    /**
     * 根据spuId查询搜索类型的基本属性及值
     * @param spuId
     * @return
     */
    @GetMapping("pms/spuattrvalue/spu/{spuId}")
    public ResponseVo<List<SpuAttrValueEntity>> querySearchAttrValueBySpuId(@PathVariable("spuId")Long spuId);

    /**
     * 根据skuId查询搜索类型的销售属性及值
     * @param skuId
     * @return
     */
    @GetMapping("pms/skuattrvalue/sku/{skuId}")
    public ResponseVo<List<SkuAttrValueEntity>> querySearchAttrValueBySkuId(@PathVariable("skuId")Long skuId);

    /**
     * 根据父id查询子分类
     * @param parentId
     * @return
     */
    @GetMapping("pms/category/parent/{parentId}")
    public ResponseVo<List<CategoryEntity>> queryCategoriesByPid(@PathVariable("parentId") Long parentId);

    /**
     * 根据父Id查询所有的子分类
     * @param parentId
     * @return
     */
    @GetMapping("pms/category/parent/with/subs/{parentId}")
    public ResponseVo<List<CategoryEntity>> queryCategoriesWithSubByPid(@PathVariable("parentId") Long parentId);

    /**
     * 根据spuId查询spu
     */
    @GetMapping("pms/spu/{id}")
    public ResponseVo<SpuEntity> querySpuById(@PathVariable("id") Long id);

    /**
     * 根据skuId查询sku
     */
    @GetMapping("pms/sku/{id}")
    @ApiOperation("详情查询")
    public ResponseVo<SkuEntity> querySkuById(@PathVariable("id") Long id);


    /**
     * 根据三级分类的Id查询一二三级分类
     * @param cid3
     * @return
     */
    @GetMapping("pms/category/all/{cid3}")
    public ResponseVo<List<CategoryEntity>> queryCategoriesByCid3(@PathVariable("cid3")Long cid3);


    /**
     * 根据skuId查询sku的图片列表
     * @param skuId
     * @return
     */
    @GetMapping("pms/skuimages/sku/{skuId}")
    public ResponseVo<List<SkuImagesEntity>> queryImagesBySkuId(@PathVariable("skuId")Long skuId);

    /**
     * 根据spuId查询所有的销售属性
     * @param spuId
     * @return
     */
    @GetMapping("pms/skuattrvalue/spu/{spuId}")
    public ResponseVo<List<SaleAttrValueVo>> querySaleAttrValuesBySpuId(@PathVariable("spuId")Long spuId);

    /**
     * 根据spuId查询销售属性和skuId的对应关系
     * @param spuId
     * @return
     */
    @GetMapping("pms/skuattrvalue/sku/spu/{spuId}")
    public ResponseVo<String> querySkuJsonsBySpuId(@PathVariable("spuId")Long spuId);

    /**
     * 根据spuId查询描述信息
     * @param spuId
     * @return
     */
    @GetMapping("pms/spudesc/{spuId}")
    public ResponseVo<SpuDescEntity> querySpuDescById(@PathVariable("spuId") Long spuId);

    @GetMapping("pms/attrgroup/item/group")
    public ResponseVo<List<ItemGroupVo>> queryItemGroupVoByCidAndSpuIdAndSkuId(@RequestParam("cid")Long cid, @RequestParam("spuId")Long spuId, @RequestParam("skuId")Long skuId);

    /**
     * 根据skuId查询当前sku所有销售属性及值
     * @param skuId
     * @return
     */
    @GetMapping("pms/skuattrvalue/sku/sale/{skuId}")
    public ResponseVo<List<SkuAttrValueEntity>> querySaleAttrValueBySkuId (@PathVariable("skuId")Long skuId);
}
