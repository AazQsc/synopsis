package com.lightbend.akka.sample.d_patterns;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/*
 * Iot - рекомендуется создавать свой актор верхнего уровня,
 * как абстракцию верхнего уровня - это наша система
 */
public class IotSupervisor extends AbstractActor {
    // Встроенный в акка логер
    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    /*
     * Рекомендуется создавать акторы через статический метод с возвращаемым типом Props
     */
    public static Props props() {
        return Props.create(IotSupervisor.class);
    }

    @Override
    public void preStart() {
        log.info("IoT Application started");
    }

    @Override
    public void postStop() {
        log.info("IoT Application stopped");
    }

    // No need to handle any messages
    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .build();
    }

}