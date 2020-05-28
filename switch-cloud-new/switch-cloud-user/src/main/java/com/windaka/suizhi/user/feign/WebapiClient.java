package com.windaka.suizhi.user.feign;

import com.windaka.suizhi.api.user.LoginAppUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


/**
 * @author: hjt
 * @Date: 2019/1/16 16:36
 * @Version 1.0
 */
@FeignClient(name = "sjwl-webapi")
public interface WebapiClient {
    @PostMapping("/webapi-internal/preQueryCellByBuilding")
    public void preQueryCellByBuilding(@RequestBody LoginAppUser loginAppUser);
}
