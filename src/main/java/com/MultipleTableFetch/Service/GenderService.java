package com.MultipleTableFetch.Service;

import com.MultipleTableFetch.Entity.Gender;

import java.util.List;

public interface GenderService {

    public List<Gender> getAllGender();

    public Gender getGender(int id);

    public String addGender(Gender gender);

    public Gender updateGender(int id, Gender gender);

    public String deleteGender(int id);

}
