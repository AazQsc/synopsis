package by.aazqsc.home.spring_ripper;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Random;

public class InjectRandomIntAnnotationBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Field[] fields = bean.getClass().getDeclaredFields();
        for (Field field : fields) {
            InjectRandomInt annotation  = field.getAnnotation(InjectRandomInt.class);
            if(annotation != null){
                int min = annotation.min();
                int max = annotation.max();
                Random random = new Random();
                int i = min + random.nextInt(max-min);
                field.setAccessible(true); // доступ к приватным полям
                ReflectionUtils.setField(field, bean, i);
            }

        }
        //не забудь вернуть тот объект который мы получили
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        //не забудь вернуть тот объект который мы получили
        return bean;
    }
}
