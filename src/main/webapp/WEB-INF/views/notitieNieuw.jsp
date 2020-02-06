<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<jsp:include page="header.jsp"/>
<title>Notitie aanmaken - ${currentUser.voornaam}</title>
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
                    <p class="lead">Notitie aanmaken</p>
                </div>
                <div class="row">
                    <div>
                        <form:form action="${pageContext.request.contextPath}/${groep.groepId}/reisItemDetail/${reisItem.reisItemId}/nieuweNotitie" method="post" modelAttribute="notitieAanmakenFormulier">
                            <div class="row">

                                    <form:input id="notitieTitel" type="text" class="form-control" path="naam" required="required"
                                                placeholder="Titel"/>
                                    <form:input id="notitieDatum" type="date" class="form-control" path="startDatum" required="required"
                                                placeholder="Datum"/>

                                    <form:textarea id="notitieTekst" path="tekst" class="form-control mt-2" rows="5" cols="30" required="required" placeholder="Vul hier uw notitie in"/>

                            </div>

                            <hr class="my-4">

                            <p class="lead">Optioneel: Voeg een budget toe aan deze notitie</p>

                            <div class="row">
                                <div class="col">
                                    <form:input id="notitieBudget" type="number" step="10" min="0" class="form-control" path="budget"/>
                                </div>
                            </div>


                            <hr class="my-4">

                            <form:button id="notitieAanmaken" type="submit" class="btn btn-primary mt-3">Notitie opslaan
                            </form:button>

                        </form:form>

                        <a href= "{pageContext.request.contextPath}/${groep.groepId}/reisItemDetail/${reisItem.reisItemId}">
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