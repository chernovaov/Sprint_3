package com.yandex;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class CourierLoginSuccessTest {
    private CourierClient courierClient;
    private int courierId;
    private CourierReg courierReg;

    @Before
    public void setUp() {
        courierReg = CourierReg.getRandomLoginAndPasswordAndName();
        courierClient = new CourierClient();
    }
    @After
    public void tearDown() {
        courierClient.delete(courierId);
    }

    @Test
    @DisplayName("Проверка, что зарегистрированный курьер может залогиниться")
    @Description("Проверка: status code=200, id курьера не пустой")

    public void courierCanLogin() {
        courierClient.create(courierReg);
        ValidatableResponse login = courierClient.login(CourierAuth.from(courierReg));
        int actualStatusCode = login.extract().statusCode();
        courierId=login.extract().path("id");
        assertEquals("Статус-код должен быть 200", SC_OK, actualStatusCode);
        assertThat("ID курьера не должен быть пустым", courierId, notNullValue());
    }
}
