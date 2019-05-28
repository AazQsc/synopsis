package by.khamenka.andersenlab.services.counter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Класс иллюстрирует работу cookie и атрибутов сессии.
 */
public class CounterManager {

    /**
     * Порядок работы с cookie
     * 1. Получить объект cookie из request:
     *      request.isRequestedSessionIdFromCookie() - есть ли куки?
     *      request.getCookies() - возвращает массив куки
     * 2. Работаем с полученными значениями:
     *      cookie.getValue() & cookie.setValue()
     * 3. Отправляем cookie клиенту:
     *      response.addCookie()
     * <p>
     * прим. полезные методы cookie
     * .setComment(String)
     * .setMaxAge(int)      // время жизни cookie в секундах
     * <p>
     * @return возврящает количество вызовов данного метода
     * текущим пользователем.
     * <p>
     * В основе работы метода лежит работа с cookie.
     * Получаем массив куки, если "cookieEntranceCounter" присутствует
     * в полученном массиве, инкриментируем значение находящееся в нём
     * и возвращаем его. Если такого cookie нет создаём его и возвращаем.
     *
     *
     */
    public Cookie increaseCookieCounter(HttpServletRequest request){
        if (request.isRequestedSessionIdFromCookie()){
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("cookieEntranceCounter")){
                    int counter = Integer.parseInt(cookie.getValue());
                    cookie.setValue(String.valueOf(++counter));
                    return cookie;
                }
            }
        }
        Cookie cookie = new Cookie("cookieEntranceCounter", "1");
        return cookie;
    }


    /**
     * Работа с атрибутами сессии.
     * <p>
     * @return возврящает количество вызовов данного метода,
     * в рамках текущей сессии.
     * <p>
     * Если текущая сессия является новой, возвращает 1.
     * Иначе инкриметирует значение, и возвращает это значение.
     */
    public int increaseSessionCounter(HttpServletRequest request) {
        if (request.getSession().isNew()) {
            request.getSession().setAttribute("sessionEntranceCounter", 1);
            return 1;
        } else {
            int count = (int) request.getSession().getAttribute("sessionEntranceCounter");
            request.getSession().setAttribute("sessionEntranceCounter", ++count);
            return count;
        }
    }
}
