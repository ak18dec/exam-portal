package com.exam.question.model;

public class Question {

    //Genre -> Subject -> Category -> Topic -> Question

    private int id;
    private String title;
    private String content;
    private int proficiencyId;
    private boolean enabled;
    private int topicId;

    public Question() {
    }

    public Question(int id, String title, String content, int proficiencyId, boolean enabled, int topicId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.proficiencyId = proficiencyId;
        this.enabled = enabled;
        this.topicId = topicId;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getProficiencyId() {
        return proficiencyId;
    }

    public void setProficiencyId(int proficiencyId) {
        this.proficiencyId = proficiencyId;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", proficiencyId=" + proficiencyId +
                ", enabled=" + enabled +
                ", topicId=" + topicId +
                '}';
    }
}
