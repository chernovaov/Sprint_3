package com.yandex;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;

import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static org.junit.Assert.assertEquals;

public class CourierNotRegisteredLoginTest {

    private CourierClient courierClient;
    private  CourierAuth courierAuth;

    @Before
    public void setUp() {
        courierClient = new CourierClient();
        courierAuth = CourierAuth.getRandomLoginAndPassword();
    }

    @Test
    @DisplayName("Проверка, что незарегистрированный курьер не может залогиниться")
    @Description("Проверка: status code=404, 'Учетная запись не найдена'")

    public void courierRandomLoginNotAllowed() {
        ValidatableResponse login = courierClient.login(courierAuth);
        final String expectedMessageCourierNotFound = "Учетная запись не найдена";
        int actualStatusCode = login.extract().statusCode();
        String actualMessage = login.extract().path("message");
        assertEquals("Статус-код должен быть 404", SC_NOT_FOUND, actualStatusCode);
        assertEquals("Неверный текст ошибки при логине незарегистрированного курьера" ,
                expectedMessageCourierNotFound,
                actualMessage);
    }
}
