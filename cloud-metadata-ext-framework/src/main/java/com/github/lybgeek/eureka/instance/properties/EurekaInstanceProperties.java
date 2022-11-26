package com.github.lybgeek.eureka.instance.properties;

import com.github.lybgeek.eureka.instance.constant.Constant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "eureka.instance.ext")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EurekaInstanceProperties {

    private String namespace = Constant.META_INFO_DEAFULT_NAMESPACE;

    private String group = Constant.META_INFO_DEAFULT_GROUP;

    private boolean loadBalanceAllowCross;


}
