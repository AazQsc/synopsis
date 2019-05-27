package by.yar.home;

import org.kie.api.KieServices;
import org.kie.api.builder.Results;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class Main {
    public static void main(String[] args) {
        System.out.println("Bootstrapping the Rule Engine ...");

        // 1) Bootstrapping a Rule Engine Session
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();

        /*
         * KieSession - представляет собой запущенный экземпляр Rule Engine
         * с конкретной конфигурацией и набором правил.
         *
         * Через этот объект мы взаимодействуем с нашими java объектами
         */
        KieSession kSession = kContainer.newKieSession();

        Item item1 = new Item(1);
        Item item2 = new Item(5);
        Item item3 = new Item(10);

        // 2) Provide information to the Rule Engine Context
        kSession.insert(item1);
        kSession.insert(item2);
        kSession.insert(item3);

        // 3) Execute the rules that are matching
        int fired = kSession.fireAllRules(); // метод вернет количество выполненных правил

        System.out.println("Number of Rules executed = " + fired);
        System.out.println(item1.getItemPrioryty());
        System.out.println(item2.getItemPrioryty());
        System.out.println(item3.getItemPrioryty());


        System.out.println("-------------------");
        // Let's verify that all the resources are loaded correctly
        Results results = kContainer.verify();

        // We can iterate the results
        results.getMessages()
                .forEach((message) ->
                        System.out.println(">> Message ( " + message.getLevel() + " ): " + message.getText()));

        //Here we make sure that all the KieBases and KieSessions
        // that we are expecting are loaded.
        kContainer.getKieBaseNames().stream()
                .map(Main::apply)
                .forEach((kieBase) ->
                        kContainer.getKieSessionNamesInKieBase(kieBase).forEach((kieSession) ->
                                System.out.println("\t >> Containing KieSession: " + kieSession)));

        System.out.println("-------------------");
    }

    private static String apply(String kieBase) {
        System.out.println(">> Loading KieBase: " + kieBase);
        return kieBase;
    }
}
