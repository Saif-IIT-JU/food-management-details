<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%--
  Author: saifuzzaman
--%>

<html>
<head>
    <title><fmt:message key="title"/></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="<c:url value="/css/bootstrap-4.0.0.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/css/custom-1.0.0.css"/>" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
</head>

<body>
<jsp:include page="header.jsp"/>

<div>
    <div class="container" style="padding: 260px; text-align: center">
        <h3>
            <fmt:message key="index.heading"/>
        </h3>
        <div class="bottom-one">
            <c:url var="loginUrl" value="/login"/>
            <a href="${loginUrl}">
                <button type="button" class="btn btn-outline-warning">
                    <fmt:message key="get.started"/>
                </button>
            </a>
        </div>

        <div class="container">
            <form:form action="/food/search"
                       method="post"
                       modelAttribute="food">

                <div class="form-group">
                    <form:input type="text"
                                class="form-control"
                                path="serveDate"
                                placeholder="Type date here"/>
                    <form:errors path="serveDate" cssClass="alert-danger"/>
                </div>

                <div class="d-flex justify-content-center">
                    <button type="submit" class="btn btn-warning" style="text-align: center">
                        <fmt:message key="form.search"/>
                    </button>
                </div>
            </form:form>
        </div>

        <div class="container top-one">
            <form:form action="/food/search"
                       method="get"
                       modelAttribute="food">

                <div class="form-group">
                    <form:input type="text"
                                class="form-control"
                                path="name"
                                placeholder="Type food name here"/>
                    <form:errors path="name" cssClass="alert-danger"/>
                </div>

                <div class="d-flex justify-content-center">
                    <button type="submit" class="btn btn-warning" style="text-align: center">
                        <fmt:message key="form.search"/>
                    </button>
                </div>
            </form:form>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp"/>
</body>
</html>
