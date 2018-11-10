package com.ugatu.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

public class GeometryServlet extends HttpServlet {
    /**
     * Основной метод класса HttpServlet, вызывается сервером для обработки GET запросов.
     *
     * @param req q{@link HttpServletRequest} объект, хранящий запрос,
     *                  сделанный клиентом к сервлету
     *
     * @param resp {@link HttpServletResponse} объект, хранящий ответ,
     *                  который сервлет отправляет на клиент
     *
     * @exception IOException вызывается, если обнаружена ошибка
     *                              ввода-вывода при обработке GET запроса
     *
     * @exception ServletException вызывается, если GET запрос
     *                                  не может быть обработан
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req,resp); //Для пустого метода, передаем запрос дальше к классу родителю
    }

    /**
     * Основной метод класса HttpServlet, вызывается сервером для обработки POST запросов.
     *
     * @param req q{@link HttpServletRequest} объект, хранящий запрос,
     *                  сделанный клиентом к сервлету
     *
     * @param resp {@link HttpServletResponse} объект, хранящий ответ,
     *                  который сервлет отправляет на клиент
     *
     * @exception IOException вызывается, если обнаружена ошибка
     *                              ввода-вывода при обработке POST запроса
     *
     * @exception ServletException вызывается, если POST запрос
     *                                  не может быть обработан
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Получение значения, переданного с клиента, в виде строки
        String reqAttribute = req.getParameter("age");
        try {
            /*
            Переводим строку с возрастом в целое число
            Если операция прошла успешно, то выполняем программу далее до окончания блока try{}
            Если в строке не было целое число (например было буквы), то произойдет ошибка и сразу же начнет выполняться блок catch{}
             */
            int age = Integer.parseInt(reqAttribute);
            /*
            Создаем новый объект календарь
            Записываем в него текущую дату
            Берем из календяря год от текщей даты
             */
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            int currentYear = calendar.get(Calendar.YEAR);
            String answer;
            /*
            Проверяем, если возраст человека больше, чем текущий год
            Если да, то значит он родился до нашей эры
            Если нет, то в н.э.
             */
            if (age >= currentYear){
                answer = (age - currentYear + 1) + " год до н.э.";
            } else {
                answer = (currentYear - age) + " год н.э";
            }
            /*
            Записываем в виде атрибута полученный результат
            атрибут имеет вид <ключ> <значение>
             */
            req.setAttribute("answer", "Ваш год рождения - " +  answer);
        } catch (NumberFormatException exception) {
            /*
            Если произошла ошибка при переводе в численный тип,
            то в атрибут пишем сообщение с ошибкой
             */
            req.setAttribute("answer", "- Нужно написать цифрами. Попробуйте снова.");
        }
        /*
        Перенаправляем наш запрос на вторую страницу,
        где и будем выводить наш ответ
         */
        req.getRequestDispatcher("result.jsp").forward(req, resp);
    }
}