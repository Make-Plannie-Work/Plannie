<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<jsp:include page="header.jsp"/>
<title>Budget overzicht</title>
<body>
<div class="wrapper">
    <div class="container shadow-lg mb-3">
    <div class="row mx-auto mb-4">
        <h3>Budget overzicht</h3>
        <div class="col-sm-offset-1 col-sm-10 mb-5">
        </div>
                    <table class="table table-borderless">
                        <thead>
                        <tr><th>Datum</th><th></th><th>Wat?</th><th></th><th>Hoeveel?</th></tr>
                        </thead>
                        <tbody>
                            <c:forEach var="reisItem" items="${reisItem.geefReisGesorteerdDatum()}">
                                <c:if test="${reisItem.budget > 0}">
                                    <tr><td>${reisItem.geefGeformatteerdeStartDatumEnTijd()}</td><td></td><td>${reisItem.naam}</td><td></td><td>&euro;${reisItem.budget}</td></tr>
                                </c:if>
                            </c:forEach>
                        </tbody>
                        <tfoot>
                        <tr><td></td><td></td><td>Totaal</td><td></td><td>&euro;${reisItem.berekenTotaalBudget()}</td></tr>
                        </tfoot>
                    </table>
                <h5><a class="tekstknop-zwart " href="${pageContext.request.contextPath}/${groep.groepId}/reisItemDetail/${reisItem.reisItemId}/">Ga terug</a></h5>
        </div>
    </div>
<jsp:include page="footer.jsp"/>




