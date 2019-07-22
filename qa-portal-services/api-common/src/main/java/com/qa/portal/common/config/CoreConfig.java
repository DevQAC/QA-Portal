package com.qa.portal.common.config;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

import static com.qa.portal.common.CommonConstants.DOZER_CONFIG_FILE;

@Configuration
public class CoreConfig {

    @Bean
    public DozerBeanMapper dozerBeanMapper() {
        DozerBeanMapper mapper = new DozerBeanMapper();
        mapper.setMappingFiles(Arrays.asList(DOZER_CONFIG_FILE));
        return mapper;
    }
}
