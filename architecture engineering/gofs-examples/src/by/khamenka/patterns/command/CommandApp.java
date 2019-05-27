package by.khamenka.patterns.command;

import by.khamenka.patterns.command.commands.AdminReadCommand;
import by.khamenka.patterns.command.commands.CreateCommand;
import by.khamenka.patterns.command.commands.UserReadCommand;
import by.khamenka.patterns.command.invokers.Hacker;
import by.khamenka.patterns.command.invokers.SimpleUser;
import by.khamenka.patterns.command.invokers.User;
import by.khamenka.patterns.command.object.DataBase;

/**
 * Комманда - поведенческий шаблон проектирования. Инкапсулирует запрос в виде объекта,
 * позволяя передавать клиентам(invokers) в качестве параметров, ставить в очередь,
 * логировать а также поддерживает отмену операций.
 * <p>
 * Кроме того двойная обертка, позволяет нам менять реализацию команды как на уровне инкапсулированного объекта
 * так и на уровне пользователя.
 *
 */


public class CommandApp {
    public static void main(String[] args) {
        DataBase db = new DataBase();

        User admin = new SimpleUser(new CreateCommand(db), new AdminReadCommand(db));
        User user = new SimpleUser(new CreateCommand(db), new UserReadCommand(db));
        User hacker = new Hacker(new CreateCommand(db), new UserReadCommand(db));

        System.out.println("--- Admin  ---");
        admin.createEntryInBD();
        admin.readDB();

        System.out.println("--- User   ---");
        user.createEntryInBD();
        user.readDB();

        /*
         * Можно сделать к примеру так, что мы распознаем хакера
         * и запретим ему выполнение любых операций в принципе.
         */
        System.out.println("--- Hacker ---");
        hacker.createEntryInBD();
        hacker.readDB();
    }
}
