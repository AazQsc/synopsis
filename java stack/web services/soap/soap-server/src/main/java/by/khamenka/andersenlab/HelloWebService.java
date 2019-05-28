package by.khamenka.andersenlab;

import by.khamenka.andersenlab.POJO.SOAPResponse;
import by.khamenka.andersenlab.POJO.User;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * WebService - указываем, что наш интерфейс будет работать как веб-сервис.
 * <p>
 * SOAPBinding - указываем, что веб-сервис будет использоваться для вызова методов
 * стиль биндинга - DOCUMENT (позволяет не только вызывать методы, но использовать soap
 * для передачи xml документов).
 * <p>
 * WebMethod - указываем, что этот метод можно вызывать удаленно.
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT)
public interface HelloWebService {

    @WebMethod
    public String getHelloString(String name);

    @WebMethod
    public SOAPResponse getUsers();

    @WebMethod
    public User getUser();
}
