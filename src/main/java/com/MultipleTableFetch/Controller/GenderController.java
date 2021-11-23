package com.MultipleTableFetch.Controller;

import com.MultipleTableFetch.Entity.Gender;
import com.MultipleTableFetch.Service.GenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GenderController {

    @Autowired
    GenderService genderService;

    @GetMapping("getGender/{id}")
    public Gender getGender(@PathVariable int id) {
        Gender gender = genderService.getGender(id);
        return gender;
    }

    @GetMapping("/getGenders")
    public List<Gender> getAllGender() {
        return genderService.getAllGender();
    }

    @PostMapping("/saveGender")
    public Gender addGender(@RequestBody Gender gender) {
        genderService.addGender(gender);
        return gender;
    }

    @DeleteMapping("/deleteGender/{id}")
    public String deleteGender(@PathVariable int id) {
        genderService.deleteGender(id);
        return "Gender deleted form database id-" + id;
    }

    @PutMapping("/updateGender/{id}")
    public Gender updateGender(@PathVariable int id, @RequestBody Gender gender) {
        genderService.updateGender(id, gender);
        return gender;
    }
}

