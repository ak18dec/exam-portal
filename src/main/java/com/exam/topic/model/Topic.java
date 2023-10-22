package com.exam.topic.model;

public class Topic {

    private int id;
    private String title;
    private int subjectId;
    private boolean enabled;

    public Topic() {
    }

    public Topic(int id, String title, int subjectId, boolean enabled) {
        this.id = id;
        this.title = title;
        this.subjectId = subjectId;
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

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", subjectId=" + subjectId +
                ", enabled=" + enabled +
                '}';
    }
}
