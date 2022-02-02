package br.com.challangeqa.automation.enums;

import lombok.Getter;

@Getter
public enum Messages {

    UNAUTHORIZED("Unauthorized");

    private String message;


    Messages(String message) {

        this.message = message;
    }
}
