<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>login</title>
    <link href="${pageContext.request.contextPath}/css/FormStyle.css" rel="stylesheet" type="text/css" />
    <meta th:name="_csrf" th:content="${_csrf.token}"/>
    <meta th:name="_csrf_header" th:content="${_csrf.headerName}"/>
</head>
<body>
    <div class="body"></div>
    <div class="grad"></div>
    <div class="header">
        <div>Site<span>Random</span></div>
    </div>

    <br>
    <div class="login">
        <form:form method="post">
            <input type="text" placeholder="login" name="name"><br>
            <input type="password" placeholder="пароль" name="password"><br>
            <input type="submit" value="Войти">
            <br>
            <div style="align: center;">
                <h2><a href="${pageContext.request.contextPath}/reg" text>Регистрация</a></h2>

                <c:if test="${param.error != null}">
                    <h2>Пожалуйста введите корректный логин/пароль</h2>
                    <h2>   Или зарегистрируйтесь по ссылке</h2>
                </c:if>
            </div>
        </form:form>
    </div>
</body>
</html>
