package com.atguigu.gmall.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.atguigu.gmall.common.bean.PageResultVo;
import com.atguigu.gmall.common.bean.PageParamVo;
import com.atguigu.gmall.pms.entity.CategoryEntity;

import java.util.List;
import java.util.Map;

/**
 * 商品三级分类
 *
 * @author chao
 * @email c15071211749@163.com
 * @date 2020-05-20 23:21:55
 */
public interface CategoryService extends IService<CategoryEntity> {

    PageResultVo queryPage(PageParamVo paramVo);

    /**
     * 根据父id查询分类
     * @param parentId -1：查询所有，0：查询一级节点
     * @return
     */
    List<CategoryEntity> queryCategory(Long parentId);
}

