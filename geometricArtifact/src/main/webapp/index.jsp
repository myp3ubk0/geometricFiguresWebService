<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="style.css"> 
    <meta charset="utf-8">
    <title>MyFirstServlet</title>
</head>
    <body>
        <h1>Сервис расчета геометрических фигур</h1>
        
        <!-- Форма, содержащая в себе кнопку и техтовое поле.
             По нажатию на кнопку отправит POST запрос на сервлет-->
        <form action="getForm" method="POST" id="foobar">
        </form>
            <select id="foobarSelection" name="figureType" form="foobar" onchange="document.getElementById('foobar').submit()">
                <% if (request.getAttribute("valueOfForm") != null && (int)request.getAttribute("valueOfForm") == 0) {%><option value="0" selected >Выберите тип фигуры</option><% } %>
                <% if (request.getAttribute("valueOfForm") == null || (int)request.getAttribute("valueOfForm") != 0) {%><option value="0" >Выберите тип фигуры</option><% } %>
                <% if (request.getAttribute("valueOfForm") != null && (int)request.getAttribute("valueOfForm") == 1) {%><option value="1" selected >Выберите тип фигуры1</option><% } %>
                <% if (request.getAttribute("valueOfForm") == null || (int)request.getAttribute("valueOfForm") != 1) {%><option value="1" >Выберите тип фигуры1</option><% } %>
                <% if (request.getAttribute("valueOfForm") != null && (int)request.getAttribute("valueOfForm") == 2) {%><option value="2" selected >Выберите тип фигуры2</option><% } %>
                <% if (request.getAttribute("valueOfForm") == null || (int)request.getAttribute("valueOfForm") != 2) {%><option value="2" >Выберите тип фигуры2</option><% } %>
            </select>
        <form action="servlet" method="POST"> 
            <script>
                document.writeln('<input class="unwatch" name="figureType" value="' + document.getElementById('foobarSelection').options[document.getElementById('foobarSelection').selectedIndex].value + '">');
            </script>
            <% if (request.getAttribute("form") != null){%><%=request.getAttribute("form")%><%}%>
        </form>        
        <h2 class="answer"> 
            <% if (request.getAttribute("answer") != null){%><%=request.getAttribute("answer")%><%}%>
        </h2>
    </body>
</html>