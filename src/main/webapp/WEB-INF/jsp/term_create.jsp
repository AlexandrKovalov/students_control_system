<%--
  Created by IntelliJ IDEA.
  User: sashk
  Date: 04.04.2023
  Time: 17:14
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="../../resources/css/style.css">
  <title>Term Creating</title>
</head>
<body>
<span>
        <h1>Система управления студентами и их успеваемостью</h1>
        <a class="Logout" href="">Logout</a>
    </span>
<div class="navP">
  <nav>
    <a href="/">На главную</a>
    <a href="terms">Назад</a>
  </nav>
  <p class="p1">Для создания семестра заполните следующие данные и нажмите кнопку "Создать".</p>
</div>
<form action="term_create" method="post">

</form>
<form action="term_create" method="post">
  <div class="input22">
    <label class="label1">Длительность (в неделях)
      <input class="input222" name="duration" type="text">
    </label>
    <label class="label1"> Название семестра
      <input class="input1" name="term" type="text">
    </label>
  </div>
  <div>
    <div class="label22">
      <label class="label2">
        <p class="p3">Дисциплины в семестре</p>
        <c:forEach items="${disciplines}" var = "d">
          <tr>
            <form>
              <label>
                <td><input type="checkbox"></td>
                <td>${discipline}</td>
              </label>
            </form>
          </tr>
        </c:forEach>
      </label>
    </div>
    <div class="button6"><button class="button66">Создать</button></div>
  </div>
</form>

</body>
</html>
