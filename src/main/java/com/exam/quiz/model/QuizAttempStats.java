package com.exam.quiz.model;

import java.util.Date;

public class QuizAttempStats {
    private int id;
    private int quizId;
    private Date attemptedOn;
    private int attemptedUserId;
    private int totalUserAttemptedCount;

    public QuizAttempStats() {
    }

    public QuizAttempStats(int id, int quizId, Date attemptedOn, int attemptedUserId, int totalUserAttemptedCount) {
        this.id = id;
        this.quizId = quizId;
        this.attemptedOn = attemptedOn;
        this.attemptedUserId = attemptedUserId;
        this.totalUserAttemptedCount = totalUserAttemptedCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public Date getAttemptedOn() {
        return attemptedOn;
    }

    public void setAttemptedOn(Date attemptedOn) {
        this.attemptedOn = attemptedOn;
    }

    public int getAttemptedUserId() {
        return attemptedUserId;
    }

    public void setAttemptedUserId(int attemptedUserId) {
        this.attemptedUserId = attemptedUserId;
    }

    public int getTotalUserAttemptedCount() {
        return totalUserAttemptedCount;
    }

    public void setTotalUserAttemptedCount(int totalUserAttemptedCount) {
        this.totalUserAttemptedCount = totalUserAttemptedCount;
    }

    @Override
    public String toString() {
        return "QuizAttempStats{" +
                "id=" + id +
                ", quizId=" + quizId +
                ", attemptedOn=" + attemptedOn +
                ", attemptedUserId=" + attemptedUserId +
                ", totalUserAttemptedCount=" + totalUserAttemptedCount +
                '}';
    }
}
