<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: koros
  Date: 21.07.2015
  Time: 0:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Project</title>
    <jsp:include page="head.jsp"/>
</head>
<body>
<div class="container">
    <jsp:include page="menu.jsp"/>
    <div class="panel panel-default">
        <c:url var="addProjectUrl" value="/project/add">
            <c:param name="category_id" value="${category_id}"/>
        </c:url>
        <form:form action="${addProjectUrl}" method="post" modelAttribute="project">
            <div class="panel-heading">
                <h1>Create Project</h1>
            </div>
            <div class="panel-body">
                <div class="col-md-12">
                    <div class="input-group">
                        <span class="input-group-addon">Name</span>
                        <form:input cssClass="form-control" path="name" title="Name"/>
                        <form:errors path="name"/>
                    </div>
                    <br/>

                    <div class="input-group">
                        <span class="input-group-addon">Short Description</span>
                        <form:textarea cssClass="form-control" path="shortDescr" title="Short Description"/>
                        <form:errors path="shortDescr"/>
                    </div>
                    <br/>

                    <div class="col-md-6">
                        <div class="input-group">
                            <span class="input-group-addon">Need Money</span>
                            <form:input cssClass="form-control" path="needMoney" title="Need Money"/>
                            <form:errors path="needMoney"/>
                        </div>
                    </div>
                    <div class="col-md-6" style="padding-left: 10px;">
                        <div class="input-group">
                            <span class="input-group-addon">Ending Date</span>
                            <form:input cssClass="form-control" path="endingDate" title="Ending Date" type="date"/>
                            <form:errors path="endingDate"/>
                        </div>
                        <br/>
                    </div>

                    <div class="input-group">
                        <span class="input-group-addon">Full Description</span>
                        <form:textarea cssClass="form-control" path="fullDescription.value" title="Full Description"/>
                        <form:errors path="fullDescription.value"/>
                    </div>
                    <br/>
                </div>
            </div>
            <div class="panel-footer">
                <p>
                    <button class="btn btn-success">Add Project</button>
                </p>
            </div>
        </form:form>
    </div>


</div>
</body>
</html>
