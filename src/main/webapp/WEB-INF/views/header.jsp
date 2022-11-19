<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--
  Author: saifuzzaman
--%>

<nav class="navbar navbar-expand-sm bg-warning navbar-dark fixed-top" style="background-color: yellow">
    <c:url var="homeUrl" value="/home"/>
    <a class="navbar-brand" href="${homeUrl}">
        <img src="/images/logo-ju-1.0.0.png" alt="Logo" height="35" width="110">
    </a>
    <span class="navbar-text text-dark"><fmt:message key="heading"/></span>
    <ul class="navbar-nav ml-auto">
        <c:if test="${not empty SESSION_USER}">
            <li class="nav-item active">
                <a class="nav-link text-dark"><c:out value="${SESSION_USER}"/></a>
            </li>
        </c:if>

        <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle text-dark" href="#" id="navbardrop" data-toggle="dropdown">
                <fmt:message key="menu"/>
            </a>
            <div class="dropdown-menu">
                <a class="dropdown-item" href="/dashboard"><fmt:message key="dashboard"/></a>
                <c:set var="username" value="${SESSION_USER}"/>
                <c:choose>
                    <c:when test="${empty username}">
                        <c:url var="loginUrl" value="/login"/>
                        <a class="dropdown-item" href="${loginUrl}"><fmt:message key="log.in"/></a>
                    </c:when>

                    <c:otherwise>
                        <c:url var="logoutUrl" value="/logout"/>
                        <a class="dropdown-item" href="${logoutUrl}"><fmt:message key="log.out"/></a>
                    </c:otherwise>
                </c:choose>
            </div>
        </li>

        <li class="nav-item active">
            <a class="nav-link text-dark" href="${homeUrl}"><fmt:message key="home"/></a>
        </li>
    </ul>
</nav>
