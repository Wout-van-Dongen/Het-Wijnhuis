<%@page pageEncoding="UTF-8" contentType="text/html"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:if test="${not empty landen}">
    <h2>Kies een Land</h2>
    <section id="vlaggenlijst">
        <ul>
            <c:forEach var="land" items="${landen}">

                <c:url var="soortURL" value="/">
                    <c:param name="landNr" value="${land.landNr}" />
                </c:url>

                <li>
                    <a href="${soortURL}" title="soorten wijn uit ${land.naam}">
                        <img
                            src="img/vlaggen/<fmt:formatNumber pattern="00" value="${land.landNr}"/>.png"
                            alt="Vlag van ${land.naam}" title="Vlag van ${land.naam}"
                            />
                    </a>
                </li>
            </c:forEach>
        </ul>
    </section>
</c:if>