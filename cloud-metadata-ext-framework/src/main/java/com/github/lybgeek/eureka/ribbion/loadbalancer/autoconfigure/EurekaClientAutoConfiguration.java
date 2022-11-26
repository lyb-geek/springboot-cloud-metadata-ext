package com.github.lybgeek.eureka.ribbion.loadbalancer.autoconfigure;

import com.github.lybgeek.eureka.instance.properties.EurekaInstanceProperties;
import com.github.lybgeek.eureka.ribbion.loadbalancer.CustomDiscoveryEnabledNIWSServerList;
import com.netflix.client.config.IClientConfig;
import com.netflix.discovery.EurekaClient;
import com.netflix.loadbalancer.ServerList;
import com.netflix.niws.loadbalancer.DiscoveryEnabledNIWSServerList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.ribbon.PropertiesFactory;
import org.springframework.cloud.netflix.ribbon.RibbonClientName;
import org.springframework.cloud.netflix.ribbon.eureka.DomainExtractingServerList;
import org.springframework.cloud.netflix.ribbon.eureka.EurekaRibbonClientConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.inject.Provider;

/**
 * @description:
 * @author: Linyb
 * @date : 2022/11/22 18:50
 **/
@Configuration
public class EurekaClientAutoConfiguration extends EurekaRibbonClientConfiguration{

    @Value("${ribbon.eureka.approximateZoneFromHostname:false}")
    private boolean approximateZoneFromHostname = false;

    @RibbonClientName
    private String serviceId = "client";;


    @Autowired
    private PropertiesFactory propertiesFactory;



    @Bean
    @Primary
    public ServerList<?> ribbonServerList(IClientConfig config,
                                    Provider<EurekaClient> eurekaClientProvider, EurekaInstanceProperties properties) {
        if (this.propertiesFactory.isSet(ServerList.class, serviceId)) {
            return this.propertiesFactory.get(ServerList.class, config, serviceId);
        }
        DiscoveryEnabledNIWSServerList discoveryServerList = new CustomDiscoveryEnabledNIWSServerList(
                config, eurekaClientProvider,properties);
        DomainExtractingServerList serverList = new DomainExtractingServerList(
                discoveryServerList, config, this.approximateZoneFromHostname);
        return serverList;
    }
}
