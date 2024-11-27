package utils;

import java.util.Objects;

public class DynamicUrl {
    public static String getDynamicUrl(String baseUrl, String endpoint, String employeeId, String userName){
       String url = Objects.equals(endpoint, "employee") ? baseUrl+"/"+endpoint+"/"+employeeId+"/"+userName:
                                 baseUrl+"/"+employeeId+"/"+userName;
        return url;
    }
}
