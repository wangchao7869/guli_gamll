package com.atguigu.gmall.item.controller;

import com.atguigu.gmall.common.bean.ResponseVo;
import com.atguigu.gmall.item.service.ItemService;
import com.atguigu.gmall.item.vo.ItemVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author chao
 * @create 2020-06-04
 */
@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("itme/{skuId}")
    @ResponseBody
    public ResponseVo<ItemVo> item(@PathVariable("skuId")Long skuId) {
        ItemVo itemVo = this.itemService.queryItemBySkuId(skuId);
        return ResponseVo.ok(itemVo);
    }
}
