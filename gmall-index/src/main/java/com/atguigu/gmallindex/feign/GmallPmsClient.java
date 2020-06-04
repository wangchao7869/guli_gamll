package com.atguigu.gmallindex.feign;

import com.atguigu.gmall.pms.api.GmallPmsApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author chao
 * @create 2020-06-01
 */
@FeignClient("pms-service")
public interface GmallPmsClient extends GmallPmsApi {
}
