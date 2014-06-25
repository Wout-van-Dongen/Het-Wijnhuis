<%@page pageEncoding="UTF-8" contentType="text/html"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<table class="winkelmand">
            <%--printing the table titles--%>
            <thead>
                <tr>
                    <th class="heading" colspan="4">Winkelmandje</th>
                </tr>
                <tr>
                    <th class="wijnCel">Wijn</th>
                    <th class="prijsCel">Prijs</th>
                    <th class="aantalCel">Aantal</th>
                    <th class="betalenCel">Te betalen</th>
                </tr>
            </thead>
            <%--creating a new var to keep the total ammount to pay--%>
            <c:set var="totaalBedrag"  value="0"/>
            <tbody>
                <c:choose>			
                    <%--als de winkelmand niet leeg is--%>
                    <c:when test="${not empty winkelmandje}">
                        <c:forEach var="entry" items="${winkelmandje}">
                            <tr>
                                <td class="wijnCel lefttext">${entry.key.soort.land.naam} ${entry.key.soort.naam} ${entry.key.jaar}</td>
                                <td class="prijsCel righttext"><fmt:formatNumber value="${entry.key.prijs}" type="currency" currencySymbol="€"/></td>
                                <td class="aantalCel righttext">${entry.value}</td>
                                <td class="betalenCel righttext"><fmt:formatNumber value="${entry.key.prijs*entry.value}" type="currency" currencySymbol="€"/></td>
                                <%--adding the ammount to the total ammount--%>
                                <c:set var="totaalBedrag" value="${totaalBedrag + (entry.key.prijs*entry.value)}"/>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <%--als de wikelmand leeg is--%>
                    <c:otherwise>
                        <tr>
                            <td class="centertext" colspan="4">Uw winkelmandje is leeg!</td>
                        </tr>
                    </c:otherwise>
                </c:choose>
            </tbody>
            <tfoot>
                <tr>
                    <td class="righttext" colspan="3">Totaal:</td>
                    <td class="prijsCel righttext"><fmt:formatNumber value="${totaalBedrag}" type="currency" currencySymbol="€"/></td>
                </tr>
            </tfoot>
        </table>
