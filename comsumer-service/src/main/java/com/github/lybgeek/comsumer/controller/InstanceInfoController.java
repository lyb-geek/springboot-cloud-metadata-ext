package com.github.lybgeek.comsumer.controller;


import com.github.lybgeek.api.dto.InstanceInfo;
import com.github.lybgeek.api.feign.InstanceServiceFeign;
import com.github.lybgeek.eureka.instance.annotation.InstanceName;
import com.github.lybgeek.eureka.instance.annotation.InstancePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("instance")
public class InstanceInfoController {

    @InstancePort
    private String port;

    @InstanceName
    private String instanceName;

    @Autowired
    private InstanceServiceFeign instanceServiceFeign;

    @GetMapping("list")
    public List<InstanceInfo> list(){
        List<InstanceInfo> instanceInfos = new ArrayList<>();
        InstanceInfo comsumeInstanceInfo = InstanceInfo.builder()
                .port(port).name(instanceName).build();
        instanceInfos.add(comsumeInstanceInfo);
        InstanceInfo providerInstanceInfo = null;
        try {
            providerInstanceInfo = instanceServiceFeign.getInstanceInfo();
            instanceInfos.add(providerInstanceInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return instanceInfos;

    }
}
