package com.ugatu.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        DoTriangleThreeSides(req);
        DoTriangleSideHeight(req);
        DoTriangleSidesAngle(req);
        DoRectangleSides(req);
        DoRectangleDiameters(req);
        DoSquare(req);
        DoRightPolygonAmount(req);
        DoRightPolygonAngle(req);
        DoCircle(req);
        DoEllipse(req);
        DoParallelogramm(req);
        //Получаем модель формы для подстановки значений
        FormVisualisation.GetForm(req);
        /*
        Перенаправляем наш запрос на вторую страницу,
        где и будем выводить наш ответ
         */
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

    protected Boolean IsTriangle(Double s1, Double s2, Double s3)
    {
        if (s1 <=0 || s2 <=0 || s3 <=0) return false;
        if (s1 >= s2+s3) return false;
        if (s2 >= s1+s3) return false;
        if (s3 >= s1+s2) return false;
        return true;
    }
    protected Boolean IsTriangleAngle(Double s1, Double s2, Double angle)
    {
        if (s1 <=0 || s2 <=0 || angle <=0) return false;
        if (angle <=0 || angle >=180) return false;
        return true;
    }
    protected Boolean IsTriangle(Double s1, Double s2)
    {
        if (s1 <=0 || s2 <=0) return false;
        return true;
    }
    protected Boolean IsRectangleSides(Double s1, Double s2)
    {
        if (s1 <=0 || s2 <=0) return false;
        return true;
    }
    protected Boolean IsRectangleDiameter(Double d, Double s)
    {
        if (d <=0 || s <=0) return false;
        if (d<=s) return false;
        return true;
    }
    protected Boolean IsSquare(Double s)
    {
        if (s <=0) return false;
        return true;
    }
    protected Boolean IsPolygon(Double s, Double n)
    {
        if (s <=0 || n <=0) return false;
        return true;
    }
    protected Boolean IsPolygonAngle(Double s, Double angle)
    {
        if (s <=0 || angle <=0) return false;
        if (angle <=0 || angle >=180) return false;
        return true;
    }
    protected Boolean IsCircle(Double r)
    {
        if (r<=0) return false;
        return true;
    }
    protected Boolean IsEllipse(Double r1,Double r2)
    {
        if (r1 <=0 || r2 <=0) return false;
        return true;
    }
    protected Boolean IsParallelogramm(Double s1,Double s2, Double h1)
    {
        if (s1 <=0 || s2 <=0 || h1 <=0) return false;
        if (s2< h1) return false;
        return true;
    }

    protected void DoTriangleThreeSides (HttpServletRequest req)
    {
        String triangle1Attribute = req.getParameter("triangle1");
        String triangle2Attribute = req.getParameter("triangle2");
        String triangle3Attribute = req.getParameter("triangle3");
        if ((triangle1Attribute != null) && (triangle2Attribute != null) && (triangle3Attribute != null))
        {
            try 
            {
                Double triangle1 = Double.parseDouble(triangle1Attribute);
                Double triangle2 = Double.parseDouble(triangle2Attribute);
                Double triangle3 = Double.parseDouble(triangle3Attribute);
                if (!IsTriangle(triangle1, triangle2, triangle3))
                {
                    req.setAttribute("error", "Значения сторон " + triangle1 + ", " + triangle2 + ", " + triangle3 + " не могут составить треугольник!");
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
                req.setAttribute("error", errorText);
            }
        }
    }
    protected void DoTriangleSideHeight (HttpServletRequest req)
    {
        String triangleSideAttribute = req.getParameter("triangleSide");
        String triangleSideHeightAttribute = req.getParameter("triangleSideHeight");
        if ((triangleSideAttribute != null) && (triangleSideHeightAttribute != null))
        {
            try 
            {
                Double triangleSide = Double.parseDouble(triangleSideAttribute);
                Double triangleSideHeight = Double.parseDouble(triangleSideHeightAttribute);
                if (!IsTriangle(triangleSide, triangleSideHeight))
                {
                    req.setAttribute("error", "Значения стороны " + triangleSide + " и высоты " + triangleSideHeight + " не могут составить треугольник!");
                }
                else
                {
                    Double triangleS = (triangleSide*triangleSideHeight)/2.0;
                    req.setAttribute("answer", "Треугольник со стороной " + triangleSide + " и высотой " + triangleSideHeight + " имеет:</br>"+
                    "Площадь - " + triangleS + "</br>Для нахождения периметра потребуется больше данных.");
                }
            }
            catch (NumberFormatException exception) 
            {
                String errorText = "Ошибка!\r\n";            
                req.setAttribute("error", errorText);
            }
        }
    }
    protected void DoTriangleSidesAngle (HttpServletRequest req)
    {
        String triangleSides1Attribute = req.getParameter("triangleSides1");
        String triangleSides2Attribute = req.getParameter("triangleSides2");
        String triangleSidesAngleAttribute = req.getParameter("triangleSidesAngle");
        if ((triangleSides1Attribute != null) && (triangleSides2Attribute != null) && (triangleSidesAngleAttribute != null))
        {
            try 
            {
                Double triangleSides1 = Double.parseDouble(triangleSides1Attribute);
                Double triangleSides2 = Double.parseDouble(triangleSides2Attribute);
                Double triangleSidesAngle = Double.parseDouble(triangleSidesAngleAttribute);
                if (!IsTriangleAngle(triangleSides1, triangleSides2, triangleSidesAngle))
                {
                    req.setAttribute("error", "Значения сторон " + triangleSides1 + "," + triangleSides2 + " и угла между ними в  " + triangleSidesAngle + " градусов не могут составить треугольник!");
                }
                else
                {
                    Double triangleSides3 = Math.sqrt(triangleSides1*triangleSides1+triangleSides2*triangleSides2-2*triangleSides1*triangleSides2*Math.cos(Math.toRadians(triangleSidesAngle)));
                    Double triangleP = triangleSides1 + triangleSides2 + triangleSides3;
                    Double triangleS = Math.sqrt((triangleP/2)*((triangleP/2)-triangleSides1)*((triangleP/2)-triangleSides2)*((triangleP/2)-triangleSides3));
                    req.setAttribute("answer", "Треугольник со сторонами " + triangleSides1 + "," + triangleSides2 + " и угла между ними в  " + triangleSidesAngle + " градусов ыимеет:</br>"+
                    "Периметр - " + triangleP + "</br>"+
                    "Площадь - " + triangleS);
                }
            }
            catch (NumberFormatException exception) 
            {
                String errorText = "Ошибка!\r\n";            
                req.setAttribute("error", errorText);
            }
        }
    }
    protected void DoRectangleSides (HttpServletRequest req)
    {
        String rect1Attribute = req.getParameter("rect1");
        String rect2Attribute = req.getParameter("rect2");
        if ((rect1Attribute != null) && (rect2Attribute != null))
        {
            try 
            {
                Double rect1 = Double.parseDouble(rect1Attribute);
                Double rect2 = Double.parseDouble(rect2Attribute);
                if (!IsRectangleSides(rect1, rect2))
                {
                    req.setAttribute("error", "Значения сторон " + rect1 + "," + rect2 + " не могут составить прямоугольник!");
                }
                else
                {
                    Double rectangleP = 2*(rect1+rect2);
                    Double rectangleS = rect1*rect2;
                    req.setAttribute("answer", "Прямоугольник со сторонами " + rect1 + "," + rect2 + " имеет:</br>"+
                    "Периметр - " + rectangleP + "</br>"+
                    "Площадь - " + rectangleS);
                }
            }
            catch (NumberFormatException exception) 
            {
                String errorText = "Ошибка!\r\n";            
                req.setAttribute("error", errorText);
            }
        }
    }
    protected void DoRectangleDiameters (HttpServletRequest req)
    {
        String rectDAttribute = req.getParameter("rectD");
        String rectSAttribute = req.getParameter("rectS");
        if ((rectDAttribute != null) && (rectSAttribute != null))
        {
            try 
            {
                Double rectD = Double.parseDouble(rectDAttribute);
                Double rectS = Double.parseDouble(rectSAttribute);
                if (!IsRectangleDiameter(rectD, rectS))
                {
                    req.setAttribute("error", "Значения диаметра " + rectD + " и стороны " + rectS + " не могут составить прямоугольник!");
                }
                else
                {
                    Double rectS2 = Math.sqrt(rectD*rectD-rectS*rectS);
                    Double rectangleP = 2*(rectS+rectS2);
                    Double rectangleS = rectS*rectS2;
                    req.setAttribute("answer", "Прямоугольник c диаметром " + rectD + " и стороной " + rectS + " имеет:</br>"+
                    "Периметр - " + rectangleP + "</br>"+
                    "Площадь - " + rectangleS);
                }
            }
            catch (NumberFormatException exception) 
            {
                String errorText = "Ошибка!\r\n";            
                req.setAttribute("error", errorText);
            }
        }
    }
    protected void DoSquare (HttpServletRequest req)
    {
        String squareAttribute = req.getParameter("square");
        if (squareAttribute != null)
        {
            try 
            {
                Double square = Double.parseDouble(squareAttribute);
                if (!IsSquare(square))
                {
                    req.setAttribute("error", "Значение стороны " + square + " не позволяет составить квадрат!");
                }
                else
                {
                    Double squareP = 4*square;
                    Double squareS = square*square;
                    req.setAttribute("answer", "Квадрат со сторонами " + square + " имеет:</br>"+
                    "Периметр - " + squareP + "</br>"+
                    "Площадь - " + squareS);
                }
            }
            catch (NumberFormatException exception) 
            {
                String errorText = "Ошибка!\r\n";            
                req.setAttribute("error", errorText);
            }
        }
    }
    protected void DoRightPolygonAmount (HttpServletRequest req)
    {
        String polyAmountSideAttribute = req.getParameter("polyAmountSide");
        String polyAmountAmountAttribute = req.getParameter("polyAmountAmount");
        if (polyAmountSideAttribute != null && polyAmountAmountAttribute != null)
        {
            try 
            {
                Double polyAmountSide = Double.parseDouble(polyAmountSideAttribute);
                Double polyAmountAmount = Double.parseDouble(polyAmountAmountAttribute);
                if (!IsPolygon(polyAmountSide,polyAmountAmount))
                {
                    req.setAttribute("error", "Значение стороны " + polyAmountSide + " не позволяет составить правильный многоугольник с количеством сторон "+ polyAmountAmount +"!");
                }
                else
                {
                    Double polyP = polyAmountSide*polyAmountAmount;
                    Double polyS = (polyAmountSide*polyAmountSide*polyAmountAmount)/(4*Math.tan(Math.toRadians(180/polyAmountAmount)));
                    req.setAttribute("answer", "Правильный многоугольник с "+ polyAmountAmount +" сторонами размером" + polyAmountSide + " имеет:</br>"+
                    "Периметр - " + polyP + "</br>"+
                    "Площадь - " + polyS);
                }
            }
            catch (NumberFormatException exception) 
            {
                String errorText = "Ошибка!\r\n";            
                req.setAttribute("error", errorText);
            }
        }
    }
    protected void DoRightPolygonAngle (HttpServletRequest req)
    {
        String polyAngleSideAttribute = req.getParameter("polyAngleSide");
        String polyAngleAngleAttribute = req.getParameter("polyAngleAngle");
        if (polyAngleSideAttribute != null && polyAngleAngleAttribute != null)
        {
            try 
            {
                Double polyAngleSide = Double.parseDouble(polyAngleSideAttribute);
                Double polyAngleAngle = Double.parseDouble(polyAngleAngleAttribute);
                if (!IsPolygonAngle(polyAngleSide,polyAngleAngle))
                {
                    req.setAttribute("error", "Значение стороны " + polyAngleSide + " и угла "+ polyAngleAngle +" не позволяет составить правильный многоугольник!");
                }
                else
                {
                    Double polyP = polyAngleSide*360/(180-polyAngleAngle);
                    Double polyS = (360*polyAngleSide*polyAngleSide/(180-polyAngleAngle))/(4*Math.tan(Math.toRadians(90-polyAngleAngle/2)));
                    req.setAttribute("answer", "Правильный многоугольник с сторонами размером" + polyAngleSide + " и углом между двумя сторонами "+ polyAngleAngle+" имеет:</br>"+
                    "Периметр - " + polyP + "</br>"+
                    "Площадь - " + polyS);
                }
            }
            catch (NumberFormatException exception) 
            {
                String errorText = "Ошибка!\r\n";            
                req.setAttribute("error", errorText);
            }
        }
    }
    protected void DoCircle (HttpServletRequest req)
    {
        String circleRadiusAttribute = req.getParameter("circleRadius");
        if (circleRadiusAttribute != null)
        {
            try 
            {
                Double circleRadius = Double.parseDouble(circleRadiusAttribute);
                if (!IsCircle(circleRadius))
                {
                    req.setAttribute("error", "Значение радиуса " + circleRadius + " не позволяет составить окружность!");
                }
                else
                {
                    Double circleP = 2*circleRadius*Math.PI;
                    Double circleS = Math.PI*circleRadius*circleRadius;
                    req.setAttribute("answer", "Окружность радиуса " + circleRadius + " имеет:</br>"+
                    "Длина окружности (периметр) - " + circleP + "</br>"+
                    "Площадь - " + circleS);
                }
            }
            catch (NumberFormatException exception) 
            {
                String errorText = "Ошибка!\r\n";            
                req.setAttribute("error", errorText);
            }
        }
    }
    protected void DoEllipse (HttpServletRequest req)
    {
        String ellipseRaduis1Attribute = req.getParameter("ellipseRaduis1");
        String ellipseRaduis2Attribute = req.getParameter("ellipseRaduis2");
        if (ellipseRaduis1Attribute != null && ellipseRaduis2Attribute != null)
        {
            try 
            {
                Double ellipseRaduis1 = Double.parseDouble(ellipseRaduis1Attribute);
                Double ellipseRaduis2 = Double.parseDouble(ellipseRaduis2Attribute);
                if (!IsEllipse(ellipseRaduis1,ellipseRaduis2))
                {
                    req.setAttribute("error", "Значения полуосей " + ellipseRaduis1 + " и " + ellipseRaduis2 + " не позволяют составить эллипс!");
                }
                else
                {
                    Double ellipseP = ellipseRaduis1*ellipseRaduis2*Math.PI;
                    Double ellipseS = 2*Math.PI*Math.sqrt((ellipseRaduis1*ellipseRaduis1+ellipseRaduis2*ellipseRaduis2)/2);
                    req.setAttribute("answer", "Эллипс с полуосями " + ellipseRaduis1 + " и " + ellipseRaduis2 + " имеет:</br>"+
                    "Длина окружности (периметр) - " + ellipseP + "</br>"+
                    "Площадь - " + ellipseS);
                }
            }
            catch (NumberFormatException exception) 
            {
                String errorText = "Ошибка!\r\n";            
                req.setAttribute("error", errorText);
            }
        }
    }
    protected void DoParallelogramm (HttpServletRequest req)
    {
        String paral1Attribute = req.getParameter("paral1");
        String paral2Attribute = req.getParameter("paral2");
        String paralH1Attribute = req.getParameter("paralH1");
        if ((paral1Attribute != null) && (paral2Attribute != null) && (paralH1Attribute != null))
        {
            try 
            {
                Double paral1 = Double.parseDouble(paral1Attribute);
                Double paral2 = Double.parseDouble(paral2Attribute);
                Double paralH1 = Double.parseDouble(paralH1Attribute);
                if (!IsParallelogramm(paral1, paral2, paralH1))
                {
                    req.setAttribute("error", "Значения сторон " + paral1 + "," + paral2 + " и высоты к первой стороне  " + paralH1 + " не могут составить параллелограмм!");
                }
                else
                {
                    Double paralP = paral1*paralH1;
                    Double paralS = 2*(paral1+paral2);
                    req.setAttribute("answer", "Паралелограмм со сторонами " + paral1 + "," + paral2 + " и высоты к первой стороне  " + paralH1 + " имеет:</br>"+
                    "Периметр - " + paralP + "</br>"+
                    "Площадь - " + paralS);
                }
            }
            catch (NumberFormatException exception) 
            {
                String errorText = "Ошибка!\r\n";            
                req.setAttribute("error", errorText);
            }
        }
    }
}