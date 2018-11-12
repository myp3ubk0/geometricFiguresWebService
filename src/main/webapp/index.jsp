<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="style.css"> 
    <meta charset="utf-8">
    <title>Сервис расчета геометрических фигур</title>
</head>
    <body>
        <h1>Сервис расчета геометрических фигур</h1>
        
        <!-- Форма, содержащая в себе кнопку и техтовое поле.
             По нажатию на кнопку отправит POST запрос на сервлет-->
        <form action="getForm" method="POST" id="foobar">
            <div class='InputContainer'>
            <h3 class='InputContainerDescription'>Выберите тип рассчитываемой фигуры</h3>
                <select id="foobarSelection" class='InputContainerInput' name="figureType" onchange="document.getElementById('foobar').submit()">
                    <% if (request.getAttribute("valueOfForm") != null && (int)request.getAttribute("valueOfForm") == 0) {%><option value="0" selected >***Выберите тип фигуры***</option><% } %>
                    <% if (request.getAttribute("valueOfForm") == null || (int)request.getAttribute("valueOfForm") != 0) {%><option value="0" >***Выберите тип фигуры***</option><% } %>
                    <% if (request.getAttribute("valueOfForm") != null && (int)request.getAttribute("valueOfForm") == 1) {%><option value="1" selected >Треугольник (по трем сторонам)</option><% } %>
                    <% if (request.getAttribute("valueOfForm") == null || (int)request.getAttribute("valueOfForm") != 1) {%><option value="1" >Треугольник (по трем сторонам)</option><% } %>
                    <% if (request.getAttribute("valueOfForm") != null && (int)request.getAttribute("valueOfForm") == 2) {%><option value="2" selected >Треугольник (по стороне и выстоте)</option><% } %>
                    <% if (request.getAttribute("valueOfForm") == null || (int)request.getAttribute("valueOfForm") != 2) {%><option value="2" >Треугольник (по стороне и выстоте)</option><% } %>
                    <% if (request.getAttribute("valueOfForm") != null && (int)request.getAttribute("valueOfForm") == 3) {%><option value="3" selected >Треугольник (по двум сторонам и углу между ними)</option><% } %>
                    <% if (request.getAttribute("valueOfForm") == null || (int)request.getAttribute("valueOfForm") != 3) {%><option value="3" >Треугольник (по двум сторонам и углу между ними)</option><% } %>
                    <% if (request.getAttribute("valueOfForm") != null && (int)request.getAttribute("valueOfForm") == 4) {%><option value="4" selected >Прямоугольник (по двум сторонам)</option><% } %>
                    <% if (request.getAttribute("valueOfForm") == null || (int)request.getAttribute("valueOfForm") != 4) {%><option value="4" >Прямоугольник (по двум сторонам)</option><% } %>
                    <% if (request.getAttribute("valueOfForm") != null && (int)request.getAttribute("valueOfForm") == 5) {%><option value="5" selected >Прямоугольник (по диагонали и стороне)</option><% } %>
                    <% if (request.getAttribute("valueOfForm") == null || (int)request.getAttribute("valueOfForm") != 5) {%><option value="5" >Прямоугольник (по диагонали и стороне)</option><% } %>
                    <% if (request.getAttribute("valueOfForm") != null && (int)request.getAttribute("valueOfForm") == 6) {%><option value="6" selected >Квадрат</option><% } %>
                    <% if (request.getAttribute("valueOfForm") == null || (int)request.getAttribute("valueOfForm") != 6) {%><option value="6" >Квадрат</option><% } %>
                    <% if (request.getAttribute("valueOfForm") != null && (int)request.getAttribute("valueOfForm") == 7) {%><option value="7" selected >Правильный N-угольник (по количеству сторон)</option><% } %>
                    <% if (request.getAttribute("valueOfForm") == null || (int)request.getAttribute("valueOfForm") != 7) {%><option value="7" >Правильный N-угольник (по количеству сторон)</option><% } %>
                    <% if (request.getAttribute("valueOfForm") != null && (int)request.getAttribute("valueOfForm") == 8) {%><option value="8" selected >Правильный N-угольник (по углу между сторонами)</option><% } %>
                    <% if (request.getAttribute("valueOfForm") == null || (int)request.getAttribute("valueOfForm") != 8) {%><option value="8" >Правильный N-угольник (по углу между сторонами)</option><% } %>
                    <% if (request.getAttribute("valueOfForm") != null && (int)request.getAttribute("valueOfForm") == 9) {%><option value="9" selected >Окружность</option><% } %>
                    <% if (request.getAttribute("valueOfForm") == null || (int)request.getAttribute("valueOfForm") != 9) {%><option value="9" >Окружность</option><% } %>
                    <% if (request.getAttribute("valueOfForm") != null && (int)request.getAttribute("valueOfForm") == 10) {%><option value="10" selected >Эллипс</option><% } %>
                    <% if (request.getAttribute("valueOfForm") == null || (int)request.getAttribute("valueOfForm") != 10) {%><option value="10" >Эллипс</option><% } %>
                    <% if (request.getAttribute("valueOfForm") != null && (int)request.getAttribute("valueOfForm") == 11) {%><option value="11" selected >Парралелограмм (по сторонам и высоте)</option><% } %>
                    <% if (request.getAttribute("valueOfForm") == null || (int)request.getAttribute("valueOfForm") != 11) {%><option value="11" >Парралелограмм (по сторонам и высоте)</option><% } %>
                </select>
            </div>
        </form>
        <form action="servlet" method="POST"> 
            <script>
                document.writeln('<input class="unwatch" name="figureType" value="' + document.getElementById('foobarSelection').options[document.getElementById('foobarSelection').selectedIndex].value + '">');
            </script>
            <% if (request.getAttribute("form") != null){%><%=request.getAttribute("form")%><%}%>
        </form> 
               
        <h2 class="answer"> 
            <% if (request.getAttribute("answer") != null){%><%=request.getAttribute("answer")%><%}%>
        </h2>       
        <h2 class="error"> 
            <% if (request.getAttribute("error") != null){%><%=request.getAttribute("error")%><%}%>
        </h2>
        <footer><br>Выполнил: Лебедев Владимир, студент группы РБП-108М<br>УГАТУ, 2018</footer>
    </body>
</html>