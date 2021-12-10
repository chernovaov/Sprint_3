package com.yandex;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;


import static org.apache.http.HttpStatus.SC_CREATED;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.*;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class OrderColorsTest {
    private final List<ScooterColor> color;
    private OrdersClient ordersClient;
    private int trackId;

    public OrderColorsTest(List<ScooterColor> color) {
        this.color = color;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][]{
                {List.of(ScooterColor.BLACK, ScooterColor.GREY)},
                {List.of(ScooterColor.BLACK)},
                {List.of(ScooterColor.GREY)},
                {List.of()}
        };
    }

    @Test
    @DisplayName("Проверка создания заказа при выборе разных цветов самоката")
    @Description("1. Заказ создается, если указаны два цвета: BLACK и GREY"
            + "\n"+ "2. Заказ создается, если указан цвет: BLACK"
            + "\n"+ "3. Заказ создается, если указан цвет: GREY"
            + "\n"+ "4. Заказ создается, если не указан цвет")
    public void orderCanBeCreated() {
        ordersClient = new OrdersClient();
        Orders order = Orders.getRandomOrder().setColor(color);
        ValidatableResponse response = ordersClient.create(order);
        int actualStatusCode = response.extract().statusCode();
        trackId = response.extract().path("track");
        assertEquals("Статус-код должен быть 201",SC_CREATED, actualStatusCode);
        assertThat("Трэк заказа не должен быть пустым", trackId, notNullValue());
    }
    @After
    public void tearDown() {
        ordersClient.cancel(trackId);
    }
}
