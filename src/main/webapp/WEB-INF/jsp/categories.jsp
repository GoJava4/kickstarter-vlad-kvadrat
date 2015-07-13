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
    <jsp:include page="menu.jsp">
        <jsp:param name="activeLink" value="categories"/>
    </jsp:include>
    <div class="panel panel-default">
        <h1>"<c:out value="${qt.value}"/>" - <c:out value="${qt.author}"/></h1>

        <h1>Categories</h1>
        <table>
            <tbody>
                <c:forEach var="category" items="${categories}">
                    <tr>
                    <c:url value="/category/${category.id}" var="categoryURL"/>
                        <form>
                            <td>
                                <a href="${categoryURL}">${category.name}</a>
                            </td>
                            <td class="text-right">
                                <security:authorize access="hasRole('ROLE_ADMIN')">
                                    <%--<button class="btn btn-xs btn-primary" formmethod="get"--%>
                                            <%--formaction='/category/<c:out value="${category.id}"/>/edit' type="submit">--%>
                                        <%--<span class="glyphicon glyphicon-pencil"></span>--%>
                                    <%--</button>--%>

                                    <button class="btn btn-xs btn-danger" formmethod="post"
                                            formaction='<c:url value="/category/${category.id}/delete"/>' type="submit">
                                        <span class="glyphicon glyphicon-trash"></span>
                                    </button>
                                </security:authorize>
                            </td>
                        </form>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
