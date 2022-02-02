package br.com.challangeqa.automation.request;

import br.com.challangeqa.automation.model.response.ResponseObject;
import br.com.challangeqa.automation.util.JsonUtils;
import br.com.challangeqa.automation.util.UrlUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

import java.text.MessageFormat;

import static io.restassured.RestAssured.given;

@Slf4j
public class RequestUtil {

    private String url;

    public <T extends ResponseObject> T post(Headers headers, Object body, Class<T> clazz,
                                             String endpoint) {

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

        return convertResponseToObject(response, clazz);
    }

    public <T extends ResponseObject> T post(Headers headers, Class<T> clazz, String endpoint) {

        url = UrlUtils.buildUrl(endpoint);

        log.info("REQUEST -> Executing POST on: {}", url);
        log.info("REQUEST -> Headers: {}", JsonUtils.convertToJson(headers.asList()));

        RestAssured.useRelaxedHTTPSValidation();

        Response response = given()
                .contentType(ContentType.JSON)
                .headers(headers)
                .post(url);

        log.info("RESPONSE -> StatusCode: {}", response.getStatusCode());
        log.info("RESPONSE -> Time: {}", response.getTime());
        log.info("RESPONSE -> Body: {}", response.getBody().asString());

        return convertResponseToObject(response, clazz);
    }

    public <T extends ResponseObject> T get(Headers headers, Class<T> clazz, String endpoint) {

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

        return convertResponseToObject(response, clazz);
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

        if (responseConverted != null)
            responseConverted.setStatusCode(response.getStatusCode());

        return responseConverted;
    }
}
