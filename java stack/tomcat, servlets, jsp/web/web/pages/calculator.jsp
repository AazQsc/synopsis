<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Calculator</title>
    <link href="/servlet_example_war/css/style.css" rel="stylesheet">
    <script type="text/javascript" src="/servlet_example_war/js/jquery.min.js"></script>
</head>
<body>
<div class="container">
    <div id="center">

        <p>Welcome!</p>

        <!-- Форма отправки в CalculatorServlet -->
        <div class="border">
            <form name="expression" action="/servlet_example_war/calculator" method="post">
                <p>Enter the mathematical expression.</p>
                <input type="text" name="expression" value="" size="40"/>
                <input type="submit" value="calculate">
            </form>
        </div>
        <!-- Ответ калькулятора -->
        <p>Answer: ${answer}</p>
        <a href="/servlet_example_war/calculator/history">history</a>
        <!-- Примеры того, как можно заполнять форму -->
        <p>Example:<br>
            '1+1='<br>
            '2(4-3)='</p>
        <p>You can use or not use spaces and the equal sign<br>
            '1 + 1'<br>
            '2*(4/3 - 2)'<br>
            '(17-1)*(23/2-4)'</p>

        <br>

        <!--info section-->
        <div class="border" id="info">
            <p>Info section</p>
            <!-- Эти параметры придут из CalculatorServlet-->
            <p>Count your entrances to this page on this session: ${sessionEntranceCounter}<br>
                Count your entrances to this page on all time: ${cookieEntranceCounter}</p>

            <!-- Погода -->
            <!-- div обрезают края картинки, которую мы получим по ссылке -->
            <div style="overflow: hidden; width: 185px;  height: 75px;">
                <div style="margin-top: -5px; margin-left: -5px;">
                    <img src="https://info.weather.yandex.net/157/2_white.ru.png?domain=by"/>
                </div>
            </div>
        </div>

        <br>

        <!-- Кнопки скрывают/раскрывают панель info -->
        <div class="border">
            <button id="btn-down">open</button>
            <button id="btn-up">close</button>
        </div>

        <!-- подключаем js, который отвечает за работу с кнопками open/close -->
        <script src="/servlet_example_war/js/jquery_UpDown.js" type="text/javascript"></script>
    </div>
</div>

</body>
</html>