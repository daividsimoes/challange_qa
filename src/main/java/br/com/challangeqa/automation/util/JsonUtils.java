package br.com.challangeqa.automation.util;

import com.google.gson.GsonBuilder;

public class JsonUtils {

    public static String convertToJson(Object obj) {

        return new GsonBuilder()
                .setPrettyPrinting()
                .create()
                .toJson(obj);
    }
}
