<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<jsp:include page="header.jsp"/>
<title>Wijzig je gegevens - ${currentUser.voornaam}</title>
<body>

<div class="container mt-3">
    <div class="row">
        <div class="col-sm-12 fixed-top">
        </div>
    </div>
    <div class="row">

        <div class="col-sm-8">
            <div class="jumbotron shadow border">
                <div class="row">
                    <p class="lead">Wijzig je gegevens</p>
                </div>
                <div class="row">
                    <div>
                        <form:form action="${pageContext.request.contextPath}/wijzigen" method="post" modelAttribute="gebruikersWijzigingsFormulier">
                            <div class="row">
                                <div class="col">
                                    <form:input type="text" class="form-control" path="voornaam" required="required"
                                                placeholder="Voornaam" value="${currentUser.voornaam}"/>
                                </div>
                                <div class="col">
                                    <form:input type="text" class="form-control" path="achternaam"
                                                required="required" placeholder="Achternaam"
                                                value="${currentUser.achternaam}"/>
                                </div>
                            </div>

                            <div class="form-group mt-3">
                                <form:input type="email" class="form-control" path="email" required="required"
                                            placeholder="Email" value="${currentUser.email}"/>
                            </div>

                            <p>
                                <button id="collapseWachtwoordenKnop" class="btn btn-primary" type="button" data-toggle="collapse"
                                        data-target="#collapseWachtwoorden" aria-expanded="false"
                                        aria-controls="collapseExample">
                                    Wachtwoord wijzigen
                                </button>
                            </p>


                            <div class="collapse" id="collapseWachtwoorden">
                                <div class="row">
                                    <div class="col">
                                        <form:input type="password" class="form-control" path="wachtwoord"
                                                    placeholder="Nieuw wachtwoord"/>
                                    </div>
                                    <div class="col">
                                        <form:input type="password" class="form-control" path="trancientWachtwoord"
                                                    placeholder="Bevestig wachtwoord"/>
                                    </div>
                                </div>
                            </div>

                            <hr class="my-4">

                            <form:button id="gebruikerWijzigen" type="submit" class="btn btn-primary mt-3">Wijzig
                                gegevens
                            </form:button>

                        </form:form>

                        <a href="${pageContext.request.contextPath}/gebruikerDetail">
                            <button type="text" class="btn btn-primary mt-3" id="annuleren">Annuleren</button>
                        </a>
                    </div>

                </div>
            </div>
        </div>

        <div class="col-sm-4">
            <div id="wouter" class="jumbotron shadow" >
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
</div>
<jsp:include page="footer.jsp"/>
