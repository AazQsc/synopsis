package by.khamenka.patterns.facade.someHardSystem;

public class DVDRom {
    private boolean data = false;

    public boolean hasData() {
        return data;
    }

    public void loadData() {
        data = true;
        System.out.println("DVD in");
    }

    public void unLoadData() {
        data = false;
        System.out.println("DVD out");
    }
}
