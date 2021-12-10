package com.yandex;
import org.apache.commons.lang3.RandomStringUtils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

public class Orders {
    public String firstName;
    public String lastName;
    public String address;
    public int metroStation;
    public String phone;
    public int rentTime;
    public String deliveryDate;
    public String comment;
    public List<ScooterColor> color;

    public Orders(String firstName, String lastName, String address, int metroStation, String phone, int rentTime,
                                 String deliveryDate, String comment, List<ScooterColor> color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
    }

    public Orders setColor(List <ScooterColor> color) {
        this.color = color;
        return this;
    }

    public static Orders getRandomOrder() {
        final String firstName = randomAlphabetic(10);
        final String lastName = randomAlphabetic(10);
        final String address = randomAlphabetic(15);
        final int metroStation = new Random().nextInt(400)+1;
        final String phone = String.format("+79%s",
                RandomStringUtils.random(9,"1234567890"));
        final int rentTime = new Random().nextInt(7)+1; // аренда от 1 до 7 дней
        final String deliveryDate = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        final String comment = randomAlphabetic(10);
        final List <ScooterColor> color = new ArrayList<>();
        return new Orders(firstName,
                lastName,
                address,
                metroStation,
                phone,
                rentTime,
                LocalDate.parse(deliveryDate).plusDays(1).toString(),//завтрашняя дата
                comment,
                color);

    }

}
