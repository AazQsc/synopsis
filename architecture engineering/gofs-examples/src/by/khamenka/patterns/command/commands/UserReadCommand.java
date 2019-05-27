package by.khamenka.patterns.command.commands;

import by.khamenka.patterns.command.object.DataBase;

public class UserReadCommand implements Command{
    private DataBase db;

    public UserReadCommand(DataBase db) {
        this.db = db;
    }

    @Override
    public void execute() {
        db.read();
        System.out.println("Но выборка чтения ограничена.");
    }
}
