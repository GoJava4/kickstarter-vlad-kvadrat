<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>PROJECT</title>
    <jsp:include page="head.jsp"/>
</head>
<body>
<%--@elvariable id="project" type="com.morkva.entities.Project"--%>
<div class="container">
    <jsp:include page="menu.jsp"/>
    <div class="panel panel-default">
        <c:set var="current_project" value="${project}"/>
        <h1>Project - ${current_project.name}</h1>

        <h3>Краткое описание - ${current_project.shortDescr}</h3>

        <h3>Нужно денег - ${current_project.needMoney}</h3>

        <h3>Уже собрано - ${current_project.currentMoney}</h3>

        <h3>Дата начала сборов - ${current_project.addingDate}</h3>

        <h3>Дата окончания - ${current_project.endingDate}</h3>

        <h3>Владелец проекта - ${current_project.user.username}</h3>
        <br/>
        <c:url var="donate_url" value="/project/${project.id}/donate"/>
        <form:form action="${donate_url}" modelAttribute="payment" method="post">
            <form:label path="amount">Doanate:</form:label>
            <form:input type="text" path="amount"/>
            <form:errors path="amount" cssClass="alert alert-danger"/>
            <p><button type="submit" class="btn btn-success">
                <span class="glyphicon glyphicon-ok-sign"></span>&nbsp;Donate
            </button></p>
        </form:form>
        <br/>
        <c:forEach var="comment" items="${comments}">
            <div>
                <p>Username: <c:out value="${comment.user.username}"/></p>

                <p>Comment: <c:out value="${comment.comment}"/></p>

                <p>Date: <c:out value="${comment.getDateTime()}"/></p>

                <p>---------------------------------------------</p>
            </div>
        </c:forEach>
        <c:url var="create_comment_url" value="/project/${project.id}/comments/add"/>
        <form action="${create_comment_url}" method="POST">
            <label>
                Your comment:
                <textarea name="comment" cols="40" rows="4"></textarea>
            </label>

            <p>
                <input type="submit" value="Отправить"/>
                <input type="reset" value="Очистить"/>
            </p>

        </form>
    </div>
</div>
</body>
</html>
