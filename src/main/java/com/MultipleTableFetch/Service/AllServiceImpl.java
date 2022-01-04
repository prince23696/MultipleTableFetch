package com.MultipleTableFetch.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

@Service
public class AllServiceImpl implements AllService {
    @Override
    public Boolean checkImage(MultipartFile file) {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("image/jpeg");
        arrayList.add("image/png");
        arrayList.add("image/gif");
        System.out.println(file.getContentType());
        boolean result = file.getContentType().equals(arrayList.get(0));
        boolean result1 = file.getContentType().equals(arrayList.get(1));
        boolean result2 = file.getContentType().equals(arrayList.get(2));
        if (result == true)
            return result;
        else if (result1 == true)
            return result1;
        else if (result2 == true)
            return result2;
        else
            return false;
    }
}