<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<jsp:include page="header.jsp"/>
<title>Plannie - ReisDetails ${reisItem.naam}</title>
<body>

<div class="container mt-3">
    <div class="row">

            <div class="col-sm-8">
                <div class="jumbotron shadow border">
                    <div class="row">
                        <p class="lead">
                            <a id="reisItemBudget">Het totale budget van ${reisItem.naam} bedraagt: ${reisItem.berekenTotaalBudget()} euro</a>
                         </p>
                    </div>
                    <div class="row">

                    <c:forEach items="${alleReisItemsVanReis}" var="reisItems">
                        <div class="card reisItem flex-row flex-wrap mb-2 mx-auto" id="reisItemCard">

                            <c:set var="soortReisItem" scope="session" value="${reisItems.getClass().name}"/>

                            <c:choose>
                                <c:when test="${soortReisItem == 'MakePlannieWork.Plannie.model.reisitem.Notitie'}">

                                        <div class="card-header border-0">
                                            Notitie ${reisItems.startDatum}
                                        </div>
                                        <div class="card-block px-2">
                                            <h4 class="card-title"><a id="NotitieDetails${reisItems.naam}"
                                                                      href="/${groepId}/${reisItemId}/${reisItems.reisItemId}/NotitieWijzigen">${reisItems.naam}</a>
                                            </h4>
                                            <p class="card-text">${reisItems.tekst}</p>
                                            <p class="card-text">${reisItems.budget}</p>
                                        </div>

                                </c:when>
                                <c:when test="${soortReisItem == 'MakePlannieWork.Plannie.model.reisitem.Poll'}">

                                    <div class="card-header border-0">
                                        Poll ${reisItems.startDatum}
                                    </div>
                                    <div class="card-block px-2">
                                        <h4 class="card-title"><a id="PollDetails${reisItems.naam}"
                                                                  href="/${groepId}/${reisItemId}/PollDetail/${reisItems.reisItemId}">${reisItems.naam}</a>
                                        </h4>
                                        <p class="card-text">
                                        <p class="card-text">
                                            <c:if test="${!reisItems.gebruikerHeeftGestemd(currentUser.gebruikersId)}">
                                                <a class="lead text-blue">Stem nu!</a>
                                            </c:if>
                                            <a id="pollOptieTekst${reisItems.reisItemId}">${reisItems.pollStatus()}</a>
                                        </p>
                                        </p>
                                    </div>
                                </c:when>
                                <c:when test="${soortReisItem == 'MakePlannieWork.Plannie.model.reisitem.Locatie'}">
                                    <div class="card-header border-0">
                                        Locatie ${reisItems.startDatum}
                                    </div>
                                    <div class="card-block px-2">
                                        <h4 class="card-title"><a id="LocatieDetails${reisItems.naam}"
                                                                  href="/${groepId}/${reisItemId}/${reisItems.reisItemId}/LocatieWijzigen">${reisItems.naam}</a>
                                        </h4>
                                        <p class="card-text">${reisItems.adres}</p>
                                        <div class="googleMapSmall" id="map${reisItems.reisItemId}"
                                             data-latitude="${reisItems.latitude}"
                                             data-longitude="${reisItems.longitude}"
                                             data-id="${reisItems.reisItemId}"></div>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="card-header border-0">
                                        <img src="https://via.placeholder.com/100.jpg" alt="">
                                    </div>
                                    <div class="card-block px-2">
                                        <h4 class="card-title"><a id="reisItemDetails${reisItems.reisItemId}"
                                                                  href="/${groep.groepId}/reisItemsDetail/${reisItems.reisItemId}">${reisItems.naam}</a>
                                        </h4>
                                        <p class="card-text">${reisItems.getClass().name}</p>
                                    </div>
                                </c:otherwise>
                            </c:choose>

                        </div>
                    </c:forEach>

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
                           href="/${groep.groepId}/reisItemDetail/${reisItem.reisItemId}/NotitieAanmaken">Een
                            Notitie</a>
                        <a class="dropdown-item" id="pollKeuze"
                           href="/${groep.groepId}/reisItemDetail/${reisItem.reisItemId}/PollAanmaken">Een Poll</a>
                        <a class="dropdown-item" id="locatieKeuze"
                           href="/${groep.groepId}/reisItemDetail/${reisItem.reisItemId}/LocatieAanmaken">Een
                            Locatie</a>
                        <a class="dropdown-item" id="activiteitKeuze" href="#">Een Activiteit</a>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="jumbotron shadow" id="wouter">

                <img id="reisItemImg" class="img-fluid card-img-top" src="/images/${reisItem.imagePath}"
                     alt="Card image cap">

                <div class="row">
                    <h5 class="lead text-white">${reisItem.naam}
                        <a id="wijzigReisItem" type="button" class="text-white" data-toggle="modal"
                           data-target="#wijzigReisItem2"><i class="far fa-edit"></i></a>
                        - <a class="lead text-white" href="/groepDetail/${groep.groepId}">${groep.groepsNaam}</a>
                    </h5>
                </div>
                <hr class="my-4">
                <div class="row">
                    <p class="lead text-white mt-3">Locatie / Datum etc..</hp>
                    <hr class="my-4">
                </div>
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
<!-- Footer -->
<footer class="py-4 bg-dark text-white-50">

    <!-- Footer Elements -->
    <div class="container">

        <!-- Call to action -->
        <ul class="list-unstyled list-inline text-center py-2">

            </li>
        </ul>
        <!-- Call to action -->

    </div>
    <!-- Footer Elements -->
</footer>
<!-- Footer -->

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
        integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
        crossorigin="anonymous"></script>

<script src="\js/googleKaart.js"></script>
<!-- Replace the value of the key parameter with your own API key. -->
<script defer
        src="https://maps.googleapis.com/maps/api/js?key=${mapsAPI}&callback=initMapsWithMarker">
</script>
</body>
</html>