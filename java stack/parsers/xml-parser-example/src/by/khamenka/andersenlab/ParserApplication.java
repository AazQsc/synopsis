package by.khamenka.andersenlab;

import by.khamenka.andersenlab.parsers.MyDOMParser;
import by.khamenka.andersenlab.parsers.MySAXParser;
import by.khamenka.andersenlab.reader.XMLReader;

public class ParserApplication {

    public static void main(String[] args) {
        XMLReader xmlReader = new XMLReader();

        xmlReader.setParser(new MySAXParser());
        xmlReader.parseDocument("documentForSax.xml");

        System.out.println("\n-------------------------\n");

        xmlReader.setParser(new MyDOMParser());
        xmlReader.parseDocument("documentForDom.xml");
    }

}
