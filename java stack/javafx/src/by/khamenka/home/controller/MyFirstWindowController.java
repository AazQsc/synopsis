package by.khamenka.home.controller;

import by.khamenka.home.model.HardLogic;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Контроллер в javaFX - это обычный класс путь к которому мы указали в файле .fxml
 * Этот объект не нужно нигде создавать явно.
 * 
 * Порядок работы с контроллером:
 * 1. Найти элементы (node) с которыми предстоит работать
 *  имя элемента == id элемента (ctrl + space по id в .fxml <--- hot key)
 *  Отметить эти поля аннотацией @FXML (исли поля public аннотация не обязательна...)
 *
 * 2. Во вкладке Code элемента button, в Scene Builder (SB), мы подписываемся на событие
 *      On Action (для этого нужно просто в соответствующем поле SB написать название метода, я
 *      выбрал название которое повторяет название события (onAction), но можно зделать имя более "звучащим")
 *
 *      описываю этот метод в контроллере (или ctrl + space по "#onAction" в .fxml)
 *
 * 3. Реализуем взаимодействие с логикой в методах контроллера...
 *    И да! Spring умеет работать c javaFX!
 *
 * */
public class MyFirstWindowController implements Initializable {
    // STEP 1:
    @FXML
    private TextField myTextField;

    /*
    * Не используем элемент button, но можем. Для работы метода onAction
    * объявление этого поля не обязательно
    * */
    @FXML
    private Button myButton;
    @FXML
    private Label labelForHordLogic;
    @FXML
    private Label myLabel;

    private HardLogic hardLogic;

    /* Интерфейс Initializable в JavaFx мы используем для
       инициализации компонентов сложной логики */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        hardLogic = new HardLogic();
    }


    // STEP 2:
    // ... этот метод отработает при нажатии на кнопку
    public void onAction(ActionEvent actionEvent) {

        // Запишем в лэйбл содержимое из формы...
        myLabel.setText(myTextField.getText());
        // и затем очистим форму
        myTextField.clear();

        // STEP 3:
        labelForHordLogic.setText(hardLogic.doLogic());
    }

}
