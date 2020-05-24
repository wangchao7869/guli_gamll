package com.atguigu.gmall.pms.service;

import com.atguigu.gmall.pms.vo.SpuVo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.atguigu.gmall.common.bean.PageResultVo;
import com.atguigu.gmall.common.bean.PageParamVo;
import com.atguigu.gmall.pms.entity.SpuEntity;

import java.util.List;
import java.util.Map;

/**
 * spu信息
 *
 * @author chao
 * @email c15071211749@163.com
 * @date 2020-05-20 23:21:55
 */
public interface SpuService extends IService<SpuEntity> {

    PageResultVo queryPage(PageParamVo paramVo);

    /**
     * 分页查询spu商品列表
     * @param pageParamVo
     * @param categoryId
     * @return
     */
    PageResultVo querySpuInfo(PageParamVo pageParamVo, Long categoryId);

    /**
     * 保存
     * @param spuVo
     */
    void bigSave(SpuVo spuVo);

}

