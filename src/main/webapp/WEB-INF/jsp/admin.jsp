<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <meta th:name="_csrf" th:content="${_csrf.token}"/>
    <meta th:name="_csrf_header" th:content="${_csrf.headerName}"/>
</head>
<body>

<h1>Java Mentor!</h1>

<h2>БД пользователей</h2><br/>
<h2>x -> Приветствие на супер-мега сайте супер-пупер админа ${admin.name}</h2><br/>

<h2>${requestScope.message}</h2>

<form:form method="post">
    <p><b>Данные пользователя</b></p>

    <table width='100%' cellspacing='0' cellpadding='4' items="${requestScope.userEdit}" var="userEdit">
        <tr>
            <td align='right' width='100'>Имя</td>
            <td><input type='text' name='name' maxlength='50' size='20' value= ${userEdit.name}></td>
        </tr>
        <tr>
            <td align='right'>login</td>
            <td><input type='text' name='login' maxlength='50' size='20' value=${userEdit.login}></td>
        </tr>
        <tr>
            <td align='right'>Пароль</td>
            <td><input type='text' name='password' maxlength='50' size='20' value=${userEdit.password}></td>
        </tr>
        <tr>
            <td align='right'>Адрес</td>
            <td><input type='text' name='address' maxlength='50' size='20' value=${userEdit.address}></td>
        </tr>
        <tr>
            <td align='right'>Доступ</td>
            <td><input type='text' name='role' maxlength='50' size='20' value=${userEdit.role}></td>
        </tr>
    </table>
    <p><b>Выберите действие c пользователем</b></p>

    <input type='submit' value='Добвить' name='Ok' formaction="${pageContext.request.contextPath}/admin/add">
    <input type='submit' value='Изменить' name='Ok' formaction="${pageContext.request.contextPath}/admin/update">

    <c:if test="${not empty userEdit.id}">
        <input type="hidden" name="id" value="${userEdit.id}">
    </c:if >


</form:form>
<br>

<table table border="1" cellspacing="0" cellpadding="2">
    <tr>
        <th>Id</th>
        <th>Имя</th>
        <th>Логин</th>
        <th>Пароль</th>
        <th>Адрес</th>
        <th>Права пользователя</th>
    </tr>
    <c:forEach items="${requestScope.list}" var="user">
        <tr>
            <td> ${user.id} </td>
            <td> ${user.name} </td>
            <td> ${user.login} </td>
            <td> ${user.password} </td>
            <td> ${user.address} </td>
            <td> ${user.role.toString().replaceAll("^\\[|\\]$", "")} </td>
            <td>
                <form:form method="get">
                    <input type="submit" value="Изменить" name="edit">
                    <input type="hidden" name="id" value="${user.id}">
                </form:form>
                <form:form method="post" action="${pageContext.request.contextPath}/admin/delete">
                    <input type="submit" value="Удалить" name="delete">
                    <input type="hidden" name="id" value="${user.id}">
                </form:form>


            </td>
        </tr>
    </c:forEach>
</table>


</body>

</html>
