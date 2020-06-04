package com.atguigu.gmall.pms.mapper;

import com.atguigu.gmall.pms.entity.SkuAttrValueEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * sku销售属性&值
 *
 * @author chao
 * @email c15071211749@163.com
 * @date 2020-05-20 23:21:55
 */
@Mapper
public interface SkuAttrValueMapper extends BaseMapper<SkuAttrValueEntity> {

    List<SkuAttrValueEntity> querySearchAttrValueBySkuId(Long skuId);

    List<SkuAttrValueEntity> querySaleAttrValuesBySpuId(Long spuId);

    List<Map<String, Object>> querySkuJsonsBySpuId(Long spuId);

    List<SkuAttrValueEntity> querySkuAttrValuesBySkuIdAndGId(@Param("skuId") Long skuId,
                                                             @Param("groupId") Long id);
}
