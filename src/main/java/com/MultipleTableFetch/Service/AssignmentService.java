package com.MultipleTableFetch.Service;

import com.MultipleTableFetch.Dto.AssignmentDto;
import com.MultipleTableFetch.Dto.AssignmentResponseDto;
import com.MultipleTableFetch.Entity.CreateAssignment;

public interface AssignmentService {

    public AssignmentResponseDto getAllAssignment();

    public AssignmentResponseDto getAssignment(Long id);

    public CreateAssignment addAssignment(AssignmentDto assignmentDto);

    public CreateAssignment updateAssignment(Long id, AssignmentDto assignmentDto);

    public CreateAssignment deleteAssignment(Long id);


}
