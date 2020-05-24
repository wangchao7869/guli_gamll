package com.atguigu.gmall.sms.api;

import com.atguigu.gmall.common.bean.ResponseVo;
import com.atguigu.gmall.sms.vo.SkuSaleVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author chao
 * @create 2020-05-24
 */

public interface GmallSmsApi {
    @PostMapping("/sms/skubounds/skusale/save")
    public ResponseVo<Object> saveSkuSaleInfo(@RequestBody SkuSaleVo skuSaleVo);
}
