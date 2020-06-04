package com.atguigu.gmall.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.atguigu.gmall.common.bean.PageResultVo;
import com.atguigu.gmall.common.bean.PageParamVo;
import com.atguigu.gmall.pms.entity.SpuAttrValueEntity;

import java.util.List;
import java.util.Map;

/**
 * spu属性值
 *
 * @author chao
 * @email c15071211749@163.com
 * @date 2020-05-20 23:21:55
 */
public interface SpuAttrValueService extends IService<SpuAttrValueEntity> {

    List<SpuAttrValueEntity> querySearchAttrValueBySpuId(Long spuId);

    PageResultVo queryPage(PageParamVo paramVo);
}

