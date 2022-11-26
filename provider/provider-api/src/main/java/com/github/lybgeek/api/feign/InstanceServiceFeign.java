package com.github.lybgeek.api.feign;

import com.github.lybgeek.api.dto.InstanceInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;


@FeignClient(name = "${feign.instance.svc:provider}",path = InstanceServiceFeign.PATH,contextId = "instance")
public interface InstanceServiceFeign {

    String PATH = "instance";

    @GetMapping("info")
    InstanceInfo getInstanceInfo();
}
