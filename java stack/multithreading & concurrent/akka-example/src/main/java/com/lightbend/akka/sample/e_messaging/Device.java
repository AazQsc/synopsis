package com.lightbend.akka.sample.e_messaging;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;

import java.util.Optional;

/*
 * Система аторов девайсов.
 * Которая позволяет нам работать с показателями температуры:
 * - хранить актуальное значение температуры
 * - принимать новое значение температуры
 * - отчитываться о том, что пришло новое значение температуры
 * - спрашивать и отвечать какая сейчас температура
 * Для того чтобы увидить как это работает нажно запустить тест DeviceTest
 */
public class Device extends AbstractActor {
    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    // Группа к которой пренадлежит девайс
    final String groupId;
    // Уникальный идентификатор девайса
    final String deviceId;

    // Здесь мы храним актуальное значение температуры
    Optional<Double> lastTemperatureReading = Optional.empty();

    public Device(String groupId, String deviceId) {
        this.groupId = groupId;
        this.deviceId = deviceId;
    }

    /*
     *  Мы создаём девайс через этот статический метод.
     *  Пропс - это своеобразный конструктор, который позволяет настроить какой-то актор,
     *  забросить туда необходимые параметры.
     */
    public static Props props(String groupId, String deviceId) {
        return Props.create(Device.class, groupId, deviceId);
    }

    /*
    * Этот класс нужен для создания объекта, по средствам которого мы будем передавать
    * значения температуры в сообщениях.
    * То есть в объект RecordTemperature мы кидаем новые показатели температуры и
    * отправляем его в сообщении.
    * */
    public static final class RecordTemperature {
        // Уникальный идентификатор для каждого сообщения.
        final long requestId;
        // Собственно значение температуры.
        final double value;

        public RecordTemperature(long requestId, double value) {
            this.requestId = requestId;
            this.value = value;
        }
    }

    /*
     * Этот класс используется для уведомления отправителя о том,
     * что температура которая пришла по такому вот id была записана.
     */
    public static final class TemperatureRecorded {
        public final long requestId;

        public TemperatureRecorded(long requestId) {
            this.requestId = requestId;
        }
    }

    // По средствам отправки такого объекта в сообщении кто-то будет запрашивать температуру.
    public static final class ReadTemperature {
        final long requestId;

        public ReadTemperature(long requestId) {
            this.requestId = requestId;
        }
    }

    // Этот объект используется для отправки значения температуры
    public static final class RespondTemperature {
        public final long requestId;
        public final Optional<Double> value;

        public RespondTemperature(long requestId, Optional<Double> value) {
            this.requestId = requestId;
            this.value = value;
        }
    }

    @Override
    public void preStart() {
        log.info("Device actor {}-{} started", groupId, deviceId);
    }

    @Override
    public void postStop() {
        log.info("Device actor {}-{} stopped", groupId, deviceId);
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                // Если пришел объект RecordTemperature - значит это пришли новые показатели температуры.
                .match(RecordTemperature.class, rec -> {
                                    //"записано показание температуры"
                    log.info("Recorded temperature reading {} with {}", rec.value, rec.requestId);
                    // Сохряняем актуальное значение температуры.
                    lastTemperatureReading = Optional.of(rec.value);
                    // Вернули отправителю результат о том, что температура записана.
                    getSender().tell(new TemperatureRecorded(rec.requestId), getSelf());
                })
                // Если пришел объект RecordTemperature - значит кто-то хочет узнать показатели температуры.
                .match(ReadTemperature.class, rec -> {
                    // отправили актульное значение температуры в ответ
                    getSender().tell(new RespondTemperature(rec.requestId, lastTemperatureReading), getSelf());
                })
                .build();
    }

}