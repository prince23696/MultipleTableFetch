package com.MultipleTableFetch.Controller;

import com.MultipleTableFetch.Dto.AssignmentDto;
import com.MultipleTableFetch.Dto.AssignmentResponseDto;
import com.MultipleTableFetch.Dto.ResponseHandler;
import com.MultipleTableFetch.Entity.CreateAssignment;
import com.MultipleTableFetch.Entity.UploadQuiz;
import com.MultipleTableFetch.Repository.QuizRepository;
import com.MultipleTableFetch.Service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Locale;

@RestController
public class AssignmentController {


    @Autowired
    AssignmentService assignmentService;
    @Autowired
    QuizRepository quizRepository;

    @GetMapping("getAssignmentByAssignmentId")
    public ResponseEntity<Object> getCreateAssignment(@RequestParam Long id, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        AssignmentResponseDto assignment = assignmentService.getAssignment(id);
        return ResponseHandler.response(assignment, "Successfully Getting Assignment Details", true, HttpStatus.OK);
    }

    @GetMapping("/getAllAssignments")
    public ResponseEntity<Object> getAllCreateAssignment(@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        AssignmentResponseDto allAssignment = assignmentService.getAllAssignment();
        return ResponseHandler.response(allAssignment, "Successfully Getting All Assignment Details", true, HttpStatus.OK);

    }

    @PostMapping("/CreateAssignment")
    public ResponseEntity<Object> addCreateAssignment(@RequestBody AssignmentDto assignmentDto) {
        CreateAssignment assignment1 = assignmentService.addAssignment(assignmentDto);
        return ResponseHandler.response(assignment1, "Successfully Created Assignment With Given Details", true, HttpStatus.OK);
    }

    @DeleteMapping("/deleteAssignment")
    public ResponseEntity<Object> deleteCreateAssignment(@RequestParam Long id, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        CreateAssignment createAssignment = assignmentService.deleteAssignment(id);
        return ResponseHandler.response(createAssignment, "Successfully Performed Delete Operation.", true, HttpStatus.OK);
    }

    @PutMapping("/updateAssignment")
    public ResponseEntity<Object> updateCreateAssignment(@RequestParam Long id, @RequestBody AssignmentDto assignmentDto, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        CreateAssignment assignment = assignmentService.updateAssignment(id, assignmentDto);
        return ResponseHandler.response(assignment, "Successfully Updated Assignment Details", true, HttpStatus.OK);
    }

    @PostMapping(value = "/uploadQuiz/",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> uploadQuiz(@RequestParam String quizName, @RequestParam MultipartFile quizFile, @RequestHeader(name = "Accept-Language", required = false) Locale locale) throws IOException {
//        FileInputStream fileInputStream = (FileInputStream) quizFile.getInputStream();
        UploadQuiz uploadQuiz1 = new UploadQuiz();
        uploadQuiz1.setQuizName(quizName);
        //      uploadQuiz1.setQuizFile(fileInputStream);
        uploadQuiz1.setQuizFile(quizFile);
        UploadQuiz uploadQuiz = quizRepository.save(uploadQuiz1);
        return ResponseHandler.response(uploadQuiz, "Successfully Created Quiz With Given Details", true, HttpStatus.OK);
    }
}