package com.MultipleTableFetch.Service;

import com.MultipleTableFetch.Dto.SubjectUpdateDto;
import com.MultipleTableFetch.Entity.Category;
import com.MultipleTableFetch.Entity.Subject;
import com.MultipleTableFetch.Repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    SubjectRepository subjectRepository;
    @Autowired
    SubCategoryService subCategoryService;

    @Override
    public List<Subject> getAllSubject(int subCategoryId) {
        return subjectRepository.findBySubjectDetailsDto(subCategoryId);
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
    public Subject updateSubject(int id, SubjectUpdateDto subject) {
        Subject subject1 = subjectRepository.findById(id).get();
        subject1.setSubCategoryId(subject.getSubCategoryId());
        subject1.setSubjectName(subject.getSubjectName());
        subject1.setSubjectSequence(subject.getSubjectSequence());
        subject1.setSubCategory(subCategoryService.getSubCategory(subject.getSubCategoryId()));
        subjectRepository.save(subject1);
        return subject1;
    }

    @Override
    public Subject deleteSubject(int id) {
        Subject subject = subjectRepository.findById(id).get();
        subjectRepository.deleteById(id);
        return subject;
    }

    @Override
    public List<Subject> getSubjectByNameOrId(String name, Integer id) {
        if (name == null && id != null) {
            List<Subject> byCategoryDetailsById = subjectRepository.findBySubjectDetailsById(id);
            return byCategoryDetailsById;
        } else if (name != null && id == null) {
            List<Subject> byCategoryDetailsByName = subjectRepository.findBySubjectDetailsByName(name);
            return byCategoryDetailsByName;
        } else if (name != null && id != null) {
            List<Subject> byCategoryDetailsByName = subjectRepository.findBySubjectDetailsByNameAndId(id, name);
            return byCategoryDetailsByName;
        } else {
            List<Subject> all = subjectRepository.findAll();
            return all;
        }
    }
}