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
                GetAnInputContainer("Сторона 1","triangle1","Введите значение")+
                GetAnInputContainer("Сторона 2","triangle2","Введите значение")+
                GetAnInputContainer("Сторона 3","triangle3","Введите значение")+
                "</div>"+
                "<input type='submit' value='Рассчитать!' />";
            }
            if (figureType == 2)
            {
                formText = "<div>"+
                "<h2>Для расчета параметров треугольника потребуется сторона и прилежащая к ней высота</h2>"+
                GetAnInputContainer("Сторона","triangleSide","Введите значение")+
                GetAnInputContainer("Высота","triangleSideHeight","Введите значение")+
                "</div>"+
                "<input type='submit' value='Рассчитать!' />";
            }
            if (figureType == 3)
            {
                formText = "<div>"+
                "<h2>Для расчета параметров треугольника потребуется две стороны и угол между ними</h2>"+
                GetAnInputContainer("Сторона 1 ","triangleSides1","Введите значение")+
                GetAnInputContainer("Сторона 2 ","triangleSides2","Введите значение")+
                GetAnInputContainer("Угол (в градусах)","triangleSidesAngle","Введите значение")+
                "</div>"+
                "<input type='submit' value='Рассчитать!' />";
            }
            if (figureType == 4)
            {
                formText = "<div>"+
                "<h2>Для расчета параметров прямоугольника потребуются его стороны</h2>"+
                GetAnInputContainer("Сторона 1 ","rect1","Введите значение")+
                GetAnInputContainer("Сторона 2 ","rect2","Введите значение")+
                "</div>"+
                "<input type='submit' value='Рассчитать!' />";
            }
            if (figureType == 5)
            {
                formText = "<div>"+
                "<h2>Для расчета параметров прямоугольника потребуются его диагональ и сторона</h2>"+
                GetAnInputContainer("Диагональ ","rectD","Введите значение")+
                GetAnInputContainer("Сторона ","rectS","Введите значение")+
                "</div>"+
                "<input type='submit' value='Рассчитать!' />";
            }
            if (figureType == 6)
            {
                formText = "<div>"+
                "<h2>Для расчета параметров квадрата потребуется его сторона</h2>"+
                GetAnInputContainer("Сторона ","square","Введите значение")+
                "</div>"+
                "<input type='submit' value='Рассчитать!' />";
            }
            if (figureType == 7)
            {
                formText = "<div>"+
                "<h2>Для расчета параметров правильного многоугольника потребуется сторона и количество сторон</h2>"+
                GetAnInputContainer("Сторона ","polyAmountSide","Введите значение")+
                GetAnInputContainer("Количество сторон ","polyAmountAmount","Введите значение")+
                "</div>"+
                "<input type='submit' value='Рассчитать!' />";
            }
            if (figureType == 8)
            {
                formText = "<div>"+
                "<h2>Для расчета параметров правильного многоугольника потребуется сторона и угол между двумя сторонами</h2>"+
                GetAnInputContainer("Сторона ","polyAngleSide","Введите значение")+
                GetAnInputContainer("Угол между сторонами ","polyAngleAngle","Введите значение")+
                "</div>"+
                "<input type='submit' value='Рассчитать!' />";
            }
            req.setAttribute("form", formText);
            req.setAttribute("valueOfForm", figureType);

            
        } catch (NumberFormatException exception) {
            /*
            Если произошла ошибка при переводе в численный тип,
            то в атрибут пишем сообщение с ошибкой
             */
            req.setAttribute("error", "Произошла ошибка работы. Попробуйте выбрать другой режим...");
        }
    }
    private static String GetAnInputContainer(String description, String name, String placeholder)
    {
        return "<dir class='InputContainer'><span class='InputContainerDescription'>"+ description +"</span>"+
        "<input class='InputContainerInput' type='text' name='" + name +"' value='0' placeholder='"+ placeholder +"'></br></dir>";
    }
}