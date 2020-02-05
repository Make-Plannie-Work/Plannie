<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<jsp:include page="header.jsp"/>
<title>Activiteit aanmaken - ${currentUser.voornaam}</title>
<body>

<div class="container mt-3">
    <div class="row">
        <div class="col-sm-12 fixed-top">
        </div>
    </div>
    <div class="row">

        <div class="col-sm-8">
            <div class="jumbotron shadow border">
                <div class="row">
                    <p class="lead">Activiteit</p>
                </div>
                <div class="row">
                    <div>
                        <div class="row">
                            <div class="col">
                                <form:form action="/${groepId}/${reisItemId}/${reisItemsId}/activiteitWijzigen" method="post" modelAttribute="activiteitWijzigFormulier">
                                            <form:radiobutton id="activiteitSoort" path="soortActiviteit" value="Borrelen" required="required" label="Borrelen"/><br>
                                            <form:radiobutton id="activiteitSoort1" path="soortActiviteit" value="Activiteit" required="required" label="Activiteit"/><br>
                                            <form:radiobutton id="activiteitSoort2" path="soortActiviteit" value="Dineren" required="required" label="Dineren"/><br>
                                            <form:radiobutton id="activiteitSoort3" path="soortActiviteit" value="Overnachten" required="required" label="Overnachten"/><br>

                                    <form:input id="activiteitTitel" type="text" class="form-control" path="naam" required="required"
                                                placeholder="Titel"/>
                                    <form:input id="activiteitOmschrijving" type="text" class="form-control" path="omschrijving"/>
                                    <form:input id="activiteitDatum" type="date" class="form-control" path="startDatum" required="required" placeholder="Datum"/>
                                    <form:input id="activiteitBudget" type="number" step="10" min="0" class="form-control" path="budget" placeholder="Budget"/>
                            </div>
                        </div>

                        <hr class="my-4">

                        <form:button id="activiteitWijzigen" type="submit" class="btn btn-primary mt-3">
                            Wijzigingen opslaan
                        </form:button>
                        </form:form>

                        <form:form action="/${groep.groepId}/${reisItem.reisItemId}/${reisItems.reisItemId}/subReisItemVerwijderen" method="post" modelAttribute="subReisItemVerwijderFormulier">
                            <form:button id="activiteitVerwijderen" type="submit" class="btn btn-primary mt-3">
                                Verwijder Activiteit
                            </form:button>
                        </form:form>

                        <hr class="my-4">

                        <c:forEach items="${alleReisItemsVanReis}" var="reisItems">
                            <div class="card reisItem flex-row flex-wrap mb-2 mx-auto" id="reisItemCard">

                                <p class="lead">Notitie</p>
                                <c:set var="soortReisItem" scope="session" value="${MakePlannieWork.Plannie.model.reisitem.Notitie}"/>
                                <div class="card-header border-0">
                                    Notitie ${reisItems.startDatum}
                                </div>
                                <div class="card-block px-2">
                                    <h4 class="card-title"><a id="LocatieDetails${reisItems.naam}"
                                                              href="/${groepId}/${reisItemId}/${reisItems.reisItemId}/NotitieWijzig">${reisItems.naam}</a>
                                    </h4>
                                    <p class="card-text">${reisItems.adres}</p>
                                    <div class="googleMapSmall" id="map${reisItems.reisItemId}" data-latitude="${reisItems.latitude}" data-longitude="${reisItems.longitude}" data-id="${reisItems.reisItemId}"></div>
                                </div>
                            </div>
                        </c:forEach>

                    </div>
                </div>
            </div>
        </div>

        <div class="col-sm-4">
            <div class="jumbotron shadow" id="wouter">
                <div class="row">

                </div>
                <hr class="my-4">
                <div class="row">
                    <p class="lead text-white mt-3">Info 2</hp>
                    <hr class="my-4">
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-offset-1 col-sm-10 mb-5">
            <div class="card w-75 mx-auto">
                <div class="card-body">
                    <p>Contact</p>
                </div>
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

<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="//code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
        crossorigin="anonymous"></script>
</body>
</html>