package com.atguigu.gmall.search.feign;

import com.atguigu.gmall.wms.api.GmallWmsApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author chao
 * @create 2020-05-27
 */
@FeignClient("wms-service")
public interface GmallWmsClient extends GmallWmsApi {
}
