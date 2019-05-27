package by.khamenka.patterns.decorator.decorators;

import by.khamenka.patterns.decorator.object.CommandOfFormula1;

public class ChangeSteering extends Decorator {
    public ChangeSteering(CommandOfFormula1 command) {
        super(command);
    }

    @Override
    public void pitStop() {
        command.pitStop();
        System.out.println("Была произведена смена рулевого управления.");
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
