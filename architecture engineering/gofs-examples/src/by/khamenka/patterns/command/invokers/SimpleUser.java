package by.khamenka.patterns.command.invokers;

import by.khamenka.patterns.command.commands.Command;

public class SimpleUser extends User{

    public SimpleUser(Command create, Command read) {
        super(create, read);
    }

    @Override
    public void readDB() {
        read.execute();
    }

    @Override
    public void createEntryInBD() {
        create.execute();
    }
}
