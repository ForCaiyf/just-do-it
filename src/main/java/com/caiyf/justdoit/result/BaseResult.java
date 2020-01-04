package com.caiyf.justdoit.result;

import lombok.Data;

/**
 * 通用返回结果
 *
 * @author caiyf
 * @date 2019-11-16
 */
@Data
public class BaseResult<T> {

    private Integer code;
    private String msg;
    private T data;

    public static <T>  BaseResult success(T data) {
        BaseResult result = new BaseResult();
        result.setCode(CodeAndMsg.SUCCESS.getCode());
        result.setMsg(CodeAndMsg.SUCCESS.getMsg());
        result.setData(data);
        return result;
    }

    public static BaseResult error(CodeAndMsg codeAndMsg) {
        BaseResult result = new BaseResult();
        result.setCode(codeAndMsg.getCode());
        result.setMsg(codeAndMsg.getMsg());
        return result;
    }

    public static BaseResult error(Integer code, String msg) {
        BaseResult result = new BaseResult();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

}
