package by.aazqsc.home.in_action.core_2_autowire;

import by.aazqsc.home.in_action.core_1_springidol.Performer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;

public class AutowiredAnnotationExample {

    //так можно внедрять примитивы, но зачем?..
    //@Value("string value")
    //а вот зачем!
    @Value("#{systemProperties.myPassword}")
    private String mystring;

    // Автоматическое связывание по типу.
    @Autowired
    /* Qualifier - Уточнить имплиментацию типа.
     *
     * Эта же аннотация над классом может задать тип этого класса для бина
     * например @Qualifier("Ловкач") над классом Juggler, уточнит, что жонглер это ловкач.
     *
     * Можно создавать своих наследников @Qualifier и вешать потом по несколько штук
     * на одно поле-конструктор-метод.
     */
    @Qualifier("Juggler")
    private Performer myPerformer;

    @Autowired
    public AutowiredAnnotationExample(Performer myPerformer) {
        this.myPerformer = myPerformer;
    }

    // required false - связывание не обязательно null допускается
    @Autowired(required=false)
    public void setMyPerformer(Performer myPerformer) {
        this.myPerformer = myPerformer;
    }
}
