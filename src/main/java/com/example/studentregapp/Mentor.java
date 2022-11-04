package com.example.studentregapp;

import java.util.List;

public class Mentor {
    String name;
    String fees;
    Classroom classroom;
    public  static List<Mentor> defaultMentors(){
        return List.of(
                new Mentor("Ko Bhone ","10000ks",Classroom.defaultClassroom().get(0&2)),
                new Mentor("Kelvin" , "10000ks",Classroom.defaultClassroom().get(1)),
                new Mentor("Jean","20000ks",Classroom.defaultClassroom().get(4)),
                new Mentor("Emma","15000ks",Classroom.defaultClassroom().get(3)),
                new Mentor("Loki","30000ks",Classroom.defaultClassroom().get(2)),
                new Mentor("KiKi" ,"70000ks",Classroom.defaultClassroom().get(5))
        );
    }
    public Mentor(){

    }

    public Mentor(String name, String fees, Classroom classroom) {
        this.name = name;
        this.fees = fees;
        this.classroom = classroom;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFees() {
        return fees;
    }

    public void setFees(String fees) {
        this.fees = fees;
    }

    public Classroom get(){
        return this.classroom;
    }

    @Override
    public String toString() {
        return String.format(name,fees);
    }

}
