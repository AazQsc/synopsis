package by.khamenka.andersenlab.servlets;

import by.khamenka.andersenlab.services.calculator.Calculator;
import by.khamenka.andersenlab.services.counter.CounterManager;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class CalculatorServlet extends HttpServlet {
    /**
     * thread-safe: использовать локальные переменные и атрибуты сессии
     * not-thr-safe: глобальные переменные
     */
    private String initParamCookie;
    private CounterManager counterManager;
    private Calculator calculator;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        /*
         * Загружаем параметры описанные в wed.xml
         * параметры содержатся в секции <init-param> текущего сервлета
         */
        this.initParamCookie = config.getInitParameter("Cookie");
        this.counterManager = new CounterManager();
        this.calculator = new Calculator();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        executeExpression(request);

        increaseCounters(request, response);
        jspDelegate(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        increaseCounters(request, response);
        jspDelegate(request, response);
    }

    /**
     * Вызывая этот метод мы делигируем работу с
     * request и response указанной jsp
     */
    private void jspDelegate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /*
         * Получаем доступ к ресурсу используя .getRequestDispatcher(URL)
         * .forward() - передаём управление выбранному ресурсу.
         */
        request.setCharacterEncoding("UTF-8");
        request.getRequestDispatcher("/pages/calculator.jsp").forward(request, response);
    }

    /**
     * Метод занимается подсчётом количества обращёний к данному сервлету.
     * Показатели счётчиков добавляются в request.
     * <p>
     * Работу cookie можно выключить установив в параметрах инициализации значение
     * отличное от "true" см. init-param в web.xml
     */
    private void increaseCounters(HttpServletRequest request, HttpServletResponse response) {
        if (initParamCookie.equals("true")) {
            /*
             * Порядок работы с куки можно найти в описании
             * метода .increaseCookieCounter(request)
             */
            Cookie cookie = counterManager.increaseCookieCounter(request);
            response.addCookie(cookie);
            /*
             * .setAttribute(имя атрибута, значение), когда мы обратимся к ресурсу(JSP),
             * там будет найден соответствующий атрибут ${cookieEntranceCounter}, в него и будет
             * подставлено значение.
             */
            request.setAttribute("cookieEntranceCounter", cookie.getValue());
        }
        request.setAttribute("entranceCounter", counterManager.increaseSessionCounter(request));
    }

    /**
     * прим.
     * метод вышел несколько функционильно перегруженным...
     * <p>
     * В методе вычисляется значение выражения и сохраняется
     * само выражение и его ответ в ServletContext.
     * <p>
     * Получаем выражение введённое пользователем.
     * Вычисляем ответ - calculate(expression)
     * Добавляем ответ как атрибут request.
     * Проверяем есть ли в контексте getServletContext()
     *      сохраненная история операций пользователя.
     * Если есть, добавляем ещё одну операцию
     * Если нет, добавляем первую.
     */
    private void executeExpression(HttpServletRequest request)
            throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");

        String expression = request.getParameter("expression");
        String answer = calculator.calculate(expression);

        request.setAttribute("answer", answer);
        String history = (String) getServletContext().getAttribute(
                "service.calculator.history");

        if (history != null) {
            getServletContext().setAttribute(
                    "service.calculator.history",
                    history +
                            "expression: " + expression +
                            " -----> " + answer + "<br>"
            );
        } else {
            getServletContext().setAttribute(
                    "service.calculator.history",
                    "expression: " + expression +
                            " -----> " + answer + "<br>"
            );
        }
    }
}
