package by.aazqsc.home.spring_ripper;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/*
* Если класс деприкейтед, заменить его на имплиментацию
* */
@Retention(RetentionPolicy.RUNTIME)
public @interface DepricatedClass {
    Class newImpl();
}
