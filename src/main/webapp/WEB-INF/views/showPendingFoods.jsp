<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%--
  Author: saifuzzaman
--%>

<html>
<head>
    <title><fmt:message key="view.foods"/></title>
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
            <div class="custom-style-twelve container">

                <c:if test="${not empty message}">
                    <div>
                        <div class="alert alert-success alert-dismissible"
                             style="margin-left: 180px; margin-right: 180px">
                            <button type="button" class="close" data-dismiss="alert">&times;</button>
                            <c:out value="${message}"/>
                        </div>
                    </div>
                </c:if>

                <h3>
                    <fmt:message key="food.view.heading"/>
                </h3>
                <div class="scrollableArea">
                    <table class="table table-warning table-striped">
                        <thead>
                        <tr>
                            <th><fmt:message key="food.serve.date"/></th>
                            <th><fmt:message key="food.name"/></th>
                            <th><fmt:message key="food.meal.type"/></th>
                            <th><fmt:message key="food.created_on"/></th>
                            <th><fmt:message key="food.updated_on"/></th>
                            <th><fmt:message key="food.owner"/></th>
                            <th><fmt:message key="food.reviews"/></th>
                            <th><fmt:message key="food.action"/></th>
                        </tr>
                        </thead>
                        <tbody>

                        <c:forEach items="${foods}" var="food">
                            <tr>
                                <td><fmt:formatDate pattern="MM/dd/yyyy" value="${food.serveDate}"/></td>
                                <td><c:out value="${food.name}"/></td>
                                <td><c:out value="${food.meal.meal}"/></td>
                                <td><fmt:formatDate pattern="MM/dd/yyyy" value="${food.createdOn}"/></td>
                                <td><fmt:formatDate pattern="MM/dd/yyyy" value="${food.updatedOn}"/></td>
                                <td><c:out value="${food.addedBy.fullName}"/></td>

                                <td>
                                    <c:forEach items="${food.reviews}" var="review">
                                        <c:out value="${review.description}"/>
                                        <fmt:message key="posted.by"/>
                                        <c:out value="${review.user.fullName}"/><br><br>
                                    </c:forEach>
                                </td>

                                <td>
                                    <c:if test="${isAdmin || (isCook && userId == food.addedBy.id)}">
                                        <c:url var="foodUrl" value="/food">
                                            <c:param name="id" value="${food.id}"/>
                                        </c:url>
                                        <a href="${foodUrl}"><fmt:message key="edit"/></a>

                                        <c:url var="foodDeleteUrl" value="/food/delete">
                                            <c:param name="id" value="${food.id}"/>
                                        </c:url>
                                        <a href="${foodDeleteUrl}"><fmt:message key="delete"/></a>
                                    </c:if>

                                    <c:if test="${isAdmin}">
                                        <c:url var="foodApprovalUrl" value="/food/approve">
                                            <c:param name="id" value="${food.id}"/>
                                        </c:url>
                                        <a href="${foodApprovalUrl}"><fmt:message key="approve"/></a>
                                    </c:if>

                                    <c:if test="${not empty SESSION_USER}">
                                        <form:form action="/review"
                                                   method="post"
                                                   modelAttribute="review">

                                            <form:input type="text"
                                                        path="description"
                                                        placeholder="Add comment"
                                                        size="9%"/>
                                            <form:errors path="description" cssClass="alert-danger"/>

                                            <form:select path="food" hidden="hidden">
                                                <form:option value="${food.id}" label="${food.name}"/>
                                            </form:select>

                                            <form:select path="user" hidden="hidden">
                                                <form:option value="${userId}" label="${username}"/>
                                            </form:select>

                                            <input type="submit" value="Comment">
                                        </form:form>
                                    </c:if>
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
