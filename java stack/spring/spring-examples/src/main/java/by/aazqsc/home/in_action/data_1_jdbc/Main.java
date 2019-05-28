package by.aazqsc.home.in_action.data_1_jdbc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext(
                "in_action/spring-data.xml");

        JdbcSpitterDaoImpl jdbcSpitterDaoImpl = (JdbcSpitterDaoImpl) ctx.getBean("spitterDao");

        // JDBC Вставка объекта в бд JDBC
        // jdbcSpitterDaoImpl.addSpitter(new Spitter("Nill", "3452", "Nill McGaget"));

        // JDBC Чтение объекта из бд
        System.out.println(jdbcSpitterDaoImpl.getSpitterById(1));
        System.out.println(jdbcSpitterDaoImpl.getSpitterById(2));
        System.out.println(jdbcSpitterDaoImpl.getSpitterById(3));

    }
}
