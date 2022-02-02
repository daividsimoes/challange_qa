package br.com.challangeqa.automation.model.response.hero;

import br.com.challangeqa.automation.model.Skills;
import br.com.challangeqa.automation.model.response.ResponseObject;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class HeroResponse extends ResponseObject implements Serializable {

    private String id;
    private String name;
    private Skills skills;
}
