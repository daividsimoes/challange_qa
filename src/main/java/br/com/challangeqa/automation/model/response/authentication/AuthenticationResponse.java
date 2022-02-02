package br.com.challangeqa.automation.model.response.authentication;

import br.com.challangeqa.automation.model.response.ResponseObject;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class AuthenticationResponse extends ResponseObject implements Serializable {

    boolean auth;
    String token;
}
