package com.atguigu.gmall.search.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author chao
 * @create 2020-05-26
 */

@Document(indexName = "goods", type = "info", shards = 3, replicas = 2)
@Data
public class Goods {

    // 基本属性
    @Id
    @Field(type = FieldType.Long)
    private Long skuId;
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String title;
    @Field(type = FieldType.Keyword, index = false)
    private String subTitle;
    @Field(type = FieldType.Keyword, index = false)
    private String defaultImage;
    @Field(type = FieldType.Float)
    private BigDecimal price;

    // 品牌
    @Field(type = FieldType.Long)
    private Long brandId;
    @Field(type = FieldType.Keyword)
    private String  brandName;
    @Field(type = FieldType.Keyword)
    private String logo;

    // 分类
    @Field(type = FieldType.Long)
    private Long categoryId;
    @Field(type = FieldType.Keyword)
    private String categoryName;

    // 规格属性的过滤
    @Field(type = FieldType.Nested)
    private List<SearchAttrVo> searchAttrs;

    // 排序字段
    /**
     * 销量
     */
    @Field(type = FieldType.Long)
    private Long sales = 0L;
    @Field(type = FieldType.Date)
    private Date CreateTime;
    @Field(type = FieldType.Boolean)
    private Boolean store = false;
}
