package com.exam.userquiz.model;

public class ScoreDetails {
    private double score = 0.0;
    private int correctQuestions = 0;
    private int incorrectQuestions = 0;
    private int totalQuestions = 0;
    private int totalAttemptedQuestions = 0;

    public ScoreDetails() {
    }

    public ScoreDetails(double score, int correctQuestions, int incorrectQuestions, int totalQuestions, int totalAttemptedQuestions) {
        this.score = score;
        this.correctQuestions = correctQuestions;
        this.incorrectQuestions = incorrectQuestions;
        this.totalQuestions = totalQuestions;
        this.totalAttemptedQuestions = totalAttemptedQuestions;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getCorrectQuestions() {
        return correctQuestions;
    }

    public void setCorrectQuestions(int correctQuestions) {
        this.correctQuestions = correctQuestions;
    }

    public int getIncorrectQuestions() {
        return incorrectQuestions;
    }

    public void setIncorrectQuestions(int incorrectQuestions) {
        this.incorrectQuestions = incorrectQuestions;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public int getTotalAttemptedQuestions() {
        return totalAttemptedQuestions;
    }

    public void setTotalAttemptedQuestions(int totalAttemptedQuestions) {
        this.totalAttemptedQuestions = totalAttemptedQuestions;
    }

    @Override
    public String toString() {
        return "ScoreDetails{" +
                "score=" + score +
                ", correctQuestions=" + correctQuestions +
                ", incorrectQuestions=" + incorrectQuestions +
                ", totalQuestions=" + totalQuestions +
                ", totalAttemptedQuestions=" + totalAttemptedQuestions +
                '}';
    }
}
