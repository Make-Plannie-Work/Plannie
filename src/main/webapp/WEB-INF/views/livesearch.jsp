<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<form:form action="#" autocomplete="off">
    <div id="autocomplete">
        <input type="text" class="form-control" path="tekst" id="Zoek_Gebruikers" placeholder="Voeg bestaande gebruikers toe">
    </div>
    <input type="hidden" id="groepId" value='${groep.groepId}'/>
    <input type="hidden" id="contextPath" value="${pageContext.request.contextPath}"/>
</form:form>
