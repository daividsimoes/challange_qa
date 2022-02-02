package br.com.challangeqa.automation.service;

import br.com.challangeqa.automation.constant.ConstantString;
import br.com.challangeqa.automation.model.response.authentication.AuthenticationResponse;
import br.com.challangeqa.automation.request.HeaderUtil;
import io.restassured.http.Header;
import io.restassured.http.Headers;

public class AuthenticationService extends AbstractService {

    private final String AUTH_ENDPOINT = "/auth";

    public AuthenticationResponse authenticate() {

        return authenticate(HeaderUtil.getHeader(
                new Header("x-app-id", ConstantString.X_APP_ID)
        ));
    }

    public AuthenticationResponse authenticateWithNoHeader() {

        return authenticate(HeaderUtil.getHeader());
    }

    public AuthenticationResponse authenticateInvalidHeader() {

        return authenticate(HeaderUtil.getHeader(
                new Header("x-app-id", fakerUtils.generateRandomUuid())
        ));
    }

    public String getToken() {

        return authenticate().getToken();
    }

    public boolean validateSchema() {

        return schemaUtils.schemaValidationObject(
                ConstantString.ROOT_JSON_DATA_PATH,
                ConstantString.AUTHENTICATION_JSON_RESPONSE_DATA_NAME,
                ConstantString.ROOT_JSON_SCHEMA_PATH,
                ConstantString.AUTHENTICATION_JSON_SCHEMA_NAME
        );
    }

    private AuthenticationResponse authenticate(Headers headers) {

        return requestUtil.post(
                headers,
                AuthenticationResponse.class,
                AUTH_ENDPOINT,
                ConstantString.AUTHENTICATION_JSON_RESPONSE_DATA_NAME
        );
    }
}
