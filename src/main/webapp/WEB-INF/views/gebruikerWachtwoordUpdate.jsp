<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<jsp:include page="header.jsp"/>
<title>Wachtwoord resetten</title>
<body>

<div class="container mt-3">
    <div class="row">
        <div class="col-sm-12 fixed-top">
        </div>
    </div>
    <div class="row">

        <div class="col-sm-8">
            <div class="jumbotron shadow">
                <div sec:authorize="hasAuthority('CHANGE_PASSWORD_PRIVILEGE')">
                <h1>Hoi ${gebruiker.voornaam}. Stel snel je wachtwoord opnieuw in</h1>
                <form:form action= "${pageContext.request.contextPath}/${gebruiker.identifier}/saveWachtwoord" method="post" modelAttribute="wachtwoordUpdateFormulier">
                    <div class="container mt-4">
                        <div class="row">
                            <div class="col">
                                <form:input type="password" class="form-control" path="wachtwoord" required="required" placeholder="Wachtwoord" />
                            </div>
                            <div class="col">
                                <form:input type="password" class="form-control" path="trancientWachtwoord" required="required" placeholder="Bevestig wachtwoord" />
                            </div>
                        </div>

                        <form:button id="wachtwoordUpdate" type="submit" class="btn btn-primary mt-3"  >Wijzig Wachtwoord</form:button>
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
