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
                            <form:form action="${pageContext.request.contextPath}/${groep.groepId}/reisItemDetail/${reisItem.reisItemId}/nieuweActiviteit" method="post" modelAttribute="activiteitAanmakenFormulier">
                                    <form:radiobutton class="ActiviteitBorrelen" id="activiteitSoort" path="soortActiviteit" value="Borrelen" required="required" label="Borrelen"/><br>
                                    <form:radiobutton class="ActiviteitActiviteit" id="activiteitSoort1" path="soortActiviteit" value="Activiteit" required="required" label="Activiteit"/><br>
                                    <form:radiobutton class="ActiviteitDineren" id="activiteitSoort2" path="soortActiviteit" value="Dineren" required="required" label="Dineren"/><br>
                                    <form:radiobutton class="ActiviteitOvernachten" id="activiteitSoort3" path="soortActiviteit" value="Overnachten" required="required" label="Overnachten"/><br>

                                    <form:input id="activiteitTitel" type="text" class="form-control" path="naam" required="required"
                                                placeholder="Titel"/>
                                    <form:input id="activiteitOmschrijving" type="text" class="form-control" path="omschrijving" placeholder="Omschrijving"/>
                                    <form:input id="activiteitDatum" type="datetime-local" class="form-control" path="startDatum" required="required"
                                                value="${reisItem.geefNieuwStartDatum()}"/>

                                    <form:textarea id="activiteitTekst" path="tekst" class="form-control mt-2" rows="5" cols="30" required="required" placeholder="Vertel hier je groepsleden wat jullie gaan doen."/>
                            </div>
                        </div>
                                    <hr class="my-4">
                                    <p class="lead">Optioneel: Voeg een budget toe aan deze activiteit</p>
                                    <div class="row">
                                        <div class="col">
                                            <form:input id="activiteitBudget" type="number" step="10" min="0" class="form-control" path="budget"/>
                                        </div>
                                    </div>
                                    <hr class="my-4">
                                    <form:button id="activiteitAanmaken" type="submit" class="btn btn-primary mt-3">Activiteit opslaan
                                    </form:button>
                            </form:form>

                        <a href= "${pageContext.request.contextPath}/${groep.groepId}/reisItemDetail/${reisItem.reisItemId}">
                            <button type="text" class="btn btn-primary mt-3" id="annuleren">Annuleren</button>
                        </a>
                    </div>

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

<jsp:include page="footer.jsp"/>
