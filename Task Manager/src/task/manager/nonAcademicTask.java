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
public class nonAcademicTask extends Task {
    private String category;
    
    public nonAcademicTask(){}

    public nonAcademicTask(String name, String description, LocalDate deadline, String category) {
        super(name, description, deadline);
        this.category = category;
    }

    public nonAcademicTask(String name, String description, LocalDate deadline) {
        super(name, description, deadline);
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    
    

}
