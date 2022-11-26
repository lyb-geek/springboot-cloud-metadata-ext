package com.github.lybgeek.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(description = "user")
public class User implements Serializable {

    @ApiModelProperty(name = "id" ,value = "编号")
    private Long id;

    @ApiModelProperty(name = "fullName" ,value = "用户全称",example = "张三")
    private String fullName;

    @ApiModelProperty(name = "username" ,value = "用户全称",example = "zhangsan")
    private String username;

    @ApiModelProperty(name = "email" ,value = "邮箱",example = "zhangsan@example.com")
    private String email;
}
