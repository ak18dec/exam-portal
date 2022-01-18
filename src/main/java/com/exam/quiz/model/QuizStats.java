package com.exam.quiz.model;

public class QuizStats {

    private int id;
    private int quizId;
    private int attemptedCount;
    private int usersAttempted;

    public QuizStats() {
    }

    public QuizStats(int id, int quizId, int attemptedCount, int usersAttempted) {
        this.id = id;
        this.quizId = quizId;
        this.attemptedCount = attemptedCount;
        this.usersAttempted = usersAttempted;
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

    public int getAttemptedCount() {
        return attemptedCount;
    }

    public void setAttemptedCount(int attemptedCount) {
        this.attemptedCount = attemptedCount;
    }

    public int getUsersAttempted() {
        return usersAttempted;
    }

    public void setUsersAttempted(int usersAttempted) {
        this.usersAttempted = usersAttempted;
    }

    @Override
    public String toString() {
        return "QuizStats{" +
                "id=" + id +
                ", quizId=" + quizId +
                ", attemptedCount=" + attemptedCount +
                ", usersAttempted=" + usersAttempted +
                '}';
    }
}
