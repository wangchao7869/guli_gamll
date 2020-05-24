package com.atguigu.gmall.pms.feign;

import com.atguigu.gmall.sms.api.GmallSmsApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author chao
 * @create 2020-05-24
 */
@FeignClient("sms-service")
public interface GmallSmsClient extends GmallSmsApi {
}
