package by.khamenka.patterns.facade.facade;

import by.khamenka.patterns.facade.someHardSystem.DVDRom;
import by.khamenka.patterns.facade.someHardSystem.HDD;
import by.khamenka.patterns.facade.someHardSystem.Power;

/*
 * Это наш фасад
 * */
public class Computer {
    private Power power;
    private DVDRom dvd;
    private HDD hdd;

    public Computer() {
        power = new Power();
        dvd = new DVDRom();
        hdd = new HDD();
    }

    public void copy() {
        power.on();
        dvd.loadData();
        hdd.copyFromDVD(dvd);
    }
}
