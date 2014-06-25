<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.servletContext.contextPath}"/>

<!doctype html>
<html>
    <head>
        <title>
            Het Wijnhuis - Uw winkelmand
        </title>
        <link rel="stylesheet" type="text/css" href="${contextPath}/styles/reset.css" media="screen"/>
        <link rel="stylesheet" type="text/css" href="${contextPath}/styles/default.css" media="screen"/>
        <link rel="stylesheet" type="text/css" href="${contextPath}/styles/fonts.css" media="screen"/>
        <link rel="stylesheet" type="text/css" href="${contextPath}/styles/colors.css" media="screen"/>
    </head>

    <body>
        <jsp:include page="/WEB-INF/jsp/includes/navigation.jsp"/>
        <header>
            <h1>Mandje ${winkelmandje.size}</h1>
        </header>

            <jsp:include page="/WEB-INF/jsp/includes/mandje.jsp"/>
            <jsp:include page="/WEB-INF/jsp/includes/formulier.jsp"/>
            
            
    </body>
</html>
