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
                        <form:form action="${pageContext.request.contextPath}/${groep.groepId}/${reisItem.reisItemId}/${reisItems.reisItemId}/notitieWijzigen" method="post" modelAttribute="notitieWijzigingsFormulier">
                            <div class="row">
                                <div class="col">
                                    <form:input type="text" class="form-control" path="naam" required="required"
                                                placeholder="notitieNaam"/>
                                </div>
                                <div class="col">
                                    <form:input type="date" class="form-control" path="startDatum"
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

                        <a id="notverwijderen1" data-toggle="modal" data-target="#waarschuwingsModal">
                            <button type="text" class="btn btn-primary mt-3" id="notVerwijderen">Verwijder notitie</button>
                        </a>
                        <hr class="my-1">

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
                    <p class="lead text-white"><a class ="lead text-white" href="${pageContext.request.contextPath}/${groep.groepId}/reisItemDetail/${reisItem.reisItemId}">${reisItem.naam} </a> <p class="lead text-white">  - <a class ="lead text-white" href="${pageContext.request.contextPath}/groepDetail/${groep.groepId}">${groep.groepsNaam}</a></p>
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
<jsp:include page="waarschuwingsModal.jsp"/>


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
</body>
</html>