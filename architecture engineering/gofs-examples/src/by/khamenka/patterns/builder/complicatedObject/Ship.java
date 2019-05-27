package by.khamenka.patterns.builder.complicatedObject;

public class Ship {
    private ShipType type;
    private int speed;
    private int guns;

    public void setType(ShipType type) {
        this.type = type;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setGuns(int guns) {
        this.guns = guns;
    }

    @Override
    public String toString() {
        return "Ship " +
                "type=" + type +
                ", speed=" + speed +
                ", guns=" + guns;
    }
}
