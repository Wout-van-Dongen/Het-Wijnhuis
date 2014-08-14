<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.servletContext.contextPath}"/>

<!doctype html>
<html>
    <head>
        <title>
            Het Wijnhuis - Bestellen
        </title>
        <link rel="stylesheet" type="text/css" href="${contextPath}/styles/reset.css" media="screen"/>
        <link rel="stylesheet" type="text/css" href="${contextPath}/styles/default.css" media="screen"/>
        <link rel="stylesheet" type="text/css" href="${contextPath}/styles/fonts.css" media="screen"/>
        <link rel="stylesheet" type="text/css" href="${contextPath}/styles/colors.css" media="screen"/>
        <script  src="${contextPath}/js/validInput.js"></script>
    </head>

    <body>
        
        <%-- Setting the URL --%>
        <c:url var="orderURL" value="/wijnen/bestellen.html">
            <c:param name="wijnNr" value="${wijn.wijnNr}"/>
        </c:url>
        
        <jsp:include page="/WEB-INF/jsp/includes/navigation.jsp"/>
        <header>
            <h1> Wijn toevoegen aan mandje </h1>
        </header>
                <section class ="form">           
                    <label > Land </label>
                    <span >${wijn.soort.land.naam}</span>
                    <label > Soort </label>
                    <span > ${wijn.soort.naam} </span>
                    <label > Jaar </label>
                    <span > ${wijn.jaar} </span>
                    <label> Beoordeling </label>
                    <span class="beoordeling" >
                        <c:forEach begin="1" end="${wijn.beoordeling}">
                            &#9819; <%--Black Queen Symbol--%>
                        </c:forEach>
                    </span>
                    <label > Prijs </label>
                    <span >
                        <fmt:formatNumber type="currency" currencySymbol="€" value="${wijn.prijs}"/>
                    </span>
                    <form action="${orderURL}" method="post">
                        <input type = "number" name = "aantal" id = "input" value="1" />
                        <div class="fouten" ><jsp:include page="/WEB-INF/jsp/includes/fouten.jsp"/><ul><li  id="error_box"></li></ul></div>
                        <input type = "submit" value = "Toevoegen" id = "toevoegen" />
                    </form>
                </section>
    </body>
</html>
