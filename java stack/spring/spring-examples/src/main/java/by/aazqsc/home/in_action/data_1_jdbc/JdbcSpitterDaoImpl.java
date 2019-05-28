package by.aazqsc.home.in_action.data_1_jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

public class JdbcSpitterDaoImpl implements SpitterDAO {
    private static final String SQL_INSERT_SPITTER =
            "insert into spitter (username, password, fullName) values (?, ?, ?)";
    private static final String SQL_SELECT_SPITTER =
            "select id, username, password, fullname from spitter where id = ?";
    private static final String SQL_UPDATE_SPITTER =
            "update spitter set username = ?, password = ?, fullname = ?"
                    + "where id = ?";

    /*
     * Создать экземпляр simpleJdbcTemplate не сложно, но если есть больше
     * одного класса DAOImp мы начинаем получать большое количество повторяющегося кода
     *
     * Хорошим решением будет создать абстрактный родительский класс
     *
     * В действительности в весне уже есть три таких класса!
     * JdbcDaoSupport , SimpleJdbcDaoSupport и NamedParameterJdbcDaoSupport
     * – по одному на каждый шаблон JDBC в Spring.
     *
     * Они обеспечивают удобное api для доступа к шаблонам, в виде метода
     * getJdbcTemplate()
     *
     * Так же имеется возможность сконфигурировать темплейты, чтобы явно
     * не прописывать их в коде вообще.
     */
    // Шаблон для упрощения работы с бд
    private JdbcTemplate jdbcTemplate;

    // Будем внедрять шаблон через этот метод
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Операция вставки в бд с помощью data_1_jdbc
    @Override
    public void addSpitter(Spitter spitter) {
        /* В таком подходе важно сохранить порядок аргументов, лучше использовать
         * Map с параметрами, а в запросе указать имена параметров, вместо вопросиков
         */

        jdbcTemplate.update(SQL_INSERT_SPITTER,
                spitter.getUsername(),
                spitter.getPassword(),
                spitter.getFullName() );
    }

    @Override
    public Spitter getSpitterById(long id) {
        return jdbcTemplate.queryForObject(
                /*
                 *Первый аргумент - значение типа String ,
                 *содержащее строку SQL-запроса для выборки данных;
                 */
                SQL_SELECT_SPITTER,
                /*
                 * Второй аргумент - объект ParameterizedRowMapper,
                 * извлекающий значения из объекта ResultSet и конструирующий объект предметной области
                 */
                (rs, rowNum) -> {
                    Spitter spitter = new Spitter();
                    spitter.setId(rs.getLong(1));
                    spitter.setUsername(rs.getString(2));
                    spitter.setPassword(rs.getString(3));
                    spitter.setFullName(rs.getString(4));
                    return spitter;
                },
                /*
                 * Третий аргумент - список аргументов переменной длины со значениями
                 * для связывания с индексированными параметрами запроса
                 */
                id
        );
    }
}
