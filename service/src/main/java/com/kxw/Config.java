package com.kxw;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public Person Person() throws Exception {
        return createConfig(Person.class);
    }

    public <T> T createConfig(Class<T> configClass) throws Exception {
        T config = configClass.newInstance();
       // config.init(business());
        return config;
    }
}
