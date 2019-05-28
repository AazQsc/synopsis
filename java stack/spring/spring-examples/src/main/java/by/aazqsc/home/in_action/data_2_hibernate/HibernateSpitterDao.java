package by.aazqsc.home.in_action.data_2_hibernate;

import by.aazqsc.home.in_action.data_1_jdbc.Spitter;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

/*
 * По хорошему этот класс должен быть implements SpitterDAO
 * Аннотация - репозиторий - опциональна, здесь мы её используем для преобразования исключений
 * ещё можно снять часть нагрузки с xml - но мы объявляем бин явно
 */
@Repository
public class HibernateSpitterDao {
    private SessionFactory sessionFactory;

    public HibernateSpitterDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // Извлекает текущий сеанс из фабрики SessionFactory
    private Session currentSession() {
        try {
            return sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            return sessionFactory.openSession();
        }
    }

    public void addSpitter(Spitter spitter) {
        // Использует текущий сеанс
        currentSession().save(spitter);
    }

    //@Override
    public Spitter getSpitterById(long id) {
        // Использует текущий сеанс
        return (Spitter) currentSession().get(Spitter.class, id);
    }

    public void saveSpitter(Spitter spitter) {
        // Использует текущий сеанс
        currentSession().update(spitter);
    }
}
