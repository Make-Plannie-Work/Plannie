<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag"%>
<%@ page import="java.util.*" %>
<jsp:include page="header.jsp"/>
<title>Activiteit aanmaken - ${currentUser.voornaam}</title>
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
                    <div class="col">
                        <div class="row">
                            <p class="lead">Activiteit</p>
                        </div>
                        <div class="row">
                            <div>
                                <div class="row">
                                    <div class="col-sm-8">
                                        <form:form
                                                action="${pageContext.request.contextPath}/${groepId}/${reisItemId}/${reisItemsId}/activiteitWijzigen"
                                                method="post" modelAttribute="activiteitWijzigFormulier">
                                            <form:radiobutton id="activiteitSoort" path="soortActiviteit"
                                                              value="Borrelen"
                                                              required="required" label="Borrelen"/>
                                            <br>
                                            <form:radiobutton id="activiteitSoort1" path="soortActiviteit"
                                                              value="Activiteit"
                                                              required="required" label="Activiteit"/>
                                            <br>
                                            <form:radiobutton id="activiteitSoort2" path="soortActiviteit"
                                                              value="Dineren"
                                                              required="required" label="Dineren"/>
                                            <br>
                                            <form:radiobutton id="activiteitSoort3" path="soortActiviteit"
                                                              value="Overnachten"
                                                              required="required" label="Overnachten"/>
                                            <br>

                                            <form:input id="activiteitTitel" type="text" class="form-control"
                                                        path="naam"
                                                        required="required"
                                                        placeholder="Titel"/>
                                            <form:input id="activiteitOmschrijving" type="text" class="form-control"
                                                        path="omschrijving"/>
                                            <form:input id="activiteitDatum" type="datetime-local" class="form-control"
                                                        path="startDatum"
                                                        required="required" placeholder="Datum"/>
                                            <form:input id="activiteitBudget" type="number" step="10" min="0"
                                                        class="form-control" path="budget" placeholder="Budget"/>
                                    </div>
                                </div>

                                <hr class="my-4">

                                <form:button id="activiteitWijzigen" type="submit" class="btn btn-primary mt-3">
                                    Wijzigingen opslaan
                                </form:button>
                                </form:form>

                                <button type="button" class="btn btn-primary mt-3" data-toggle="modal"
                                        data-target="#waarschuwingsModal"
                                        onclick="vullenModal(
                                    'Activiteit verwijderen',
                                    'Weet u zeker dat u deze activiteit wil verwijderen?',
                                    'Verwijder',
                                    '${pageContext.request.contextPath}/${groep.groepId}/${reisItem.reisItemId}/${reisItems.reisItemId}/subReisItemVerwijderen')">
                                    Verwijder activiteit
                                </button>

                                <a href="${pageContext.request.contextPath}/${groep.groepId}/reisItemDetail/${reisItem.reisItemId}">
                                    <button type="text" class="btn btn-primary mt-3" id="annuleren">Annuleren</button>
                                </a>
                                <hr class="my-4">

                                <div class="row">
                                    <div class="col-sm-11">
                                        <tag:reisItemAccordion subReisItems="${reisItems.geefReisGesorteerdDatum()}"/>
                                    </div>
                                </div>
                            </div>
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

    </div>
    <jsp:include page="waarschuwingsModal.jsp"/>
    <jsp:include page="footer.jsp"/>
    <script defer
            src="https://maps.googleapis.com/maps/api/js?key=${mapsAPI}&callback=initMapsWithMarker">
    </script>
