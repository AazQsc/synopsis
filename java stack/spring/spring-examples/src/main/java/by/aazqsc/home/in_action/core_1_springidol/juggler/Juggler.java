package by.aazqsc.home.in_action.core_1_springidol.juggler;

import by.aazqsc.home.in_action.core_1_springidol.Performer;

public class Juggler implements Performer {
    private int beanBags = 3;

    public Juggler() {
    }

    public Juggler(int beanBags) {
        this.beanBags = beanBags;
    }

    @Override
    public void perform() {
        System.out.println("JUGGLING " + beanBags + " BEANBAGS");
    }
}
