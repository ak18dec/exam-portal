package com.exam.topic.model;

public class Topic {

    //Genre -> Subject -> Category -> Topic

    private int id;
    private String title;
    private String description;
    private int categoryId;
    private boolean enabled;

    public Topic() {
    }

    public Topic(int id, String title, String description, int categoryId, boolean enabled) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.categoryId = categoryId;
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

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", categoryId=" + categoryId +
                ", enabled=" + enabled +
                '}';
    }
}
