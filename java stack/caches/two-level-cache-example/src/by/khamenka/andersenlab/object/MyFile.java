package by.khamenka.andersenlab.object;

import java.io.Serializable;
import java.util.Objects;

public class MyFile implements Serializable {
    private static final long serialVersionUID = 4051888415115624062L;

    private String name;
    private int number;

    public MyFile(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyFile MyFile = (MyFile) o;
        return number == MyFile.number &&
                Objects.equals(name, MyFile.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, number);
    }

    @Override
    public String toString() {
        return "MyFile{" +
                "name='" + name + '\'' +
                ", number=" + number +
                '}';
    }
}
