package com.exam.quiz.model;

import java.util.List;

public class Quiz {

    private int id;
    private String title;
    private String description;
    private List<Integer> questionIds;
    private int maxMarks;
    private int maxTime;
    private boolean published;

    public Quiz() {

    }

    public Quiz(int id, String title, String description, List<Integer> questionIds, int maxMarks, int maxTime, boolean published) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.questionIds = questionIds;
        this.maxMarks = maxMarks;
        this.maxTime = maxTime;
        this.published = published;
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

    public List<Integer> getQuestionIds() {
        return questionIds;
    }

    public void setQuestionIds(List<Integer> questionIds) {
        this.questionIds = questionIds;
    }

    public int getMaxMarks() {
        return maxMarks;
    }

    public void setMaxMarks(int maxMarks) {
        this.maxMarks = maxMarks;
    }

    public int getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(int maxTime) {
        this.maxTime = maxTime;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", questionIds=" + questionIds.toString() +
                ", maxMarks=" + maxMarks +
                ", maxTime=" + maxTime +
                ", published=" + published +
                '}';
    }
}
