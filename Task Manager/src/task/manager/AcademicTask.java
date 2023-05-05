/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package task.manager;

import java.time.LocalDate;

/**
 *
 * @author huongtran
 */
public class AcademicTask extends Task {
    private String course;

    /**
     *
     * @param name
     * @param description
     * @param deadline
     * @param course
     */
    public AcademicTask(String name, String description, LocalDate deadline, String course) {
        super(name, description, deadline);
        this.course = course;
    }

    public AcademicTask() {
        
    }

    public AcademicTask(String name, String description, LocalDate deadline) {
        super(name, description, deadline);
    }

    

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
    
}
