package by.khamenka.patterns.singleton;

public class Singleton {
    private int counter;

    private static volatile Singleton instance = null;

    private Singleton() {
        this.counter++;
    }

    public static Singleton getInstance() {
        if (instance == null){
            synchronized (Singleton.class){
                if (instance == null){
                    instance = new Singleton();
                }
            }
        }

        return instance;
    }

    public void doSomethig(){
        System.out.println("I'm doing something");
    }

    public int getCounter() {
        System.out.println(" " + counter);
        return counter;
    }

}
