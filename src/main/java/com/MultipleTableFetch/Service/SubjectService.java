package com.MultipleTableFetch.Service;

import com.MultipleTableFetch.Dto.SubjectUpdateDto;
import com.MultipleTableFetch.Entity.Subject;

import java.util.List;

public interface SubjectService {

    public List<Subject> getAllSubject(int subCategoryId);

    public Subject getSubject(int id);

    public Subject addSubject(Subject subject);

    public Subject updateSubject(int id, SubjectUpdateDto subject);

    public Subject deleteSubject(int id);

    public List<Subject> getSubjectByNameOrId(String name, Integer id);
}