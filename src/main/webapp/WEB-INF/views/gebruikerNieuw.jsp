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

<form:form action = "/registreren" method="post" modelAttribute="registratieFormulier">
    <div class="container">
        <div class="row">
            <div class="col">
                <form:input type="text" class="form-control" path="voornaam" placeholder="Voornaam" />
            </div>
            <div class="col">
                <form:input type="text" class="form-control" path="achternaam" placeholder="Achternaam" />
            </div>
       </div>



        <div class="form-group">
            <form:input type="email" class="form-control" path="email" placeholder="Email" />
        </div>


        <div class="row">
            <div class="col">
                <form:input type="password" class="form-control" path="wachtwoord" placeholder="Wachtwoord" />
            </div>
            <div class="col">
                <form:input type="password" class="form-control" path="trancientWachtwoord" placeholder="Bevestig wachtwoord" />
            </div>
        </div>

        <form:button type="submit" class="btn btn-primary">Registreer</form:button>
    </div>

</form:form>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>