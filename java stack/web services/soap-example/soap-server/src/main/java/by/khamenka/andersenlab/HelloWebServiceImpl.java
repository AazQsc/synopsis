package by.khamenka.andersenlab;

import by.khamenka.andersenlab.POJO.SOAPResponse;
import by.khamenka.andersenlab.POJO.User;

import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;

import java.util.LinkedList;
import java.util.List;

/**
 * WebService(endpointInterface) - имплиментация должна ссылаться на интерфейс вебсервиса.
 * <p>
 * XmlSeeAlso - указываем java beans, используемые нами, для загрузки их в JAXB контекст
 * это аналог использования: JAXBContext.newInstance(BeanClass.class);
 */
@WebService(endpointInterface = "by.khamenka.andersenlab.HelloWebService")
@XmlSeeAlso({SOAPResponse.class, by.khamenka.andersenlab.POJO.User.class})
public class HelloWebServiceImpl implements HelloWebService {

    @Override
    public String getHelloString(String name) {
        return "Hello, " + name + "╯°□°）╯  !!!";
    }

    @Override
    public User getUser() {
        return new User(98, "Steve", "+3736429341");
    }

    @Override
    public SOAPResponse getUsers() {
        List<User> users = new LinkedList();
        users.add(new User(34, "Bob", "+9458438932"));
        users.add(new User(25, "Megan", "+2238230948"));
        users.add(new User(98, "Steve", "+3736429341"));

        /*
         * Мы используем объект SOAPResponse для передачи коллекции,
         * это своего рода обертка над коллекцией, может содержать другие коллекции
         * и самые разные поля, что позволяет нам отправить клиенту достаточно сложный
         * набор сущностей.
         *
         * В самом простом варианте можно просто вернуть коллекцию
         */
        SOAPResponse response = new SOAPResponse();
        response.setUsersList(users);

        return response;
    }

}
