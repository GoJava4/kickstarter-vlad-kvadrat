<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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

        <h3>${current_project.shortDescr}</h3>

        <h3>${current_project.needMoney}</h3>

        <h3>${current_project.currentMoney}</h3>

        <h3>${current_project.addingDate}</h3>

        <h3>${current_project.endingDate}</h3>

        <h3>${current_project.user}</h3>
        <br/>
        <c:url var="pr" value="/project">
            <c:param name="projectId" value="${project.id}"/>
        </c:url>
        <form action="${pr}" method="post">
            <label>
                Doanate:
                <input type="text" name="donateCount"/>
            </label>

            <input type="submit" value="DONATE!">
        </form>
    </div>
</div>
</body>
</html>
