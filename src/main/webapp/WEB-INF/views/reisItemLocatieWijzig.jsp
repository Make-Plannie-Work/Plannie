<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<jsp:include page="header.jsp"/>
<title>Plannie - ReisItemDetails ${reisItem.naam}</title>
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
                    <p class="lead">Locatie aanmaken</p>
                </div>
                <div class="row">
                    <div class="googleMapLarge" id="map"></div>

                        <form:form
                                action="${pageContext.request.contextPath}/${groep.groepId}/${reisItem.reisItemId}/${reisItems.reisItemId}/locatieWijzigen"
                                method="post" modelAttribute="locatieWijzigingsFormulier">


                            <div class="row">


                                <form:input id="locatieNaam" type="text" class="form-control" path="naam"
                                            required="required" data-toggle="tooltip" data-placement="top"
                                            title="Naam van deze locatie"
                                            placeholder="Geef een naam op voor deze locatie"/>
                                <form:input id="locatieDatum" type="date" class="form-control" path="startDatum"
                                            required="required"
                                            placeholder="Datum"/>
                                <label for="locatieAdres">Adres</label>
                                <form:input id="locatieAdres" type="text" class="form-control" path="adres"
                                            required="required" data-toggle="tooltip" data-placement="top" title="Adres"
                                            placeholder="Adres van de locatie"/>
                                <label for="locatieLatitude">Latitude</label>
                                <label for="locatieLongitude">Longitude</label>
                                <form:input id="locatieLatitude" type="text" class="form-control-inline"
                                            path="latitude" required="required" data-toggle="tooltip" data-placement="top"
                                            title="Latitude" readonly="true"/>
                                <form:input id="locatieLongitude" type="text" class="form-control-inline"
                                            path="longitude" required="required" data-toggle="tooltip" data-placement="top"
                                            title="Longitude" readonly="true"/>
                            </div>

                            <hr class="my-4">


                            <form:button id="locatieWijzigen" type="submit" class="btn btn-primary mt-3">Wijzig
                                locatie
                            </form:button>

                        </form:form>


                        <form:form
                                action="${pageContext.request.contextPath}/${groep.groepId}/${reisItem.reisItemId}/${reisItems.reisItemId}/subReisItemVerwijderen"
                                method="post" modelAttribute="subReisItemVerwijderFormulier">


                            <form:button id="locatieVerwijderen" type="submit" class="btn btn-primary mt-3">Verwijder
                                locatie
                            </form:button>

                        </form:form>


                        <a href="${pageContext.request.contextPath}/${groep.groepId}/reisItemDetail/${reisItem.reisItemId}">
                            <button type="text" class="btn btn-primary mt-3" id="annuleren">Annuleren</button>
                        </a>
                    </div>

                </div>
            </div>
        </div>


        <div class="col-sm-4">
            <div class="jumbotron shadow" id="wouter">
                <div class="row">
                    <p class="lead text-white"><a class="lead text-white"
                                                  href="${pageContext.request.contextPath}/${groep.groepId}/reisItemDetail/${reisItem.reisItemId}">${reisItem.naam} </a>
                    <p class="lead text-white"> - <a class="lead text-white" href="${pageContext.request.contextPath}/groepDetail/${groep.groepId}">${groep.groepsNaam}</a>
                    </p>
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
<!-- Replace the value of the key parameter with your own API key. -->
<script defer
        src="https://maps.googleapis.com/maps/api/js?key=${mapsAPI}&callback=initMapAndGeo">
</script>
<script src="${pageContext.request.contextPath}\js/googleKaart.js"></script>
</body>
</html>