package com.exam.userquiz.model;

public class AttemptedQuizQuestion {
    private int id;
    private String question;
    private String optionSelected;

    public AttemptedQuizQuestion() {
    }

    public AttemptedQuizQuestion(int id, String question, String optionSelected) {
        this.id = id;
        this.question = question;
        this.optionSelected = optionSelected;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOptionSelected() {
        return optionSelected;
    }

    public void setOptionSelected(String optionSelected) {
        this.optionSelected = optionSelected;
    }

    @Override
    public String toString() {
        return "AttemptedQuizQuestion{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", optionSelected='" + optionSelected + '\'' +
                '}';
    }
}
