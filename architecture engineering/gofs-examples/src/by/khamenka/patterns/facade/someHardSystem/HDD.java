package by.khamenka.patterns.facade.someHardSystem;

public class HDD {
    public void copyFromDVD(DVDRom dvd) {
        if (dvd.hasData()) {
            System.out.println("Data is copied");
        } else {
            System.out.println("Please load DvD");
        }
    }
}
