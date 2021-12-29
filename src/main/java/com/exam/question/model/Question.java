package com.exam.question.model;

import java.util.List;

public class Question {

    //Genre -> Subject -> Category -> Topic -> Question

    private int id;
    private String content;
    private int proficiencyId;
    private boolean enabled;
    private int topicId;
    private List<QuestionChoice> questionChoices;

    public Question() {
    }

    public Question(int id, String content, int proficiencyId, boolean enabled, int topicId, List<QuestionChoice> questionChoices) {
        this.id = id;
        this.content = content;
        this.proficiencyId = proficiencyId;
        this.enabled = enabled;
        this.topicId = topicId;
        this.questionChoices = questionChoices;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<QuestionChoice> getQuestionChoices() {
        return questionChoices;
    }

    public void setQuestionChoices(List<QuestionChoice> questionChoices) {
        this.questionChoices = questionChoices;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", proficiencyId=" + proficiencyId +
                ", enabled=" + enabled +
                ", topicId=" + topicId +
                ", questionChoices=" + questionChoices +
                '}';
    }
}
