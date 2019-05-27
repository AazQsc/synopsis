package by.khamenka.patterns.singleton;

/**
 * Singleton - пораждающий шаблон проектирования, гарантирующий создание одного
 * и только одного экземпляра некоторого класса, и предоставляет глобальную точку доступа
 * к этому экземпляру.
 *
 * Ещё можно делать синглтон через статику, а лучший вариант - делать через enum! (Дж. Блох)
 */
public class SingletonApp {
    public static void main(String[] args) {
        /*
         * Это не сработает:
         * Singleton singleton = new Singleton();
         * */

        Singleton singleton = Singleton.getInstance();
        singleton.doSomethig();
        singleton.getCounter();

        /*
         * Как альтернатива можно использовать так (чаще используется
         * именно так, через глобальную точку доступа):
         * */

        Singleton.getInstance().doSomethig();
        Singleton.getInstance().getCounter();
    }
}
