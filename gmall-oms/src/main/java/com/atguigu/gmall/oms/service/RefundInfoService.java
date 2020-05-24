package com.atguigu.gmall.oms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.atguigu.gmall.common.bean.PageResultVo;
import com.atguigu.gmall.common.bean.PageParamVo;
import com.atguigu.gmall.oms.entity.RefundInfoEntity;

import java.util.Map;

/**
 * 退款信息
 *
 * @author chao
 * @email c15071211749@163.com
 * @date 2020-05-22 22:18:59
 */
public interface RefundInfoService extends IService<RefundInfoEntity> {

    PageResultVo queryPage(PageParamVo paramVo);
}

