<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--
  Author: saifuzzaman
--%>

<html>
<head>
    <title><fmt:message key="food.details"/></title>
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
                                <fmt:message key="food.form.heading"/>
                            </h4>
                            <div class="container">
                                <form:form action="/food"
                                           method="post"
                                           modelAttribute="food">

                                    <form:input type="hidden" path="id"/>

                                    <div class="form-group">
                                        <form:label path="name"><fmt:message key="food.description"/></form:label>
                                        <form:textarea rows="5" cols="300" class="form-control" path="name"/>
                                        <form:errors path="name" cssClass="alert-danger"/>
                                    </div>

                                    <div class="form-group">
                                        <form:label path="serveDate"><fmt:message key="food.serve.date"/></form:label>
                                        <form:input class="form-control" path="serveDate"/>
                                        <fmt:message key="date.format"/>
                                        <form:errors path="serveDate" cssClass="alert-danger"/>
                                    </div>

                                    <div class="form-group">
                                        <form:label path="meal"><fmt:message key="select.meal"/></form:label>
                                        <form:select class="form-control" path="meal">
                                            <form:options items="${meals}"/>
                                        </form:select>
                                    </div>

                                    <form:hidden path="addedBy" value="${userId}"/>

                                    <form:input type="hidden" path="approval"/>

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
