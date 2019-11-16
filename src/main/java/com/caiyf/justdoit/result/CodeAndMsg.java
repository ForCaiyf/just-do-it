package com.caiyf.justdoit.result;

public enum CodeAndMsg {

    // 通用code
    SUCCESS(10000, "请求成功"),
    ERROR(10001, "系统错误");

    private Integer code;
    private String msg;

    CodeAndMsg(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return this.code;
    }
    public String getMsg() {
        return this.msg;
    }

}
