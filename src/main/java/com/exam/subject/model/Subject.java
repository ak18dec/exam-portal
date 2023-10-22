package com.exam.subject.model;

public class Subject {

    private int id;
    private String title;
    private boolean enabled;

    public Subject() {
    }

    public Subject(int id, String title, boolean enabled) {
        this.id = id;
        this.title = title;
        this.enabled = enabled;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}
