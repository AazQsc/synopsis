package by.khamenka.andersenlab;

import javax.xml.ws.Endpoint;

public class SOAPServer {

    /**
     * Endpoint - класс, для запуска веб-сервера с веб-сервисами.
     * Запускаем веб-сервер на порту 8080 и по адресу,
     *   указанному в первом аргументе, запускаем веб-сервис, передаваемый во втором аргументе.
     */
    public static void main(String[] args) {

        // можно проверить на http://localhost:8080/mySOAP/hello?wsdl
        Endpoint.publish("http://localhost:8080/mySOAP/hello", new HelloWebServiceImpl());
    }
}
