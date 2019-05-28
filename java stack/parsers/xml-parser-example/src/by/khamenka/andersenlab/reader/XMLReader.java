package by.khamenka.andersenlab.reader;

import by.khamenka.andersenlab.parsers.Parser;

public class XMLReader implements Parser {
    private Parser parser;

    public void setParser(Parser parser) {
        this.parser = parser;
    }

    @Override
    public void parseDocument(String pathname) {
        if (parser == null) {
            System.out.println("Метод XMLReader.parseDocument не может быть использован" +
                    " необходимо задать парсер (XMLReader.setParser).");
        } else {
            parser.parseDocument(pathname);
        }
    }

}
