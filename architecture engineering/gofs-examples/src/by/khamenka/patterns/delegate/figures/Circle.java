package by.khamenka.patterns.delegate.figures;

public class Circle implements Graphics {
    @Override
    public void draw() {
        System.out.println("Paint Circle");
    }
}
