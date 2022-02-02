package br.com.challangeqa.automation.util;

import java.text.MessageFormat;

public class UrlUtil {

    private static final String HOST = "https://challenge-fielo-qa.herokuapp.com";

    public static String buildUrl(String endpoint, Object... args) {

        return HOST.concat(MessageFormat.format(endpoint, args));
    }
}
