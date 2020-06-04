package com.atguigu.gmall.pms.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 属性分组
 *
 * @author chao
 * @email c15071211749@163.com
 * @date 2020-05-20 23:21:55
 */
@Data
@TableName("pms_attr_group")
public class AttrGroupEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 分组id
     */
    @TableId
    private Long id;
    /**
     * 组名
     */
    private String name;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 组图标
     */
    private String icon;
    /**
     * 所属分类id
     */
    private Long categoryId;
    /**
     * 备注
     */
    private String remark;

}
