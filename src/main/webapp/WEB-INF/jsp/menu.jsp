<%--@elvariable id="qt" type="com.morkva.entities.Quote"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<div class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="<c:url value="/home"/>">Kickstarter</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <c:set var="css" value="active"/>
                <c:choose>
                    <c:when test="${param.activeLink == \"categories\"}">
                        <c:set var="cssLink1" value="${css}"/>
                    </c:when>
                    <c:when test="${param.activeLink == \"contacts\"}">
                        <c:set var="cssLink2" value="${css}"/>
                    </c:when>
                    <c:when test="${param.activeLink == \"category\"}">
                        <c:set var="cssLink3" value="${css}"/>
                    </c:when>
                </c:choose>
                <li class="${cssLink1}"><a href="<c:url value="/categories"/>">Home</a></li>
                <li class="dropdown ${cssLink3}">
                    <a href="#" data-toggle="dropdown" class="dropdown-toggle">Categories<b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <c:forEach var="category" items="${applicationScope.categories}">
                            <c:url value="/category/${category.id}" var="categoryURL"/>
                            <li>
                                <a href="${categoryURL}">${category.name}</a>
                            </li>
                        </c:forEach>
                    </ul>
                </li>
                <li class="${cssLink2}"><a href="<c:url value="/contacts"/>">Contacts</a></li>
                <li><a href="#">Link 3</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <security:authorize access="!isAuthenticated()">
                    <li>
                        <form id="login-form" name='loginForm' class="form-inline"
                              action="<c:url value='/login' />" method='POST'>
                            <div class="form-group">
                                <input class="form-control" placeholder="username" type='text' name='username' value=''>
                            </div>
                            <div class="form-group">
                                <input class="form-control" placeholder="password" type='password' name='password'/>
                            </div>
                            <input class="btn btn-sm btn-success" name="submit" type="submit" value="Login"/>

                            <input type="hidden" name="${_csrf.parameterName}"
                                   value="${_csrf.token}"/>

                        </form>
                    </li>
                    <%--<li>--%>
                    <%--<a class="btn btn-success" href="<c:url value="/login"/>">Log in</a>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                    <%--<a class="btn btn-default">Sign up</a>--%>
                    <%--</li>--%>
                </security:authorize>
                <security:authorize access="isAuthenticated()">
                    <li>
                        <a class="btn btn-lg btn-danger" href="<c:url value="/logout"/>" role="button">Logout</a>
                    </li>
                </security:authorize>
                <br>
            </ul>
        </div>
    </div>
</div>