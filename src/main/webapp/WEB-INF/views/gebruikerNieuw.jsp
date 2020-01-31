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
        <link href="/css/style.css" type="text/css" rel="stylesheet">
        <title>Registreren in Plannie</title>
    </head>
    <body>

    <jsp:include page="header.jsp"/>

    <div class="container mt-3">
        <div class="row">
            <div class="col-sm-12 fixed-top">
            </div>
        </div>
        <div class="row">

            <div class="col-sm-8">
                <div class="jumbotron shadow border">
                    <h3>Registreren</h3>
                    <form:form action = "/registreren" id="registreren" method="post" modelAttribute="registratieFormulier"  class="needs-validation was-validation" novalidate="true">
                        <div class="container mt-4">
                            <div class="row">
                                <div class="col">
                                    <form:input type="text" class="form-control" id="voornaam" name="voornaam" path="voornaam" required="required" placeholder="Voornaam" />
                                </div>

                                <div class="col">
                                    <form:input type="text" class="form-control" id="achternaam" name="achternaam" path="achternaam" required="required" placeholder="Achternaam" />
                                </div>

                            </div>
                            <div class="form-group mt-3">
                                <form:input type="email" class="form-control" id="email" name="email" pattern="^([a-zA-Z0-9_\-\.]+)@([a-zA-Z0-9_\-\.]+)\.([a-zA-Z]{2,5})$" path="email" required="required" placeholder="Email" />
                            </div>

                            <div class="row">
                                <div class="col">
                                    <form:input type="password" class="form-control" id="psw" name="wachtwoord" path="wachtwoord" pattern="(?=^.{8,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$" required="required" placeholder="Wachtwoord" />
                                </div>
                                <div class="col">
                                    <form:input type="password" class="form-control" id="trancientWachtwoord" name="trancientWachtwoord" path="trancientWachtwoord" required="required" placeholder="Bevestig wachtwoord" />
                                </div>

                            </div>

                            <form:button id="registreer" type="submit" class="btn btn-primary mt-3"  >Registreer</form:button>

                        </div>

                    </form:form>
                </div>
            </div>

            <div class="col-sm-4">
                <div class="jumbotron shadow" id="wouter">
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
                            <a id="resetten" class="nav-link text-dark" data-toggle="modal" data-target="#resetmodal">Wachtwoord resetten</a>
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

    <div class="modal fade" id="resetmodal" tabindex="-1" role="dialog" aria-labelledby="resetmodal" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" class="close" id="resetmodal2"><p>Wachtwoord Resetten</p></h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <form:form id="updatePasswordForm" action="/wachtwoordReset" modelAttribute="updatePasswordForm" method="post">
                    <div class="modal-body">


                        <div class="form-group">
                            <form:input type="email" class="form-control" id="usernameReset" path="email" placeholder="Email"/>
                        </div>

                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Annuleer</button>
                        <button id="resetWachtwoord" type="submit" class="btn btn-primary">Reset Wachtwoord</button>

                    </div>
                </form:form>
            </div>
        </div>
    </div>

    <!-- Footer -->
    <footer class="py-4 bg-dark text-white-50">

        <!-- Footer Elements -->
        <div class="container">

        </div>
        <!-- Footer Elements -->
    </footer>
    <!-- Footer -->
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.1/dist/jquery.validate.min.js"></script>
    <script src="\js/validation.js" type="text/javascript"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
            crossorigin="anonymous"></script>
    </body>
</html>