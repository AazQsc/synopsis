package com.lightbend.akka.sample.quickstart;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/*
 * Actor - это класс являющийся наследником AbstractActor
 */
public class Printer extends AbstractActor {

    private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    /*
     * Статический метод инициализации
     */
    static public Props props() {
        return Props.create(Printer.class, () -> new Printer());
    }

    // Класс который умеет хранить строку?!
    //#printer-messages
    static public class Greeting {
        public final String message;

        public Greeting(String message) {
            this.message = message;
        }
    }
    //#printer-messages

    /*
     * Метод который отрабатывает когда актор принимает сообщение
     */
    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(Greeting.class, greeting -> {
                    log.info(greeting.message);
                })
                .build();
    }

}
