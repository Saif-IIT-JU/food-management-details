<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--
  Author: saifuzzaman
--%>

<html>
<head>
    <title><fmt:message key="user.details"/></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="<c:url value="/css/bootstrap-4.0.0.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/css/custom-1.0.0.css"/>" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
</head>

<body>
<jsp:include page="header.jsp"/>
<div class="container-fluid">
    <div class="row">
        <div class="col-xl-3">
            <jsp:include page="menuView.jsp"/>
        </div>

        <div class="col-xl-9">
            <center>
                <div class="custom-style-three container">
                    <div class="custom-style-four card">
                        <div class="card-body text-left">
                            <h4 class="card-title text-center">
                                <fmt:message key="user.form.heading"/>
                            </h4>
                            <div class="container">
                                <form:form action="/user"
                                           method="post"
                                           modelAttribute="user">

                                    <form:input type="hidden" path="id"/>

                                    <div class="form-group">
                                        <form:label path="firstName"><fmt:message key="user.firstname"/></form:label>
                                        <form:input class="form-control" path="firstName"/>
                                        <form:errors path="firstName" cssClass="alert-danger"/>
                                    </div>

                                    <div class="form-group">
                                        <form:label path="lastName"><fmt:message key="user.lastname"/></form:label>
                                        <form:input class="form-control" path="lastName"/>
                                        <form:errors path="lastName" cssClass="alert-danger"/>
                                    </div>

                                    <div class="form-group">
                                        <form:label path="email"><fmt:message key="user.email"/></form:label>
                                        <form:input class="form-control" path="email"/>
                                        <form:errors path="email" cssClass="alert-danger"/>
                                    </div>

                                    <div class="form-group">
                                        <form:label path="username"><fmt:message key="user.username"/></form:label>
                                        <form:input class="form-control" path="username"/>
                                        <form:errors path="username" cssClass="alert-danger"/>
                                    </div>

                                    <c:choose>
                                        <c:when test="${user.isNew()}">
                                            <div class="form-group">
                                                <form:label path="password"><fmt:message
                                                        key="user.password"/></form:label>
                                                <form:input class="form-control" path="password"/>
                                                <form:errors path="password" cssClass="alert-danger"/>
                                            </div>
                                        </c:when>

                                        <c:otherwise>
                                            <form:input type="hidden" path="password"/>
                                        </c:otherwise>
                                    </c:choose>

                                    <div class="form-group">
                                        <form:label path="role"><fmt:message key="select.role"/></form:label>
                                        <form:select class="form-control" path="role">
                                            <form:option value="${user.role}" label="${user.role}"/>
                                        </form:select>
                                    </div>

                                    <form:input type="hidden" path="isBlocked"/>

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
        </div>
    </div>
</div>

<jsp:include page="footer.jsp"/>
</body>
</html>
