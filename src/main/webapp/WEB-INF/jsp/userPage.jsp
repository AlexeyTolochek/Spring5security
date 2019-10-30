<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration form</title>
    <link href="${pageContext.request.contextPath}/css/FormStyle.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="body"></div>
<div class="grad"></div>
<div class="header">
    <h1>Java Mentor!</h1>

    <h2>x -> Приветствие на супер-мега сайте супер-пупер юзверя  ${byLogin.name}</h2><br/>

    <h2>${requestScope.message}</h2>
    <div>Site<span>Random</span></div>
</div>





</body>

</html>
