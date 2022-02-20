package com.exam.userquiz.model;

import java.util.List;

public class AttemptedQuiz {
    private int id;
    private String title;
    private String description;
    private List<AttemptedQuizQuestion> questions;
    private List<String> instructions;

    public AttemptedQuiz() {
    }

    public AttemptedQuiz(int id, String title, String description, List<AttemptedQuizQuestion> questions,
                         List<String> instructions) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.questions = questions;
        this.instructions = instructions;
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

    public List<AttemptedQuizQuestion> getQuestions() {
        return questions;
    }

    public void setQuestions(List<AttemptedQuizQuestion> questions) {
        this.questions = questions;
    }

    public List<String> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<String> instructions) {
        this.instructions = instructions;
    }

    @Override
    public String toString() {
        return "AttemptedQuiz{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", questions=" + questions +
                ", instructions=" + instructions +
                '}';
    }
}
