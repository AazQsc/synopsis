package by.aazqsc.home.in_action.core_2_autowire;

import by.aazqsc.home.in_action.core_1_springidol.Performer;

import javax.xml.ws.Provider;
import java.util.HashSet;
import java.util.Set;

//по спецификации JSR-330
public class InjectAnnotationExample {

    //аналог Autowired, не имеет атрибута required
    //@Inject
    //@Named("guitar") // уточняем, по имени (айди), что мы хотим инджектнуть
                       // можно создавать собственные квалификаторы
    private Performer myPerformer;

    /*
     * Очень интересная возможность.
     * Мы можем создать имплиментацию интерфейса Provider
     * Там есть метод get() который будет возвращать нам
     * объект указанного типа.
     *
     * Мы можем настроить наш тип перед тем как вернуть его, например.
     * Выходит, что мы имеем посредника между классом который инджектим и класом
     * в который инджектим
     */
    private Set<Performer> performers;
    //@Inject
    public void setPerformers(Provider<Performer> performerProvider) {
        performers = new HashSet<Performer>();
        for (int i = 0; i < 5; i++) {
            //performers.add(performerProvider.get());
        }
    }
}
