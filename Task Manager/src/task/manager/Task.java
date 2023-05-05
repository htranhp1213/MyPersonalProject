/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package task.manager;

/**
 *
 * @author huongtran
 */
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

enum Category {
    ACADEMIC,
    NON_ACADEMIC
}

enum Priority {
    LOW,
    MEDIUM,
    HIGH
}

public abstract class Task {
    private String name;
    private String description;
    private LocalDate deadline;
    private int progress;
    private boolean completed;
    private Priority priority;
    private Date reminder;
    private LocalDateTime manReminder;
    
    public Task (){}

    public Task(String name, String description, LocalDate deadline) {
        this.name = name;
        this.description = description;
        this.deadline = deadline;
        this.progress = 0;
        this.completed = false;
        this.priority = null;
        this.reminder = null;
        this.manReminder = null;
    }

    public void updateName(String name) {
        this.name = name;
    }

    public void updateDescription(String description) {
        this.description = description;
    }

    public void updateDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public void updateProgress(int progress) {
        this.progress = progress;
    }

    public void updateCompleted(boolean completed) {
        this.completed = completed;
    }


    public void updatePriority(Priority priority) {
        this.priority = priority;
    }

    public void setReminder(Date reminder) {
        this.reminder = reminder;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public int getProgress() {
        return progress;
    }

    public boolean isCompleted() {
        return completed;
    }


    public Priority getPriority() {
        return priority;
    }

    public Date getReminder() {
        return reminder;
    }

    void setReminder(LocalDateTime manReminder) {
        this.manReminder = manReminder;
    }

    
    
}


    

