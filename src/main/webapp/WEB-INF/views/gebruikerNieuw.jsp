<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<jsp:include page="header.jsp"/>
<link id="csrfToken" data-csrfToken="${_csrf.token}"/>
<title>Registreren in Plannie</title>
<body>

<div class="container mt-3">
    <div class="row">
        <div class="col-sm-12 fixed-top">
        </div>
    </div>
    <div class="row">

        <div class="col-sm-8">
            <div class="jumbotron shadow border">
                <h3>Registreren</h3>
                <form:form action="${pageContext.request.contextPath}/js/registreerEmailCheck.js" id="registreren" method="post" modelAttribute="registratieFormulier"  class="needs-validation was-validation" novalidate="true">
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

                        <form:button id="registreer" type="submit" class="btn btn-primary mt-3"><span>Registreer</span></form:button>

                        <div id="registratieMelding" class="alert alert-danger fade">
                            <button href="#" type="button" class="close">&times;</button>
                            <h4 id="alertTitel">Alert title</h4>
                            <p id="alertTekst">Alert tekst</p>
                        </div>

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

                <form:form id="loginForm" action="${pageContext.request.contextPath}/index" modelAttribute="loginForm" method="post">
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
            <form:form id="updatePasswordForm" action="${pageContext.request.contextPath}/wachtwoordReset" modelAttribute="updatePasswordForm" method="post">
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
<jsp:include page="footer.jsp"/>
<script src="${pageContext.request.contextPath}/js/registreerEmailCheck.js"> </script>
