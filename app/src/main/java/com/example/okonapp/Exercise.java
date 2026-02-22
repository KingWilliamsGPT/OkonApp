package com.example.okonapp;

public class Exercise {
    private final String title;
    private final String description;
    private final Class<?> activityClass;

    public Exercise(String title, String description, Class<?> activityClass) {
        this.title = title;
        this.description = description;
        this.activityClass = activityClass;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Class<?> getActivityClass() {
        return activityClass;
    }
}
