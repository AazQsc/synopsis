package com.lightbend.akka.sample.quickstart;

import java.io.IOException;

import com.lightbend.akka.sample.quickstart.Greeter.Greet;
import com.lightbend.akka.sample.quickstart.Greeter.WhoToGreet;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

public class AkkaQuickstart {

    public static void main(String[] args) {
        /*
         * ActorSystem - контейнер акторов, через него мы получаем доступ к root-актору: helloakka
         * который имеет url - //helloakka
         * Также по средствам ActorSystem мы будем создавать прямых наследников user-актора //helloakka/user
         */
        ActorSystem system = ActorSystem.create("helloakka");
        try {
            /* create-actors
             *
             * 1.
             * Чтобы создать дочернего от /user-актора актора
             * нужно воспользоваться методом system.actorOf(Props, имя актора)
             *
             * 2.
             * ActorRef ссылка на url данного актора
             *
             * 3.
             * Props - это класс конфигурации, который указывает параметры для создания аккторов ...
             */
            ActorRef printerActor = system.actorOf(Printer.props(), "printerActor");

            ActorRef howdyGreeter = system.actorOf(Greeter.props("Howdy", printerActor), "howdyGreeter");
            ActorRef helloGreeter = system.actorOf(Greeter.props("Hello", printerActor), "helloGreeter");
            /*
             * Пример url:
             * //helloakka/user/goodDayGreeter#-567733426
             *
             * #-567733426 - это уникальный идентификатор
             */
            ActorRef goodDayGreeter = system.actorOf(Greeter.props("Good day", printerActor), "goodDayGreeter");

            //#main-send-messages
            howdyGreeter.tell(new WhoToGreet("Akka"), ActorRef.noSender());
            howdyGreeter.tell(new Greet(), ActorRef.noSender());

            howdyGreeter.tell(new WhoToGreet("Lightbend"), ActorRef.noSender());
            howdyGreeter.tell(new Greet(), ActorRef.noSender());

            helloGreeter.tell(new WhoToGreet("Java"), ActorRef.noSender());
            helloGreeter.tell(new Greet(), ActorRef.noSender());

            goodDayGreeter.tell(new WhoToGreet("Play"), ActorRef.noSender());
            goodDayGreeter.tell(new Greet(), ActorRef.noSender());
            //#main-send-messages

            System.out.println(">>> Press ENTER to exit <<<");
            System.in.read();
        } catch (IOException ioe) {
            System.out.println("Catch ioe c_exception at " + ioe.getStackTrace()[0].toString());
        } finally {
            system.terminate();
        }
    }
}
