<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<jsp:include page="header.jsp"/>
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
                <div sec:authorize="hasAuthority('NEW_USER_PRIVILEGE')">
                    <h3>Hoi ${gebruiker.voornaam}. Voltooi hier je aanmelding voor Plannie!</h3>
                    <form:form action= "${pageContext.request.contextPath}/${gebruiker.identifier}/saveGebruiker" method="post" modelAttribute="maakRegistratieCompleetFormulier">
                        <div class="container mt-4">

                            <form:button id="registratieCompleet" type="submit" class="btn btn-primary mt-3"  >Registratie voltooien</form:button>
                        </div>

                    </form:form>
                </div>
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
                <div class="plannieCard-body">
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
</div>
<jsp:include page="footer.jsp"/>
