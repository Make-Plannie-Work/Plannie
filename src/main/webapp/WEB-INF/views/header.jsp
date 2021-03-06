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
    <link href="${pageContext.request.contextPath}/css/style.css" type="text/css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Viga&display=swap" rel="stylesheet">
    <link rel="shortcut icon" type="image/icon" href="${pageContext.request.contextPath}/images/favicon.ico"/>
    <script src="https://kit.fontawesome.com/d450c035a5.js" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/modernizr/2.8.3/modernizr.min.js" type="text/javascript"></script>
    <link href='https://fonts.googleapis.com/css?family=Quicksand:300,400,700' rel='stylesheet' type='text/css'>
    <link href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" rel="stylesheet">

</head>

<nav class="navbar sticky-top">

    <security:authorize access="isAuthenticated()">
        <img src="${pageContext.request.contextPath}/images/planniewit.png" class="ml-5" id="logowit"/>
    </security:authorize>

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
                <c:choose>
                    <c:when test="${not empty currentUser.voornaam}">
                        <a class="nav-link" method="post" id="gebruikerDetailHeader" href="${pageContext.request.contextPath}/gebruikerDetail">${currentUser.voornaam}'s Plannie</a>
                    </c:when>
                </c:choose>
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
                <a class="nav-link" href="#" onclick="document.forms['logoutForm'].submit()">Uitloggen</a>
                <form id="logoutForm" action="${pageContext.request.contextPath}/logout" method="POST">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </form>

            </li>

        </security:authorize>
    </ul>

</nav>

