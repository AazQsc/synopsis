package by.khamenka.patterns.decorator;

import by.khamenka.patterns.decorator.decorators.ChangeSteering;
import by.khamenka.patterns.decorator.decorators.ChangeWheel;
import by.khamenka.patterns.decorator.decorators.Refueling;
import by.khamenka.patterns.decorator.object.CommandOfFormula1;
import by.khamenka.patterns.decorator.object.RealCommandOfFormula;
import com.sun.org.apache.xpath.internal.SourceTree;

/**
 * Декоратор - структурный шаблон проектирования который применяется для
 * расширения имеющейся функциональности и является альтернативой
 * порождению подклассов на основе динамического назначения объектам новых операций.
 * <p>
 * Динамический предоставляет объекту дополнительные возможности, представляет собой гибкую
 * альтернативу наследованию для расширения функциональности.
 */
public class DecoratorApp {


    public static void main(String[] args) {
        /*
         * Интересно с какими шаблонами можно совместить,
         * для того, чтобы не создавать таких вложенных конструкций
         *
         * ни с какими :)
         * */

        CommandOfFormula1 command = new ChangeSteering(new ChangeWheel(new RealCommandOfFormula()));
        command.pitStop();
        command.getPitStopTime();

        System.out.println("--- --- ---");

        CommandOfFormula1 command2 = new Refueling(new ChangeWheel(new RealCommandOfFormula()));
        command2.pitStop();
        command2.getPitStopTime();

    }

}
