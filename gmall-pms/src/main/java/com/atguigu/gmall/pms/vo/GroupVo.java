package com.atguigu.gmall.pms.vo;

import com.atguigu.gmall.pms.entity.AttrEntity;
import com.atguigu.gmall.pms.entity.AttrGroupEntity;
import lombok.Data;

import java.util.List;

/**
 * @author chao
 * @create 2020-05-21
 */
@Data
public class GroupVo extends AttrGroupEntity {
    private List<AttrEntity> attrEntities;
}
