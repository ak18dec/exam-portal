package com.exam.instruction.model;

public class Instruction {

    private int id;
    private String content;
    private boolean enabled;

    public Instruction() {
    }

    public Instruction(int id, String content, boolean enabled) {
        this.id = id;
        this.content = content;
        this.enabled = enabled;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "Instruction{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}
