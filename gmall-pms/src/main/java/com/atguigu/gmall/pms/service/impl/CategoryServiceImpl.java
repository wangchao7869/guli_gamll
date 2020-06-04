package com.atguigu.gmall.pms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.gmall.common.bean.PageResultVo;
import com.atguigu.gmall.common.bean.PageParamVo;

import com.atguigu.gmall.pms.mapper.CategoryMapper;
import com.atguigu.gmall.pms.entity.CategoryEntity;
import com.atguigu.gmall.pms.service.CategoryService;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, CategoryEntity> implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public PageResultVo queryPage(PageParamVo paramVo) {
        IPage<CategoryEntity> page = this.page(
                paramVo.getPage(),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageResultVo(page);
    }

    /**
     * 根据父id查询分类
     *
     * @param parentId -1：查询所有，0：查询一级节点
     * @return
     */
    @Override
    public List<CategoryEntity> queryCategory(Long parentId) {
        QueryWrapper<CategoryEntity> queryWrapper = new QueryWrapper<>();
        if (parentId != -1) {
            queryWrapper.eq("parent_id", parentId);
        }
        return this.categoryMapper.selectList(queryWrapper);
    }


    @Override
    public List<CategoryEntity> queryCategoriesWithSubByPid(Long parentId) {

        return this.categoryMapper.queryCategoriesWithSubByPid(parentId);
    }

    @Override
    public List<CategoryEntity> queryCategoriesByCid3(Long cid3) {
        CategoryEntity lvl3category = this.getById(cid3);
        Long cid2 = lvl3category.getParentId();
        CategoryEntity lvl2Category = this.getById(cid2);

        Long cid1 = lvl2Category.getParentId();
        CategoryEntity lvl1Category = this.getById(cid1);

        List<CategoryEntity> categoryEntities = Arrays.asList(lvl1Category,lvl2Category,lvl3category);
        return categoryEntities ;
    }

}