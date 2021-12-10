package com.yandex;


import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

public class CourierAuth {
    public String login;
    public String password;
    private static final int DATA_SIZE = 10;

    public CourierAuth(String login, String password) {
        this.login = login;
        this.password = password;
    }
    public CourierAuth() {
   }

    public static CourierAuth from(CourierReg courierReg) {
        return new CourierAuth(courierReg.login, courierReg.password);
    }

    public CourierAuth setLogin (String login) {
        this.login = login;
        return this;
    }

    public CourierAuth setPassword (String password) {
        this.password = password;
        return this;
    }

    public static CourierAuth getRandomLoginOnly() {
        return new CourierAuth().setLogin(randomAlphabetic(DATA_SIZE));
    }

    public static CourierAuth getRandomPasswordOnly() {
        return new CourierAuth().setPassword(randomAlphabetic(DATA_SIZE));
    }

    public static CourierAuth getRandomLoginAndPassword () {
        return new CourierAuth().setLogin(randomAlphabetic(DATA_SIZE)).setPassword(randomAlphabetic(DATA_SIZE));
    }
}
