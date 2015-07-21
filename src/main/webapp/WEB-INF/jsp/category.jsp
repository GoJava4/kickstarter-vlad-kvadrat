<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CATEGORY ${category_id}</title>
    <jsp:include page="head.jsp"/>
</head>
<body>
<div class="container">
    <jsp:include page="menu.jsp">
        <jsp:param name="activeLink" value="category"/>
    </jsp:include>
    <div class="panel panel-default">
        <div class="panel-heading">
            <p><h1>${category_name}</h1></p>
        </div>
        <div class="panel-body">
            <ul>
                <%--@elvariable id="projects" type="java.util.List<com.morkva.entities.Project>"--%>
                <c:forEach var="prject" items="${projects}">
                    <c:url value="/project/${prject.id}" var="projectURL"/>
                    <li>
                        <h2>
                            <a href="${projectURL}">${prject.name}</a>
                        </h2>
                        <br/>
                    </li>
                </c:forEach>
            </ul>
        </div>
        <div class="panel-footer">
            <security:authorize access="hasRole('ROLE_USER')">
                <c:url var="projectAddUrl" value="/project/add">
                    <c:param name="category_id" value="${category_id}"/>
                </c:url>
                <p><a class="btn btn-success" href="${projectAddUrl}">Add new project</a></p>
            </security:authorize>
        </div>
    </div>
</div>
</body>
</html>
