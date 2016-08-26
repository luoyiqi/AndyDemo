package com.andy.a.demo.domain;

/*
*
*
* 描述最外层json结构的实体类
* */
public class JsonResponse<T> {

    private T data;
    private String code;
    private String msg;
    private boolean success;

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    public boolean isSuccess() {
        return success;
    }


}
