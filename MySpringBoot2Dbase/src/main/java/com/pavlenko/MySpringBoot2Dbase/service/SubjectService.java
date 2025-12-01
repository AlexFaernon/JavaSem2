package com.pavlenko.MySpringBoot2Dbase.service;

import com.pavlenko.MySpringBoot2Dbase.entity.Subject;

import java.util.List;

public interface SubjectService {

    List<Subject> getAllSubjects();

    Subject saveSubject(Subject subject);

    Subject getSubject(int id);

    void deleteSubject(int id);
}
