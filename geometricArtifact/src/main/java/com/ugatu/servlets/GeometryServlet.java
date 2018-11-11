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
        String triangle1Attribute = req.getParameter("triangle1");
        String triangle2Attribute = req.getParameter("triangle2");
        String triangle3Attribute = req.getParameter("triangle3");
        try 
        {
            Double triangle1 = Double.parseDouble(triangle1Attribute);
            Double triangle2 = Double.parseDouble(triangle2Attribute);
            Double triangle3 = Double.parseDouble(triangle3Attribute);
            if (!IsTriangle(triangle1, triangle2, triangle3))
            {
                req.setAttribute("answer", "Значения сторон " + triangle1 + ", " + triangle2 + ", " + triangle3 + " не могут составить треугольник!");
            }
            else
            {
                Double triangleP = triangle1 + triangle2 + triangle3;
                Double triangleS = Math.sqrt((triangleP/2)*((triangleP/2)-triangle1)*((triangleP/2)-triangle2)*((triangleP/2)-triangle3));
                req.setAttribute("answer", "Треугольник со сторонами " + triangle1 + ", " + triangle2 + ", " + triangle3 + " имеет:</br>"+
                "Периметр - " + triangleP + "</br>"+
                "Площадь - " + triangleS);
            }

        }
        catch (NumberFormatException exception) 
        {
            String errorText = "Ошибка!\r\n";            
            req.setAttribute("answer", errorText);
        }
        // try {
        //     /*
        //     Переводим строку с возрастом в целое число
        //     Если операция прошла успешно, то выполняем программу далее до окончания блока try{}
        //     Если в строке не было целое число (например было буквы), то произойдет ошибка и сразу же начнет выполняться блок catch{}
        //      */
        //     int age = Integer.parseInt(reqAttribute);
        //     /*
        //     Создаем новый объект календарь
        //     Записываем в него текущую дату
        //     Берем из календяря год от текщей даты
        //      */
        //     Calendar calendar = Calendar.getInstance();
        //     calendar.setTime(new Date());
        //     int currentYear = calendar.get(Calendar.YEAR);
        //     String answer;
        //     /*
        //     Проверяем, если возраст человека больше, чем текущий год
        //     Если да, то значит он родился до нашей эры
        //     Если нет, то в н.э.
        //      */
        //     if (age >= currentYear){
        //         answer = (age - currentYear + 1) + " год до н.э.";
        //     } else {
        //         answer = (currentYear - age) + " год н.э";
        //     }
        //     /*
        //     Записываем в виде атрибута полученный результат
        //     атрибут имеет вид <ключ> <значение>
        //      */
        //     req.setAttribute("answer", "Ваш год рождения - " +  answer);
        // } catch (NumberFormatException exception) {
        //     /*
        //     Если произошла ошибка при переводе в численный тип,
        //     то в атрибут пишем сообщение с ошибкой
        //      */
        //     req.setAttribute("answer", "- Нужно написать цифрами. Попробуйте снова.");
        // }
        /*
        Перенаправляем наш запрос на вторую страницу,
        где и будем выводить наш ответ
         */
        FormVisualisation.GetForm(req);
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

    protected Boolean IsTriangle(Double s1, Double s2, Double s3)
    {
        boolean result = true;
        if (s1 > s2+s3) result = false;
        if (s2 > s1+s3) result = false;
        if (s3 > s1+s2) result = false;
        return result;
    }
}