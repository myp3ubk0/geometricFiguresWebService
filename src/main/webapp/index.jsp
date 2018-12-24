<%@ page contentType="text/html;charset=UTF-8" language="java" %> <!-- Указываем, что этот файл - страница сервлета java-->
<html>
<head>
    <link rel="stylesheet" type="text/css" href="style.css"> <!-- Подключение файла со стилями-->
    <meta charset="utf-8"><!-- Указываем кодировку -->
    <title>Сервис расчета геометрических фигур</title> <!-- Указываем заголовок страницы -->
</head>
    <body>
        <h1>Сервис расчета геометрических фигур</h1> <!-- Название сервиса на самом верху страницы-->
        
        <!-- Форма, содержащая в себе поле со списком типов фигур и техтовое поле.
             По выбору отправит POST запрос на сервлет-->
        <form action="getForm" method="POST" id="foobar">
            <div class='InputContainer'>
                <h3 class='InputContainerDescription'>Выберите тип рассчитываемой фигуры</h3>
                <select id="foobarSelection" class='InputContainerInput' name="figureType" onchange="document.getElementById('foobar').submit()">
                    <!--В поле со списком по умолчанию отображается первое значение. 
                    В загруженной странице после выбора соответствующий элемент становится выбранным по-умолчанию(Будет использоваться первая строка)
                    Иначе этот элемент не будет выбран (Будет использоватья вторая строка)-->
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
        
        <!-- Форма, содержащая в себе от одного до трех контейнеров для ввода.
             По нажатию на кнопку "Рассчитать" отправит POST запрос на сервлет-->
        <form action="servlet" method="POST"> 
            <!-- Добавление невидимого поля для передачи номера выбранной фигуры из поля выбора при отправке формы-->
            <script>document.writeln('<input class="unwatch" name="figureType" value="' + document.getElementById('foobarSelection').options[document.getElementById('foobarSelection').selectedIndex].value + '">');</script>
            <!-- Отображение формы, если она пришла-->
            <% if (request.getAttribute("form") != null){%><%=request.getAttribute("form")%><%}%>
        </form> 
               
        <!-- Отображение ответа по рассчету фигуры в нормальном виде-->
        <h2 class="answer"> 
            <% if (request.getAttribute("answer") != null){%><%=request.getAttribute("answer")%><%}%>
        </h2>     
        <!-- Отображение ошибок-->  
        <h2 class="error"> 
            <% if (request.getAttribute("error") != null){%><%=request.getAttribute("error")%><%}%>
        </h2>
        <footer><br>Выполнил: Лебедев Владимир, студент группы РБП-108М<br>УГАТУ, 2018</footer>
    </body>
</html>