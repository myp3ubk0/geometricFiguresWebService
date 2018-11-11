package com.ugatu.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FormVisualisation extends HttpServlet {
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
        GetForm(req);
        /*
        Перенаправляем наш запрос на вторую страницу,
        где и будем выводить наш ответ
         */
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
    public static void GetForm (HttpServletRequest req) throws ServletException, IOException
    {
        String reqAttribute = req.getParameter("figureType");
        try {
            /*
            Переводим строку с возрастом в целое число
            Если операция прошла успешно, то выполняем программу далее до окончания блока try{}
            Если в строке не было целое число (например было буквы), то произойдет ошибка и сразу же начнет выполняться блок catch{}
             */
            int figureType = Integer.parseInt(reqAttribute);
            String formText = "";
            if (figureType == 1)
            {
                formText = "<div>"+
                "<h2>Для расчета параметров треугольника потребуется три стороны</h2>"+
                "Сторона 1"+
                "<input type='text' name='triangle1' value='0' placeholder='Введите значение'></br>"+
                "Сторона 2"+
                "<input type='text' name='triangle2' value='0' placeholder='Введите значение'></br>"+
                "Сторона 3"+
                "<input type='text' name='triangle3' value='0' placeholder='Введите значение'></br>"+
                "</div>"+
                "<input type='submit' value='Рассчитать!' />";
            }
            if (figureType == 2)
            {
                formText = "<div>"+
                "<h1>2 - Ответь, сколько тебе лет, человек!?</h1>"+
                "<input type='text' name='age' value='' placeholder='Введите ваш возраст'>"+
                "</div>";
            }
            if (figureType == 3)
            {
                formText = "<div>"+
                "<h1>3 - Ответь, сколько тебе лет, человек!?</h1>"+
                "<input type='text' name='age' value='' placeholder='Введите ваш возраст'>"+
                "</div>";
            }
            if (figureType == 4)
            {
                formText = "<div>"+
                "<h1>4 - Ответь, сколько тебе лет, человек!?</h1>"+
                "<input type='text' name='age' value='' placeholder='Введите ваш возраст'>"+
                "</div>";
            }
            if (figureType == 5)
            {
                formText = "<div>"+
                "<h1>5 - Ответь, сколько тебе лет, человек!?</h1>"+
                "<input type='text' name='age' value='' placeholder='Введите ваш возраст'>"+
                "</div>";
            }
            req.setAttribute("form", formText);
            req.setAttribute("valueOfForm", figureType);

            
        } catch (NumberFormatException exception) {
            /*
            Если произошла ошибка при переводе в численный тип,
            то в атрибут пишем сообщение с ошибкой
             */
            req.setAttribute("form", "");
        }
    }
}