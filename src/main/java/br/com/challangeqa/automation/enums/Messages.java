package br.com.challangeqa.automation.enums;

import lombok.Getter;

@Getter
public enum Messages {

    HERO_ALREADY_EXISTS("This hero already exists"),
    UNAUTHORIZED("Unauthorized");

    private String message;


    Messages(String message) {

        this.message = message;
    }
}
