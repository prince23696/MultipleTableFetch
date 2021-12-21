package com.MultipleTableFetch.Service;

import com.MultipleTableFetch.Entity.Subject;
import com.MultipleTableFetch.Repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    SubjectRepository subjectRepository;

    @Override
    public List<Subject> getAllSubject() {
        return subjectRepository.findAll();
    }

    @Override
    public Subject getSubject(int id) {
        return subjectRepository.findById(id).get();
    }

    @Override
    public Subject addSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    @Override
    public Subject updateSubject(int id, Subject subject) {
        return null;
    }

    @Override
    public Subject deleteSubject(int id) {
        Subject subject = subjectRepository.findById(id).get();
        subjectRepository.deleteById(id);
        return subject;
    }
}
