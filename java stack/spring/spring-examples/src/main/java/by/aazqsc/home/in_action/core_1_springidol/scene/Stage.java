package by.aazqsc.home.in_action.core_1_springidol.scene;

public class Stage {

    private Stage() {
    }

    private static class StageSingletonHolder {
        static Stage instance = new Stage(); // Создание экземпляра
    }

    public static Stage getInstance() {
        return StageSingletonHolder.instance; // Возвращает экземпляр
    }

}
