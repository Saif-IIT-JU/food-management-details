<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--
  Author: saifuzzaman
--%>

<html>
<head>
    <title><fmt:message key="view.users"/></title>
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
            <div class="custom-style-thirteen container">
                <c:if test="${not empty message}">
                    <div>
                        <div class="custom-style-six alert alert-success alert-dismissible">
                            <button type="button" class="close" data-dismiss="alert">&times;</button>
                            <c:out value="${message}"/>
                        </div>
                    </div>
                </c:if>

                <h3>
                    <fmt:message key="user.view.heading"/>
                </h3>
                <div class="scrollableArea">
                    <table class="table table-warning table-striped">
                        <thead>
                        <tr>
                            <th><fmt:message key="user.firstname"/></th>
                            <th><fmt:message key="user.lastname"/></th>
                            <th><fmt:message key="user.email"/></th>
                            <th><fmt:message key="user.username"/></th>
                            <th><fmt:message key="action"/></th>
                        </tr>
                        </thead>
                        <tbody>

                        <c:forEach items="${users}" var="user">
                            <tr>
                                <td><c:out value="${user.firstName}"/></td>
                                <td><c:out value="${user.lastName}"/></td>
                                <td><c:out value="${user.email}"/></td>
                                <td><c:out value="${user.username}"/></td>
                                <td>
                                    <c:choose>
                                        <c:when test="${user.getIsBlocked()}">
                                            <c:url var="userUnblockUrl" value="/user/unblock">
                                                <c:param name="id" value="${user.id}"/>
                                            </c:url>
                                            <a href="${userUnblockUrl}"><fmt:message key="unblock"/></a>
                                        </c:when>

                                        <c:otherwise>
                                            <c:url var="userBlockUrl" value="/user/block">
                                                <c:param name="id" value="${user.id}"/>
                                            </c:url>
                                            <a href="${userBlockUrl}"><fmt:message key="block"/></a>
                                        </c:otherwise>
                                    </c:choose>

                                    <c:url var="userDeleteUrl" value="/user/delete">
                                        <c:param name="id" value="${user.id}"/>
                                    </c:url>
                                    <a href="${userDeleteUrl}"><fmt:message key="delete"/></a>

                                    <c:url var="userEditUrl" value="/user">
                                        <c:param name="id" value="${user.id}"/>
                                    </c:url>
                                    <a href="${userEditUrl}"><fmt:message key="edit"/></a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp"/>
</body>
</html>