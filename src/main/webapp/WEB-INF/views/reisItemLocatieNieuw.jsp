<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<jsp:include page="header.jsp"/>
<title>Locatie aanmaken - ${currentUser.voornaam}</title>
<body>

<div class="wrapper">
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
                                action="${pageContext.request.contextPath}/${groep.groepId}/reisItemDetail/${reisItem.reisItemId}/nieuweLocatie"
                                method="post" modelAttribute="locatieAanmakenFormulier">

                            <div class="row">


                                <form:input id="locatieNaam" type="text" class="form-control" path="naam"
                                            required="required" data-toggle="tooltip" data-placement="top"
                                            title="Naam van deze locatie"
                                            placeholder="Geef een naam op voor deze locatie"/>
                                <form:input id="locatieDatum" type="datetime-local" class="form-control"
                                            path="startDatum"
                                            required="required"
                                            value="${reisItem.geefNieuwStartDatum()}"/>
                                <label for="locatieAdres">Adres</label>
                                <form:input id="locatieAdres" type="text" class="form-control" path="adres"
                                            required="required" data-toggle="tooltip" data-placement="top" title="Adres"
                                            placeholder="Adres van de locatie"/>
                                <label for="locatieLatitude">Latitude</label>
                                <label for="locatieLongitude">Longitude</label>
                                <form:input id="locatieLatitude" type="text" class="form-control-inline"
                                            path="latitude" required="required" data-toggle="tooltip"
                                            data-placement="top"
                                            title="Latitude" readonly="true"/>
                                <form:input id="locatieLongitude" type="text" class="form-control-inline"
                                            path="longitude" required="required" data-toggle="tooltip"
                                            data-placement="top"
                                            title="Longitude" readonly="true"/>
                            </div>

                            <hr class="my-4">

                            <form:button id="locatieAanmaken" type="submit" class="btn btn-primary mt-3">Locatie opslaan
                            </form:button>

                        </form:form>

                        <a href="${pageContext.request.contextPath}/${groep.groepId}/reisItemDetail/${reisItem.reisItemId}">
                            <button type="text" class="btn btn-primary mt-3" id="annuleren">Annuleren</button>
                        </a>
                    </div>

                </div>
            </div>

            <div class="col-sm-4">
                <div class="jumbotron shadow" id="wouter">
                    <div class="row">
                        <p class="lead text-white">Info 1</p>
                    </div>
                    <hr class="my-4">
                    <div class="row">
                        <p class="lead text-white mt-3">Info 2</hp>
                        <hr class="my-4">
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-offset-1 col-sm-10 mb-5">
            <div class="card w-75 mx-auto">
                <div class="plannieCard-body">
                    <p>Contact</p>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="footer.jsp"/>
    <script defer
            src="https://maps.googleapis.com/maps/api/js?key=${mapsAPI}&callback=initMapAndGeo">
    </script>