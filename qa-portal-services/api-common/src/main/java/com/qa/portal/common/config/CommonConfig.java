package com.qa.portal.common.config;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.Arrays;

import static com.qa.portal.common.CommonConstants.DOZER_CONFIG_FILE;

@Configuration
@EnableAsync
public class CommonConfig {


    @Autowired
    private Environment environment;

    @Bean
    public DozerBeanMapper dozerBeanMapper() {
        DozerBeanMapper mapper = new DozerBeanMapper();
        mapper.setMappingFiles(Arrays.asList(DOZER_CONFIG_FILE));
        return mapper;
    }
}
