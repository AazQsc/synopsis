package by.khamenka.patterns.decorator.object;

public class RealCommandOfFormula implements CommandOfFormula1 {
    private int pitStopTime;

    @Override
    public void pitStop() {
        System.out.println("Технический питстоп.");
        setPitStopTime();
    }

    @Override
    public void getPitStopTime() {
        System.out.println("Комманда потратила на питстоп: " + pitStopTime + "сек..");
    }

    @Override
    public void setPitStopTime() {
        pitStopTime++;
    }
}
