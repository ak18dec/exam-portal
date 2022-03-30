package com.exam.proficiency.model;

public enum Level {
    EASY("Easy"),
    MEDIUM("Medium"),
    HARD("Hard");

    public final String label;

    private Level(String label) {
        this.label = label;
    }
}
