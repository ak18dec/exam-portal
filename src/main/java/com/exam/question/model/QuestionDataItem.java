package com.exam.question.model;

public class QuestionDataItem {

    private int id;
    private String content;
    private int proficiencyId;
    private boolean enabled;
    private int topicId;
    private QuestionChoice questionChoice;

    public QuestionDataItem() {
    }

    public QuestionDataItem(int id, String content, int proficiencyId, boolean enabled, int topicId, QuestionChoice questionChoice) {
        this.id = id;
        this.content = content;
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
                ", content='" + content + '\'' +
                ", proficiencyId=" + proficiencyId +
                ", enabled=" + enabled +
                ", topicId=" + topicId +
                ", questionChoice=" + questionChoice +
                '}';
    }
}
