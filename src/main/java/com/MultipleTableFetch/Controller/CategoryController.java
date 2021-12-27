package com.MultipleTableFetch.Controller;

import com.MultipleTableFetch.Dto.*;
import com.MultipleTableFetch.Entity.Admin;
import com.MultipleTableFetch.Entity.Category;
import com.MultipleTableFetch.Entity.SubCategory;
import com.MultipleTableFetch.Entity.Subject;
import com.MultipleTableFetch.Repository.AdminRepository;
import com.MultipleTableFetch.Repository.CategoryRepository;
import com.MultipleTableFetch.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
public class CategoryController {

    @Autowired
    AdminRepository adminRepository;
    @Autowired
    SubjectService subjectService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    CategoryService categoryService;
    @Autowired
    SubCategoryService subCategoryService;

    @PostMapping("/AddCategory")
    public ResponseEntity<Object> AddCategory(@RequestParam String categorySequence, @RequestParam String
            categoryName, @RequestBody AdminDto adminDto, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(adminDto.getEmail(), adminDto.getPassword()));
            Admin admin1 = adminRepository.findByEmail(adminDto.getEmail());
            if (admin1.getRole().equalsIgnoreCase("ROLE_ADMIN")) {
                Category category = new Category(categorySequence, categoryName);
                Category category1 = categoryService.addCategory(category);
                return ResponseHandler.response(category1, "Category Added Successfully.", true, HttpStatus.OK);
            }
        } catch (DataIntegrityViolationException e) {
            return ResponseHandler.response("", "ConstraintViolationException Enter Different CategoryName ", false, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseHandler.response("", "You Don't Have Correct Access Right To Create Category.", false, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/getCategory")
    public ResponseEntity<Object> getCategory(@RequestBody AdminDto adminDto, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(adminDto.getEmail(), adminDto.getPassword()));
            Admin admin1 = adminRepository.findByEmail(adminDto.getEmail());
            if (admin1.getRole().equalsIgnoreCase("ROLE_ADMIN")) {
                CategoryDetailsResponseDto allCategory = categoryService.getAllCategory();
                return ResponseHandler.response(allCategory, "All Category Fetched Successfully.", true, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseHandler.response("", "You Don't Have Correct Access Right To Access This Api.", false, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/getCategoryAll")
    public ResponseEntity<Object> getCategoryAll(@RequestBody AdminDto adminDto, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(adminDto.getEmail(), adminDto.getPassword()));
            Admin admin1 = adminRepository.findByEmail(adminDto.getEmail());
            if (admin1.getRole().equalsIgnoreCase("ROLE_ADMIN")) {
                List<Category> all = categoryService.getAll();
                return ResponseHandler.response(all, "All Category Fetched Successfully.", true, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseHandler.response("", "You Don't Have Correct Access Right To Access This Api.", false, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/AddSubCategory")
    public ResponseEntity<Object> AddSubCategory(@RequestParam int categoryId, @RequestParam String subCategorySequence, @RequestParam String
            subCategoryName, @RequestBody AdminDto adminDto, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(adminDto.getEmail(), adminDto.getPassword()));
            Admin admin1 = adminRepository.findByEmail(adminDto.getEmail());
            if (admin1.getRole().equalsIgnoreCase("ROLE_ADMIN")) {
                SubCategory subCategory = new SubCategory(categoryId, subCategorySequence, subCategoryName);
                subCategory.setCategory(categoryService.getCategory(categoryId));
                SubCategory subCategory1 = subCategoryService.addSubCategory(subCategory);
                return ResponseHandler.response(subCategory1, "SubCategory Added Successfully.", true, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseHandler.response("", "You Don't Have Correct Access Right To Access This Api.", false, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/getSubCategory")
    public ResponseEntity<Object> getSubCategory(@RequestParam int categoryId, @RequestBody AdminDto adminDto, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(adminDto.getEmail(), adminDto.getPassword()));
            Admin admin1 = adminRepository.findByEmail(adminDto.getEmail());
            if (admin1.getRole().equalsIgnoreCase("ROLE_ADMIN")) {
                List<SubCategory> allSubCategory = subCategoryService.getAllSubCategory(categoryId);
                return ResponseHandler.response(allSubCategory, "All Sub Category Fetched Successfully.", true, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseHandler.response("", "You Don't Have Correct Access Right To Access This Api.", false, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/AddSubject")
    public ResponseEntity<Object> AddSubject(@RequestParam int subCategoryId, @RequestParam String subjectSequence, @RequestParam String
            subjectName, @RequestBody AdminDto adminDto, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(adminDto.getEmail(), adminDto.getPassword()));
            Admin admin1 = adminRepository.findByEmail(adminDto.getEmail());
            if (admin1.getRole().equalsIgnoreCase("ROLE_ADMIN")) {
                Subject subject = new Subject(subCategoryId, subjectSequence, subjectName);
                subject.setSubCategory(subCategoryService.getSubCategory(subCategoryId));
                Subject subject1 = subjectService.addSubject(subject);
                return ResponseHandler.response(subject1, "Subject Added Successfully.", true, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseHandler.response("", "You Don't Have Correct Access Right To Access This Api.", false, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/getSubject")
    public ResponseEntity<Object> getSubject(@RequestParam int subCategoryId, @RequestBody AdminDto adminDto, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(adminDto.getEmail(), adminDto.getPassword()));
            Admin admin1 = adminRepository.findByEmail(adminDto.getEmail());
            if (admin1.getRole().equalsIgnoreCase("ROLE_ADMIN")) {
                List<Subject> allSubject = subjectService.getAllSubject(subCategoryId);
                return ResponseHandler.response(allSubject, "All Subject Fetched Successfully.", true, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseHandler.response("", "You Don't Have Correct Access Right To Access This Api.", false, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/deleteCategory")
    public ResponseEntity<Object> deleteCategory(@RequestParam int categoryId, @RequestBody AdminDto adminDto, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(adminDto.getEmail(), adminDto.getPassword()));
            Admin admin1 = adminRepository.findByEmail(adminDto.getEmail());
            if (admin1.getRole().equalsIgnoreCase("ROLE_ADMIN")) {
                Category category = categoryService.deleteCategory(categoryId);
                return ResponseHandler.response(category, "Category Deleted Successfully.", true, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseHandler.response("", "You Don't Have Correct Access Right To Access This Api.", false, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/deleteSubCategory")
    public ResponseEntity<Object> deleteSubCategory(@RequestParam int subCategoryId, @RequestBody AdminDto adminDto, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(adminDto.getEmail(), adminDto.getPassword()));
            Admin admin1 = adminRepository.findByEmail(adminDto.getEmail());
            if (admin1.getRole().equalsIgnoreCase("ROLE_ADMIN")) {
                SubCategory subCategory = subCategoryService.deleteSubCategory(subCategoryId);
                return ResponseHandler.response(subCategory, "SubCategory Deleted Successfully.", true, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseHandler.response("", "You Don't Have Correct Access Right To Access This Api.", false, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/deleteSubject")
    public ResponseEntity<Object> deleteSubject(@RequestParam int subjectId, @RequestBody AdminDto adminDto, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(adminDto.getEmail(), adminDto.getPassword()));
            Admin admin1 = adminRepository.findByEmail(adminDto.getEmail());
            if (admin1.getRole().equalsIgnoreCase("ROLE_ADMIN")) {
                Subject subject = subjectService.deleteSubject(subjectId);
                return ResponseHandler.response(subject, "Subject Deleted Successfully.", true, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseHandler.response("", "You Don't Have Correct Access Right To Access This Api.", false, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/updateCategory")
    public ResponseEntity<Object> UpdateCategory(@RequestParam int categoryId, @RequestParam String email, @RequestParam String password, @RequestBody CategoryUpdateDto category, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
            Admin admin1 = adminRepository.findByEmail(email);
            if (admin1.getRole().equalsIgnoreCase("ROLE_ADMIN")) {
                //Category category1 = categoryService.getCategory(categoryId);
                Category category1 = categoryService.updateCategory(categoryId, category);
                return ResponseHandler.response(category1, "Category Updated Successfully.", true, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseHandler.response("", "You Don't Have Correct Access Right To Access This Api.", false, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/updateSubCategory")
    public ResponseEntity<Object> UpdateSubCategory(@RequestParam int subCategoryId, @RequestParam String email, @RequestParam String password, @RequestBody SubCategoryUpdateDto subCategory, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
            Admin admin1 = adminRepository.findByEmail(email);
            if (admin1.getRole().equalsIgnoreCase("ROLE_ADMIN")) {
                //Category category1 = categoryService.getCategory(categoryId);
                SubCategory subCategory1 = subCategoryService.updateSubCategory(subCategoryId, subCategory);
                return ResponseHandler.response(subCategory1, "Sub-Category Updated Successfully.", true, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseHandler.response("", "You Don't Have Correct Access Right To Access This Api.", false, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/updateSubject")
    public ResponseEntity<Object> UpdateSubject(@RequestParam int subjectId, @RequestParam String email, @RequestParam String password, @RequestBody SubjectUpdateDto subject, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
            Admin admin1 = adminRepository.findByEmail(email);
            if (admin1.getRole().equalsIgnoreCase("ROLE_ADMIN")) {
                //Category category1 = categoryService.getCategory(categoryId);
                Subject subject1 = subjectService.updateSubject(subjectId, subject);
                return ResponseHandler.response(subject1, "Subject Updated Successfully.", true, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseHandler.response("", "You Don't Have Correct Access Right To Access This Api.", false, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/getCategoryByNameOrId")
    public ResponseEntity<Object> getCategoryByNameOrId(@RequestParam(required = false) String name, @RequestParam(required = false) Integer id, @RequestBody AdminDto adminDto, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(adminDto.getEmail(), adminDto.getPassword()));
            Admin admin1 = adminRepository.findByEmail(adminDto.getEmail());
            if (admin1.getRole().equalsIgnoreCase("ROLE_ADMIN")) {
                List<Category> categoryByNameOrId = categoryService.getCategoryByNameOrId(name, id);
                return ResponseHandler.response(categoryByNameOrId, "All Category Fetched Successfully.", true, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseHandler.response("", "You Don't Have Correct Access Right To Access This Api.", false, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/getSubCategoryByNameOrId")
    public ResponseEntity<Object> getSubCategoryByNameOrId(@RequestParam(required = false) String name, @RequestParam(required = false) Integer id, @RequestBody AdminDto adminDto, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
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
        return ResponseHandler.response("", "You Don't Have Correct Access Right To Access This Api.", false, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/getSubjectByNameOrId")
    public ResponseEntity<Object> getSubjectByNameOrId(@RequestParam(required = false) String name, @RequestParam(required = false) Integer id, @RequestBody AdminDto adminDto, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
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
        return ResponseHandler.response("", "You Don't Have Correct Access Right To Access This Api.", false, HttpStatus.BAD_REQUEST);
    }
}