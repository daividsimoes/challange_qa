package br.com.challangeqa.automation.model.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ResponseObject implements Serializable {

    private int statusCode;

    private String message;
}
