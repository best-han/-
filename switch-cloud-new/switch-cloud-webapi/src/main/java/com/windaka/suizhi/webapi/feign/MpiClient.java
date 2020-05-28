package com.windaka.suizhi.webapi.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "sjwl-mpi")
public interface MpiClient {

    @GetMapping("/mpi-anon/internal")
    Map<String,Object> sendWebsocketMessage(@RequestParam("msg") String msg);

}
