package by.aazqsc.home.in_action.data_1_jdbc;

// мы не реализуем всю мощь интерфейса DAO, но можем = )
public interface SpitterDAO {

    //вставка элемента в бд
    void addSpitter(Spitter spitter);

    //чтение элемента по id
    Spitter getSpitterById(long id);
}
