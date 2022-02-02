package br.com.challangeqa.automation.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Skills implements Serializable {

    private int power;
    private int velocity;
    private int combat;
}
