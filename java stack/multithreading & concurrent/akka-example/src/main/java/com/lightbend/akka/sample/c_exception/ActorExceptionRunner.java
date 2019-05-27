package com.lightbend.akka.sample.c_exception;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

import java.io.IOException;

/*
 * Смотрим как отрабатывает стандартная стратегия обработки ошибок
 */
public class ActorExceptionRunner {
    public static void main(String[] args) throws IOException {
        ActorSystem system = ActorSystem.create("testExceptionSystem");

        // создаем актора-супервизора
        ActorRef supervisingActor = system.actorOf(Props.create(SupervisingActor.class), "supervising-actor");

        // отправляем сообщение "failChild" актору-супервизору, отправителя не указываем
        supervisingActor.tell("failChild", ActorRef.noSender());
        //supervisingActor.tell("failChild", ActorRef.noSender());

        System.out.println(">>> Press ENTER to exit <<<");
        try {
            System.in.read();
        } finally {
            system.terminate();
        }
    }
}
