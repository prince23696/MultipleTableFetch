package com.MultipleTableFetch.Dto;

import com.MultipleTableFetch.Entity.CreateAssignment;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AssignmentResponseDto {

    private int recordCount;
    private List<CreateAssignment> createAssignmentList;
    private CreateAssignment assignmentDto;
}