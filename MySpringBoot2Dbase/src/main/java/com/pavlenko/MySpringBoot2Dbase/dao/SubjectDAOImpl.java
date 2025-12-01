package com.pavlenko.MySpringBoot2Dbase.dao;

import com.pavlenko.MySpringBoot2Dbase.entity.Subject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class SubjectDAOImpl implements SubjectDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Subject> getAllSubjects() {
        Query query = entityManager.createQuery("from Subject");
        List<Subject> allSubjects = query.getResultList();
        log.info("getAllSubjects " + allSubjects);
        return allSubjects;
    }

    @Override
    public Subject saveSubject(Subject subject) {
        Subject saved = entityManager.merge(subject);
        log.info("saveSubject " + saved);
        return saved;
    }

    @Override
    public Subject getSubject(int id) {
        Subject subject = entityManager.find(Subject.class, id);
        log.info("getSubject id=" + id + ", result=" + subject);
        return subject;
    }

    @Override
    public void deleteSubject(int id) {
        Query query = entityManager.createQuery("delete from Subject where id = :subjectId");
        query.setParameter("subjectId", id);
        int deleted = query.executeUpdate();
        log.info("deleteSubject id=" + id + ", deletedRows=" + deleted);
    }
}
