<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<jsp:include page="header.jsp"/>
<title>Welkom bij Plannie</title>
<body>

<div class="container mt-3">
    <div class="row">
        <div class="col-sm-8">
            <div class="jumbotron shadow border">
                <div class="row">
                    <p class="lead">Registreren mislukt</p>
                    <p>
                       Het e-mail adres waarmee u heeft geprobeerd zich te registreren bestaat al!
                        Weet u uw wachtwoord niet meer? Ga naar Login en klik vervolgens op: Wachtwoord resetten.
                    </p>

                </div>
            </div>
        </div>

        <div class="col-sm-4">
            <div class="jumbotron shadow" style="background-color: #FF3B56;">
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
    <div class="row mb-3">
        <div class="col-sm-offset-1 col-sm-10 mb-5">
            <div class="card w-75 mx-auto">
                <div class="card-body">
                    <p>Contact</p>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="loginmodal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
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
                        <form:input type="email" class="form-control" id="username" path="username"
                                    placeholder="Email"/>
                    </div>
                    <div class="form-group">
                        <form:input type="password" id="password" path="password" class="form-control"
                                    placeholder="Wachtwoord"/>
                        <a id="resetten" class="nav-link text-dark" data-toggle="modal" data-target="#resetmodal">Wachtwoord
                            resetten</a>
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
            <form:form id="updatePasswordForm" action="/wachtwoordReset" modelAttribute="updatePasswordForm"
                       method="post">
                <div class="modal-body">


                    <div class="form-group">
                        <form:input type="email" class="form-control" id="usernameReset" path="email"
                                    placeholder="Email"/>
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

        <!-- Call to action -->
        <ul class="list-unstyled list-inline text-center py-2">

            <h6 class="mb-1"><small>Registreer je snel: <a id="registreren" href="/registreren" class="text-white">klik
                hier!</a></small></h6>


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