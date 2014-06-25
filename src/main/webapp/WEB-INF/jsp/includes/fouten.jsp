<%@page pageEncoding="UTF-8" contentType="text/html"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${not empty fouten}">
	<section class="fouten">
		<%-- Print all the errors --%>
		<ul>
			<c:forEach var="fout" items="${fouten}">
				<li>${fout}</li>
			</c:forEach>
		</ul>
	</section>
</c:if>