package com.lightbend.akka.sample.b_lifecycle;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

// наблюдаем за работой методов preStart() и postStop()
public class LifeCycleRunner {

    public static void main(String[] args) throws java.io.IOException {
        ActorSystem system = ActorSystem.create("testLCSystem");

        // создали первый актор
        ActorRef first = system.actorOf(Props.create(StartStopActor1.class), "first");
        // отправим первому актору сообщение "stop", отправитель не указан
        first.tell("stop", ActorRef.noSender());

        System.out.println(">>> Press ENTER to exit <<<");
        try {
            System.in.read();
        } finally {
            system.terminate();
        }
    }
}
