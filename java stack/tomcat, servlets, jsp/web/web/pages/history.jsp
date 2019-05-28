<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>History</title>
</head>
<body>
    <p>History of operation</p>

    <!-- Объявляем переменную -->
    <c:set var="jspHistory" scope="session" value="${history}" />

    <!-- Проверяем объявленную переменную на null -->
    <c:if test="${jspHistory == null}">
        <p>Execute some expression witch will see the history.</p>
    </c:if>

    <p>${history}</p>
    <a href="/servlet_example_war/calculator">back</a>
</body>
</html>
