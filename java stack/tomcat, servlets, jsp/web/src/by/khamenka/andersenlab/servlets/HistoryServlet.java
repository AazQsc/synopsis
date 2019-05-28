package by.khamenka.andersenlab.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HistoryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        /*
        * Делегируем работу с request и response history.jsp
        * добавив атрибут service.calculator.history, в котором хранится информация
        * о проведённых математических операциях.
        * */
        request.setAttribute("history",
                getServletContext().getAttribute("service.calculator.history"));
        request.getRequestDispatcher("/pages/history.jsp").forward(request, response);
    }
}
