package by.aazqsc.home.spring_ripper;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // Создаём контекст
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring_ripper.xml");
        while (true){
            Thread.sleep(200);
            // Достаем бин из контекста
            // лукап по классу - это плохо, делай лукап по интерфейсу!
            context.getBean(Quoter.class).sayQuote();
        }

        //context.getBean(Quoter.class).sayQuote();
    }
}
