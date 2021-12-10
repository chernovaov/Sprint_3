package com.yandex;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class OrdersClient extends RestAssuredClient {
    private static final String ORDERS_PATH = "api/v1/orders";
    private static final String CANCEL_PATH = "api/v1/orders/cancel";

   @Step("Создать заказ")
    public ValidatableResponse create(Orders order) {
        return given()
                .spec(getBaseSpec())
                .body(order).log().all()
                .when()
                .post(ORDERS_PATH)
                .then().log().all();
    }
    @Step("Получить список заказов")
    public ValidatableResponse getOrdersList(int limit) {
        return given()
                .spec(getBaseSpec())
                .queryParam("limit",limit)
                .log().all()
                .when()
                .get(ORDERS_PATH)
                .then().log().all();
    }
    @Step("Отменить заказ")
    public ValidatableResponse cancel(int trackId) {
        return given()
                .spec(getBaseSpec())
                .body(String.format("{\"track\":% d}",trackId)).log().all()
                .when()
                .put(CANCEL_PATH)
                .then().log().all();
    }
}
