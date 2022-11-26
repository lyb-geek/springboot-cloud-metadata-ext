package com.github.lybgeek.eureka.instance.annotation;

import org.springframework.beans.factory.annotation.Value;

import java.lang.annotation.*;


@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Value("${spring.application.name}")
public @interface InstanceName {
}
