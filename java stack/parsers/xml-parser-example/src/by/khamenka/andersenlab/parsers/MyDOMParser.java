package by.khamenka.andersenlab.parsers;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class MyDOMParser implements Parser {

    /**
     * Это основной метод класса, используется для прочтения содержимого документа.
     * <p>
     * Аналогично методу MySAXParser.parseDocument этот медод берёт на себя слишком
     * много функциональности. Это сделано, для наглядности:
     * удобно в учебном примере видеть последовательность действий.
     */
    @Override
    public void parseDocument(String pathname) {
        File docFile = new File(pathname);

        /*
         * Объект Document является интерфейсом
         * Объект этого типа строится с помощью абстрактной фабрике
         */
        Document doc = null;
        try {
            /*
             * В среде Java разбор файла является 3-шаговым процессом:
             *  1. Создание DocumentBuilderFactory. Этот объект создает DocumentBuilder.
             *     у него в атрибутах мы можем указать нажна ли проверка правильности документов,
             *    нужно ли игнорировать комментарии и тому подобное.
             *  2. Создание DocumentBuilder. DocumentBuilder действительно выполняет разбор для
             *     создания объекта Document.
             *  3. Разбор файла для создания объекта Document.
             */
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            doc = db.parse(docFile);

            /*
             * Когда парсер создал документ, приложение может
             * проходить через него для обработки данных.
             */

        } catch (javax.xml.parsers.ParserConfigurationException pce) {
            System.out.println("The parser was not configured correctly.");
            System.exit(1);
        } catch (java.io.IOException ie) {
            System.out.println("Cannot read input file.");
            System.exit(1);
        } catch (org.xml.sax.SAXException se) {
            System.out.println("Problem parsing the file.");
            System.exit(1);
        } catch (java.lang.IllegalArgumentException ae) {
            System.out.println("Please specify an XML source.");
            System.exit(1);
        }

        //1: получить корневой элемент
        Element root = doc.getDocumentElement();
        System.out.println("The root element is " + root.getNodeName());

        //2: получить наследников (количество)
        NodeList children = root.getChildNodes();
        System.out.println("There are " + children.getLength()
                + " nodes in this документ." + "\n");

        //3: пройтись по наследникам и наследникам наследников...
        stepThrough(root);

        //4: изменить значение элемента
        //changeOrder(root, "status", "processing");

        //5: находим элементы по имени
        //getAllElementsByTagName(root, "status");
    }

    private static void stepThrough(Node start) {
        // выводим имя узла и его содержимое
        System.out.println(start.getNodeName() + " = " + start.getNodeValue());

        // получаем все атрибуты и их содержимое
        if (start.getNodeType() == start.ELEMENT_NODE) {
            NamedNodeMap startAttr = start.getAttributes();
            for (int i = 0; i < startAttr.getLength(); i++) {
                Node attr = startAttr.item(i);
                System.out.println(" Attribute: " + attr.getNodeName()
                        + " = " + attr.getNodeValue());
            }
        }

        // идём по узлам
        for (Node child = start.getFirstChild(); child != null; child = child.getNextSibling()) {
            stepThrough(child);
        }
    }

    private static void changeOrder(Node start, String elemName, String elemValue) {
        if (start.getNodeName().equals(elemName)) {
            start.getFirstChild().setNodeValue(elemValue);
        }
        for (Node child = start.getFirstChild(); child != null; child = child.getNextSibling()) {
            changeOrder(child, elemName, elemValue);
        }
    }

    private static void getAllElementsByTagName(Element root, String name) {
        NodeList orders = root.getElementsByTagName(name);
        for (int orderNum = 0; orderNum < orders.getLength(); orderNum++) {
            System.out.println(orders.item(orderNum).getFirstChild().getNodeValue());
        }
    }

}
