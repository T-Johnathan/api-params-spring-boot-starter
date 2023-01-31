package com.ittc.project.common;

/**
 * 错误码
 *
 * @author tong
 */
public enum ErrorCode {

    SUCCESS(20000, "操作成功"),

    OPERATION_ERROR(50001, "操作失败"),

    PARAMS_ERROR(40000, "请求参数错误"),

    INTERFACE_NOT_FOUND(60000, "当前接口不存在，请联系管理员"),

    INTERFACE_OFFLINE(60200, "接口已下线，请联系管理员"),

    NO_LEFT_COUNT(60500,"当前接口调用次数已用完，请重新申请"),

    NOT_LOGIN_ERROR(40100, "当前用户不存在，或已过期，请重新登录"),

    NO_AUTH_ERROR(40101, "当前操作无权限，请联系管理员"),

    NOT_FOUND_ERROR(40400, "当前请求数据不存在，请联系管理员"),

    FORBIDDEN_ERROR(40300, "当前资源禁止访问与操作"),

    FREQUENT_OPERATE(42900, "当前操作频繁，请稍后重试"),

    SYSTEM_ERROR(50000, "系统内部异常");


    /**
     * 状态码
     */
    private final int code;

    /**
     * 信息
     */
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
