package com.atguigu.gmall.search.entity;

import lombok.Data;

import java.util.List;

/**
 * @author chao
 * @create 2020-05-28
 */
@Data
public class SearchParam {
    /**
     * 搜索框中的条件
     */
    private String keyword;

    /**
     * 品牌过滤条件
     */
    private List<Long> brandId;

    /**
     * 分类过滤条件
     */
    private List<Long> cid;

    /**
     * 规格参数的过滤条件
     * ["5:128G-256G","4:8G-12G"]
     */
    private List<String > props;

    /**
     * 默认得分排序，1-价格降序，2-价格降序，3-销量降序，4-新品降序
     */
    private Integer sort;


    /**
     * 价格区间筛选
     */
    private Integer priceFrom;
    private Integer priceTo;

    /**
     * 分页参数
     */
    private Integer pageNum = 1;
    private final Integer pageSize = 20;

    /**
     * 是否有货筛选
     */
    private Boolean store;

}
