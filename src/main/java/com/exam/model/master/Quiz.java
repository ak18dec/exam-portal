package com.exam.model.master;

import java.util.List;

public class Quiz {

    private int id;
    private String title;
    private String description;
    private List<Integer> questionIds;
    private int proficiencyId;
    private boolean instructionEnabled;
    private List<Integer> instructionIds;
}
