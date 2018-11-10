<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>MyFirstServlet</title>
</head>
    <body>
        <h1> - Я великий робот Бендер! Я могу предсказывать прошлое!</h1>
        <!-- Форма, содержащая в себе кнопку и техтовое поле.
             По нажатию на кнопку отправит POST запрос на сервлет-->
        <form action="servlet" method="POST">
            <div>
                <h1> - Ответь, сколько тебе лет, человек!?</h1>
                <input type="text" name="age" value="" placeholder="Введите ваш возраст">
            </div>
            <input type="submit" value="Ответить" />
        </form>
    </body>
</html>