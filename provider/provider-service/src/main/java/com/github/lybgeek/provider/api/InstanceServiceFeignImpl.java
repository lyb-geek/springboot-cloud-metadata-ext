package com.github.lybgeek.provider.api;


import com.github.lybgeek.api.dto.InstanceInfo;
import com.github.lybgeek.api.feign.InstanceServiceFeign;
import com.github.lybgeek.eureka.instance.annotation.InstanceName;
import com.github.lybgeek.eureka.instance.annotation.InstancePort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(InstanceServiceFeign.PATH)
public class InstanceServiceFeignImpl implements InstanceServiceFeign {

    @InstancePort
    private String port;

    @InstanceName
    private String instanceName;


    @Override
    public InstanceInfo getInstanceInfo() {
        return InstanceInfo.builder()
                .name(instanceName).port(port).build();
    }
}
