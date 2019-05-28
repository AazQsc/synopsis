package by.aazqsc.home.spring_ripper;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class ProfilingHandlerBeanPostProcessor implements BeanPostProcessor {
    private Map<String, Class> map = new HashMap<>();
    private ProfilingController controller = new ProfilingController();

    public ProfilingHandlerBeanPostProcessor() throws Exception {
        // регистрируем м-биан
        MBeanServer platformMBeanServer = ManagementFactory.getPlatformMBeanServer();
        platformMBeanServer.registerMBean(controller, new ObjectName("Profiling", "name", "controller"));
    }

    /*
     * specialization: database,
     *                 ai+algorithms,
     *                 security,
     *                 front
     * way: about, blizzard,
     *
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = bean.getClass();
        if(beanClass.isAnnotationPresent(Profiling.class)){
            map.put(beanName, beanClass);
        }
        return bean;
    }

    /*
     * Если BPP в классе что-то меняют, это должно делаться в автеринитиализэйшен,
     * до того как все прокси накрутятся на него. Иначе мы вернём объект без метаинформации.
     *
     * Proxy.newProxyInstance(); ---> Создает объект из нового класса, который он создаст на лету.
     * принимает:
     *  1. Класслоадер - при помощи которого класс который сгенериться на лету загрузится в перм(j8-heap)
     *  2. Список интерфейсов, которые должен имплиментнуть класс который сгенериться на лету
     *  3. Инвокейшен хендлер - объект который инкапсулирует логику которая попадет во все методы класса
     *       который сгенериться на лету.
     * */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class beanClass = map.get(beanName);
        if(beanClass != null){
            return Proxy.newProxyInstance(
                    beanClass.getClassLoader(),
                    beanClass.getInterfaces(),
                    new InvocationHandler() {
                        @Override
                        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                            if(controller.isEnabled()){
                                System.out.println("Профилиру...");
                                long before = System.nanoTime();
                                /*
                                 * Вызов оригинального метода.
                                 * То что он вернёт сохраним в retValue
                                 */
                                Object retValue =  method.invoke(bean, args);
                                long after = System.nanoTime();
                                System.out.println(after-before);
                                System.out.println("Всё");
                                return retValue;
                            } else {
                                // здесь просто прокси который ничего не делает
                                return method.invoke(bean, args);
                            }
                        }
                    });
        }

        return bean;
    }
}
