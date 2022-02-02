package br.com.challangeqa.automation.model.request.hero;

import br.com.challangeqa.automation.model.Skills;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddHeroRequest implements Serializable {

    private String name;
    private Skills skills;
}
