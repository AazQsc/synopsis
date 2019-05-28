package by.khamenka.andersenlab;

import java.net.URL;
import java.net.MalformedURLException;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class HelloWebServiceClient {
    public static void main(String[] args) throws MalformedURLException {
        /*
         * создаем ссылку на wsdl описание
         */
        URL url = new URL("http://localhost:8080/mySOAP/hello?wsdl");

        /*
         * QName представляет полностью определенное имя как определено в спецификациях XML
         * Значение QName содержит URI Пространства имен, локальную часть.
         *
         * Параметры следующего конструктора смотрим в самом первом теге WSDL описания - definitions
         * 1-ый аргумент смотрим в атрибуте targetNamespace
         * 2-ой аргумент смотрим в атрибуте name
         */
        QName qname = new QName("http://andersenlab.khamenka.by/", "HelloWebServiceImplService");

        /*
         * Qname в связке с service участвуют в парсе wsdl
         * из wsdl получаем собственно ссылку на объект веб-сервиса и
         * порт на котором он находится.
         */
        Service service = Service.create(url, qname);
        HelloWebService hello = service.getPort(HelloWebService.class);

        // Вызываем удаленные методы.
        System.out.println(hello.getHelloString("SOAP"));
    }
}
