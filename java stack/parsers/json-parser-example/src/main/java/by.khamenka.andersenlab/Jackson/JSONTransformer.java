package by.khamenka.andersenlab.Jackson;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import by.khamenka.andersenlab.POJO.User;
import org.codehaus.jackson.map.ObjectMapper;

public class JSONTransformer {
    /*
     * Основной объект по средствам которого Jackson работает с POJO
     */
    ObjectMapper mapper = new ObjectMapper();

    /*
     * В этом методе:
     * - находим файл.json и класс в который будет выполнено преобразования
     * - открываем поток
     * - выполняем преобразование и возвращаем полученную сущность: user'а
     */
    public User readValue() {
        String filepath = System.getProperty("user.dir")
                + File.separator
                + "user.json";

        try {
            User user = mapper.readValue(new FileInputStream(filepath), User.class);
            return user;
        } catch (IOException ex) {
            System.out.println("File can't be read.");
            ex.printStackTrace();
        }

        return null;
    }

    /*
     * В этом методе:
     * - находи корень classpath (сюда будет записан файл-json)
     * - выбираем класс pojo который будет преобразован в json
     * - открываем исходящий поток и выполняем преобразование.
     */
    public void writeValue(User user) {
        try {
            // Выводим в json файл
            mapper.writeValue(new FileOutputStream(
                    System.getProperty("user.dir")
                            + File.separator
                            + "user.json"), user);

            System.out.println("JSON - " + mapper.writeValueAsString(user));
        } catch (IOException ex) {
            System.out.println("File can't be read.");
            ex.printStackTrace();
        }
    }
}
