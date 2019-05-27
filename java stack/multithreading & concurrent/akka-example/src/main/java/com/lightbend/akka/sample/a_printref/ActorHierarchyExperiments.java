package com.lightbend.akka.sample.a_printref;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

/*
 * Распечатываем иерархию акторов
 */
public class ActorHierarchyExperiments {

    public static void main(String[] args) throws java.io.IOException {
        /*
         * ActorSystem - контейнер акторов, через него мы получаем доступ к root-актору: testSystem
         * который имеет url - //testSystem
         * Также по средствам ActorSystem мы будем создавать прямых наследников user-актора //testSystem/user
         */
        ActorSystem system = ActorSystem.create("testSystem");

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
        ActorRef firstRef = system.actorOf(Props.create(PrintMyActorRefActor.class), "first-actor");
        /*
         * Пример url:
         * //testSystem/user/first-actor#1374659578
         *
         * #1374659578 - это уникальный идентификатор
         */
        System.out.println("First: " + firstRef);

        // отправляем сообщение "printit" по референс к PrintMyActorRefActor, отправителя не указываем
        firstRef.tell("printit", ActorRef.noSender());

        System.out.println(">>> Press ENTER to exit <<<");
        try {
            System.in.read();
        } finally {
            // завершаем работу с системой акторов
            system.terminate();
        }
    }
}
