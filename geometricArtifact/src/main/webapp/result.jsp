<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>MyFirstServlet</title>
</head>
    <body>
        <!-- Выводим сообщение, полученное от сервлета,
             для этого производим поиск по <ключ>-->
        <h1> ${answer}</h1>
        <!-- Форма, содержащая в себе кнопку,
             которая перенаправит нас на исходную страницу-->
        <form action=/bender method="POST">
            <button type="submit">Заново</button>
        </form>
    </body>
</html>