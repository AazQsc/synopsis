package by.aazqsc.home.in_action.core_4_producerconsumer;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ProducerMain {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext(
                "in_action/spring-core-prod-cons.xml");

        Course course = (Course) ctx.getBean("socialScience");

        // так контекст может опубликовать событие (produce)
        // и это событие сразу схватит наш литнер
        ctx.publishEvent(new CourseFullEvent(ctx, course));
    }
}
