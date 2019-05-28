package by.khamenka.andersenlab.parsers;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;


/*
 * 1.
 * Для использования SAX парсера, нужно определить класс, расширяющий DefaultHandler.
 * Правильный разбор при помощи SAX требует вызовов методов этого обработчика.
 */
public class MySAXParser extends DefaultHandler implements Parser {

    // вспомогательные переменные см. characters
    private String thisQuestion = "";
    private String thisElement = "";

    /**
     * Это основной метод класса, используется для прочтения содержимого документа.
     * <p>
     * Этот медод берёт на себя слишком много функциональности. Это сделано, для наглядности:
     * удобно в учебном примере видеть последовательность действий.
     * <p>
     * Хорошим тоном будет, в принципе, разделить класс MySAXParser, как минимум, на два:
     * - объект DefaultHandler'a = DH
     * - объект конструирующий XMLReader
     * последний будет использовать функционал DH
     */
    @Override
    public void parseDocument(String pathname) {
        /*
         * 3.
         * Мы используем пару классов, SAXParserFactory и SAXParser, чтобы создать парсер.
         *
         * - SAXParserFactory настраивает и создает объект анализатора SAX.
         * - SAXParser является оберткой объекта SAXReader, на который он может ссылаться
         *   с помощью getXMLReader()
         */
        try {
            SAXParserFactory spfactory = SAXParserFactory.newInstance();
            // проверку правильности XML использовать не будем.
            spfactory.setValidating(false);

            SAXParser saxParser = spfactory.newSAXParser();

            /*
             * 2.
             * Это собственно наш парсер.
             */
            XMLReader xmlReader = saxParser.getXMLReader();

            /*
             * 4.
             * Парсер должен посылать свои события в ContentHandler.
             *
             * Всего DefaultHandler реализует четыре различных обработчика:
             * ContentHandler,
             * ErrorHandler,
             * DTDHandler,
             * EntityResolver
             */
            xmlReader.setContentHandler(this);

            /*
             * 5.
             * InputSource - класс является SAX оболочкой для любых данных,
             * которые мы будем обрабатывать.
             *
             * Оборачиваем в эту оболочку наш файл xml:
             * метод parse(InputSource) принимает такой объект в качестве аргумента
             */
            InputSource source = new InputSource(pathname);

            /*
             * 6.
             * Начинаем процесс парсинга
             */
            xmlReader.parse(source);
        } catch (SAXException e) {
            System.out.println("Problem parsing the file.");
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            System.out.println("Parser was not configurated.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Cannot read input file.");
            e.printStackTrace();
        }
    }

    /*
     * Вызывается когда мы начали чтение документа.
     */
    public void startDocument() throws SAXException {
        System.out.println("--- START ---");
    }

    /*
     * Чтение элемента, получаем об элементе:
     * - qName - квалифицированное имя
     * - URI пространства имен
     * - Локальное имя
     * - массив атрибутов элемента
     */
    public void startElement(String namespaceURI, String localName,
                             String qName, Attributes atts) throws SAXException {
        if (qName == "response") {
            System.out.println("User: " + atts.getValue("username"));
        } else if (qName == "question") {
            thisQuestion = atts.getValue("subject");
        }
        thisElement = qName;
    }

    /*
     * Вызывается когда чтение элемента завершено.
     */
    public void endElement(String namespaceURI, String localName, String qName)
            throws SAXException {

        thisQuestion = "";
        thisElement = "";
    }

    /*
     * characters вызывается, когда анализатор встречает группу символов в элементе XML.
     * ch - массив char xml
     */
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (thisElement == "question") {
            System.out.print("  " + thisQuestion + ": ");
            System.out.println(new String(ch, start, length));
        }
    }

    /*
     * Вызывается когда чтение документа завершено.
     */
    public void endDocument() {
        System.out.println("--- FINISH ---");
    }

}
