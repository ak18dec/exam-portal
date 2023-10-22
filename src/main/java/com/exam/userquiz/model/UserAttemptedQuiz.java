package com.exam.userquiz.model;

import java.util.Date;

public class UserAttemptedQuiz {
    private int id;
    private int userId;
    private String userFullName;
    private String username;
    private Date attemptedOn;
    private String proficiency;
    private int maxMarks;
    private int maxTime;
    private int userTime;
    private double score;
    private AttemptedQuiz quiz;

    public UserAttemptedQuiz() {
    }

    public UserAttemptedQuiz(int id, int userId, String userFullName, String username, Date attemptedOn,
                             String proficiency, int maxMarks, int maxTime, int userTime, double score,
                             AttemptedQuiz quiz) {
        this.id = id;
        this.userId = userId;
        this.userFullName = userFullName;
        this.username = username;
        this.attemptedOn = attemptedOn;
        this.proficiency = proficiency;
        this.maxMarks = maxMarks;
        this.maxTime = maxTime;
        this.userTime = userTime;
        this.score = score;
        this.quiz = quiz;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getAttemptedOn() {
        return attemptedOn;
    }

    public void setAttemptedOn(Date attemptedOn) {
        this.attemptedOn = attemptedOn;
    }

    public String getProficiency() {
        return proficiency;
    }

    public void setProficiency(String proficiency) {
        this.proficiency = proficiency;
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

    public int getUserTime() {
        return userTime;
    }

    public void setUserTime(int userTime) {
        this.userTime = userTime;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public AttemptedQuiz getQuiz() {
        return quiz;
    }

    public void setQuiz(AttemptedQuiz quiz) {
        this.quiz = quiz;
    }

    @Override
    public String toString() {
        return "UserAttemptedQuiz{" +
                "id=" + id +
                ", userId=" + userId +
                ", userFullName='" + userFullName + '\'' +
                ", username='" + username + '\'' +
                ", attemptedOn=" + attemptedOn +
                ", proficiency=" + proficiency +
                ", maxMarks=" + maxMarks +
                ", maxTime=" + maxTime +
                ", userTime=" + userTime +
                ", score=" + score +
                ", quiz=" + quiz +
                '}';
    }
}
