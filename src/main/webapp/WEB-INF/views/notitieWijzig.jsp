<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<jsp:include page="header.jsp"/>
<title>Plannie - ReisItemDetails ${reisItem.naam}</title>
<body>

<div class="container mt-3">
    <div class="row">
        <div class="col-sm-8">
            <div class="jumbotron shadow">
                <div class="row">
                    <p class="lead">Notitie wijzigen of verwijderen</p>
                </div>
                <div class="row">
                    <div>
                        <form:form action="${pageContext.request.contextPath}/${groep.groepId}/${reisItem.reisItemId}/${reisItems.reisItemId}/notitieWijzigen"
                                   method="post" modelAttribute="notitieWijzigingsFormulier">
                            <div class="row">
                                <div class="col">
                                    <form:input type="text" class="form-control" path="naam" required="required"
                                                placeholder="notitieNaam"/>
                                </div>
                                <div class="col">
                                    <form:input type="datetime-local" class="form-control" path="startDatum"
                                                required="required" placeholder="startDatum"/>
                                </div>
                            </div>
                            <div class="form-group mt-3">
                                     <form:input type="number" class="form-control" path="budget" required="required"
                                                placeholder="notitieBudget" />
                            </div>
                            <div class="form-group mt-3">
                                <form:textarea id="notitieTekst" path="tekst" class="form-control mt-2" rows="5" cols="30" required="required"
                                               placeholder="Vul hier uw notitie in"/>
                            </div>
                          <hr class="my-1">
                            <form:button id="notitieWijzigen" type="submit" class="btn btn-primary mt-3">Wijzig
                                notitie
                            </form:button>
                        </form:form>

                        <button type="button" class="btn btn-primary mt-3" data-toggle="modal" data-target="#waarschuwingsModal"
                             onclick="vullenModal(
                            'Notitie verwijderen',
                            'Weet u zeker dat u deze notitie wil verwijderen?',
                            'Verwijder',
                            '${pageContext.request.contextPath}/${groep.groepId}/${reisItem.reisItemId}/${reisItems.reisItemId}/subReisItemVerwijderen')">
                             Verwijder notitie
                        </button>
                        <p><br></p>
                        <a href="${pageContext.request.contextPath}/${groep.groepId}/reisItemDetail/${reisItem.reisItemId}">
                            <button type="text" class="btn btn-primary mt-3" id="annuleren">Annuleren</button>
                        </a>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="jumbotron shadow" id="wouter">
                <div class="row" >
                    <p class="lead text-white">
                        <a class ="lead text-white" href="${pageContext.request.contextPath}/${groep.groepId}/reisItemDetail/${reisItem.reisItemId}">${reisItem.naam} </a>
                    <p class="lead text-white">  -
                        <a class ="lead text-white" href="${pageContext.request.contextPath}/groepDetail/${groep.groepId}">${groep.groepsNaam}</a>
                    </p>
                </div>
                <hr class="my-4">
                <div class="row">
                    <p class="lead text-white mt-3">Locatie / Datum etc..
                    </p>
                    <hr class="my-4">
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<jsp:include page="waarschuwingsModal.jsp"/>
<jsp:include page="footer.jsp"/>
