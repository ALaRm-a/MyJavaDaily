package com.zhong.validation;

import com.zhong.anno.State;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

// 泛型是作用于的注解+要检验的值的类型
public class StateValidation implements ConstraintValidator<State,String> {

    /**
     * 验证逻辑
     * @param value 待验证的值
     * @param constraintValidatorContext
     * @return
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {

        if(value== null){
            return false;
        }
        if(value.equals("已发布")||value.equals("草稿")){
            return true;
        }
        return false;
    }
}
