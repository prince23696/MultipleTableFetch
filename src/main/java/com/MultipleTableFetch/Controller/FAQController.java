package com.MultipleTableFetch.Controller;

import com.MultipleTableFetch.Dto.*;
import com.MultipleTableFetch.Entity.*;
import com.MultipleTableFetch.Repository.AdminRepository;
import com.MultipleTableFetch.Repository.FAQCategoryRepository;
import com.MultipleTableFetch.Repository.FAQQuestionAnswerRepository;
import com.MultipleTableFetch.Repository.FAQTopicRepository;
import com.MultipleTableFetch.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
public class FAQController {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    FAQCategoryService faqCategoryService;
    @Autowired
    FAQTopicService faqTopicService;
    @Autowired
    FAQQuestionAnswerService faqQuestionAnswerService;
    @Autowired
    FAQCategoryRepository faqCategoryRepository;
    @Autowired
    FAQTopicRepository faqTopicRepository;
    @Autowired
    FAQQuestionAnswerRepository faqQuestionAnswerRepository;

    @PostMapping("/AddFAQCategory")
    public ResponseEntity<Object> AddFAQCategory(@RequestParam String categoryName, @RequestBody AdminDto adminDto, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(adminDto.getEmail(), adminDto.getPassword()));
            Admin admin1 = adminRepository.findByEmail(adminDto.getEmail());
            if (admin1.getRole().equalsIgnoreCase("ROLE_ADMIN")) {
                Boolean b = faqCategoryRepository.findByFAQCategoryByCategoryName(categoryName);
                if (!b) {
                    FAQCategory category = new FAQCategory(categoryName);
                    FAQCategory category1 = faqCategoryService.addFAQCategory(category);
                    return ResponseHandler.response(category1, "FAQ-Category Added Successfully.", true, HttpStatus.OK);
                } else
                    return ResponseHandler.response("", "FAQCategory Already Exist.", false, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseHandler.response("", "You Don't Have Correct Access Right To Create FAQ-Category.", true, HttpStatus.OK);
    }

    @PostMapping("/getFAQCategory")
    public ResponseEntity<Object> getFAQCategory(@RequestBody AdminDto adminDto, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(adminDto.getEmail(), adminDto.getPassword()));
            Admin admin1 = adminRepository.findByEmail(adminDto.getEmail());
            if (admin1.getRole().equalsIgnoreCase("ROLE_ADMIN")) {
                FAQCategoryDetailsResponseDto allFAQCategory = faqCategoryService.getAllFAQCategory();
                return ResponseHandler.response(allFAQCategory, "All FAQ-Category Fetched Successfully.", true, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseHandler.response("", "You Don't Have Correct Access Right To Access This Api.", true, HttpStatus.OK);
    }

    @PostMapping("/getFAQCategoryAll")
    public ResponseEntity<Object> getFAQCategoryAll(@RequestBody AdminDto adminDto, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(adminDto.getEmail(), adminDto.getPassword()));
            Admin admin1 = adminRepository.findByEmail(adminDto.getEmail());
            if (admin1.getRole().equalsIgnoreCase("ROLE_ADMIN")) {
                List<FAQCategory> all = faqCategoryService.getAll();
                return ResponseHandler.response(all, "All FAQ-Category Fetched Successfully.", true, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseHandler.response("", "You Don't Have Correct Access Right To Access This Api.", true, HttpStatus.OK);
    }

    @PostMapping("/AddFAQTopic")
    public ResponseEntity<Object> AddFAQTopic(@RequestParam int categoryId, @RequestParam String faqTopicName, @RequestBody AdminDto adminDto, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(adminDto.getEmail(), adminDto.getPassword()));
            Admin admin1 = adminRepository.findByEmail(adminDto.getEmail());
            if (admin1.getRole().equalsIgnoreCase("ROLE_ADMIN")) {
                Boolean b = faqTopicRepository.findByFAQTopicByTopicName(faqTopicName);
                if (!b) {
                    FAQTopic faqTopic = new FAQTopic(categoryId, faqTopicName);
                    faqTopic.setFaqCategory(faqCategoryService.getFAQCategory(categoryId));
                    FAQTopic faqTopic1 = faqTopicService.addFAQTopic(faqTopic);
                    return ResponseHandler.response(faqTopic1, "FAQTopic Added Successfully.", true, HttpStatus.OK);
                } else
                    return ResponseHandler.response("", "FAQTopic Already Exist.", false, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseHandler.response("", "You Don't Have Correct Access Right To Create FAQTopic Api.", true, HttpStatus.OK);
    }

    @PostMapping("/getFAQTopic")
    public ResponseEntity<Object> getFAQTopic(@RequestParam int categoryId, @RequestBody AdminDto adminDto, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(adminDto.getEmail(), adminDto.getPassword()));
            Admin admin1 = adminRepository.findByEmail(adminDto.getEmail());
            if (admin1.getRole().equalsIgnoreCase("ROLE_ADMIN")) {
                List<FAQTopic> allFAQTopic = faqTopicService.getAllFAQTopic(categoryId);
                return ResponseHandler.response(allFAQTopic, "All FAQ-Topic Fetched Successfully.", true, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseHandler.response("", "You Don't Have Correct Access Right To Access This Api.", true, HttpStatus.OK);
    }

    @PostMapping("/AddFAQQuestionAnswer")
    public ResponseEntity<Object> AddFAQQuestionAnswer(@RequestParam int faqTopicId, @RequestParam String faqQuestion, @RequestParam String
            faqAnswer, @RequestBody AdminDto adminDto, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(adminDto.getEmail(), adminDto.getPassword()));
            Admin admin1 = adminRepository.findByEmail(adminDto.getEmail());
            if (admin1.getRole().equalsIgnoreCase("ROLE_ADMIN")) {
                Boolean b = faqQuestionAnswerRepository.findByFAQQuestionByQuestion(faqQuestion);
                if (!b) {
                    FAQQuestionAnswer faqQuestionAnswer = new FAQQuestionAnswer(faqTopicId, faqQuestion, faqAnswer);
                    faqQuestionAnswer.setFaqTopic(faqTopicService.getFAQTopic(faqTopicId));
                    FAQQuestionAnswer faqQuestionAnswer1 = faqQuestionAnswerService.addFAQQuestionAnswer(faqQuestionAnswer);
                    return ResponseHandler.response(faqQuestionAnswer1, "FAQ Question Answer Added Successfully.", true, HttpStatus.OK);
                } else
                    return ResponseHandler.response("", "FAQ Question Already Exist.", false, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseHandler.response("", "You Don't Have Correct Access Right To Create FAQ Question Answer Api.", true, HttpStatus.OK);
    }

    @PostMapping("/getFAQQuestionAnswer")
    public ResponseEntity<Object> getFAQQuestionAnswer(@RequestParam int faqTopicId, @RequestBody AdminDto adminDto, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(adminDto.getEmail(), adminDto.getPassword()));
            Admin admin1 = adminRepository.findByEmail(adminDto.getEmail());
            if (admin1.getRole().equalsIgnoreCase("ROLE_ADMIN")) {
                List<FAQQuestionAnswer> allFAQQuestionAnswer = faqQuestionAnswerService.getAllFAQQuestionAnswer(faqTopicId);
                return ResponseHandler.response(allFAQQuestionAnswer, "All FAQ-Question Answer Fetched Successfully.", true, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseHandler.response("", "You Don't Have Correct Access Right To Access This Api.", true, HttpStatus.OK);
    }

    @DeleteMapping("/deleteFAQCategory")
    public ResponseEntity<Object> deleteFAQCategory(@RequestParam int categoryId, @RequestBody AdminDto adminDto, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(adminDto.getEmail(), adminDto.getPassword()));
            Admin admin1 = adminRepository.findByEmail(adminDto.getEmail());
            if (admin1.getRole().equalsIgnoreCase("ROLE_ADMIN")) {
                FAQCategory faqCategory = faqCategoryService.deleteFAQCategory(categoryId);
                return ResponseHandler.response(faqCategory, "FAQCategory Deleted Successfully.", true, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseHandler.response("", "You Don't Have Correct Access Right To Delete FAQCategory.", true, HttpStatus.OK);
    }

    @DeleteMapping("/deleteFAQTopic")
    public ResponseEntity<Object> deleteFAQTopic(@RequestParam int faqTopicId, @RequestBody AdminDto adminDto, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(adminDto.getEmail(), adminDto.getPassword()));
            Admin admin1 = adminRepository.findByEmail(adminDto.getEmail());
            if (admin1.getRole().equalsIgnoreCase("ROLE_ADMIN")) {
                FAQTopic faqTopic = faqTopicService.deleteFAQTopic(faqTopicId);
                return ResponseHandler.response(faqTopic, "FAQTopic Deleted Successfully.", true, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseHandler.response("", "You Don't Have Correct Access Right To Delete FAQTopic Api.", true, HttpStatus.OK);
    }

    @DeleteMapping("/deleteFAQQuestionAnswer")
    public ResponseEntity<Object> deleteFAQQuestionAnswer(@RequestParam int faqQuestionId, @RequestBody AdminDto adminDto, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(adminDto.getEmail(), adminDto.getPassword()));
            Admin admin1 = adminRepository.findByEmail(adminDto.getEmail());
            if (admin1.getRole().equalsIgnoreCase("ROLE_ADMIN")) {
                FAQQuestionAnswer faqQuestionAnswer = faqQuestionAnswerService.deleteFAQQuestionAnswer(faqQuestionId);
                return ResponseHandler.response(faqQuestionAnswer, "FAQ-Question-Answer Deleted Successfully.", true, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseHandler.response("", "You Don't Have Correct Access Right To Delete FAQ-Question-Answer Api.", true, HttpStatus.OK);
    }

    @PutMapping("/updateFAQCategory")
    public ResponseEntity<Object> UpdateFAQCategory(@RequestParam int categoryId, @RequestParam String email, @RequestParam String password, @RequestBody FAQCategoryUpdateDto faqCategoryUpdateDto, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
            Admin admin1 = adminRepository.findByEmail(email);
            if (admin1.getRole().equalsIgnoreCase("ROLE_ADMIN")) {
                FAQCategory faqCategory = faqCategoryService.updateFAQCategory(categoryId, faqCategoryUpdateDto);
                return ResponseHandler.response(faqCategory, "FAQ-Category Updated Successfully.", true, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseHandler.response("", "You Don't Have Correct Access Right To Access This Api.", true, HttpStatus.OK);
    }

    @PutMapping("/updateFAQTopic")
    public ResponseEntity<Object> updateFAQTopic(@RequestParam int faqTopicId, @RequestParam String email, @RequestParam String password, @RequestBody FAQTopicUpdateDto faqTopicUpdateDto, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
            Admin admin1 = adminRepository.findByEmail(email);
            if (admin1.getRole().equalsIgnoreCase("ROLE_ADMIN")) {
                FAQTopic faqTopic = faqTopicService.updateFAQTopic(faqTopicId, faqTopicUpdateDto);
                return ResponseHandler.response(faqTopic, "FAQ-Topic Updated Successfully.", true, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseHandler.response("", "You Don't Have Correct Access Right To Access This Api.", true, HttpStatus.OK);
    }

    @PutMapping("/updateFAQQuestionAnswer")
    public ResponseEntity<Object> updateFAQQuestionAnswer(@RequestParam int faqQuestionId, @RequestParam String email, @RequestParam String password, @RequestBody FAQQuestionAnswerUpdateDto faqQuestionAnswerUpdateDto, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
            Admin admin1 = adminRepository.findByEmail(email);
            if (admin1.getRole().equalsIgnoreCase("ROLE_ADMIN")) {
                FAQQuestionAnswer faqQuestionAnswer = faqQuestionAnswerService.updateFAQQuestionAnswer(faqQuestionId, faqQuestionAnswerUpdateDto);
                return ResponseHandler.response(faqQuestionAnswer, "FAQ-Question-Answer Updated Successfully.", true, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseHandler.response("", "You Don't Have Correct Access Right To Access This Api.", true, HttpStatus.OK);
    }

    @PostMapping("/getFAQCategoryByNameOrId")
    public ResponseEntity<Object> getFAQCategoryByNameOrId(@RequestParam(required = false) String name, @RequestParam(required = false) Integer id, @RequestBody AdminDto adminDto, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(adminDto.getEmail(), adminDto.getPassword()));
            Admin admin1 = adminRepository.findByEmail(adminDto.getEmail());
            if (admin1.getRole().equalsIgnoreCase("ROLE_ADMIN")) {
                List<FAQCategory> faqCategoryByNameOrId = faqCategoryService.getFAQCategoryByNameOrId(id, name);
                return ResponseHandler.response(faqCategoryByNameOrId, "All Category Fetched Successfully.", true, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseHandler.response("", "You Don't Have Correct Access Right To Access This Api.", true, HttpStatus.OK);
    }
/*
    @PostMapping("/getFAQTopicByNameOrId")
    public ResponseEntity<Object> getFAQTopicByNameOrId(@RequestParam(required = false) String name, @RequestParam(required = false) Integer id, @RequestBody AdminDto adminDto, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(adminDto.getEmail(), adminDto.getPassword()));
            Admin admin1 = adminRepository.findByEmail(adminDto.getEmail());
            if (admin1.getRole().equalsIgnoreCase("ROLE_ADMIN")) {
                List<SubCategory> subCategoryByNameOrId = subCategoryService.getSubCategoryByNameOrId(name, id);
                return ResponseHandler.response(subCategoryByNameOrId, "All Sub Category Fetched Successfully.", true, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseHandler.response("", "You Don't Have Correct Access Right To Access This Api.", true, HttpStatus.OK);
    }

    @PostMapping("/getFAQQuestionAnswerByNameOrId")
    public ResponseEntity<Object> getFAQQuestionAnswerByNameOrId(@RequestParam(required = false) String name, @RequestParam(required = false) Integer id, @RequestBody AdminDto adminDto, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(adminDto.getEmail(), adminDto.getPassword()));
            Admin admin1 = adminRepository.findByEmail(adminDto.getEmail());
            if (admin1.getRole().equalsIgnoreCase("ROLE_ADMIN")) {
                List<Subject> subjectByNameOrId = subjectService.getSubjectByNameOrId(name, id);
                return ResponseHandler.response(subjectByNameOrId, "All Subject Fetched Successfully.", true, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseHandler.response("", "You Don't Have Correct Access Right To Access This Api.", true, HttpStatus.OK);
    }*/
}