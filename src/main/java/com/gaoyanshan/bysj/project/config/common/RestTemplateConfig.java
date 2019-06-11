package com.gaoyanshan.bysj.project.config.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * <pre>类名: RestTemplateConfig</pre>
 * <pre>描述: RestTemplate配置类</pre>
 * <pre>日期: 19-4-4 下午10:32</pre>
 * <pre>作者: gaoyanshan</pre>
 */


@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate(simpleClientHttpRequestFactory());
    }


    @Bean
    public ClientHttpRequestFactory simpleClientHttpRequestFactory(){
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setReadTimeout(10000);
        factory.setConnectTimeout(10000);
        return factory;
    }
}
