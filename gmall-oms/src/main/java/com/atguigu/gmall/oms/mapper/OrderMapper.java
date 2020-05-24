package com.atguigu.gmall.oms.mapper;

import com.atguigu.gmall.oms.entity.OrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单
 * 
 * @author chao
 * @email c15071211749@163.com
 * @date 2020-05-22 22:18:59
 */
@Mapper
public interface OrderMapper extends BaseMapper<OrderEntity> {
	
}
