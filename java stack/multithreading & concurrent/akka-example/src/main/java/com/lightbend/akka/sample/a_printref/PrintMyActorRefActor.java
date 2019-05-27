package com.lightbend.akka.sample.a_printref;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;

// Класс актора
class PrintMyActorRefActor extends AbstractActor {


    // Метод который отрабатывает когда актор принимает сообщение
    @Override
    public Receive createReceive() {
        // сработает в ответ на сообщение - "printit"
        return receiveBuilder()
                .matchEquals("printit", p -> {
                    // создаёт актора, когда получает сообщение "printit"
                    ActorRef secondRef = getContext().actorOf(Props.empty(), "second-actor");
                    System.out.println("Second: " + secondRef);
                })
                .build();
    }
}