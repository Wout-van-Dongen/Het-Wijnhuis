<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.servletContext.contextPath}"/>

<!doctype html>
<html>
    <head>
        <title>
            Het Wijnhuis - Bevestigd
        </title>
        <link rel="stylesheet" type="text/css" href="${contextPath}/styles/reset.css" media="screen"/>
        <link rel="stylesheet" type="text/css" href="${contextPath}/styles/default.css" media="screen"/>
        <link rel="stylesheet" type="text/css" href="${contextPath}/styles/fonts.css" media="screen"/>
        <link rel="stylesheet" type="text/css" href="${contextPath}/styles/colors.css" media="screen"/>
    </head>

    <body>
        <jsp:include page="/WEB-INF/jsp/includes/navigation.jsp"/>
        <header>
            
            <c:if test="${empty bonNr}"><script>alert("bonNr is null")</script></c:if>
            <h1>Je Mandje is bevestigd als bestelbon ${bonNr}!</h1>
        </header>
    </body>
</html>

