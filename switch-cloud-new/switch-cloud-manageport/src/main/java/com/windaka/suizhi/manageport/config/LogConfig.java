package com.windaka.suizhi.manageport.config;

import com.windaka.suizhi.api.log.LoginInfo;
import com.windaka.suizhi.common.LogImpl.LoginInfoImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: hjt
 * @Date: 2018/12/21 01:59
 * @Version 1.0
 */
@Configuration
public class LogConfig {

    @Bean
    public LoginInfo loginInfo(){
        return new LoginInfoImpl();
    }

}
