package com.pavlenko.MySpringBoot2Dbase.controller;

import com.pavlenko.MySpringBoot2Dbase.entity.Student;
import com.pavlenko.MySpringBoot2Dbase.entity.Subject;
import com.pavlenko.MySpringBoot2Dbase.model.Response;
import com.pavlenko.MySpringBoot2Dbase.service.StudentService;
import com.pavlenko.MySpringBoot2Dbase.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MyController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private SubjectService subjectService;

    @GetMapping("/students")
    public Response<List<Student>> allStudents() {
        List<Student> allStudents;
        try {
            allStudents = studentService.getAllStudents();
        }
        catch (Exception e) {
            return new Response<>(
                    false,
                    e.toString(),
                    null
            );
        }
        return new Response<>(
                true,
                "",
                allStudents
        );
    }

    @GetMapping("/students/{id}")
    public Response<Student> getStudent(@PathVariable("id") int id) {
        try {
            return new Response<>(
                    true,
                    "",
                    studentService.getStudent(id)
            );
        }
        catch (Exception e) {
            return new Response<>(
                    false,
                    e.toString(),
                    null
            );
        }
    }

    @PostMapping("/students")
    public Response<Student> saveStudent(@RequestBody Student student) {
        try {
            return new Response<>(
                    true,
                    "",
                    studentService.saveStudent(student)
            );
        }
        catch (Exception e) {
            return new Response<>(
                    false,
                    e.toString(),
                    null
            );
        }
    }

    @PutMapping("/students")
    public Response<Student> updateStudent(@RequestBody Student student) {
        try {
            studentService.saveStudent(student);
            return new Response<>(
                    true,
                    "",
                    student
            );
        }
        catch (Exception e) {
            return new Response<>(
                    false,
                    e.toString(),
                    null
            );
        }
    }

    @DeleteMapping("/students/{id}")
    public void deleteStudent(@PathVariable("id") int id) {
        studentService.deleteStudent(id);
    }

    @GetMapping("/subjects")
    public Response<List<Subject>> getAllSubjects() {
        try {
            return new Response<>(
                    true,
                    "",
                    subjectService.getAllSubjects()
            );
        }
        catch (Exception e) {
            return new Response<>(
                    false,
                    e.toString(),
                    null
            );
        }
    }

    @GetMapping("/subjects/{id}")
    public Response<Subject> getSubject(@PathVariable int id) {
        try {
            return new Response<>(
                    true,
                    "",
                    subjectService.getSubject(id)
            );
        }
        catch (Exception e) {
            return new Response<>(
                    false,
                    e.toString(),
                    null
            );
        }
    }

    @PostMapping("/subjects")
    public Response<Subject> addSubject(@RequestBody Subject subject) {
        try {
            return new Response<>(
                    true,
                    "",
                    subjectService.saveSubject(subject)
            );
        }
        catch (Exception e) {
            return new Response<>(
                    false,
                    e.toString(),
                    null
            );
        }
    }

    @PutMapping("/subjects")
    public Response<Subject> updateSubject(@RequestBody Subject subject) {
        try {
            return new Response<>(
                    true,
                    "",
                    subjectService.saveSubject(subject)
            );
        }
        catch (Exception e) {
            return new Response<>(
                    false,
                    e.toString(),
                    null
            );
        }
    }

    @DeleteMapping("/subjects/{id}")
    public Response<String> deleteSubject(@PathVariable int id) {
        try {
            subjectService.deleteSubject(id);
            return new Response<>(
                    true,
                    "",
                    "Subject with id = " + id + " was deleted"
            );
        }
        catch (Exception e) {
            return new Response<>(
                    false,
                    e.toString(),
                    null
            );
        }
    }
}
