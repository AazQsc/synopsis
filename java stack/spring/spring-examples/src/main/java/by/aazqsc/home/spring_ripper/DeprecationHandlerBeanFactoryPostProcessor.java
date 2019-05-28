package by.aazqsc.home.spring_ripper;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/*
* 1. Получаем имена дефенишенов
* 2. идем по именам
*   1 получаем дифенишен по имени
*   2 получаем имя класа бина из дефенишена
*   3 получаем класс по имени класса
*   4 получаем аннотацию деприкейтед из класса
*   если получили...
*   5 в биндефенишене меняем текущий класс на тот который указан в аннотации как деприкейтед
* */
public class DeprecationHandlerBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        String[] names = beanFactory.getBeanDefinitionNames();
        for (String name : names) {
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(name);
            String beanClassName = beanDefinition.getBeanClassName();
            try {
                Class<?> beanClass = Class.forName(beanClassName);
                DepricatedClass annotation = beanClass.getAnnotation(DepricatedClass.class);
                if(annotation!=null){
                    beanDefinition.setBeanClassName(annotation.newImpl().getName());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
