package by.khamenka.patterns.decorator.decorators;

import by.khamenka.patterns.decorator.object.CommandOfFormula1;

public class ChangeWheel extends Decorator{
    public ChangeWheel(CommandOfFormula1 command) {
        super(command);
    }

    @Override
    public void pitStop() {
        command.pitStop();
        System.out.println("Была произведена смена резины на дождевую.");
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
