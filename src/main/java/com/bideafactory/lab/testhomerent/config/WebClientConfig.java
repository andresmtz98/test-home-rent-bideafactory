package com.bideafactory.lab.testhomerent.config;

import com.bideafactory.lab.testhomerent.util.Constant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration public class WebClientConfig {

    @Bean public WebClient webClient() {
        return WebClient.builder().baseUrl(Constant.API_DISCOUNT_BASE_URL)
            .build();
    }
}
