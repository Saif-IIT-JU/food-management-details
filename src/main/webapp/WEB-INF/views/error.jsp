<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--
  Author: saifuzzaman
--%>

<html>
<head>
    <title><fmt:message key="page.error"/></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="<c:url value="/css/bootstrap-4.0.0.min.css"></c:url>" rel="stylesheet">
    <link href="<c:url value="/css/custom-1.0.0.css"/>" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
</head>

<body>
<jsp:include page="header.jsp"/>
<div class="container-fluid">
    <div class="row">
        <div class="col-xl-3">
        </div>

        <div class="col-xl-9">
            <div class="container custom-style-one">

                <div class="alert alert-info">
                    <strong><fmt:message key="info"/></strong> <c:out value="${alert}"/>
                    <c:url var="homeUrl" value="/home"/>
                    <a href="${homeUrl}">
                        <button type="button" class="btn btn-outline-warning"><fmt:message key="go.home"/></button>
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp"/>
</body>
</html>
