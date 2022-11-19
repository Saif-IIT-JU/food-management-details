<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--
  Author: saifuzzaman
--%>

<link href="<c:url value="/css/custom-1.0.0.css"/>" rel="stylesheet">
<div class="scrollableMenu">
    <div class="custom-style-eight container">
        <div class="custom-style-nine card">
            <div class="card-body text-left">
                <h4 class="card-title text-center"><fmt:message key="dashboard"/></h4>
                <div class="container">
                    <c:if test="${isAdmin || isCook}">
                        <c:url var="newFood" value="/food"/>
                        <a href="${newFood}">
                            <button type="button" class="btn btn-warning btn-block">
                                <fmt:message key="add.food"/>
                            </button>
                        </a>
                        <br>
                    </c:if>

                    <c:if test="${isAdmin || isUser}">
                        <c:url var="foodsUrl" value="/food/approved"/>
                        <a href="${foodsUrl}">
                            <button type="button" class="btn btn-warning btn-block">
                                <fmt:message key="view.foods"/>
                            </button>
                        </a>
                        <br>
                    </c:if>

                    <c:if test="${isCook}">
                        <c:url var="foodsUrl" value="/food/cook">
                            <c:param name="id" value="${userId}"/>
                        </c:url>
                        <a href="${foodsUrl}">
                            <button type="button" class="btn btn-warning btn-block">
                                <fmt:message key="view.foods"/>
                            </button>
                        </a>
                        <br>
                    </c:if>

                    <c:if test="${isAdmin}">
                        <c:url var="notApprovedFoodsUrl" value="/food/unapproved"/>
                        <a href="${notApprovedFoodsUrl}">
                            <button type="button" class="btn btn-warning btn-block">
                                <fmt:message key="view.food.not.approved"/>
                            </button>
                        </a>
                        <br>

                        <c:url var="addUserUrl" value="/user"/>
                        <a href="${addUserUrl}">
                            <button type="button" class="btn btn-warning btn-block">
                                <fmt:message key="add.user"/>
                            </button>
                        </a>
                        <br>

                        <c:url var="usersUrl" value="/user/all"/>
                        <a href="${usersUrl}">
                            <button type="button" class="btn btn-warning btn-block">
                                <fmt:message key="view.users"/>
                            </button>
                        </a>
                        <br>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</div>