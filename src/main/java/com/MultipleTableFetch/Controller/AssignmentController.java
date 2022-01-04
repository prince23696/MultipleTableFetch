package com.MultipleTableFetch.Controller;

import com.MultipleTableFetch.Dto.AssignmentDto;
import com.MultipleTableFetch.Dto.AssignmentResponseDto;
import com.MultipleTableFetch.Dto.ResponseHandler;
import com.MultipleTableFetch.Entity.CreateAssignment;
import com.MultipleTableFetch.Entity.DemoEntityForValidation;
import com.MultipleTableFetch.Entity.UploadQuiz;
import com.MultipleTableFetch.Helper.FileUploadHelper;
import com.MultipleTableFetch.Repository.DemoEntityForValidationRepository;
import com.MultipleTableFetch.Repository.QuizRepository;
import com.MultipleTableFetch.Service.AssignmentService;
import com.google.common.net.HttpHeaders;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Locale;
import java.util.Optional;

@RestController
public class AssignmentController {

    @Autowired
    FileUploadHelper fileUploadHelper;
    @Autowired
    AssignmentService assignmentService;
    @Autowired
    QuizRepository quizRepository;
    @Autowired
    DemoEntityForValidationRepository demoEntityForValidationRepository;

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

    @PostMapping(path = "/uploadQuiz", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> uploadQuiz(/*@RequestParam String quizName,*/ @RequestPart("quizFile") @ApiParam(value = "quizFile", required = true) MultipartFile quizFile, @RequestHeader(name = "Accept-Language", required = false) Locale locale) throws IOException {

        if (quizFile.isEmpty()) {
            return ResponseHandler.response("", "Request Must Contain File.", false, HttpStatus.INTERNAL_SERVER_ERROR);
        } else if (!quizFile.getContentType().equals("image/jpeg")) {
            return ResponseHandler.response("", "Only JPEG Content Type Is Allowed.", false, HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            String s = fileUploadHelper.uploadFile(quizFile);
            if (s != null) {
                UploadQuiz uploadQuiz = new UploadQuiz();
                uploadQuiz.setQuizName(quizFile.getOriginalFilename());
                uploadQuiz.setQuizFile(s);
                UploadQuiz save = quizRepository.save(uploadQuiz);
                return ResponseHandler.response(save, "Successfully Added Assignment.", true, HttpStatus.OK);
            } else
                return ResponseHandler.response("", "Could not upload the file: %s!" + quizFile.getOriginalFilename(), false, HttpStatus.BAD_REQUEST);

            /*try {
                assignmentService.save(quizFile);
                return ResponseEntity.status(HttpStatus.OK)
                        .body(String.format("File uploaded successfully: %s", quizFile.getOriginalFilename()));
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(String.format("Could not upload the file: %s!", quizFile.getOriginalFilename()));
            }*/
        }
    }

    /* UploadQuiz uploadQuiz1 = new UploadQuiz();
        uploadQuiz1.setQuizName(quizName);
    //      uploadQuiz1.setQuizFile(fileInputStream);
    //   uploadQuiz1.setQuizFile(quizFile);
    UploadQuiz uploadQuiz = quizRepository.save(uploadQuiz1);
        return ResponseHandler.response(uploadQuiz,"Successfully Created Quiz With Given Details",true,HttpStatus.OK);
}*/
    @GetMapping("getFileById")
    public ResponseEntity<Object> getFile(@RequestParam Long id) {
        Optional<UploadQuiz> fileEntityOptional = assignmentService.getFile(id);

        if (!fileEntityOptional.isPresent()) {
            return ResponseEntity.notFound()
                    .build();
        }
        UploadQuiz uploadQuiz = fileEntityOptional.get();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + uploadQuiz.getQuizName() + "\"")
                .body(uploadQuiz.getQuizFile());
    }

    @PostMapping("/DemoEntity")
    public ResponseEntity<Object> DemoEntity(@Valid @RequestBody DemoEntityForValidation demoEntityForValidation) {
        DemoEntityForValidation assignment1 = demoEntityForValidationRepository.save(demoEntityForValidation);
        return ResponseHandler.response(assignment1, "Successfully Created Assignment With Given Details", true, HttpStatus.OK);
    }
}