package com.yandex;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

public class CourierReg {
    public String login;
    public String password;
    public String firstName;
    private static final int DATA_SIZE = 10;

    public CourierReg(String login, String password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }

    public CourierReg() {

    }
    public static CourierReg getRandomLoginAndPasswordAndName() {
        final String login = randomAlphabetic(DATA_SIZE);
        final String password = randomAlphabetic(DATA_SIZE);
        final String firstName = randomAlphabetic(DATA_SIZE);
        return new CourierReg(login, password, firstName);
    }

    public CourierReg setLogin(String login) {
        this.login = login;
        return this;
    }

    public CourierReg setPassword(String password) {
        this.password = password;
        return this;
    }

    public CourierReg setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public static CourierReg getRandomLoginAndPassword() {
        return new CourierReg().setLogin(randomAlphabetic(DATA_SIZE)).setPassword(randomAlphabetic(DATA_SIZE));
    }

    public static CourierReg getRandomLoginOnly() {
            return new CourierReg().setLogin(randomAlphabetic(DATA_SIZE));
    }

    public static CourierReg getRandomPasswordOnly() {
        return new CourierReg().setPassword(randomAlphabetic(DATA_SIZE));
    }

    public static CourierReg getRandomFirstNameOnly() {
        return new CourierReg().setFirstName(randomAlphabetic(DATA_SIZE));
    }

    public static CourierReg getRandomLoginAndName() {
        return new CourierReg().setLogin(randomAlphabetic(DATA_SIZE)).setFirstName(randomAlphabetic(DATA_SIZE));
    }

    public static CourierReg getRandomPasswordAndName() {
        return new CourierReg().setPassword(randomAlphabetic(DATA_SIZE)).setFirstName(randomAlphabetic(DATA_SIZE));
    }
}