package com.zhong.controller;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Result<T> {

    private int code;
    private String msg;
    private T data;


    public static <T> Result <T> success(T Data){
        return new Result<>(0,"操作成功",Data);
    }


    public static Result error(){
        return new Result<>(1,"操作失败",null);
    }

    // 快速响应结果
    public static  Result  success(){
        return new Result<>(0,"操作成功",null);
    }

    public static Result error(String msg){
        return new Result<>(1,msg,null);
    }

}
