package myProject.Coupons_Project.utils;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class RestTmpltUtils {
    public static HttpHeaders getHeader (ResponseEntity<String> response){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", response.getHeaders().getFirst("Authorization"));
        return httpHeaders;
    }

    public static HttpEntity<?> getHttpEntity (Object object, HttpHeaders header){
       return new HttpEntity<>(object, header);
    }

    public static HttpEntity<?> getHttpEntity (Object object){
        return new HttpEntity<>(object);
    }

    public static HttpEntity<?> getHttpEntity (HttpHeaders header){
        return new HttpEntity<>(header);
    }

    public static Map<String,String> getParams (String pathVariable, Object object){
        Map <String, String> params = new HashMap<>();
        params.put(pathVariable, String.valueOf(object));
        return params;
    }


}
