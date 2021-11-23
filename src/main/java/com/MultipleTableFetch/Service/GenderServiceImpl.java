package com.MultipleTableFetch.Service;

import com.MultipleTableFetch.Entity.Gender;
import com.MultipleTableFetch.Repository.GenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenderServiceImpl implements GenderService {

    @Autowired
    GenderRepository genderRepository;

    @Override
    public List<Gender> getAllGender() {
        return genderRepository.findAll();
    }

    @Override
    public Gender getGender(int id) {
        return genderRepository.findById(id).get();
    }

    @Override
    public String addGender(Gender gender) {
        genderRepository.save(gender);
        return "Gender Added Successfully";
    }

    @Override
    public Gender updateGender(int id, Gender gender) {
        Gender gender1 = genderRepository.findById(id).get();
        gender1.setGender(gender.getGender());
        genderRepository.save(gender1);
        return gender1;
    }

    @Override
    public String deleteGender(int id) {
        genderRepository.deleteById(id);
        return "Record Deleted Successfully";
    }
}
