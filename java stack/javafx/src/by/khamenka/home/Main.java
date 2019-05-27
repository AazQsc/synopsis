package by.khamenka.home;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Класс в котором происходит запуск приложения наследуется от javafx.application.Application
 * <p>
 * Ещё два обязательных компонента приложения javaFX - это файл с расшарением fxml (описание view)
 * и класс контроллера.
 *
 * _____
 * Доп. инф.
 *
 * JavaFX предоставляет новую возможность развёртывания, называемую "нативная упаковка" (Native Packaging)
 * (другое название - "Автономная упаковка приложения" (Self-Containde Application Package)).
 * Нативный Пакет - это пакет, который вместе с приложением Java содержит и среду выполнения Java
 * для конкретной платформы.
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        /*
         * Мы находим корневой элемент в .fxml (root node, который внутри содержит
         * все другие элементы (node)) используя FXMLLoader.
         */
        Parent root = FXMLLoader.load(getClass().getResource("view/myFirstWindow.fxml"));
        /*
         * Stage - один из ключевых элементов, простым языкаом говоря - это наше окно.
         * Внутри окна находится такой компонент как сцена (Scene)
         * На сцене мы видим наши nodes
         * Вот такая матрешка.)
         *
         * Stage - это контейнер JavaFX верхнего уровня. Там инкапсулированы различные опирации, за
         * которые отвечает платформа JavaFX (наследуется от Window)
         *
         * Scene - это контейнер для наших элементов (node)
         * Если я не ошибаюсь у сцены есть глубина. Она умеет работать с трехмерными объектами.
         * Для создания сцены нужно указать корневой узел fxml, сцена будет построена в соответствии
         * с характеристиками элементов этого документа
         */
        primaryStage.setTitle("Hello World (echo!)");
        primaryStage.setScene(new Scene(root));
        /*
         * Отобразить сцену нужно явно.
         *
         * Можно динамический менять сцену. Для этого нужно в
         * primaryStage.setScene(new Scene(root));
         * Положить другую сцену.
         */
        primaryStage.show();
    }


    public static void main(String[] args) {

        launch(args);
    }

    /*
     * В отличии от swing javaFX умеет закрывать прилохения на крестик по-умолчанию :)
     *
     * Но если нам нужно закрыть какие-то ресурсы мы можем сделать это переопределив
     * метод stop(), он отработает при закрытии приложения.
     *
     */
    @Override
    public void stop() throws Exception {
        super.stop();
    }
}
