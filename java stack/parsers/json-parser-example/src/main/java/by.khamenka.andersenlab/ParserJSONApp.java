package by.khamenka.andersenlab;

import by.khamenka.andersenlab.Jackson.JSONTransformer;
import by.khamenka.andersenlab.POJO.User;

import java.util.ArrayList;

public class ParserJSONApp {

    public static void main(String[] args) {
        /*
         * Создадим нового юзера.
         * */
        User firstUser = new User("Smith Smiths", 26,
                new ArrayList<String>() {{
                    add("I ");
                    add("say ");
                    add("something");
                }});

        //Создаём объект преобразователь:
        JSONTransformer jsonTransformer = new JSONTransformer();

        //преобразуем объект юзера в json:
        jsonTransformer.writeValue(firstUser);

        //преобразуем полученный json обратно в pojo class:
        User secondUser = jsonTransformer.readValue();

        System.out.println("firstUser  - " + firstUser);
        System.out.println("secondUser - " + secondUser);
    }

}
