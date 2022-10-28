package com.dating.app.task.dating.model;

public enum UserSexualOrientation {

    HETEROSEXUAL ("heterosexual"),
    BISEXUAL ("bisexual"),
    HOMOSEXUAL ("homosexual"),
    OTHER ("other");

    private final String name;

    UserSexualOrientation(String name){
        this.name = name;
    }

}
