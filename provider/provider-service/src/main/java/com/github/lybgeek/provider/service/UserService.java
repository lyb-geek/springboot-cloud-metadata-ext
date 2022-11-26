package com.github.lybgeek.provider.service;


import com.github.lybgeek.api.dto.User;

public interface UserService {

    User getUserById(Long id);

    User add(User user);

}
