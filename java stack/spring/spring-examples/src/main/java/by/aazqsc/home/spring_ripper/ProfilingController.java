package by.aazqsc.home.spring_ripper;


/*
 * этот объект зарегистрирован в м-бин сервер
 *
 * Конвенция:
 *  1. имплиментнуть интерфейс "имя этого класса + MBean"
 *  2. в этом интерфейсе мы должны указать все методы к которым gmx-consul будет иметь доступ
 *  3. нужно этот MBean зарегистрировать
 *  MBeanServer platformMBeanServer = ManagementFactory.getPlatformMBeanServer();
 *  platformMBeanServer.registerMBean(controller, new ObjectName("Profiling", "name", "controller"));
 */
public class ProfilingController implements ProfilingControllerMBean {
    private boolean enabled = true;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
