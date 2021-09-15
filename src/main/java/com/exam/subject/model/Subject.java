package com.exam.subject.model;

public class Subject {

    //Genre -> Subject

    private int id;
    private String title;
    private String description;
    private int genreId;
    private boolean enabled;

    public Subject() {
    }

    public Subject(int id, String title, String description, int genreId, boolean enabled) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.genreId = genreId;
        this.enabled = enabled;
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

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", genreId=" + genreId +
                ", enabled=" + enabled +
                '}';
    }
}
