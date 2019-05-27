package by.khamenka.patterns.command.commands;

import by.khamenka.patterns.command.object.DataBase;

public class CreateCommand implements Command {
    private DataBase db;

    public CreateCommand(DataBase db) {
        this.db = db;
    }

    @Override
    public void execute() {
        db.create();
    }
}
