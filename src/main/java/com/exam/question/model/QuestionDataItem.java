package com.exam.question.model;

public class QuestionDataItem {

    private int id;
    private String description;
    private String proficiency;
    private boolean enabled;
    private int topicId;
    private QuestionChoice questionChoice;

    public QuestionDataItem() {
    }

    public QuestionDataItem(int id, String description, String proficiency, boolean enabled, int topicId, QuestionChoice questionChoice) {
        this.id = id;
        this.description = description;
        this.proficiency = proficiency;
        this.enabled = enabled;
        this.topicId = topicId;
        this.questionChoice = questionChoice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProficiency() {
        return proficiency;
    }

    public void setProficiency(String proficiency) {
        this.proficiency = proficiency;
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

    public QuestionChoice getQuestionChoice() {
        return questionChoice;
    }

    public void setQuestionChoice(QuestionChoice questionChoice) {
        this.questionChoice = questionChoice;
    }

    @Override
    public String toString() {
        return "QuestionDataItem{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", proficiency=" + proficiency +
                ", enabled=" + enabled +
                ", topicId=" + topicId +
                ", questionChoice=" + questionChoice +
                '}';
    }
}
