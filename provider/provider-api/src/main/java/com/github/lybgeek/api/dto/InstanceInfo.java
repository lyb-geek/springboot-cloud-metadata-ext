package com.github.lybgeek.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: Linyb
 * @date : 2022/11/23 14:55
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(description = "instanceInfo")
public class InstanceInfo {

    @ApiModelProperty(name = "name" ,value = "服务名")
    private String name;

    @ApiModelProperty(name = "port" ,value = "端口")
    private String port;
}
