package com.atguigu.gmall.ums.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 购物积分记录表
 * 
 * @author chao
 * @email c15071211749@163.com
 * @date 2020-05-21 10:29:02
 */
@Data
@TableName("ums_integration_history")
public class IntegrationHistoryEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;
	/**
	 * member_id
	 */
	private Long userId;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 变动数量
	 */
	private Integer count;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 来源
	 */
	private Integer sourceType;

}
