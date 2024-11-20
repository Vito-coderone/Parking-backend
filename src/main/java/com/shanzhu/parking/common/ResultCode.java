package com.shanzhu.parking.common;


/**
 * Interface return status code
 *
 * @author: Zi Cheng
 * @date: 2024-09-21
 */
public enum ResultCode {

    /**
     * SUCCESS
     */
    SUCCESS(true, "200", "success"),

    /**
     * FAIL
     */
    FAIL(false, "555", "fail"),

    /**
     * ERROR
     */
    ERROR(false, "666", "System abnormality");

    /**
     * STATE
     */
    private Boolean state;

    /**
     * STRING CODE
     */
    private String code;

    /**
     * STATE MESSAGE
     */
    private String message;

    ResultCode(Boolean state, String code, String message) {
        this.state = state;
        this.code = code;
        this.message = message;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
