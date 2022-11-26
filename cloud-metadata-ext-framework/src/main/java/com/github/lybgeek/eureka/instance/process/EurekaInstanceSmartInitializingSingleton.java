package com.github.lybgeek.eureka.instance.process;

import com.github.lybgeek.eureka.instance.constant.Constant;
import com.github.lybgeek.eureka.instance.properties.EurekaInstanceProperties;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.cloud.netflix.eureka.EurekaInstanceConfigBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;


public class EurekaInstanceSmartInitializingSingleton implements SmartInitializingSingleton, ApplicationContextAware {


    private ApplicationContext applicationContext;

    @Override
    public void afterSingletonsInstantiated() {
        EurekaInstanceProperties eurekaInstanceProperties = applicationContext.getBean(EurekaInstanceProperties.class);
        EurekaInstanceConfigBean bean = applicationContext.getBean(EurekaInstanceConfigBean.class);
        Map<String, String> metadataMap = bean.getMetadataMap();
        metadataMap.put(Constant.META_INFO_KEY_NAMESPACE,eurekaInstanceProperties.getNamespace());
        metadataMap.put(Constant.META_INFO_KEY_GROUP,eurekaInstanceProperties.getGroup());

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
