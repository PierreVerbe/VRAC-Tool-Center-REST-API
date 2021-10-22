package com.vrac.restservice.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public interface IntegrationTest {

    public static String ObjectToString(Object object) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonContent = mapper.writeValueAsString(object);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
