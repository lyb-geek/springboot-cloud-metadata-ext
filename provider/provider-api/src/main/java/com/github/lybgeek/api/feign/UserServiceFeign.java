package com.github.lybgeek.api.feign;



import com.github.lybgeek.api.dto.User;
import com.github.lybgeek.common.model.AjaxResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "provider",path = UserServiceFeign.PATH,contextId = "user")
public interface UserServiceFeign {
     String PATH = "user";

     @GetMapping("{id}")
     AjaxResult<User> getUserById(@PathVariable("id") Long id);


}
