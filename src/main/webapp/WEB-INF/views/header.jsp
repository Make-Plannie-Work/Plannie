<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link href="/css/style.css" type="text/css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Viga&display=swap" rel="stylesheet">
    <link rel="shortcut icon" type="image/icon" href="/images/favicon.ico"/>
    <script src="https://kit.fontawesome.com/d450c035a5.js" crossorigin="anonymous"></script>

</head>

<nav class="navbar sticky-top">
    <ul class="nav justify-content-end">

        <security:authorize access="isAnonymous()">
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/registreren"><i class="fas fa-user-plus"></i> Registreer</a>
            </li>
            <li class="nav-item">
                <a id="inloggen" class="nav-link" href="#" data-toggle="modal" data-target="#loginmodal"><i
                        class="fas fa-sign-in-alt"></i> Login</a>
            </li>
        </security:authorize>
        <security:authorize access="isAuthenticated()">
            <li class="nav-item">
                <a class="nav-link" method="post" id="gebruikerDetailHeader" href="${pageContext.request.contextPath}/gebruikerDetail">${currentUser.voornaam}'s Plannie</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" method="post" id="gebruikerWijzigen" href="${pageContext.request.contextPath}/gebruikerWijzig">Jouw gegevens</a>
            </li>
            <security:authorize access="hasAuthority('ROLE_ADMIN')">
                <li class="nav-item">
                    <a class="nav-link" method="post" id="admin" href="${pageContext.request.contextPath}/admin">Admin</a>
                </li>
            </security:authorize>
            <li class="nav-item">
                <a class="nav-link" href="#" onclick="${pageContext.request.contextPath}/document.forms['logoutForm'].submit()">Uitloggen</a>
                <form id="logoutForm" action="${pageContext.request.contextPath}/logout" method="POST">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </form>

            </li>
        </security:authorize>
</nav>
<div class="container mt-3">
    <div class="row">
        <span class="navbar-brand mb-0 h1 mt-3"><a id="gebruikerDetail" href="${pageContext.request.contextPath}/gebruikerDetail"><img
                src="${pageContext.request.contextPath}/images/PlannieLogo.png" class="img-fluid" alt="Responsive image"></a></span>
    </div>
    <div class="row">
        <div class="col-sm-12 fixed-top">
        </div>
    </div>
</div>
