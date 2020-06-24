package com.zxw.childhood.jojoauth;

import com.fasterxml.jackson.core.type.TypeReference;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.security.web.savedrequest.SavedCookie;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author zxw
 * @date 2020-06-01 15:27
 */
public class JacksonTest {
    public static void main(String[] args) {

        ObjectMapper ob=new ObjectMapper();
        try {
            JsonNode jsonNode = ob.readTree("[\"java.util.ArrayList\",[{\"@class\":\"org.springframework.security.web.savedrequest.SavedCookie\",\"name\":\"Idea-86d3a62a\",\"value\":\"601c0fce-08e8-4056-b02b-af68580db947\",\"comment\":null,\"domain\":null,\"maxAge\":-1,\"path\":null,\"secure\":false,\"version\":0,\"cookie\":{\"@class\":\"javax.servlet.http.Cookie\",\"name\":\"Idea-86d3a62a\",\"value\":\"601c0fce-08e8-4056-b02b-af68580db947\",\"version\":0,\"comment\":null,\"domain\":null,\"maxAge\":-1,\"path\":null,\"secure\":false,\"httpOnly\":false}}]]");
            ObjectMapper ob1=new ObjectMapper();
          /*  ob1.convertValue(jsonNode,new TypeReference<ArrayList<SavedCookie>>());*/
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
