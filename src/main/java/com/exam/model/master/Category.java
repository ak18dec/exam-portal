package com.exam.model.master;

public class Category {

    //Genre -> Subject -> Category

    private int id;
    private String title;
    private String description;
    private int subjectId;
    private boolean enabled;

    public Category() {
    }

    public Category(int id, String title, String description, int subjectId, boolean enabled) {
        this.id = id;
        this.title = title;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", subjectId=" + subjectId +
                ", enabled=" + enabled +
                '}';
    }
}
