package com.codesurfers.duthealthcareclinic.utils.helpers;

import com.codesurfers.duthealthcareclinic.utils.pojo.AppStat;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.util.ArrayList;
import java.util.List;

public class Helper {
    private static int getRandomNumber() {
        return (int) Math.floor(Math.random() * (1 - 9 + 1)) + 1;
    }

    public static String generateOtp() {
        return getRandomNumber() + "" + getRandomNumber() + "" + getRandomNumber() + "" + getRandomNumber() + "" + getRandomNumber();
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static List<AppStat> cleanStat(List<Object[]> results) {
        List<AppStat> stats = new ArrayList<>();
        for (Object[] row : results) {
            AppStat ss = new AppStat((String) row[0], (Long) row[1]);
            stats.add(ss);
        }
        return  stats;
    }
}
