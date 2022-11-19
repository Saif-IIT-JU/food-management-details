<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%--
  Author: saifuzzaman
--%>

<html>
<head>
    <title><fmt:message key="login.here"/></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="<c:url value="/css/bootstrap-4.0.0.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/css/custom-1.0.0.css"/>" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
</head>

<body>
<jsp:include page="header.jsp"/>

<center>
    <div class="custom-style-five container">

        <c:if test="${not empty LoginAlert}">
            <div>
                <div class="custom-style-six alert alert-success alert-dismissible">
                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                    <strong>Alert!</strong> <c:out value="${LoginAlert}"/>
                </div>
            </div>
        </c:if>

        <div class="custom-style-four card">
            <div class="card-body text-left">
                <h4 class="card-title text-center">
                    <fmt:message key="login.heading"/>
                </h4>
                <div class="container">
                    <form:form action="/login/process"
                               method="post"
                               modelAttribute="loginCmd">

                        <div class="form-group">
                            <form:label path="username"><fmt:message key="login.username"/></form:label>
                            <form:input type="text"
                                        class="form-control"
                                        path="username"/>
                            <form:errors path="username" cssClass="alert-danger"/>
                        </div>

                        <div class="form-group">
                            <form:label path="password"><fmt:message key="login.password"/></form:label>
                            <form:input type="password"
                                        class="form-control"
                                        path="password"/>
                            <form:errors path="password" cssClass="alert-danger"/>
                        </div>

                        <div class="d-flex justify-content-center">
                            <button type="submit" class="custom-style-seven btn btn-warning">
                                <fmt:message key="form.submit"/>
                            </button>
                        </div>

                    </form:form>
                </div>
            </div>
        </div>
    </div>
</center>

<jsp:include page="footer.jsp"/>
</body>
</html>
