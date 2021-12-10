package com.yandex;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.apache.http.HttpStatus.*;

public class CourierRegistrationDuplicateTest {

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
    @DisplayName("Проверка, что нельзя повторно создать такого же курьера")
    @Description("Проверка: status code=409, message='Этот логин уже используется'")
    public void courierCanNotBeCreatedTwice() {
        final String expectedMessageCourierAlreadyCreated = "Этот логин уже используется.";
        courierClient.create(courierReg);
        ValidatableResponse login = courierClient.login(CourierAuth.from(courierReg));
        courierId = login.extract().path("id");

        //еще раз создаем курьера с теми же рег.данными
        ValidatableResponse response = courierClient.create(courierReg);
        int actualStatusCode = response.extract().statusCode();
        String actualMessage = response.extract().path("message");

        assertEquals("Статус-код должен быть 409", SC_CONFLICT, actualStatusCode);
        assertEquals("Неверный текст ошибки при повторном создании курьера" ,
                expectedMessageCourierAlreadyCreated,
                actualMessage);
    }

}
