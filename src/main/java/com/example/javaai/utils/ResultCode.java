package com.example.javaai.utils;

import lombok.Getter;

/**
 * 统一响应状态码枚举
 */
@Getter
public enum ResultCode {
    /** 操作成功 */
    SUCCESS(200, "操作成功"),
    /** 参数错误 */
    PARAM_ERROR(400, "参数错误"),
    /** 未授权 */
    UNAUTHORIZED(401, "未授权"),
    /** 权限不足 */
    FORBIDDEN(403, "权限不足"),
    /** 服务器内部错误 */
    SERVER_ERROR(500, "服务器内部错误"),
    /** 服务不可用 */
    SERVICE_UNAVAILABLE(503, "服务不可用"),
    /** 文件上传错误 */
    FILE_UPLOAD_ERROR(40001, "文件上传失败"),
    /** 文件格式不支持 */
    FILE_FORMAT_ERROR(40002, "文件格式不支持"),
    /** 消息发送失败 */
    MESSAGE_SEND_ERROR(40003, "消息发送失败");

    private final int code;
    private final String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}