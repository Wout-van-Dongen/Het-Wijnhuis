<%@page contentType="text/html;" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"
       value="${pageContext.servletContext.contextPath}" />

<!DOCTYPE html>
<html>
    <%-- Head region --%>
    <head>
        <title>Het Wijnhuis - Uw specialist in wijnen.</title>
        <link rel="stylesheet" type="text/css"
              href="${contextPath}/styles/reset.css" />
        <link rel="stylesheet" type="text/css"
              href="${contextPath}/styles/default.css" />
        <link rel="stylesheet" type="text/css"
              href="${contextPath}/styles/colors.css" />
        <link rel="stylesheet" type="text/css"
              href="${contextPath}/styles/fonts.css" />
    </head>

    <%-- Body region --%>
    <body>
        <%-- Navigation --%>
        <jsp:include page="/WEB-INF/jsp/includes/navigation.jsp"/>
        <header>
            <h1>Het Wijnhuis</h1>
        </header>

        <c:choose>
            <%-- If no errors have occurred --%>

            <c:when test="${empty fouten}">

                <%-- ======= If there are 'LANDEN' ========================== --%>
                <c:if test="${not empty landen}"> 
                    <jsp:include page="/WEB-INF/jsp/includes/vlaggen.jsp" />

                    <%-- ======= If there is a 'LAND' selected ========================== --%>
                    <c:if test="${not empty selectedLand}">
                        <jsp:include page="/WEB-INF/jsp/includes/soorten.jsp" />

                        <%-- If there is a 'SOORT' selected --%>
                        <c:if test="${not empty selectedSoort}"> 
                            <jsp:include page="/WEB-INF/jsp/includes/wijnen.jsp" /> 

                        </c:if>

                    </c:if>

                </c:if>

            </c:when>
            <%-- When errors DO occurre --%>
            <c:otherwise>
                <jsp:include page="/WEB-INF/jsp/includes/fouten.jsp" />
            </c:otherwise>
        </c:choose>
    </body>
</html>