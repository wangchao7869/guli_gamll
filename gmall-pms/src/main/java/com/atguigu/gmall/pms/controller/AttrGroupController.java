package com.atguigu.gmall.pms.controller;

import java.util.List;

import com.atguigu.gmall.pms.vo.GroupVo;
import com.atguigu.gmall.pms.vo.ItemGroupVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atguigu.gmall.pms.entity.AttrGroupEntity;
import com.atguigu.gmall.pms.service.AttrGroupService;
import com.atguigu.gmall.common.bean.PageResultVo;
import com.atguigu.gmall.common.bean.ResponseVo;
import com.atguigu.gmall.common.bean.PageParamVo;

/**
 * 属性分组
 *
 * @author chao
 * @email c15071211749@163.com
 * @date 2020-05-20 23:21:55
 */
@Api(tags = "属性分组 管理")
@RestController
@RequestMapping("pms/attrgroup")
public class AttrGroupController {

    @Autowired
    private AttrGroupService attrGroupService;

    @GetMapping("item/group")
    public ResponseVo<List<ItemGroupVo>> queryItemGroupVoByCidAndSpuIdAndSkuId(@RequestParam("cid")Long cid, @RequestParam("spuId")Long spuId, @RequestParam("skuId")Long skuId) {
        List<ItemGroupVo> itemGroupVos = this.attrGroupService.queryItemGroupVoByCidAndSpuIdAndSkuId(cid,spuId,skuId);
        return ResponseVo.ok(itemGroupVos);
    }

    @ApiOperation("根据三级分类id查询分组及组下的规格参数")
    @GetMapping("withattrs/{cid}")
    public ResponseVo<List<GroupVo>> queryByCid(@PathVariable("cid") Long cid) {
        List<GroupVo> groupVos = attrGroupService.queryByCatId(cid);
        return ResponseVo.ok(groupVos);
    }

    @ApiOperation("三级分类的分组")
    @GetMapping("category/{cid}")
    public ResponseVo<List<AttrGroupEntity>> queryByCidPage(@PathVariable("cid") Long cid) {
        List<AttrGroupEntity> list = attrGroupService
                .list(new QueryWrapper<AttrGroupEntity>()
                        .eq("category_id", cid));
        return ResponseVo.ok(list);
    }

    /**
     * 列表
     */
    @GetMapping
    @ApiOperation("分页查询")
    public ResponseVo<PageResultVo> queryAttrGroupByPage(PageParamVo paramVo) {
        PageResultVo pageResultVo = attrGroupService.queryPage(paramVo);

        return ResponseVo.ok(pageResultVo);
    }


    /**
     * 信息
     */
    @GetMapping("{id}")
    @ApiOperation("详情查询")
    public ResponseVo<AttrGroupEntity> queryAttrGroupById(@PathVariable("id") Long id) {
        AttrGroupEntity attrGroup = attrGroupService.getById(id);

        return ResponseVo.ok(attrGroup);
    }

    /**
     * 保存
     */
    @PostMapping
    @ApiOperation("保存")
    public ResponseVo<Object> save(@RequestBody AttrGroupEntity attrGroup) {
        attrGroupService.save(attrGroup);

        return ResponseVo.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation("修改")
    public ResponseVo update(@RequestBody AttrGroupEntity attrGroup) {
        attrGroupService.updateById(attrGroup);

        return ResponseVo.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation("删除")
    public ResponseVo delete(@RequestBody List<Long> ids) {
        attrGroupService.removeByIds(ids);

        return ResponseVo.ok();
    }

}
