<%@page pageEncoding="UTF-8" contentType="text/html"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<c:if test="${not empty selectedSoort}">
    <h2>Kies een wijn uit ${selectedSoort.naam}</h2>
    <section id="wijnenlijst">
        <ul>
            <c:forEach var="wijn" items="${selectedSoort.wijnen}">

                <c:url var="bestelURL" value="/wijnen/bestellen.html">
                    <c:param name="wijnNr" value="${wijn.wijnNr}" />
                </c:url>

                <li>
                    <a href="${bestelURL}" title="bestellen van ${selectedSoort.naam} van ${wijn.jaar} uit ${selectedLand.naam}" >
                        ${selectedSoort.naam} ${wijn.jaar}
                    </a>
                    <span class="beoordeling"><%--adds for each point in the 'beoordeling' a visualisation (symbol)--%>
	<c:forEach begin="0" end="${wijn.beoordeling-1}">
		&#9819; <%--Black Queen Symbol--%>
	</c:forEach>
</span>
                </li>
            </c:forEach>
        </ul>
    </section>
</c:if>