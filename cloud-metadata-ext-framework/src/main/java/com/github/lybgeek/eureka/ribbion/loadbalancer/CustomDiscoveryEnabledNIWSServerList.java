package com.github.lybgeek.eureka.ribbion.loadbalancer;

import com.github.lybgeek.eureka.instance.constant.Constant;
import com.github.lybgeek.eureka.instance.properties.EurekaInstanceProperties;
import com.netflix.client.config.IClientConfig;
import com.netflix.discovery.EurekaClient;
import com.netflix.niws.loadbalancer.DiscoveryEnabledNIWSServerList;
import com.netflix.niws.loadbalancer.DiscoveryEnabledServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import javax.inject.Provider;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Slf4j
public class CustomDiscoveryEnabledNIWSServerList extends DiscoveryEnabledNIWSServerList {

    private final Provider<EurekaClient> eurekaClientProvider;
    private final EurekaInstanceProperties eurekaInstanceProperties;

    public CustomDiscoveryEnabledNIWSServerList(IClientConfig clientConfig, Provider<EurekaClient> eurekaClientProvider,EurekaInstanceProperties eurekaInstanceProperties) {
        this.eurekaClientProvider = eurekaClientProvider;
        this.eurekaInstanceProperties = eurekaInstanceProperties;
        initWithNiwsConfig(clientConfig);
    }

    @Override
    public List<DiscoveryEnabledServer> getInitialListOfServers(){
        List<DiscoveryEnabledServer> initialListOfServers = super.getInitialListOfServers();

        return selectListOfServersByMetaInfo(initialListOfServers);

    }

    @Override
    public List<DiscoveryEnabledServer> getUpdatedListOfServers(){
        List<DiscoveryEnabledServer> updatedListOfServers = super.getUpdatedListOfServers();
        return selectListOfServersByMetaInfo(updatedListOfServers);

    }

    private List<DiscoveryEnabledServer> selectListOfServersByMetaInfo(List<DiscoveryEnabledServer> discoveryEnabledServerList){
        List<DiscoveryEnabledServer> discoveryEnabledServersByMetaInfo = new ArrayList<>();
        if(!CollectionUtils.isEmpty(discoveryEnabledServerList)){
            for (DiscoveryEnabledServer discoveryEnabledServer : discoveryEnabledServerList) {
                Map<String, String> metadata = discoveryEnabledServer.getInstanceInfo().getMetadata();
                String namespace = metadata.get(Constant.META_INFO_KEY_NAMESPACE);
                String group = metadata.get(Constant.META_INFO_KEY_GROUP);
                if(eurekaInstanceProperties.getNamespace().equals(namespace) &&
                        eurekaInstanceProperties.getGroup().equals(group)){
                    discoveryEnabledServersByMetaInfo.add(discoveryEnabledServer);
                }

            }
        }

        if(CollectionUtils.isEmpty(discoveryEnabledServersByMetaInfo) &&
                eurekaInstanceProperties.isLoadBalanceAllowCross()){
            log.warn("not found enabledServerList in namespace : 【{}】 and group : 【{}】. will select default enabledServerList by isLoadBalanceAllowCross is {}",eurekaInstanceProperties.getNamespace(),eurekaInstanceProperties.getGroup(),eurekaInstanceProperties.isLoadBalanceAllowCross());
            return discoveryEnabledServerList;
        }

        return discoveryEnabledServersByMetaInfo;
    }


}
