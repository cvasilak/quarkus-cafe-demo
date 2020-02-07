package com.redhat.quarkus.cafe.barista.domain;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class BaristaTest {


    @Inject
    Barista barista;


    @Test
    public void testBlackCoffeeOrder() throws ExecutionException, InterruptedException {

        BeverageOrder beverageOrder = new BeverageOrder(EventType.BEVERAGE_ORDER_IN, UUID.randomUUID().toString(), UUID.randomUUID().toString(), "Jeremy", Item.COFFEE_BLACK);
        barista.make(beverageOrder).thenAccept(result -> {
            assertEquals(result, Status.READY);
        });
    }

    @Test
    public void testLatteOrder() throws ExecutionException, InterruptedException {

        BeverageOrder beverageOrder = new BeverageOrder(EventType.BEVERAGE_ORDER_IN, UUID.randomUUID().toString(), UUID.randomUUID().toString(), "Jeremy", Item.LATTE);
        barista.make(beverageOrder).thenAccept(result -> {
            assertEquals(result, Status.READY);
        });
    }
}
