package com.atguigu.gmall.pms.vo;

import lombok.Data;

import java.util.List;

/**
 * @author chao
 * @create 2020-06-03
 */

@Data
public class ItemGroupVo {
    // 规格参数组的名称
    private String groupName;
    // 分组下的规格参数列表
    private List<AttrValueVo> attrValues;

}
