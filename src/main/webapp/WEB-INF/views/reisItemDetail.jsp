<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag"%>
<%@ page import="java.util.*" %>
<jsp:include page="header.jsp"/>
<title>Plannie - ReisDetails ${reisItem.naam}</title>
<body>

<div class="wrapper">
    <div class="container shadow-lg mb-3">
        <div class="row">
            <div class="col-sm-12">
                <div class="row">
                    <div class="col-sm-4">
                        <img id="reisItemImg" class="img-fluid card-img-top"
                             src="${pageContext.request.contextPath}/images/${reisItem.imagePath}"
                             alt="Card image cap">
                    </div>
                    <div class="col-sm-8">
                        <h3 class="mt-5">${reisItem.naam}
                            <a id="wijzigReisItem" class="tekstknop-zwart" type="button" data-toggle="modal"
                               data-target="#wijzigReisItem2"><i class="far fa-edit"></i></a><a class="mt-5 tekstknop-zwart"
                                 href="${pageContext.request.contextPath}/groepDetail/${groep.groepId}">${groep.groepsNaam}</a>
                        </h3>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-4 text-center">
                        <h5>${reisItem.geefGeformatteerdeReisDuratie()}</h5>
                    </div>
                    <div class="col-sm-8">
                        <h5 href="${pageContext.request.contextPath}/${groep.groepId}/reisItemDetail/${reisItem.reisItemId}/BudgetOverzicht">
                            <a class="tekstknop-zwart"
                               href="${pageContext.request.contextPath}/${groep.groepId}/reisItemDetail/${reisItem.reisItemId}/BudgetOverzicht">
                                Kosten<br/>Totaal: ${reisItem.berekenTotaalBudget()} euro
                            </a>
                        </h5>
                    </div>
                </div>
                <hr class="my-4">
            </div>

            <div class="jumbotron" style="width:100%;">
                <div class="container">
                    <tag:reisItemAccordion subReisItems="${reisItem.geefReisGesorteerdDatum()}"
                                           reisDagen="${reisItem.geefDagenOverzicht()}" level="0"/>
                </div>
            </div>

            <div class="text-center" style="width:100%;">
                <div class="jumbotron">
                    <h5>Wat voor soort reisitem wil je toevoegen?</h5>

                    <tag:nieuwReisItemKnoppen optie="0"/>
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
