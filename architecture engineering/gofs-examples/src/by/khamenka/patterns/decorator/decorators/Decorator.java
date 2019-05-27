package by.khamenka.patterns.decorator.decorators;

import by.khamenka.patterns.decorator.object.CommandOfFormula1;

public abstract class Decorator implements CommandOfFormula1{
    protected CommandOfFormula1 command;

    public Decorator(CommandOfFormula1 command) {
        this.command = command;
    }
}
