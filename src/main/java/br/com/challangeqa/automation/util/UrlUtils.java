package br.com.challangeqa.automation.util;

import java.text.MessageFormat;

public class UrlUtils {

    private static final String HOST = "https://challenge-fielo-qa.herokuapp.com";

    public static String buildUrl(String endpoint) {

        return HOST.concat(endpoint);
    }
}
