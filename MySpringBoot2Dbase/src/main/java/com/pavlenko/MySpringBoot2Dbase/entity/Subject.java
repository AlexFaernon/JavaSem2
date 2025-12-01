package com.pavlenko.MySpringBoot2Dbase.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "SUBJECTS")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "hours")
    private Integer hours;

    @Column(name = "faculty")
    private String faculty;

    public Subject() {
    }

    public Subject(String name, String description, Integer hours, String faculty) {
        this.name = name;
        this.description = description;
        this.hours = hours;
        this.faculty = faculty;
    }

    // getters / setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", hours=" + hours +
                ", faculty='" + faculty + '\'' +
                '}';
    }
}
