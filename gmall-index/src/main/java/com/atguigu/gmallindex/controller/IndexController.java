package com.atguigu.gmallindex.controller;

import com.atguigu.gmall.common.bean.ResponseVo;
import com.atguigu.gmall.pms.entity.CategoryEntity;
import com.atguigu.gmallindex.service.IndexService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author chao
 * @create 2020-06-01
 */
@Controller
public class IndexController {

    @Autowired
    private IndexService indexService;

    @ApiOperation("跳转到首页，并加载一级分类")
    @GetMapping
    public String toIndex(Model model) {
        List<CategoryEntity> categoryEntity = this.indexService.queryLvl1Categories();
        model.addAttribute("categories",categoryEntity);
        // TODO: 加载其他数据
        return "index";
    }

    @GetMapping("/index/cates/{pid}")
    @ResponseBody
    public ResponseVo<List<CategoryEntity>> queryLvl2CategoriesWithSubs(@PathVariable("pid")Long pid) {
        List<CategoryEntity> categoryEntities = this.indexService.queryLvl2CategoriesWithSubs(pid);
        return ResponseVo.ok(categoryEntities);
    }
}
