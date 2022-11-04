package com.example.studentregapp;

import java.util.ArrayList;
import java.util.List;

public class DataStore {
    List<Classroom> classrooms = new ArrayList<>();
    List<Student> students = new ArrayList<>();
    List<Mentor> mentors = new ArrayList<>();
    List<User> users = new ArrayList<>();
    private static DataStore instance;


    public static DataStore getInstance() {

        if (instance == null) {
            instance = new DataStore();
        }
        return instance;

    }

    private DataStore() {
        classrooms.addAll(Classroom.defaultClassroom());
        students.addAll(Student.defaultStudent());
        mentors.addAll(Mentor.defaultMentors());
        users.addAll(User.defaultUser());


    }
}
