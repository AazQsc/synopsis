package com.lightbend.akka.sample;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.testkit.javadsl.TestKit;
import com.lightbend.akka.sample.e_messaging.Device;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;


public class DeviceTest {

    static ActorSystem system;

    @BeforeClass
    public static void setup() {
        system = ActorSystem.create();
    }

    @AfterClass
    public static void teardown() {
        TestKit.shutdownActorSystem(system);
        system = null;
    }

    @Test
    public void testReplyWithEmptyReadingIfNoTemperatureIsKnown() {
        TestKit probe = new TestKit(system);
        ActorRef deviceActor = system.actorOf(Device.props("group", "device"));
        deviceActor.tell(new Device.ReadTemperature(42L), probe.getRef());
        Device.RespondTemperature response = probe.expectMsgClass(Device.RespondTemperature.class);
        assertEquals(42L, response.requestId);
        assertEquals(Optional.empty(), response.value);
    }

    @Test
    public void testReplyWithLatestTemperatureReading() {
        TestKit probe = new TestKit(system);
        /*
         * Создаем девайс актор.
         */
        ActorRef deviceActor = system.actorOf(Device.props("group", "device"));

        // Отправляем в актор сообщениее с новыми показателями температуры.
        deviceActor.tell(new Device.RecordTemperature(1L, 24.0), probe.getRef());
        // Проверяем доставлено ли письмо
        assertEquals(1L, probe.expectMsgClass(Device.TemperatureRecorded.class).requestId);

        // Спросили какая сейчас температура
        deviceActor.tell(new Device.ReadTemperature(2L), probe.getRef());
        // Получили ответ
        Device.RespondTemperature response1 = probe.expectMsgClass(Device.RespondTemperature.class);
        // Проверили приходит ли нам ответ именно на наш запрос
        assertEquals(2L, response1.requestId);
        // Проверяем действительно ли равно значение отправленой температуры и актуальное значение в девайсе
        assertEquals(Optional.of(24.0), response1.value);

        /*
         * И ещё раз тоже самое...
         */
        deviceActor.tell(new Device.RecordTemperature(3L, 55.0), probe.getRef());
        assertEquals(3L, probe.expectMsgClass(Device.TemperatureRecorded.class).requestId);

        deviceActor.tell(new Device.ReadTemperature(4L), probe.getRef());
        Device.RespondTemperature response2 = probe.expectMsgClass(Device.RespondTemperature.class);
        assertEquals(4L, response2.requestId);
        assertEquals(Optional.of(55.0), response2.value);
    }
}
