package com.example.studentregapp;

import javafx.fxml.FXML;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class Classroom {
    private final UUID id;
   private String classroomName;
   private String fees;


   public static List<Classroom> defaultClassroom(){
       return List.of(
               new Classroom("English","10000ks"),
               new Classroom("Digital painting","15000ks"),
               new Classroom("German","10000ks"),
               new Classroom("Korean","5000ks"),
               new Classroom("Guitar","10000ks"),
               new Classroom("Business IT","80000ks")
       );

   }
    public Classroom(String classroomName, String fees) {
       this.id = UUID.randomUUID();
        this.classroomName = classroomName;
        this.fees = fees;
    }
    public Classroom(){

       this.id = UUID.randomUUID();

    }

    public String getClassroomName() {
        return classroomName;
    }

    public void setClassroomName(String classroomName) {
        this.classroomName = classroomName;
    }

    public String getFees() {
        return fees;
    }

    public void setFees(String fees) {
        this.fees = fees;
    }

    @Override
    public String toString() {
        return this.classroomName;
    }
}
