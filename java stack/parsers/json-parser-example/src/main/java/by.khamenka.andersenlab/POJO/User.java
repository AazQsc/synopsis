package by.khamenka.andersenlab.POJO;

import java.util.List;

public class User {

    private String name;
    private int age;
    private List<String> messages;

    public User() {
    }

    public User(String _name, int _age, List<String> _messages) {
        name = _name;
        age = _age;
        messages = _messages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        return "User [name=" + name + ", age=" + age + ", messages="
                + messages + "]";
    }
}
