package com.MultipleTableFetch.Helper;

public class Utils {

    public static  Pair fetchCounts (int pageNo) {
        int min = 0;
        int max = 0;
        int fetchCount = 0;
        if (pageNo > 1) {
            min = (pageNo - 1) * 10;
            max = (pageNo) * 10;
            fetchCount = max - min;
        } else {
            min = 0;
            max = 10;
            fetchCount = max;
        }
        return  new Pair(min,fetchCount);
    }
}