package com.jamesye.starter.realtimeserver.User;

// User.java
public class User {
    private Integer id;
    private String name;
    private String gender;
    private boolean isActive;

    // Constructors, Getters, and Setters
    public User(Integer id, String name, String gender, boolean isActive) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.isActive = isActive;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

