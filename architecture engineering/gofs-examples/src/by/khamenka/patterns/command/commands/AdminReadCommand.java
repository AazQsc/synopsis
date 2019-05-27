package by.khamenka.patterns.command.commands;

import by.khamenka.patterns.command.object.DataBase;

public class AdminReadCommand implements Command{
    private DataBase db;

    public AdminReadCommand(DataBase db) {
        this.db = db;
    }

    @Override
    public void execute() {
        db.read();
    }
}
