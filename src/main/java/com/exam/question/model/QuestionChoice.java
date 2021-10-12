package com.exam.question.model;

public class QuestionChoice {

    private int id;
    private String description;
    private boolean enabled;
    private boolean correct;
    private int questionId;

    public QuestionChoice() {
    }

    public QuestionChoice(int id, String description, boolean enabled, boolean correct, int questionId) {
        this.id = id;
        this.description = description;
        this.enabled = enabled;
        this.correct = correct;
        this.questionId = questionId;
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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    @Override
    public String toString() {
        return "QuestionChoice{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", enabled=" + enabled +
                ", correct=" + correct +
                ", questionId=" + questionId +
                '}';
    }
}
