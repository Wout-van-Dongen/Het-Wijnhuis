<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.servletContext.contextPath}"/>
        <nav>
            <ul>
                <li><a href="${contextPath}">wijnen</a></li>
                <li><a href="${contextPath}/wijnen/winkelmandje.html">mandje</a></li>
            </ul>
        </nav>
