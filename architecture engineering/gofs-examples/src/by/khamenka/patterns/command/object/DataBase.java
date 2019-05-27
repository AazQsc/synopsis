package by.khamenka.patterns.command.object;

public class DataBase {
    public void create(){
        System.out.println("Произведена запись в бд.");
    }

    public void read(){
        System.out.println("Произведено чтение из бд.");
    }
}
