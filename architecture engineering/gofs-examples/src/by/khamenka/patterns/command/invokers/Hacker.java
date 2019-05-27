package by.khamenka.patterns.command.invokers;

import by.khamenka.patterns.command.commands.Command;

public class Hacker extends User{

    public Hacker(Command create, Command read) {
        super(create, read);
    }

    @Override
    public void readDB() {
        read.execute();
        System.out.println("Обошел все ограничения и прочитал все записи из БД!");
    }

    @Override
    public void createEntryInBD() {
        create.execute();
    }
}
