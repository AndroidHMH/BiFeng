package com.coinwind.bifeng.ui.homepage.bean;

/**
 * 首页操作按钮的传值bean
 */
public class MessageEvent {
    private String type;

    public MessageEvent(String type) {
        this.type = type;
    }

    public MessageEvent() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
