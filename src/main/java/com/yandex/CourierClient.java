package com.yandex;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class CourierClient extends RestAssuredClient {
    private static final String COURIER_PATH = "api/v1/courier/";
    private static final String LOGIN_PATH = "api/v1/courier/login";

    @Step ("Зарегистрировать курьера")
    public ValidatableResponse create(CourierReg courierReg) {
        return given()
                .spec(getBaseSpec())
                .body(courierReg).log().all()
                .when()
                .post(COURIER_PATH)
                .then().log().all();
    }
    @Step ("Залогиниться")
    public ValidatableResponse login(CourierAuth courierAuth) {
        return given()
                .spec(getBaseSpec())
                .body(courierAuth).log().all()
                .when()
                .post(LOGIN_PATH)
                .then().log().all();
    }

    @Step ("Удалить курьера по id")
    public ValidatableResponse delete(int courierId) {
        return given()
                .spec(getBaseSpec()).log().all()
                .when()
                .delete(COURIER_PATH + courierId)
                .then().log().all();
    }
}
