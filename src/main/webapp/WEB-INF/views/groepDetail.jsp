<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://kit.fontawesome.com/d450c035a5.js" crossorigin="anonymous"></script>
    <title>Plannie - Groepsdetails ${groep.groepsNaam}
    </title>
</head>
<body>

<nav class="navbar navbar-light bg-light">
    <span class="navbar-brand mb-0 h1"><a href="/gebruikerDetail" class="text-dark">${currentUser.voornaam}'s Plannie</a></span>

    <ul class="nav justify-content-end">
        <li class="nav-item">
            <a class="nav-link text-dark" method="post" id="gebruikerWijzigen" href="/gebruikerWijzig">Jouw gegevens</a>
        </li>
        <li class="nav-item">

            <a class="nav-link text-dark" id="logout" method="post" href="<c:url value="/logout"/>">Log uit</a>
        </li>
</nav>
<div class="container mt-3">
    <div class="row">

        <div class="col-sm-8">
            <div class="jumbotron">
                <div class="row">
                    <div class="col-12">
                        <table class="table table-hover table-borderless">
                            <thead>
                            <tr>
                                <th scope="col">Reizen</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr><td>Reis 1</td></tr>
                            <tr><td>Reis 2</td></tr>
                            <tr><td>Reis 3</td></tr>
                            <tr><td>Reis 4</td></tr>
                            <tr><td>Reis 5</td></tr>
                            <tr><td>Reis 6</td></tr>
                            </tbody>
                        </table>
                        <div class="container mt-3" >
                            <div class="row">
                                <form:form action="/${groep.groepId}/reisItemAanmaken" method="post" modelAttribute="nieuwReisItemFormulier">
                                    <input type="text" name="naam" required="required" placeholder="Naam Reis">
                                    <input id="reisItemAanmaken" type="submit" class="btn btn-primary" value="Maak reis aan">
                                </form:form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-sm-4">
            <div class="jumbotron" style="background-color: #666666;">
                <div class="row" >
                    <p class="lead text-white strong">${groep.groepsNaam}
                        <a id="wijzigGroepsNaam2" type="button" class="text-white" data-toggle="modal" data-target="#wijzigGroepsNaam"><i class="far fa-edit"></i></a>
                        </h3>
                    <table class="table table-hover table-borderless text-white">
                        <thead>
                        <tr>
                            <th scope="col">Naam</th>

                            <th scope="col"></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${groepsLedenLijst}" var="groepslid">
                            <tr>
                                <td data-toggle="tooltip" data-placement="bottom" title="${groepslid.email}">${groepslid.voornaam} ${groepslid.achternaam}</td>
                                <td><a href="${groep.groepId}/VerwijderLedenUitGroep/${groepslid.gebruikersId}">
                                    <i class="far fa-trash-alt"></i>
                                </a></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <hr class="my-4">
                <div class="row">
                    <p class="lead text-white mt-3">Alle Leden</h3>
                    <hr class="my-4">

                    <table class="table table-hover table-borderless text-white">
                        <thead>
                        <tr>
                            <th scope="col">Naam</th>
                            <th scope="col"></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${AlleLedenLijst}" var="lid">
                            <tr>
                                <td data-toggle="tooltip" data-placement="bottom" title="${lid.email}">${lid.voornaam} ${lid.achternaam}</td>
                                <td><a href="${groep.groepId}/voegGebruikerToeAanGroep/${lid.gebruikersId}">
                                    <i class="fas fa-plus"></i>
                                </a></td>
                            </tr>
                        </c:forEach>

                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-offset-1 col-sm-10">
            <div class="card w-75 mx-auto">
                <div class="card-body">
                    <form:form action="/{groepId}/voegLedenToeAanGroepViaEmail" method="post" modelAttribute="groepslidEmail">
                        <h5 class="card-title">Stuur een uitnodiging naar een nog niet bestaande gebruiker.</h5>
                        <p class="card-text">
                        <div class="form-group">
                            <div class="col">
                                <form:input type="email" class="form-control" path="email" placeholder="Email"/>
                            </div>
                        </div>
                        </p>
                        <div>
                            <button type="submit" class="btn btn-primary">Stuur</button>
                        </div>
                    </form:form>
                </div>
            </div>

        </div>
    </div>
</div>
</div>

<!-- Modal -->
<div class="modal fade" id="wijzigGroepsNaam" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Groepsnaam Wijzigen</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <jsp:include page="groepWijzig.jsp"/>
            </div>
        </div>
    </div>
</div>

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