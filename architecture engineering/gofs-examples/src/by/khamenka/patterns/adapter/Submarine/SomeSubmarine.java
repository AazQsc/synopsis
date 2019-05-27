package by.khamenka.patterns.adapter.Submarine;

public class SomeSubmarine implements Submarine {
    @Override
    public void dive() {
        System.out.println("Submarine is dive");
    }

    @Override
    public void move() {
        System.out.println("The submarine is move, but not so fast");
    }
}
