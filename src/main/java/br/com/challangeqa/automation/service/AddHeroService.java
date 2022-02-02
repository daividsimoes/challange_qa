package br.com.challangeqa.automation.service;

import br.com.challangeqa.automation.constant.ConstantString;
import br.com.challangeqa.automation.model.response.hero.HeroResponse;
import br.com.challangeqa.automation.request.HeaderUtil;
import io.restassured.http.Header;

public class AddHeroService extends AbstractService {

    private final String HEROES_ENDPOINT = "/heroes";

    public HeroResponse addHero(String token,Object body) {

        return requestUtil.post(
                HeaderUtil.getHeader(new Header("x-access-token", token)),
                body,
                HeroResponse.class,
                HEROES_ENDPOINT,
                ConstantString.ADD_HERO_JSON_RESPONSE_DATA_NAME
        );
    }

    public boolean validateSchema() {

        return schemaUtils.schemaValidationObject(
                ConstantString.ROOT_JSON_DATA_PATH,
                ConstantString.ADD_HERO_JSON_RESPONSE_DATA_NAME,
                ConstantString.ROOT_JSON_SCHEMA_PATH,
                ConstantString.ADD_HERO_JSON_SCHEMA_NAME
        );
    }
}
