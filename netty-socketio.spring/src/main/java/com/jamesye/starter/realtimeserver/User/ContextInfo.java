package com.jamesye.starter.realtimeserver.User;

import java.io.Serializable;

public class ContextInfo implements Serializable {
    private String gender;
    private boolean isActive;

    public ContextInfo(String gender, boolean isActive) {
        this.gender = gender;
        this.isActive = isActive;
    }

    // Getters and Setters
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
