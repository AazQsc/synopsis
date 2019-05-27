package by.khamenka.patterns.delegate.figures;

public class Square implements Graphics {
    @Override
    public void draw() {
        System.out.println("Paint Square");
    }
}
