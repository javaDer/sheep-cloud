package com.sheep4cloud.common.base.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sheep4cloud.common.base.constant.BaseConstant;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;

@Data
@Getter
public class ResultVo<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private int code;

    private String msg;

    private long time;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    private ResultVo() {
        this.time = System.currentTimeMillis();
    }

    private ResultVo(IResultCode resultCode) {
        this(resultCode, null, resultCode.getMsg());
    }

    private ResultVo(IResultCode resultCode, String msg) {
        this(resultCode, null, msg);
    }

    private ResultVo(IResultCode resultCode, T data) {
        this(resultCode, data, resultCode.getMsg());
    }

    private ResultVo(IResultCode resultCode, T data, String msg) {
        this(resultCode.getCode(), data, msg);
    }

    private ResultVo(int code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
        this.time = System.currentTimeMillis();
    }

    /**
     * 返回状态码
     *
     * @param resultCode 状态码
     * @param <T>        泛型标识
     * @return ApiResult
     */
    public static <T> ResultVo<T> success(IResultCode resultCode) {
        return new ResultVo<>(resultCode);
    }

    public static <T> ResultVo<T> success(String msg) {
        return new ResultVo<>(ResultCode.SUCCESS, msg);
    }

    public static <T> ResultVo<T> success(IResultCode resultCode, String msg) {
        return new ResultVo<>(resultCode, msg);
    }

    public static <T> ResultVo<T> data(T data) {
        return data(data, BaseConstant.DEFAULT_SUCCESS_MESSAGE);
    }

    public static <T> ResultVo<T> data(T data, String msg) {
        return data(ResultCode.SUCCESS.code, data, msg);
    }

    public static <T> ResultVo<T> data(int code, T data, String msg) {
        return new ResultVo<>(code, data, data == null ? BaseConstant.DEFAULT_NULL_MESSAGE : msg);
    }

    public static <T> ResultVo<T> fail() {
        return new ResultVo<>(ResultCode.FAILURE, ResultCode.FAILURE.getMsg());
    }

    public static <T> ResultVo<T> fail(String msg) {
        return new ResultVo<>(ResultCode.FAILURE, msg);
    }

    public static <T> ResultVo<T> fail(int code, String msg) {
        return new ResultVo<>(code, null, msg);
    }

    public static <T> ResultVo<T> fail(IResultCode resultCode) {
        return new ResultVo<>(resultCode);
    }

    public static <T> ResultVo<T> fail(IResultCode resultCode, String msg) {
        return new ResultVo<>(resultCode, msg);
    }

    public static <T> ResultVo<T> condition(boolean flag) {
        return flag ? success(BaseConstant.DEFAULT_SUCCESS_MESSAGE) : fail(BaseConstant.DEFAULT_FAIL_MESSAGE);
    }
}

