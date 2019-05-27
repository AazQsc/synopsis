package com.lightbend.akka.sample.c_exception;

import akka.actor.AbstractActor;

// Супервизируемый актор
public class SupervisedActor extends AbstractActor {
    /*
     * Обратите внимание на перезапуск актора
     *
     * на самом деле, вызываются методы preRestart() и postRestart(),
     * которые, если не переопределены, по умолчанию делегируют к postStop() и preStart().
     */
    @Override
    public void preStart() {
        System.out.println("supervised actor started");
    }

    @Override
    public void postStop() {
        System.out.println("supervised actor stopped");
    }

    @Override
    public Receive createReceive() {
        // если получили сообщение fail - кидаем исключение
        return receiveBuilder()
                .matchEquals("fail", f -> {
                    System.out.println("supervised actor fails now");
                    throw new Exception("I failed!");
                })
                .build();
    }
}
