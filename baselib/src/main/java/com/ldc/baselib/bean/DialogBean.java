package com.ldc.baselib.bean;

public class DialogBean {
    private String message;
    private boolean isShow;

    public DialogBean(String message, boolean isShow) {
        this.message = message;
        this.isShow = isShow;
    }

    public DialogBean() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }
}
