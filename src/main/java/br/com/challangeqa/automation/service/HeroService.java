package br.com.challangeqa.automation.service;

import br.com.challangeqa.automation.constant.ConstantString;
import br.com.challangeqa.automation.model.response.ResponseObject;
import br.com.challangeqa.automation.model.response.hero.HeroResponse;
import br.com.challangeqa.automation.request.HeaderUtil;
import io.restassured.http.Header;

import java.util.List;

public class HeroService extends AbstractService {

    private final String HEROES = "/heroes";

    public List<HeroResponse> getHeroesList(String token) {

        return requestUtil.getList(
                HeaderUtil.getHeader(new Header("x-access-token", token)),
                HeroResponse.class,
                HEROES,
                ConstantString.HERO_LIST_JSON_RESPONSE_DATA_NAME
        );
    }

    public ResponseObject getHeroesListWithNoHeader() {

        return getList();
    }

    public ResponseObject getHeroesListWithInvalidHeader() {

        return getList(new Header("x-access-token", fakerUtils.generateRandomUuid()));
    }

    public boolean validateSchema() {

        return schemaUtils.schemaValidationList(
                ConstantString.ROOT_JSON_DATA_PATH,
                ConstantString.HERO_LIST_JSON_RESPONSE_DATA_NAME,
                ConstantString.ROOT_JSON_SCHEMA_PATH,
                ConstantString.HERO_LIST_JSON_SCHEMA_NAME
        );
    }

    private ResponseObject getList(Header... headers) {

        return requestUtil.get(
                HeaderUtil.getHeader(headers),
                ResponseObject.class,
                HEROES,
                ConstantString.HERO_LIST_JSON_RESPONSE_DATA_NAME
        );
    }
}
