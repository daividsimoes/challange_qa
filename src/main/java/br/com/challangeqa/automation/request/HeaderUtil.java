package br.com.challangeqa.automation.request;

import io.restassured.http.Header;
import io.restassured.http.Headers;

public class HeaderUtil {

    public static Headers getHeader(Header... header) {

        return new Headers(header);
    }
}
