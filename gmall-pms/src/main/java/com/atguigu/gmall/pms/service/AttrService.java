package com.atguigu.gmall.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.atguigu.gmall.common.bean.PageResultVo;
import com.atguigu.gmall.common.bean.PageParamVo;
import com.atguigu.gmall.pms.entity.AttrEntity;

import java.util.List;
import java.util.Map;

/**
 * 商品属性
 *
 * @author chao
 * @email c15071211749@163.com
 * @date 2020-05-20 23:21:55
 */
public interface AttrService extends IService<AttrEntity> {

    PageResultVo queryPage(PageParamVo paramVo);


    /**
     * 查询分类下的销售属性
     * @param cid
     * @param type
     * @param searchType
     * @return
     */
    List<AttrEntity> queryAttrsByCid(Long cid, Integer type, Integer searchType);
}

