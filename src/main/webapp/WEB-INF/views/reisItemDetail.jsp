<!doctype html>
<html lang="en">
<div class="view" style="background-image: url('https://images.unsplash.com/photo-1473496169904-658ba7c44d8a?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1950&q=80'); background-repeat: no-repeat; background-size: cover; background-attachment: fixed;">

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
        <title>Plannie - Groepsdetails ${groep.groepsNaam}</title>
    </head>
    <body>

    <nav class="navbar navbar-light bg-light shadow">
        <span class="navbar-brand mb-0 h1"><a id="gebruikerDetail" href="/gebruikerDetail" class="text-dark">${currentUser.voornaam}'s Plannie</a></span>

        <ul class="nav justify-content-end">
            <li class="nav-item">
                <a class="nav-link text-dark" method="post" id="gebruikerWijzigen" href="/gebruikerWijzig">Jouw gegevens</a>
            </li>
            <li class="nav-item">
                <form:form action="${pageContext.request.contextPath}/logout" method="POST">
                    <input id="logout" class="nav-link text-dark" style="border: none; background: transparent;" type="submit" value="Log uit" />
                </form:form>
            </li>
    </nav>
    <div class="container mt-3">
        <div class="row">

            <div class="col-sm-8">
                <div class="jumbotron shadow">
                    <div class="row">
                        <div class="col-12">
                            <table class="table table-hover table-borderless">
                                <thead>
                                <tr>
                                    Activiteiten
                                </tr>
                                </thead>
                                <tbody>
                                <tr><td>ReisItem 1</td></tr>
                                <tr><td>ReisItem 2</td></tr>
                                <tr><td>ReisItem 3</td></tr>
                                <tr><td>ReisItem 4</td></tr>
                                <tr><td>ReisItem 5</td></tr>
                                <tr><td>ReisItem 6</td></tr>
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
                <div class="jumbotron shadow" style="background-color: #666666;">
                    <div class="row" >
                        <p class="lead text-white">${reisItem.naam}</p>
                    </div>
                    <hr class="my-4">
                    <div class="row">
                        <p class="lead text-white mt-3">Locatie / Datum etc..</hp>
                        <hr class="my-4">
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-offset-1 col-sm-10">
                <div class="card w-75 mx-auto mb-5">
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
                                <button id="stuurEmail" type="submit" class="btn btn-primary">Stuur</button>
                            </div>
                        </form:form>
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

            <h6 class="mb-1"><small>Registreer je snel: <a href="/registreer" class="text-white">klik hier!</a></small></h6>


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