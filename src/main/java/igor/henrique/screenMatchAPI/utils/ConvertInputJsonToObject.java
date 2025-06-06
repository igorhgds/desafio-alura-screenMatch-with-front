package igor.henrique.screenMatchAPI.utils;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvertInputJsonToObject {
    private ObjectMapper mapper = new ObjectMapper();

    public <T> T convertToObject(String json, Class<T> classe) {
        try{
            return mapper.readValue(json, classe);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
