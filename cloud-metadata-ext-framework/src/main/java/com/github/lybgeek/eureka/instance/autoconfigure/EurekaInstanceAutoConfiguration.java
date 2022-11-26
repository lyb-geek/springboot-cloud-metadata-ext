package com.github.lybgeek.eureka.instance.autoconfigure;


import com.github.lybgeek.eureka.instance.process.EurekaInstanceSmartInitializingSingleton;
import com.github.lybgeek.eureka.instance.properties.EurekaInstanceProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EurekaClientAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableConfigurationProperties({EurekaInstanceProperties.class})
@Slf4j
@AutoConfigureBefore(EurekaClientAutoConfiguration.class)
public class EurekaInstanceAutoConfiguration {


    @Bean
    @ConditionalOnMissingBean
    public EurekaInstanceSmartInitializingSingleton eurekaInstanceSmartInitializingSingleton(){
        return new EurekaInstanceSmartInitializingSingleton();
    }

}
