package com.MultipleTableFetch.Service;

import com.MultipleTableFetch.Dto.AssignmentDto;
import com.MultipleTableFetch.Dto.AssignmentResponseDto;
import com.MultipleTableFetch.Entity.CreateAssignment;
import com.MultipleTableFetch.Entity.UploadQuiz;
import com.MultipleTableFetch.Repository.AssignmentRepository;
import com.MultipleTableFetch.Repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class AssignmentServiceImpl implements AssignmentService {

    @Autowired
    AssignmentRepository assignmentRepository;
    @Autowired
    CourseService courseService;
    @Autowired
    UserService userService;
    @Autowired
    QuizRepository quizRepository;

    @Override
    public AssignmentResponseDto getAllAssignment() {
        int i = assignmentRepository.countRecords();
        List<CreateAssignment> all = assignmentRepository.findAll();
        AssignmentResponseDto assignmentResponseDto = new AssignmentResponseDto();
        assignmentResponseDto.setRecordCount(i);
        assignmentResponseDto.setCreateAssignmentList(all);
        return assignmentResponseDto;
    }

    @Override
    public AssignmentResponseDto getAssignment(Long id) {
        int i = assignmentRepository.countRecords();
        CreateAssignment createAssignment = assignmentRepository.findById(id).get();
        AssignmentResponseDto assignmentResponseDto = new AssignmentResponseDto();
        assignmentResponseDto.setRecordCount(i);
        assignmentResponseDto.setAssignmentDto(createAssignment);
        return assignmentResponseDto;
    }

    @Override
    public CreateAssignment addAssignment(AssignmentDto assignmentDto) {
        CreateAssignment createAssignment = new CreateAssignment();
        createAssignment.setCourseId(assignmentDto.getCourseId());
        createAssignment.setUserId(assignmentDto.getUserId());
        createAssignment.setMarks(assignmentDto.getMarks());
        createAssignment.setQuestion(assignmentDto.getQuestion());
        createAssignment.setCourse(courseService.getCourse(assignmentDto.getCourseId()));
        createAssignment.setUsers(userService.getUserUsingId(assignmentDto.getUserId()));
        return assignmentRepository.save(createAssignment);
    }

    @Override
    public CreateAssignment updateAssignment(Long id, AssignmentDto assignment) {
        CreateAssignment createAssignment = assignmentRepository.findById(id).get();
        createAssignment.setCourseId(assignment.getCourseId());
        createAssignment.setUserId(assignment.getUserId());
        createAssignment.setMarks(assignment.getMarks());
        createAssignment.setQuestion(assignment.getQuestion());
        createAssignment.setCourse(courseService.getCourse(assignment.getCourseId()));
        createAssignment.setUsers(userService.getUserUsingId(assignment.getUserId()));
        return assignmentRepository.save(createAssignment);
    }

    @Override
    public CreateAssignment deleteAssignment(Long id) {
        CreateAssignment createAssignment = assignmentRepository.findById(id).get();
        assignmentRepository.deleteById(id);
        return createAssignment;
    }

    @Override
    public void save(MultipartFile file) throws IOException {
        UploadQuiz uploadQuiz = new UploadQuiz();
        uploadQuiz.setQuizName(StringUtils.cleanPath(file.getOriginalFilename()));
        //uploadQuiz.setQuizFile(file.getBytes());
        UploadQuiz save = quizRepository.save(uploadQuiz);
        return;
    }

    @Override
    public Optional<UploadQuiz> getFile(Long id) {
        return quizRepository.findById(id);
    }
}