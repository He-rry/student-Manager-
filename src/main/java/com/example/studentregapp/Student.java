package com.example.studentregapp;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class Student {
    private final UUID id;
    private String name;
    private String age;
    private String email;
    private String address;
    private LocalDate dOfBirth;
    private String nRcNumber;
    private String gender;
    private Classroom classroom;
    private Mentor mentor;

    public Student(String name,
                   String age,
                   String email,
                   String address,
                   LocalDate dOfBirth,
                   String nRcNumber,
                   String gender,
                   Classroom classroom,
                   Mentor mentor) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.address = address;
        this.dOfBirth = dOfBirth;
        this.nRcNumber = nRcNumber;
        this.gender = gender;
        this.classroom = classroom;
        this.mentor = mentor;
        this.id = UUID.randomUUID();
    }

    public Student() {
        this.id = UUID.randomUUID();

    }

    public static List<Student> defaultStudent() {

        return List.of(
                new Student("Herry", "20", "herry@gamil.com", "Yangon", LocalDate.of(2000, 4, 6),
                        "12laMaNa(N)12348", "Male",
                        Classroom.defaultClassroom().get(1), Mentor.defaultMentors().get(1)),
                new Student("Min Min", "19", "minmin@gamil.com", "Mandalay", LocalDate.of(2001, 6, 9),
                        "12laMaNa(N)54675", "Male",
                        Classroom.defaultClassroom().get(2), Mentor.defaultMentors().get(0)),
                new Student("Hla Hla", "20", "hlahla@gamil.com", "Kalaw", LocalDate.of(2000, 8, 23),
                        "12laMaNa(N)55323", "Female",
                        Classroom.defaultClassroom().get(1), Mentor.defaultMentors().get(1)),
                new Student("Min Aung", "18", "minaung@gamil.com", "Yangon", LocalDate.of(2003, 4, 5), "13/LaMaNa(N)2345", "Male",
                        Classroom.defaultClassroom().get(1), Mentor.defaultMentors().get(1)),
                new Student("Hla Yi", "20", "hlayi@gamil.com", "Yangon", LocalDate.of(2000, 2, 4), "13/KaNAPA(N)24455", "Female",
                        Classroom.defaultClassroom().get(0 & 1), Mentor.defaultMentors().get(0))
                , new Student("SuKo", "19", "suko@gamil.com", "Yangon", LocalDate.of(2001, 5, 5), "13/KaKatha(N)253221", "Female",
                        Classroom.defaultClassroom().get(3), Mentor.defaultMentors().get(4))
        );
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress(String text) {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getDateOfBirth() {
        return dOfBirth;
    }

    public void setDateBirth(LocalDate dOfBirth) {
        this.dOfBirth = dOfBirth;
    }

    public String getNRCNumber() {
        return nRcNumber;
    }

    public void setNRCumber(String nRcNumber) {
        this.nRcNumber = nRcNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public String getClassroomName() {
        return this.classroom.getClassroomName();
    }

    public Mentor getMentor() {
        return mentor;
    }

    public void setMentor(Mentor mentor) {
        this.mentor = mentor;
    }

    public String getMentorName() {
        return this.mentor.getName();
    }

    public String getAddress() {
        return address;
    }
}
