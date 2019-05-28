package by.aazqsc.home.in_action.core_1_springidol;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainScene {
    public static void main(String[] args) {
        // загружаем контекст спринга
        ApplicationContext ctx = new ClassPathXmlApplicationContext(
                "in_action/spring-core.xml");

        // Находим бин в контекте
        Performer performer = (Performer) ctx.getBean("duke");
        performer.perform();

        System.out.println("--- next 2 ---");

        Performer performer2 = (Performer) ctx.getBean("kenny");
        performer2.perform();


        System.out.println("--- next 3 ---");
        Performer performer3 = (Performer) ctx.getBean("stefano");
        performer3.perform();

        System.out.println("--- next 4 ---");
        /*
         * Если не настроить декларативно компоненты, экземпляры которых создаются за
         * пределами Spring, то мы можем получить NullPointerException
         *
         * 1. Аннотировать класс @Configurable("pianist")
         * 2. создать астрактный бин
         * 3. подключить простанство имен <core_3_aop:spring-configured />
         * 4. подключить зависимость 'aspectjweaver'
         */
        //Instrumentalist pianist = new Instrumentalist();
        //pianist.perform(); //NullPointerException
        //System.out.println("trоmbone: yya yya yya");
    }
}
