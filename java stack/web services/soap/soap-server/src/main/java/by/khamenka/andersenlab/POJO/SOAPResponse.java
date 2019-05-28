package by.khamenka.andersenlab.POJO;

import javax.xml.bind.annotation.*;
import java.util.List;


/**
 * Мы используем объект SOAPResponse для передачи коллекции,
 * это своего рода обертка над коллекцией, может содержать другие коллекции
 * и самые разные поля, что позволяет нам отправить клиенту достаточно сложный
 * набор сущностей.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Response")
public class SOAPResponse {

    @XmlElementWrapper(name = "users")
    @XmlElement(name = "user")
    private List<User> usersList;

    public SOAPResponse() {
    }

    public List<User> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<User> users) {
        this.usersList = users;
    }

}
