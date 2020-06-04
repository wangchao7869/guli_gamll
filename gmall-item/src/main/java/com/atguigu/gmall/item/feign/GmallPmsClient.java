package com.atguigu.gmall.item.feign;

import com.atguigu.gmall.pms.api.GmallPmsApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author chao
 * @create 2020-06-04
 */

@FeignClient("pms-service")
public interface GmallPmsClient extends GmallPmsApi {
}
