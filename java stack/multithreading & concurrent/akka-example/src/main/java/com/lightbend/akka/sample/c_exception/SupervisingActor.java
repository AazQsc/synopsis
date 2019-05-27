package com.lightbend.akka.sample.c_exception;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;

// актор супервизор
public class SupervisingActor extends AbstractActor {
    // ооздаём супервизируемого актора
    private ActorRef child = getContext().actorOf(Props.create(SupervisedActor.class), "supervised-actor");

    @Override
    public Receive createReceive() {
        /*
         * если получили сообщение "failChild"
         * генерим новое сообщение "fail" и отправляем его супервизируемому актору, указав себя в отправителях
         */
        return receiveBuilder()
                .matchEquals("failChild", f -> {
                    child.tell("fail", getSelf());
                })
                .build();
    }
}
