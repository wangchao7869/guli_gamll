package com.atguigu.gmall.item.feign;

import com.atguigu.gmall.sms.api.GmallSmsApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author chao
 * @create 2020-06-04
 */

@FeignClient("sms-service")
public interface GmallSmsClient extends GmallSmsApi {
}
