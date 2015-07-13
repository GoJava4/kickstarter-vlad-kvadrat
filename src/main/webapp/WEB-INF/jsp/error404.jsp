<%--
  Created by IntelliJ IDEA.
  User: koros
  Date: 20.06.2015
  Time: 18:45
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>TITLE</title>
    <jsp:include page="head.jsp"/>
</head>
<body>
<%--@elvariable id="list" type="java.util.List<com.morkva.entities.Category>"--%>
<%--@elvariable id="quote" type="com.morkva.entities.Quote"--%>
<c:set var="qt" value="${quote}"/>
<c:set var="categories" value="${list}" scope="application"/>
<div class="container">
    404 GTFO!

    <img src="<c:url value="/pictures/1"/>"/>
</div>
</body>
</html>
