package com.ashpex.fragment;

public class Student {
    private String name;
    private String ID;
    private String avatar;

    public Student(String name, String ID, String avatar) {
        this.name = name;
        this.ID = ID;
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return this.name + " (ID: " + this.ID + ")";
    }
}

