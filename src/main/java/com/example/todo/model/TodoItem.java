package com.example.todo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "todo_item")
public class TodoItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private boolean starred;
    private boolean done;
    private String title;
    private String description;

    public TodoItem() {
    }

    public TodoItem(String title, String description) {
        this.title = title;
        this.description = description;
    }
    public TodoItem(long id, String title, String description) {
        this.title = title;
        this.description = description;
        this.id = id;
    }

    public TodoItem(long id, boolean starred, boolean done, String title, String description) {
        this.id = id;
        this.starred = starred;
        this.done = done;
        this.title = title;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isStarred() {
        return starred;
    }

    public void setStarred(boolean starred) {
        this.starred = starred;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
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

    @Override
    public String toString() {
        return "TodoItem{" +
                "id=" + id +
                ", starred=" + starred +
                ", done=" + done +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
