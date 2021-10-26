package com.allezan.easypayapi;

@SuppressWarnings("unused")
public class PayResponse {
    private final boolean success;
    private final Object data;
    private final String errormsg;

    public PayResponse(boolean success, Object data, String errormsg) {
        this.success = success;
        this.data = data;
        this.errormsg = errormsg;
    }

    public boolean isSuccess() {
        return success;
    }

    public Object getData() {
        return data;
    }

    public String getErrormsg() {
        return errormsg;
    }
}
