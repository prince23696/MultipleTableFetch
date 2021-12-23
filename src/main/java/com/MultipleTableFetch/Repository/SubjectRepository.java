package com.MultipleTableFetch.Repository;

import com.MultipleTableFetch.Entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {
    @Query(value = "select count(subject_id) from subject", nativeQuery = true)
    public int countRecords();

    @Query(value = "select * from subject c where sub_category_id=?1 ", nativeQuery = true)
    public List<Subject> findBySubjectDetailsDto(int categoryId);

    @Query(value = "select * from subject c where c.subject_id=?1", nativeQuery = true)
    public List<Subject> findBySubjectDetailsById(int id);

    @Query(value = "select * from subject c where c.subject_name like ?1% ", nativeQuery = true)
    public List<Subject> findBySubjectDetailsByName(String name);

    @Query(value = "select * from subject c where c.subject_id=?1 or c.subject_name like ?2%", nativeQuery = true)
    public List<Subject> findBySubjectDetailsByNameAndId(int id, String name);
}