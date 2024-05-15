package Restwrapper;

import Enum.EndPoints;

import java.util.Map;
import java.util.Properties;

import static Helpers.PropertiesLoader.readPropertyFile;
import static io.restassured.RestAssured.given;

public class RestWrapper {

    private static final Properties urlProps = readPropertyFile("ConfigData/CommonData.properties");

    public static <T> T restGet(EndPoints endpoint, Map<String, String> headers, Class<T> responseClass, String statusCode) {
        return given()
                .relaxedHTTPSValidation()
                .headers(headers)
                .when()
                .get(System.getProperty("url", urlProps.getProperty("url")).concat(endpoint.getValue()))
                .then().log().body().statusCode(Integer.parseInt(statusCode)).and()
                .extract()
                .as(responseClass);
    }
    public static <T> T restGetWithQueryParams(EndPoints endpoint, Map<String, String> headers,Map<String, Integer> queryParams, Class<T> responseClass, String statusCode) {
        return given()
                .relaxedHTTPSValidation()
                .headers(headers)
                .queryParams(queryParams)
                .when()
                .get(System.getProperty("url", urlProps.getProperty("url")).concat(endpoint.getValue()))
                .then().log().body().statusCode(Integer.parseInt(statusCode)).and()
                .extract()
                .as(responseClass);
    }

    public static <T> T restPost(EndPoints endpoint, Map<String, String> headers, Object bodyData, Class<T> responseClass, String statusCode) {
        return given()
                .relaxedHTTPSValidation()
                .headers(headers)
                .body(bodyData)
                .log().body()
                .when()
                .post(System.getProperty("url", urlProps.getProperty("url")).concat(endpoint.getValue()))
                .then().log().body().statusCode(Integer.parseInt(statusCode)).and()
                .extract()
                .as(responseClass);
    }

    public static <T> T restPut(EndPoints endpoint, Map<String, String> headers, String UserId, Object bodyData, Class<T> responseClass, String statusCode) {
        return given()
                .relaxedHTTPSValidation()
                .headers(headers)
                .body(bodyData)
                .log().body()
                .when()
                .put(System.getProperty("url", urlProps.getProperty("url")).concat(endpoint.getValue()+"/"+UserId))
                .then()
                .log().body().statusCode(Integer.parseInt(statusCode)).and()
                .extract()
                .as(responseClass);
    }

    public static int restDelete(EndPoints endpoint, Map<String, String> headers,String userId) {
        return given()
                .relaxedHTTPSValidation()
                .headers(headers)
                .when()
                .delete(System.getProperty("url", urlProps.getProperty("url")).concat(endpoint.getValue()+"/"+userId))
                .then()
                .log().body().
                extract().statusCode();
    }
}
