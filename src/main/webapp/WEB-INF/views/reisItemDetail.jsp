<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag"%>
<%@ page import="java.util.*" %>
<jsp:include page="header.jsp"/>
<title>Plannie - ReisDetails ${reisItem.naam}</title>
<body>

<div class="container mt-3">
    <div class="row">

        <div class="col-sm-8">
            <div class="jumbotron shadow border">
                <div class="row">
                    <div class="col-sm-12">
                        <tag:reisItemAccordion subReisItems="${reisItem.geefReisGesorteerdDatum()}" reisDagen="${reisItem.geefDagenOverzicht()}" level="0"/>
                    </div>

                </div>
            </div>
            <div class="jumbotron shadow border">
                <div class="dropdown">
                    <button class="btn btn-secondary dropdown-toggle" type="button" id="keuzeReisItemMenu"
                            data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Wat voor soort reisitem wil je maken?
                    </button>
                    <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                        <a class="dropdown-item" id="notitieKeuze"
                           href="${pageContext.request.contextPath}/${groep.groepId}/reisItemDetail/${reisItem.reisItemId}/NotitieAanmaken">Een
                            Notitie</a>
                        <a class="dropdown-item" id="pollKeuze"
                           href="${pageContext.request.contextPath}/${groep.groepId}/reisItemDetail/${reisItem.reisItemId}/PollAanmaken">Een Poll</a>
                        <a class="dropdown-item" id="locatieKeuze"
                           href="${pageContext.request.contextPath}/${groep.groepId}/reisItemDetail/${reisItem.reisItemId}/LocatieAanmaken">Een
                            Locatie</a>
                        <a class="dropdown-item" id="activiteitKeuze" href="${pageContext.request.contextPath}/${groep.groepId}/reisItemDetail/${reisItem.reisItemId}/ActiviteitAanmaken">Een Activiteit</a>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="jumbotron shadow" id="wouter">

                <img id="reisItemImg" class="img-fluid card-img-top" src="${pageContext.request.contextPath}/images/${reisItem.imagePath}"
                     alt="Card image cap">

                <div class="row mt-5">
                    <h5>Naam reis: <a id="wijzigReisItem" type="button" class="tekstknop" data-toggle="modal"
                       data-target="#wijzigReisItem2">${reisItem.naam}</a>
                    </h5>
                </div>
                <div class="row">
                    <h5>Groep: <a class="tekstknop" href="${pageContext.request.contextPath}/groepDetail/${groep.groepId}">${groep.groepsNaam}</a></h5>
                </div>
                <div class="row">
                    <h5>${reisItem.geefNieuwStartDatum()} tot ${reisItem.getEindDatum()}</h5>
                </div>
                <hr class="my-4">
                <div class="row">
                    <p class="lead text-white mt-3">Locatie / Datum </p>

                </div>
                <hr class="my-4 shadow">
                <div class="row">
                    <h5><a class="tekstknop" href="${pageContext.request.contextPath}/${groep.groepId}/reisItemDetail/${reisItem.reisItemId}/BudgetOverzicht">Kosten</a></h5>
                </div>
                <div class="row"><p class="lead text-white">Totaal: ${reisItem.berekenTotaalBudget()} euro</p>
            </div>
        </div>
    </div>
</div>
</div>
<!-- Modal Reis wijzigen -->
<div class="modal fade" id="wijzigReisItem2" tabindex="-1" role="dialog" aria-labelledby="Wijzig ReisItem"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Reis Item Wijzigen</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <jsp:include page="reisItemWijzig.jsp"/>
            </div>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>
<!-- Replace the value of the key parameter with your own API key. -->
<script defer
        src="https://maps.googleapis.com/maps/api/js?key=${mapsAPI}&callback=initMapsWithMarker">
</script>
