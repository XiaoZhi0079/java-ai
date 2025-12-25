package com.example.javaai.utils;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor


/**
 * 统一响应结果封装类
 * 适配所有接口的 JSON 响应格式
 */
@Data
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 业务状态码（与接口文档一致） */
    private int status;
    /** 响应提示消息 */
    private String msg;
    /** 响应数据（可选） */
    private T data;

    /** 私有化构造器，禁止外部直接实例化 */
    private Result() {}

    /**
     * 成功响应（无数据）
     */
    public static <T> Result<T> success() {
        return success(ResultCode.SUCCESS.getMsg(), null);
    }

    /**
     * 成功响应（自定义消息，无数据）
     */
    public static <T> Result<T> success(String msg) {
        return success(msg, null);
    }

    /**
     * 成功响应（自定义消息 + 数据）
     */
    public static <T> Result<T> success(String msg, T data) {
        Result<T> result = new Result<>();
        result.setStatus(ResultCode.SUCCESS.getCode());
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    /**
     * 成功响应（枚举状态 + 数据）
     */
    public static <T> Result<T> success(ResultCode code, T data) {
        Result<T> result = new Result<>();
        result.setStatus(code.getCode());
        result.setMsg(code.getMsg());
        result.setData(data);
        return result;
    }

    /**
     * 失败响应（枚举状态）
     */
    public static <T> Result<T> fail(ResultCode code) {
        return fail(code.getCode(), code.getMsg());
    }

    /**
     * 失败响应（自定义状态码 + 消息）
     */
    public static <T> Result<T> fail(int code, String msg) {
        Result<T> result = new Result<>();
        result.setStatus(code);
        result.setMsg(msg);
        result.setData(null);
        return result;
    }

    /**
     * 失败响应（枚举状态 + 自定义消息）
     */
    public static <T> Result<T> fail(ResultCode code, String msg) {
        Result<T> result = new Result<>();
        result.setStatus(code.getCode());
        result.setMsg(msg);
        result.setData(null);
        return result;
    }

    // 链式调用方法（可选）
    public Result<T> status(int status) {
        this.setStatus(status);
        return this;
    }

    public Result<T> msg(String msg) {
        this.setMsg(msg);
        return this;
    }

    public Result<T> data(T data) {
        this.setData(data);
        return this;
    }
}