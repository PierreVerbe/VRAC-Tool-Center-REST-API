package com.vrac.restservice.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;

public interface UtilTest {

    static String ObjectToString(Object object) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            mapper.setDateFormat(simpleDateFormat);

            return mapper.writeValueAsString(object);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
