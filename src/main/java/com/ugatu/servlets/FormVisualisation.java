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
     * @param req {@link HttpServletRequest} объект, хранящий запрос,
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
        GetForm(req); //оформление ответа и формирование атрибутов
        req.getRequestDispatcher("index.jsp").forward(req, resp); //оформление jsp документа и передача ему управления
    }

    /**
     * Основной метод класса HttpServlet, вызывается сервером для обработки POST запросов.
     *
     * @param req {@link HttpServletRequest} объект, хранящий запрос,
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
        GetForm(req); //оформление ответа и формирование атрибутов
        req.getRequestDispatcher("index.jsp").forward(req, resp); //оформление jsp документа и передача ему управления
    }

    /**
     * Метод обработки аргументов из запроса и передачи ему ответа в виде html строки
     *
     * @param req {@link HttpServletRequest} объект, хранящий запрос,
     *                  сделанный клиентом к сервлету
     *
     * @exception IOException вызывается, если обнаружена ошибка
     *                              ввода-вывода при обработке POST запроса
     *
     * @exception ServletException вызывается, если POST запрос
     *                                  не может быть обработан
     */
    public static void GetForm (HttpServletRequest req) throws ServletException, IOException
    {
        String reqAttribute = req.getParameter("figureType"); //передача значения атрибута "тип фигуры" в строковую переменную
        /*
        Переводим строку с типом фигуры в целое число
        Если операция прошла успешно, то выполняем команды до окончания блока try{}
        Если в строке кроме цифр были иные символы, то произойдет ошибка и сразу же начнет выполняться блок catch{}
        */
        try {
            int figureType = Integer.parseInt(reqAttribute); //перевод строки в целое число
            String formText = ""; //переменная, отвечающая за разметку формы на странице
            //Здесь и далее проходит проверка на значение типа фигуры
            //В зависимости от номера, будет появляться соответствующая ей форма
            if (figureType == 1)
            {
                formText = "<div>"+
                "<h3>Для расчета параметров треугольника потребуется три стороны</h3>"+
                GetAnInputContainer("Сторона 1","triangle1","Введите значение")+
                GetAnInputContainer("Сторона 2","triangle2","Введите значение")+
                GetAnInputContainer("Сторона 3","triangle3","Введите значение")+
                "</div>"+
                "<input type='submit' value='Рассчитать!' />";
            }
            if (figureType == 2)
            {
                formText = "<div>"+
                "<h3>Для расчета параметров треугольника потребуется сторона и прилежащая к ней высота</h3>"+
                GetAnInputContainer("Сторона","triangleSide","Введите значение")+
                GetAnInputContainer("Высота","triangleSideHeight","Введите значение")+
                "</div>"+
                "<input type='submit' value='Рассчитать!' />";
            }
            if (figureType == 3)
            {
                formText = "<div>"+
                "<h3>Для расчета параметров треугольника потребуется две стороны и угол между ними</h3>"+
                GetAnInputContainer("Сторона 1 ","triangleSides1","Введите значение")+
                GetAnInputContainer("Сторона 2 ","triangleSides2","Введите значение")+
                GetAnInputContainer("Угол (в градусах)","triangleSidesAngle","Введите значение")+
                "</div>"+
                "<input type='submit' value='Рассчитать!' />";
            }
            if (figureType == 4)
            {
                formText = "<div>"+
                "<h3>Для расчета параметров прямоугольника потребуются его стороны</h3>"+
                GetAnInputContainer("Сторона 1 ","rect1","Введите значение")+
                GetAnInputContainer("Сторона 2 ","rect2","Введите значение")+
                "</div>"+
                "<input type='submit' value='Рассчитать!' />";
            }
            if (figureType == 5)
            {
                formText = "<div>"+
                "<h3>Для расчета параметров прямоугольника потребуются его диагональ и сторона</h3>"+
                GetAnInputContainer("Диагональ ","rectD","Введите значение")+
                GetAnInputContainer("Сторона ","rectS","Введите значение")+
                "</div>"+
                "<input type='submit' value='Рассчитать!' />";
            }
            if (figureType == 6)
            {
                formText = "<div>"+
                "<h3>Для расчета параметров квадрата потребуется его сторона</h3>"+
                GetAnInputContainer("Сторона ","square","Введите значение")+
                "</div>"+
                "<input type='submit' value='Рассчитать!' />";
            }
            if (figureType == 7)
            {
                formText = "<div>"+
                "<h3>Для расчета параметров правильного многоугольника потребуется сторона и количество сторон</h3>"+
                GetAnInputContainer("Сторона ","polyAmountSide","Введите значение")+
                GetAnInputContainer("Количество сторон ","polyAmountAmount","Введите значение")+
                "</div>"+
                "<input type='submit' value='Рассчитать!' />";
            }
            if (figureType == 8)
            {
                formText = "<div>"+
                "<h3>Для расчета параметров правильного многоугольника потребуется сторона и угол между двумя сторонами</h3>"+
                GetAnInputContainer("Сторона ","polyAngleSide","Введите значение")+
                GetAnInputContainer("Угол между сторонами ","polyAngleAngle","Введите значение")+
                "</div>"+
                "<input type='submit' value='Рассчитать!' />";
            }
            if (figureType == 9)
            {
                formText = "<div>"+
                "<h3>Для расчета параметров окружности необходим радиус (половина диаметра)</h3>"+
                GetAnInputContainer("Радиус ","circleRadius","Введите значение")+
                "</div>"+
                "<input type='submit' value='Рассчитать!' />";
            }
            if (figureType == 10)
            {
                formText = "<div>"+
                "<h3>Для расчета параметров эллипса необходимы малая и большая полуоси (половины осей эллипса)</h3>"+
                GetAnInputContainer("Малая полуось ","ellipseRaduis1","Введите значение")+
                GetAnInputContainer("Большая полуось ","ellipseRaduis2","Введите значение")+
                "</div>"+
                "<input type='submit' value='Рассчитать!' />";
            }
            if (figureType == 11)
            {
                formText = "<div>"+
                "<h3>Для расчета параметров параллелограмма необходимы две стороны и высота (проведенная к первой стороне!)</h3>"+
                GetAnInputContainer("Сторона 1","paral1","Введите значение")+
                GetAnInputContainer("Высота проведенная к стороне 1","paralH1","Введите значение")+
                GetAnInputContainer("Сторона 2","paral2","Введите значение")+
                "</div>"+
                "<input type='submit' value='Рассчитать!' />";
            }
            req.setAttribute("form", formText); //задание атрибута с разметкой формы
            req.setAttribute("valueOfForm", figureType); //задание атрибута с номером типа формы (для поля с выбором)

        } catch (NumberFormatException exception) {
            // Если произошла ошибка при переводе в численный тип, то в атрибут ошибки пишем сообщение с ошибкой
            req.setAttribute("error", "Произошла ошибка работы. Попробуйте выбрать другой режим...");
        }
    }

    /**
     * Метод позволяет получить код контейнера ввода (текствое описание и поле для ввода)
     *
     * @param description {@link String} описание поля
     *
     * @param name {@link String} название поля (id)
     *
     * @param placeholder {@link String} сообщение при отсутствии значения
     * 
     */
    private static String GetAnInputContainer(String description, String name, String placeholder)
    {
        return "<dir class='InputContainer'><span class='InputContainerDescription'>"+ description +"</span>"+
        "<input class='InputContainerInput' type='text' name='" + name +"' value='0' placeholder='"+ placeholder +"'></br></dir>";
    }
}