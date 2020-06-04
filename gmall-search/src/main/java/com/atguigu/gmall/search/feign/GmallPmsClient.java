package com.atguigu.gmall.search.feign;

import com.atguigu.gmall.pms.api.GmallPmsApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author chao
 * @create 2020-05-27
 */

@FeignClient("pms-service")
public interface GmallPmsClient extends GmallPmsApi {
}
