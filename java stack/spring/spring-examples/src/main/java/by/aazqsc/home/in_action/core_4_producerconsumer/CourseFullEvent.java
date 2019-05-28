package by.aazqsc.home.in_action.core_4_producerconsumer;

import org.springframework.context.ApplicationEvent;


/*
 * Создаем собственное событие ApplicationEvent,
 * Которое сможет словить ContextListener
 */
public class CourseFullEvent extends ApplicationEvent {
    private Course course;

    public CourseFullEvent(Object source, Course course) {
        super(source);
        this.course = course;
    }
    public Course getCourse() {
        return course;
    }
}