package Helpers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.hamcrest.MatcherAssert;

import java.io.*;


public class JsonUtils {

    public static void ValidateClassToResponseSchema(Object javaClass, String filePath) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(javaClass);
        MatcherAssert.assertThat(json, JsonSchemaValidator.matchesJsonSchema(new File(filePath)));
    }

    public static <T>T  mapJsonToJavaClass(Class<T> javaClass, String filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File(filePath), javaClass);
    }
    public static String getStringValueFromJsonFile(String keyName, String filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(new File(filePath));
        return jsonNode.get(keyName).asText();
    }
}
