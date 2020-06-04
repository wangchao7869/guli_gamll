package com.atguigu.gmall.pms.vo;

import lombok.Data;

import java.util.List;
import java.util.Set;

/**
 * @author chao
 * @create 2020-06-03
 */

@Data
public class SaleAttrValueVo {

    private Long attrId;
    private String attrName;
    private Set<String> attrValues;
}
