package com.yandex;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

import java.util.*;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertEquals;

public class OrdersListGettingTest {
    int randomLimit = new Random().nextInt(30) +1;

    @Test
    @DisplayName("Проверка получения списка заказов")
    @Description("Проверка: status code=200, id заказов не пустые")
    public void ordersCanBeReturned() {

        OrdersClient ordersClient = new OrdersClient();
        ValidatableResponse response = ordersClient.getOrdersList(randomLimit);
        List<HashMap<String, Object>> orders = response.extract().jsonPath().getList("orders");
        int actualStatusCode = response.extract().statusCode();

        assertEquals("Статус-код должен быть 200", SC_OK, actualStatusCode);
        assertThat("Количество заказов в ответе не совпадает с указанным лимитом", orders, hasSize(randomLimit));
        orders.forEach(order -> assertThat("ID заказа не должен быть нулевым", order.get("id"), notNullValue()));

    }
}
