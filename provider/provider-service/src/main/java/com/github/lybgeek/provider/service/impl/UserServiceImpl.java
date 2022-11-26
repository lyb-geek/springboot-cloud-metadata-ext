package com.github.lybgeek.provider.service.impl;



import com.github.javafaker.Faker;
import com.github.lybgeek.api.dto.User;
import com.github.lybgeek.provider.service.UserService;
import com.github.lybgeek.common.exception.BizException;
import lombok.SneakyThrows;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;

@Service
public class UserServiceImpl implements UserService {

    private Map<Long,User> userMap = new ConcurrentHashMap();

    @Value("${spring.application.name}")
    private String applicationName;

    private LongAdder longAdder = new LongAdder();

    @Override
    public User getUserById(Long id) {
        if(!userMap.containsKey(id)){
            throw new BizException(404, String.format("不存在用户ID为【%s】的用户",id));
        }

        if(id == 2){
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return userMap.get(id);
    }

    @Override
    public User add(User user) {
        longAdder.increment();
        user.setId(longAdder.longValue());
        userMap.put(user.getId(),user);
        return userMap.get(user.getId());
    }


    @PostConstruct
    public void init(){
        for (int i = 0; i < 4; i++) {
            User user = buildUser();
            userMap.put(user.getId(),user);
        }
    }

    @SneakyThrows
    public User buildUser() {
        Faker faker = Faker.instance(Locale.CHINA);
        String fullName = faker.name().name();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        String username = PinyinHelper.toHanYuPinyinString(fullName, defaultFormat, "", false);
        String email = username + "@example.com";
        longAdder.increment();
        Long id = longAdder.longValue();

        return User.builder().email(email).id(id).username(username).fullName(fullName).build();

    }
}
