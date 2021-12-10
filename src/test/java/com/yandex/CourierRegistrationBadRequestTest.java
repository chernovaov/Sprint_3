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
public class CourierRegistrationBadRequestTest {

    private final CourierReg courierReg;

    public CourierRegistrationBadRequestTest(CourierReg courierReg) {
        this.courierReg = courierReg;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][] {
                {CourierReg.getRandomLoginOnly()},
                {CourierReg.getRandomPasswordOnly()},
                {CourierReg.getRandomFirstNameOnly()},
                {CourierReg.getRandomLoginAndName()},
                {CourierReg.getRandomPasswordAndName()}
        };
    }

    @Test
    @DisplayName("Проверка валидации при регистрации курьера")
    @Description("1. Нельзя зарегистрировать курьера только с полем Логин"
            + "\n"+ "2. Нельзя зарегистрировать курьера только с полем Пароль"
            + "\n"+ "3. Нельзя зарегистрировать курьера только с полем Имя"
            + "\n"+ "4. Нельзя зарегистрировать курьера только с полями Логин и Имя, но без поля Пароль"
            + "\n"+ "5. Нельзя зарегистрировать курьера только с полями Пароль и Имя, но без поля Логин")

    public void invalidCourierIsNotAllowed() {
        final String expectedMessage = "Недостаточно данных для создания учетной записи";
        ValidatableResponse response = new CourierClient().create(courierReg);
        String actualMessage = response.extract().path("message");

        assertEquals("Статус-код должен быть 400", SC_BAD_REQUEST, response.extract().statusCode());
        assertEquals("В сообщении об ошибке другой текст",
                expectedMessage, actualMessage);
    }
}
