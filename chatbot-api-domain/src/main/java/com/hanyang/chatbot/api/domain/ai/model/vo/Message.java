package com.hanyang.chatbot.api.domain.ai.model.vo;

/**
 * 内容
 */
public class Message {
    private String role;
    private String content;

    // Getters and setters
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
