package com.github.lybgeek.provider.api;


import com.github.lybgeek.api.dto.User;
import com.github.lybgeek.api.feign.UserServiceFeign;
import com.github.lybgeek.common.model.AjaxResult;
import com.github.lybgeek.provider.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(UserServiceFeign.PATH)
@RequiredArgsConstructor
public class UserFeignServiceImpl implements UserServiceFeign{

    private final UserService userService;


    @Override
    public AjaxResult<User> getUserById(@PathVariable("id") Long id) {
        User user = userService.getUserById(id);
        return AjaxResult.success(user);
    }
}
