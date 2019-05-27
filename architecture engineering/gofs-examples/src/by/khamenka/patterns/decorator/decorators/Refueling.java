package by.khamenka.patterns.decorator.decorators;

import by.khamenka.patterns.decorator.object.CommandOfFormula1;

public class Refueling extends Decorator{
    public Refueling(CommandOfFormula1 command) {
        super(command);
    }

    @Override
    public void pitStop() {
        command.pitStop();
        System.out.println("Была произведена дозаправка.");
        setPitStopTime();

    }

    @Override
    public void getPitStopTime() {
        command.getPitStopTime();
    }

    @Override
    public void setPitStopTime() {
        command.setPitStopTime();
    }
}
