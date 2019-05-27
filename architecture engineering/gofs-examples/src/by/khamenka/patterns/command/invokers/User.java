package by.khamenka.patterns.command.invokers;

import by.khamenka.patterns.command.commands.Command;

public abstract class User {
    protected Command create;
    protected Command read;

    public User(Command create, Command read) {
        this.create = create;
        this.read = read;
    }

    public abstract void readDB();
    public abstract void createEntryInBD();
}
