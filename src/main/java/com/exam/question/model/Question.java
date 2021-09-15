package com.exam.question.model;

public class Question {

    //Genre -> Subject -> Category -> Topic -> Question

    private int id;
    private String title;
    private String content;
    private int proficiencyId;
    private boolean enabled;
    private int topicId;
}
