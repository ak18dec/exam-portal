package com.exam.question.model;

import java.util.List;

public class QuestionDataItem {

    private int id;
    private String title;
    private String description;
    private int proficiencyId;
    private boolean enabled;
    private int topicId;
    private QuestionChoice questionChoice;

    public QuestionDataItem() {
    }

    public QuestionDataItem(int id, String title, String description, int proficiencyId, boolean enabled, int topicId, QuestionChoice questionChoice) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.proficiencyId = proficiencyId;
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
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", proficiencyId=" + proficiencyId +
                ", enabled=" + enabled +
                ", topicId=" + topicId +
                ", questionChoice=" + questionChoice +
                '}';
    }
}
