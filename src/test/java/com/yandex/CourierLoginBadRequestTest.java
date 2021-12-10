package com.yandex;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class CourierLoginBadRequestTest {
    private final CourierAuth courierAuth;

    public CourierLoginBadRequestTest(CourierAuth courierAuth) {
        this.courierAuth = courierAuth;
    }
    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][] {
                {CourierAuth.getRandomLoginOnly()},
                {CourierAuth.getRandomPasswordOnly()}
        };
    }

    @Test
    @DisplayName("Проверка невозможности авторизации только по Логину или только по Паролю")
    @Description("1. Нельзя залогиниться с одним полем Логин, ожидается ошибка 400"
            + "\n"+ "2. Нельзя залогиниться с одним полем Пароль, ожидается ошибка 400")

    public void invalidLoginIsNotAllowed() {
        final String expectedMessage = "Недостаточно данных для входа";
        ValidatableResponse response = new CourierClient().login(courierAuth);
        int actualStatusCode = response.extract().statusCode();
        assertEquals("Статус-код должен быть 400",
                       SC_BAD_REQUEST, actualStatusCode);
        assertEquals("В сообщении об ошибке другой текст",
                expectedMessage,
                response.extract().path("message"));
    }
}
