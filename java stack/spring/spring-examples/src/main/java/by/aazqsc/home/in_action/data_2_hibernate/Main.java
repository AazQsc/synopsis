package by.aazqsc.home.in_action.data_2_hibernate;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext(
                "in_action/spring-data.xml");

        HibernateSpitterDao hibernateSpitterDao = (HibernateSpitterDao) ctx.getBean("HibernateSpitterDao");

        //hibernateSpitterDao.addSpitter(new Spitter("Ron", "2387", "Ron Malchholm"));
        //hibernateSpitterDao.addSpitter(new Spitter("Bob", "1111", "Bob B"));

        System.out.println(hibernateSpitterDao.getSpitterById(4));
        System.out.println(hibernateSpitterDao.getSpitterById(5));
        System.out.println(hibernateSpitterDao.getSpitterById(6));
    }
}
