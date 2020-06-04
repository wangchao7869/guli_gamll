package com.atguigu.gmallindex.service;

import com.atguigu.gmall.common.bean.ResponseVo;
import com.atguigu.gmall.pms.entity.CategoryEntity;
import com.atguigu.gmallindex.config.GmallCache;
import com.atguigu.gmallindex.feign.GmallPmsClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chao
 * @create 2020-06-01
 */
@Service
public class IndexService {

    @Autowired
    private GmallPmsClient pmsClient;

    private static final String KEY_PREFIX = "index:cats:";

    /**
     * 一级分类
     * @return
     */
    public List<CategoryEntity> queryLvl1Categories() {
        ResponseVo<List<CategoryEntity>> listResponseVo = this.pmsClient.queryCategoriesByPid(0L);
        return listResponseVo.getData();
    }


    @GmallCache(prefix = KEY_PREFIX, lock = "lock", timeout = 129600L, random = 10080)
    public List<CategoryEntity> queryLvl2CategoriesWithSubs(Long pid) {
        ResponseVo<List<CategoryEntity>> listResponseVo = this.pmsClient.queryCategoriesWithSubByPid(pid);
        return listResponseVo.getData();
    }
}
