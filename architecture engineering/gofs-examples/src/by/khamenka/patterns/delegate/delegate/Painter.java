package by.khamenka.patterns.delegate.delegate;

import by.khamenka.patterns.delegate.figures.Graphics;

/*
 * Это и есть наш делегат
 * */
public class Painter {
    Graphics graphics;

    /*
     * Ещё это метод называют мутатором
     * */
    public void setGraphics(Graphics graphics) {
        this.graphics = graphics;
    }

    /*
     * Художник дилегат метода draw()
     * */
    public void draw() {
        graphics.draw();
    }
}
