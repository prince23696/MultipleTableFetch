package com.MultipleTableFetch.Service;

import com.MultipleTableFetch.Dto.AssignmentDto;
import com.MultipleTableFetch.Dto.AssignmentResponseDto;
import com.MultipleTableFetch.Entity.CreateAssignment;
import com.MultipleTableFetch.Entity.UploadQuiz;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

public interface AssignmentService {

    public AssignmentResponseDto getAllAssignment();

    public AssignmentResponseDto getAssignment(Long id);

    public CreateAssignment addAssignment(AssignmentDto assignmentDto);

    public CreateAssignment updateAssignment(Long id, AssignmentDto assignmentDto);

    public CreateAssignment deleteAssignment(Long id);

    public void save(MultipartFile file) throws IOException;

    public Optional<UploadQuiz> getFile(Long id);
}
