package by.khamenka.patterns.delegate.figures;

public class Triangle implements Graphics {
    @Override
    public void draw() {
        System.out.println("Paint Triangle");
    }
}
