package com.lightbend.akka.sample.d_patterns;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

import java.io.IOException;

/*
 * Соответственно основная точка входа в систему через актор IotSupervisor
 */
public class IotMain {

    public static void main(String[] args) throws IOException {
        ActorSystem system = ActorSystem.create("iot-system");

        try {
            // Create top level supervisor
            ActorRef supervisor = system.actorOf(IotSupervisor.props(), "iot-supervisor");

            System.out.println("Press ENTER to exit the system");
            System.in.read();
        } finally {
            system.terminate();
        }
    }

}
