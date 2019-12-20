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
        <title>Registreren in Plannie</title>
    </head>
    <body>

    <nav class="navbar navbar-light bg-light shadow">
        <span class="navbar-brand mb-0 h1"><a href="/gebruikerDetail" class="text-dark">Plannie</a></span>

        <ul class="nav justify-content-end">
            <li class="nav-item">
                <a id="registreren" class="nav-link text-dark" href="/registreren">Registreer</a>
            </li>
            <li class="nav-item">
                <a id="inloggen" class="nav-link text-dark" data-toggle="modal" data-target="#loginmodal">Login</a>
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
                    <h3>Registreren</h3>
                    <form:form action = "/registreren" method="post" modelAttribute="registratieFormulier">
                        <div class="container mt-4">
                            <div class="row">
                                <div class="col">
                                    <form:input type="text" class="form-control" path="voornaam" required="required" placeholder="Voornaam" />
                                </div>
                                <div class="col">
                                    <form:input type="text" class="form-control" path="achternaam" required="required" placeholder="Achternaam" />
                                </div>
                            </div>



                            <div class="form-group mt-3">
                                <form:input type="email" class="form-control" path="email" required="required" placeholder="Email" />
                            </div>


                            <div class="row">
                                <div class="col">
                                    <form:input type="password" class="form-control" path="wachtwoord" required="required" placeholder="Wachtwoord" />
                                </div>
                                <div class="col">
                                    <form:input type="password" class="form-control" path="trancientWachtwoord" required="required" placeholder="Bevestig wachtwoord" />
                                </div>
                            </div>

                            <form:button id="registreer" type="submit" class="btn btn-primary mt-3"  >Registreer</form:button>
                        </div>

                    </form:form>
                </div>
            </div>

            <div class="col-sm-4">
                <div class="jumbotron shadow" style="background-color: #666666;">
                    <div class="row" >
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

    <div class="modal fade" id="loginmodal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="inloggenmodal">Inloggen</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">

                    <form:form id="loginForm" action="/index" modelAttribute="loginForm" method="post">
                        <div class="form-group">
                            <form:input type="email" class="form-control" id="username" path="username" placeholder="Email"/>
                        </div>
                        <div class="form-group">
                            <form:input type="password" id="password" path="password" class="form-control" placeholder="Wachtwoord"/>
                        </div>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Annuleer</button>
                    <button id="login" type="submit" class="btn btn-primary">Login</button>
                    </form:form>
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