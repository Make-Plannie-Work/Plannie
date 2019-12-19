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
    <title>Groepsdetails ${groep.groepsNaam}
    </title>
</head>
<body>
<div class="jumbotron">
    <div class="row">
        <div class="col-8">
            <table class="table table-hover table-dark">
                <thead>
                <tr>
                    <th scope="col">Reizen</th>
                </tr>
                </thead>
                <tbody>


                </tbody>
            </table>
        </div>
        <div class="col-4">
            <h3 class="display-4">Groepsdetails ${groep.groepsNaam}
                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#wijzigGroepsNaam"><i
                        class="fas fa-edit"></i></button>
            </h3>
            <hr class="my-4">

            <table class="table table-hover table-dark col-2">
                <thead>
                <tr>
                    <th scope="col">Voornaam</th>
                    <th scope="col">Email</th>
                    <th scope="col"></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${groepsLedenLijst}" var="groepslid">
                    <tr>
                        <td>${groepslid.voornaam}</td>
                        <td>${groepslid.email}</td>
                        <td><a href="${groep.groepId}/VerwijderLedenUitGroep/${groepslid.gebruikersId}">
                            <i class="far fa-trash-alt"></i>
                        </a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <a href="/gebruikerDetail">
                <button type="text" class="btn btn-primary mt-3" id="annuleren">Terug</button>
            </a>
        </div>
    </div>
    <div class="row">
        <div class="col-8">

        </div>
        <div class="col-4">
            <h3 class="display-4">Alle Leden</h3>
            <hr class="my-4">

            <table class="table table-hover table-dark col-2">
                <thead>
                <tr>
                    <th scope="col">Voornaam</th>
                    <th scope="col">Achternaam</th>
                    <th scope="col"></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${AlleLedenLijst}" var="lid">
                    <tr>
                        <td>${lid.voornaam}</td>
                        <td>${lid.achternaam}</td>
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

<!-- Modal -->
<div class="modal fade" id="wijzigGroepsNaam" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
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