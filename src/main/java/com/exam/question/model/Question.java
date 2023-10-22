package com.exam.question.model;

import java.util.List;

public class Question {

    private int id;
    private String description;
    private String proficiency;
    private boolean enabled;
    private int topicId;
    private List<QuestionChoice> questionChoices;

    public Question() {
    }

    public Question(int id, String description, String proficiency, boolean enabled, int topicId, List<QuestionChoice> questionChoices) {
        this.id = id;
        this.description = description;
        this.proficiency = proficiency;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
                ", content='" + description + '\'' +
                ", proficiency=" + proficiency +
                ", enabled=" + enabled +
                ", topicId=" + topicId +
                ", questionChoices=" + questionChoices +
                '}';
    }

    public String getCorrectChoice() {
        String correctChoice = "";
        for (QuestionChoice choice : questionChoices) {
            if(choice.isCorrect()){
                correctChoice = choice.getDescription();
                break;
            }
        }
        return correctChoice;
    }
}
