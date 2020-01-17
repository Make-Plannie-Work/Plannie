<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<nav class="navbar sticky-top" style="background-color: #FFFFFF; text-color: #FF3B56;">
    <ul class="nav justify-content-end">

        <security:authorize access="isAnonymous()">
        <li class="nav-item">
            <a class="nav-link text-dark" href="/registreren">Registreer</a>
        </li>
        <li class="nav-item">
            <a id="inloggen" class="nav-link text-dark" href="#" data-toggle="modal" data-target="#loginmodal">Login</a>
        </li>
        </security:authorize>
        <security:authorize access="isAuthenticated()">
            <li class="nav-item">
                <a class="nav-link text-dark" method="post" id="gebruikerWijzigen" href="/gebruikerWijzig">Jouw gegevens</a>
            </li>
            <security:authorize access="hasAuthority('ROLE_ADMIN')">
                <li class="nav-item">
                    <a class="nav-link text-dark" method="post" id="admin" href="/admin">Admin</a>
                </li>
            </security:authorize>
            <li class="nav-item">
                <a class="nav-link text-dark" href="#" onclick="document.forms['logoutForm'].submit()">Uitloggen</a>
                <form id="logoutForm" action="${pageContext.request.contextPath}/logout" method="POST" >
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                </form>

            </li>
        </security:authorize>
</nav>
<div class="container mt-3">
    <div class="row">
        <span class="navbar-brand mb-0 h1 mt-3" ><a id="gebruikerDetail" href="/gebruikerDetail"><img src="/images/PlannieLogo.png" class="img-fluid" alt="Responsive image"></a></span>
    </div>
    <div class="row">
        <div class="col-sm-12 fixed-top">
        </div>
    </div>
</div>
