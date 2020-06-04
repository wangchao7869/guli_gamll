package com.atguigu.gmall.item.feign;

import com.atguigu.gmall.wms.api.GmallWmsApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author chao
 * @create 2020-06-04
 */

@FeignClient("wms-service")
public interface GmallWmsClient extends GmallWmsApi {
}
