package com.atguigu.gmall.pms.vo;

import com.alibaba.nacos.client.utils.StringUtils;
import com.atguigu.gmall.pms.entity.SpuAttrValueEntity;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Stream;

/**
 * @author chao
 * @create 2020-05-22
 */

public class SpuAttrValueVo extends SpuAttrValueEntity {
    public void setValueSelected(List<Object> valueSelected) {
        if (CollectionUtils.isEmpty(valueSelected)) {
            return;
        }
        this.setAttrValue(StringUtils.join(valueSelected, ","));
    }

}
