package com.zhong.anno;


import com.zhong.validation.StateValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented // 元注解，用于描述注解的元信息
@Constraint(validatedBy = {StateValidation.class}) // 指定注解使用的校验的规则的类
@Target({ElementType.FIELD}) // 注解作用目标
@Retention(RetentionPolicy.RUNTIME) // 注解起作用的时间
public @interface State {

    // 违反校验规则以后提供的默认错误信息
    String message() default "文章状态错误";

    // 默认分组，用于限定校验范围
    Class<?>[] groups() default {};

    // 默认负载，用于限定校验范围
    Class<? extends Payload>[] payload() default {};

}
