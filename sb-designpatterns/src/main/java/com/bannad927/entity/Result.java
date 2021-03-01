package com.bannad927.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

/**
 * @author cbb
 * @date 2021.3.1
 */
@Data
public class Result<T> implements Serializable {
    private static final long serialVersionUID = -1L;

    /**
     * 状态，0为失败，1为成功，其他自定义
     */
    private Integer code;

    /**
     * 状态说明信息
     */
    private String message;

    /**
     * 内容
     */
    private T data;

    public static <T> Result getSuccessResult(T content) {
        Result<T> result = new Result<>();
        result.setCode(1);
        result.setMessage("success");
        result.setData(content);
        return result;
    }

    @JsonIgnore
    public static Result getFailResult(String message) {
        Result result = new Result();
        result.setCode(0);
        result.setMessage(message);
        return result;
    }
}
