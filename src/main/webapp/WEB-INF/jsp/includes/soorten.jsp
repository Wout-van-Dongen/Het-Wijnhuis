<%@page pageEncoding="UTF-8" contentType="text/html"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



    <h2>Kies een Soort uit ${selectedLand.naam}</h2>
    <section id="soortenlijst">
        <ul>
            <c:forEach var="soort" items="${selectedLand.soorten}">

                <c:url var="wijnURL" value="/">
                   <c:param name="landNr" value="${selectedLand.landNr}" /> 
                    <c:param name="soortNr" value="${soort.soortNr}" />
                    
                </c:url>

                <li><a href="${wijnURL}"
                       title="beschikbare jaartallen van de wijnsoort ${soort.naam} uit ${selectedLand.naam}">
                        ${soort.naam} </a></li>
                    </c:forEach>
        </ul>
    </section>
