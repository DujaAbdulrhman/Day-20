package com.example.demo.Model;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TaskModel {
    private int id;
    private String title;
    private String description;
    private boolean status;


}
