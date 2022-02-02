package br.com.challangeqa.automation.util;

import com.github.javafaker.Faker;

import java.util.Locale;

public class FakerUtil {

    private Faker faker;

    public FakerUtil(){

        this.faker = new Faker();
    }

    public String generateRandomHero() {

        return faker.superhero().name();
    }

    public String generateRandomUuid() {

        return faker.internet().uuid();
    }
}
