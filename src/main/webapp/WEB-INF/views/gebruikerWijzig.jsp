<!doctype html>
<html lang="en" xmlns:form="http://www.w3.org/1999/xhtml">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    <title>Registreren in Plannie</title>
</head>
<body>

<div class="container mt-4">
    <form:form action = "/wijzigen" method="post" modelAttribute="gebruikersWijzigingsFormulier">
            <div class="row">
                <div class="col">
                    <form:input type="text" class="form-control" path="voornaam" required="required" placeholder="Voornaam" value ="${currentUser.voornaam}" />
                </div>
                <div class="col">
                    <form:input type="text" class="form-control" path="achternaam" required="required" placeholder="Achternaam" value ="${currentUser.achternaam}" />
                </div>
           </div>

            <div class="form-group mt-3">
                <form:input type="email" class="form-control" path="email" required="required" placeholder="Email" value ="${currentUser.email}" />
            </div>

            <form:button id="gebruikerWijzigen" type="submit" class="btn btn-primary mt-3"  >Wijzig gegevens</form:button>

    </form:form>

    <a href="/gebruikerDetail"><button type="text" class="btn btn-primary mt-3" id="gebruikerWijzigen">Annuleren</button></a>
</div>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>