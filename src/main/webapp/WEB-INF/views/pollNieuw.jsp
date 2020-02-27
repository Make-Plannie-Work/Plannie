<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<jsp:include page="header.jsp"/>
<title>Poll aanmaken - ${currentUser.voornaam}</title>
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
                    <p class="lead">Poll aanmaken</p>
                </div>
                    <div>
                        <form:form action="${pageContext.request.contextPath}/${groep.groepId}/reisItemDetail/${reisItem.reisItemId}/nieuwePoll"
                                   method="post" modelAttribute="pollAanmakenFormulier">
                            <div class="row">
                                <div class="col-md-" >
                                    <form:input id="pollTitel" type="text" class="form-control" path="naam"
                                                required="required"
                                                placeholder="Titel"/>
                                    <form:input id="pollDatum" type="datetime-local" class="form-control" path="startDatum"
                                                required="required"
                                                value="${reisItem.geefNieuwStartDatum()}"/>
                                </div>
                            </div>

                            <hr class="my-4">

                            <div class="row">
                                <form:input id="pollOpties" type="text" class="form-control" path="locatie"
                                            required="required"
                                            placeholder="Vul stemopties in, gescheiden door komma's"/>
                            </div>

                            <hr class="my-4">

                            <p class="lead">Optioneel: Voeg een budget toe aan deze poll</p>

                            <div class="row">
                                <form:input id="pollBudget" type="number" step="10" min="0" class="form-control" path="budget"/>

                            </div>

                            <form:button id="pollAanmaken" type="submit" class="btn btn-primary mt-3">Poll opslaan
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
<jsp:include page="footer.jsp"/>
