package com.ohdoking.tddpractice;

import org.junit.Assert;
import org.junit.Test;

public class ReservationTest {

    @Test
    public void create(){
        Reservation reservation = new Reservation("1", "Jane");

        Assert.assertEquals(reservation.getId(), "1");
        Assert.assertEquals(reservation.getName(), "Jane");
    }
}
