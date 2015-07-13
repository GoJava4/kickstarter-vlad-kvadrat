<%--
  Created by IntelliJ IDEA.
  User: koros
  Date: 20.06.2015
  Time: 18:45
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>TITLE</title>
    <link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body>
<%--@elvariable id="list" type="java.util.List<com.morkva.entities.Category>"--%>
<%--@elvariable id="quote" type="com.morkva.entities.Quote"--%>
<c:set var="qt" value="${quote}"/>
<div class="container">
    <jsp:include page="menu.jsp">
        <jsp:param name="activeLink" value="contacts"/>
    </jsp:include>
    <div class="panel panel-default">
        CONTACTS =)
    </div>
</div>
</body>
</html>
