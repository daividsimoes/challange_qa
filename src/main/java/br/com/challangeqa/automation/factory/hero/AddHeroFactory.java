package br.com.challangeqa.automation.factory.hero;

import br.com.challangeqa.automation.model.Skills;
import br.com.challangeqa.automation.model.request.hero.AddHeroRequest;
import br.com.challangeqa.automation.util.FakerUtils;

public class AddHeroFactory {

    private FakerUtils fakerUtils;

    public AddHeroFactory() {

        fakerUtils = new FakerUtils();
    }

    public AddHeroRequest buildHero(int power, int velocity, int combat) {

        return AddHeroRequest.builder()
                .name(fakerUtils.generateRandomHero())
                .skills(buildSkills(power, velocity, combat))
                .build();
    }

    private Skills buildSkills(int power, int velocity, int combat) {

        return Skills.builder()
                .power(power)
                .velocity(velocity)
                .combat(combat)
                .build();
    }
}
