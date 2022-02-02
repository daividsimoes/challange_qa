package br.com.challangeqa.automation.request;

import br.com.challangeqa.automation.constant.ConstantString;
import br.com.challangeqa.automation.model.response.ResponseObject;
import br.com.challangeqa.automation.util.JsonUtils;
import br.com.challangeqa.automation.util.SchemaUtils;
import br.com.challangeqa.automation.util.UrlUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

@Slf4j
public class RequestUtil {

    private String url;
    private SchemaUtils schemaUtils;

    public RequestUtil() {

        schemaUtils = new SchemaUtils();
    }

    public <T extends ResponseObject> T post(Headers headers, Object body, Class<T> clazz,
                                             String endpoint, String jsonResponseName) {

        url = UrlUtils.buildUrl(endpoint);

        log.info("REQUEST -> Executing POST on: {}", url);
        log.info("REQUEST -> Headers: {}", JsonUtils.convertToJson(headers.asList()));
        log.info("REQUEST -> BODY: {}", JsonUtils.convertToJson(body));

        RestAssured.useRelaxedHTTPSValidation();
        Response response = given()
                .contentType(ContentType.JSON)
                .headers(headers)
                .body(body)
                .post(url);

        log.info("RESPONSE -> StatusCode: {}", response.getStatusCode());
        log.info("RESPONSE -> Time: {}", response.getTime());
        log.info("RESPONSE -> Body: {}", response.getBody().asString());

        T obj = convertResponseToObject(response, clazz);
        schemaUtils.createJsonDataFile(obj, ConstantString.ROOT_JSON_DATA_PATH, jsonResponseName);

        if (obj != null)
            obj.setStatusCode(response.getStatusCode());

        return obj;
    }

    public <T extends ResponseObject> T post(Headers headers, Class<T> clazz, String endpoint,
                                             String jsonResponseName) {


        return post(headers, "", clazz, endpoint, jsonResponseName);

    }

    public <T extends ResponseObject> T get(Headers headers, Class<T> clazz, String endpoint,
                                            String jsonResponseName) {

        url = UrlUtils.buildUrl(endpoint);

        log.info("REQUEST -> Executing GET on: {}", url);
        log.info("REQUEST -> Headers: {}", JsonUtils.convertToJson(headers.asList()));

        Response response = given()
                .contentType(ContentType.JSON)
                .headers(headers)
                .get(url);

        log.info("RESPONSE -> StatusCode: {}", response.getStatusCode());
        log.info("RESPONSE -> Time: {}", response.getTime());
        log.info("RESPONSE -> Body: {}", response.getBody().asString());

        T obj = convertResponseToObject(response, clazz);
        schemaUtils.createJsonDataFile(obj, ConstantString.ROOT_JSON_DATA_PATH, jsonResponseName);

        if (obj != null)
            obj.setStatusCode(response.getStatusCode());

        return obj;
    }

    public <T extends ResponseObject> List<T> getList(Headers headers, Class<T> clazz, String endpoint,
                                                      String jsonResponseName) {

        url = UrlUtils.buildUrl(endpoint);

        log.info("REQUEST -> Executing GET on: {}", url);
        log.info("REQUEST -> Headers: {}", JsonUtils.convertToJson(headers.asList()));

        Response response = given()
                .contentType(ContentType.JSON)
                .headers(headers)
                .get(url);

        log.info("RESPONSE -> StatusCode: {}", response.getStatusCode());
        log.info("RESPONSE -> Time: {}", response.getTime());
        log.info("RESPONSE -> Body: {}", response.getBody().asString());

        List<LinkedHashMap> heroList = convertResponseToList(response);
        schemaUtils.createJsonDataFile(heroList, ConstantString.ROOT_JSON_DATA_PATH, jsonResponseName);

        List<T> dataList = new ArrayList<>();
        for(LinkedHashMap map : heroList) {

            ObjectMapper mapper = new ObjectMapper();
            T pojo = mapper.convertValue(map, clazz);
            pojo.setStatusCode(response.getStatusCode());
            dataList.add(pojo);
        }

        return dataList;
    }

    private <T extends ResponseObject> T convertResponseToObject(Response response, Class<T> clazz) {

        T responseConverted = null;

        if (!response.getBody().asString().isEmpty()) {

            try {

                log.info("Converting response to class: {}", clazz);
                responseConverted = response.getBody().as(clazz);

            } catch (Exception e) {

                throw new RuntimeException(
                        MessageFormat.format("Failure to convert response -> {0}\nException Message -> {1}",
                                response.getBody().asString(), e.getMessage())
                );
            }
        }

        return responseConverted;
    }

    private List convertResponseToList(Response response) {

        List responseConverted = null;
        if (!response.getBody().asString().isEmpty()) {

            try {

                log.info("Converting response to class: {}", List.class);
                responseConverted = response.getBody().as(List.class);

            } catch (Exception e) {

                throw new RuntimeException(
                        MessageFormat.format("Failure to convert response -> {0}\nException Message -> {1}",
                                response.getBody().asString(), e.getMessage())
                );
            }
        }

        return responseConverted;
    }
}
