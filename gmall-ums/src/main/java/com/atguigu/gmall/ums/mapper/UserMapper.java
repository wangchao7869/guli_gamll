package com.atguigu.gmall.ums.mapper;

import com.atguigu.gmall.ums.entity.UserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户表
 *
 * @author chao
 * @email c15071211749@163.com
 * @date 2020-05-21 10:29:02
 */
@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {

}
