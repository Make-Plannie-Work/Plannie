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
        <title>Welkom bij Plannie - ${currentUser.voornaam}</title>
    </head>
    <body>

    <nav class="navbar navbar-light bg-light shadow">
        <span class="navbar-brand mb-0 h1"><a href="/gebruikerDetail" class="text-dark">${currentUser.voornaam}'s Plannie</a></span>

        <ul class="nav justify-content-end">

            <li class="nav-item">
                <form:form action="${pageContext.request.contextPath}/logout" method="POST">
                    <input id="logout" class="nav-link text-dark" style="border: none; background: transparent;" type="submit" value="Log uit" />
                </form:form>
            </li>
    </nav>

    <div class="container mt-3">
        <div class="row">
            <div class="col-sm-12 fixed-top">
            </div>
        </div>
        <div class="row">

            <div class="col-sm-8">
                <div class="jumbotron shadow">
                    <div class="row">
                        <p class="lead">Wijzig je gegevens</p>
                    </div>
                    <div class ="row">
                        <div>
                            <form:form action = "/wijzigen" method="post" modelAttribute="gebruikersWijzigingsFormulier">
                                <div class="row">
                                    <div class="col">
                                        <form:input type="text" class="form-control" path="voornaam" required="required" placeholder="Voornaam" value ="${currentUser.voornaam}" />
                                    </div>
                                    <div class="col">
                                        <form:input type="text" class="form-control" path="achternaam" required="required" placeholder="Achternaam" value ="${currentUser.achternaam}" />
                                    </div>
                                </div>

                                <div class="form-group mt-3">
                                    <form:input type="email" class="form-control" path="email" required="required" placeholder="Email" value ="${currentUser.email}" />
                                </div>

                                <form:button id="gebruikerWijzigen" type="submit" class="btn btn-primary mt-3"  >Wijzig gegevens</form:button>

                            </form:form>

                            <a href="/gebruikerDetail"><button type="text" class="btn btn-primary mt-3" id="annuleren">Annuleren</button></a>
                        </div>

                    </div>
                </div>
            </div>

            <div class="col-sm-4">
                <div class="jumbotron shadow" style="background-color: #666666;">
                    <div class="row" >
                        <c:forEach items="${lijstMetGroepen}" var="groep">
                            <div class="accordion " id="lijstMetGroepen">
                                <div class="text-white">
                                    <div class="card-header" id="headingOne">
                                        <h2 class="mb-0">
                                            <button class="btn btn-link text-white" type="button" data-toggle="collapse" data-target="#collapseOne" aria-expanded="false" aria-controls="collapseOne">
                                                <a href="/groepDetail/${groep.groepId}" class="text-white">${groep.groepsNaam}</a>
                                            </button>
                                        </h2>
                                    </div>

                                    <div id="collapseOne" class="collapse" aria-labelledby="headingOne" data-parent="#accordionExample">
                                        <div class="card-body text-white">
                                            <ul class="list-group list-group-flush">
                                                <a href="/#"><li class="list-group-item text-white bg-primary">Reis 1</li></a>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                    <hr class="my-4">
                    <div class="row">
                        <form:form action="/groepAanmaken" method="post" modelAttribute="nieuweGroepFormulier">
                            <input type="text" name="groepsNaam" required="required" placeholder="Naam Groep">
                            <input id="groepAanmaken" type="submit" class="btn btn-primary" value="Maak groep aan">
                        </form:form>
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