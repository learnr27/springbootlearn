package com.bannad927.config;

import com.bannad927.bean.TerminateBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: chengbinbin
 * @date: 2020/12/10
 **/
@Configuration
public class ShutDownConfig {
    @Bean
    public TerminateBean getTerminateBean() {
        return new TerminateBean();
    }
}
