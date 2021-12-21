package com.MultipleTableFetch.Service;

import com.MultipleTableFetch.Entity.Subject;

import java.util.List;

public interface SubjectService {

    public List<Subject> getAllSubject();

    public Subject getSubject(int id);

    public Subject addSubject(Subject subject);

    public Subject updateSubject(int id, Subject subject);

    public Subject deleteSubject(int id);

}
