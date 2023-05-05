/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package task.manager;

import login_or_signup.User;
import java.util.ArrayList;
import java.util.Date;
import java.time.LocalDate;

/**
 *
 * @author huongtran
 */
import java.time.LocalDate;
import java.util.ArrayList;

public class TaskManager {
    private ArrayList<User> users;
    private ArrayList<AcademicTask> academicTasks;
    private ArrayList<nonAcademicTask> nonAcademicTasks;
    private ArrayList<Task> allTasks;

    public TaskManager() {
        this.users = new ArrayList<>();
        this.academicTasks = new ArrayList<>();
        this.nonAcademicTasks = new ArrayList<>();
        this.allTasks = new ArrayList<>();
    }

    public void addUser(User user) {
        users.add(user);
    }
    public void createUser(String username, String password) {
        User user = new User(username, password);
        users.add(user);
        System.out.println("User " + username + " created successfully!");
    }

    public void addAcademicTask(String name, String description, LocalDate deadline, String courseName) {
        AcademicTask academicTask = new AcademicTask(name, description, deadline, courseName);
        academicTasks.add(academicTask);
        allTasks.add(academicTask);
    }

    public void addNonAcademicTask(String name, String description, LocalDate deadline, String typeName) {
        nonAcademicTask nonAcademicTask = new nonAcademicTask(name, description, deadline, typeName);
        nonAcademicTasks.add(nonAcademicTask);
        allTasks.add(nonAcademicTask);
    }

    public ArrayList<Task> getAllTasks() {
        return allTasks;
    }

    public ArrayList<Task> getTasksByStatus(boolean completed) {
        ArrayList<Task> tasks = new ArrayList<>();
        for (Task task : allTasks) {
            if (task.isCompleted() == completed) {
                tasks.add(task);
            }
        }
        return tasks;
    }

    public ArrayList<AcademicTask> getAcademicTasksByCourse(String courseName) {
        ArrayList<AcademicTask> tasks = new ArrayList<>();
        for (AcademicTask task : academicTasks) {
            if (task.getCourse().equals(courseName)) { // comparing course name
                tasks.add(task);
            }
        }
        return tasks;
    }

    public ArrayList<Task> getTasksByCategory(String category) {
        ArrayList<Task> tasks = new ArrayList<>();
        for (Task task : allTasks) {
            if (task instanceof AcademicTask) { // casting from task to academic task
                
                tasks.add(task);
                
            }
        }
        return tasks;
    }
}

    

