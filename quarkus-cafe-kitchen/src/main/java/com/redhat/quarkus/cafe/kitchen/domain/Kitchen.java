package com.redhat.quarkus.cafe.kitchen.domain;

import com.redhat.quarkus.cafe.kitchen.infrastructure.RESTClient;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.Instant;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@ApplicationScoped
public class Kitchen {

    static final Logger logger = Logger.getLogger(Kitchen.class.getName());

    @Inject
    @RestClient
    RESTClient restClient;

    public void orderIn(OrderEvent orderInEvent) {

        make(orderInEvent).thenAccept(o -> {
            restClient.orderUp(o);
        });
    }

    public CompletionStage<OrderEvent> make(OrderEvent orderIn) {

        logger.info("Received order: " + orderIn.toString());
        logger.info("Sending order at " + Instant.now().toString() + " " + orderIn.toString());

        return CompletableFuture.supplyAsync(() -> {

            switch (orderIn.item) {
                case COOKIE:
                    return prepare(orderIn, 2);
                case MUFFIN:
                    return prepare(orderIn, 3);
                case PANINI:
                    return prepare(orderIn, 10);
                default:
                    return prepare(orderIn, 5);
            }
        });
    }

    private OrderEvent prepare(final OrderEvent orderIn, int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        OrderEvent retVal = new OrderEvent(orderIn.orderId, orderIn.name, orderIn.item, orderIn.itemId, EventType.KITCHEN_ORDER_UP);
        logger.debug("returning: " + retVal.toString());
        return retVal;
    }

    private void prepare(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }


}
