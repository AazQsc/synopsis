package by.aazqsc.home.spring_ripper;

import javax.annotation.PostConstruct;

@Profiling
/*
* Мы хотим, чтобы все классы которые деприкейтед заменились на имплиментации
* */
@DepricatedClass(newImpl = T1000.class)
public class TerminatorQuoter implements Quoter {
    /*
     * поля в спринге называют 'проперти'
     */
    private String message;

    /*
     * InjectRandomInt - наша собственная аннотация.
     * О наших аннотациях спринг ничего не знает, необходимо создать имплиментацию
     * которая реализует BeanPostProcessor, в нашем случае это
     * будет InjectRandomIntAnnotationBeanPostProcessor
     *
     * Конвенция по именованию: "имя аннотации + AnnotationBeanPostProcessor"
     */
    @InjectRandomInt(min = 2, max = 7)
    private int repeat;

    /*
     * На этапе работы post construct ещё никаких прокси ещё нет
     * Это вторая фаза инициализации
     */
    @PostConstruct
    public void init(){
        System.out.println("Phase 2 : BPP");
    }

    public TerminatorQuoter() {
        System.out.println("Phase 1 : конструктор");
    }

    @Override
    /*
     * Эта наша аннотация, будем имметировать 3ю фазу инициализации
     * используя ContextListener
     *
     * метод должен быть в интерфейсе
     */
    @PostProxy
    public void sayQuote() {
        System.out.println("Phase 3 : ContextListener");

        for (int i = 0; i < repeat; i++) {
            System.out.println(message);
        }
    }

    /*
     * Если мы работаем через XML для всех проперти необходимо сделать
     * сеттеры, спринг в любом случае будет искать их через рефлекшн, есть они или нет,
     * если нет, всё упадет.
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
