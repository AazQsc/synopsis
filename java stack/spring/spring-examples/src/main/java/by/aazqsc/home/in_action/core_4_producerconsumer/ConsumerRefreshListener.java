package by.aazqsc.home.in_action.core_4_producerconsumer;

import org.springframework.context.ApplicationListener;

// Ловим созданное нами событие здесь.
public class ConsumerRefreshListener implements ApplicationListener<CourseFullEvent> {

    // в этом методе (consume)
    @Override
    public void onApplicationEvent(CourseFullEvent event) {
        System.out.print("Словили событие! : ");
        System.out.println(event.getCourse().getNameOfCourse());
    }
}
