package com.yandex;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class CourierRegistrationSuccessTest {

    private CourierClient courierClient;
    private int courierId;
    private final CourierReg courierReg;

    public CourierRegistrationSuccessTest(CourierReg courierReg) {
        this.courierReg = courierReg;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][] {
                {CourierReg.getRandomLoginAndPasswordAndName()},
                {CourierReg.getRandomLoginAndPassword()}
        };
    }

    @Test
    @DisplayName("Проверка создания курьера")
    @Description("1. Можно создать курьера с полями Логин, Пароль, Имя"
            + "\n"+"2. Можно создать курьера с полями Логин и Пароль")
    public void wayToCreateCourier() {
        courierClient = new CourierClient();
        ValidatableResponse response = courierClient.create(courierReg);
        int actualStatusCode = response.extract().statusCode();
        boolean isCourierCreated = response.extract().path("ok");
        ValidatableResponse login = courierClient.login(CourierAuth.from(courierReg));
        courierId = login.extract().path("id");

        assertEquals("Статус-код должен быть 201", SC_CREATED, actualStatusCode);
        assertTrue("Ответ при регистрации курьера должен быть 'ok: true'", isCourierCreated);
        assertThat("ID курьера не должен быть пустым", courierId, notNullValue());
    }
    @After
    public void tearDown() {
        courierClient.delete(courierId);
    }
}
