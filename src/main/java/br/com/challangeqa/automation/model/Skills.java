package br.com.challangeqa.automation.model;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Skills implements Serializable {

    private int power;
    private int velocity;
    private int combat;
}
