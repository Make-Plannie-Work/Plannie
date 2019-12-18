<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!doctype html>
<html lang="en" xmlns:form="http://www.w3.org/1999/xhtml" xmlns:security="http://www.springframework.org/schema/security">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    <title>Hallo, ${currentUser.voornaam}</title>
</head>
<body>
<div class="container mt-4 ">
<h1>Hallo, ${currentUser.voornaam}</h1>

    <c:forEach items="${lijstMetGroepen}" var="groep">
    <div class="accordion mb-4 " id="lijstMetGroepen">
        <div class="card text-white bg-primary rounded-4 mb-4">
            <div class="card-header" id="headingOne">
                <h2 class="mb-0">
                    <button class="btn btn-link text-white" type="button" data-toggle="collapse" data-target="#collapseOne" aria-expanded="false" aria-controls="collapseOne">
                        <a href="/groepDetail/${groep.groepId}" class="text-white">${groep.groepsNaam}</a>
                    </button>
                </h2>
            </div>

            <div id="collapseOne" class="collapse" aria-labelledby="headingOne" data-parent="#accordionExample">
                <div class="card-body text-white">
                        <ul class="list-group list-group-flush">
                            <a href="/#"><li class="list-group-item text-white bg-primary">Reis 1</li></a>
                        </ul>
                </div>
            </div>
        </div>
    </div>
    </c:forEach>
    <div class="container mt-3" >
        <div class="row">
            <form:form action="/groepAanmaken" method="post" modelAttribute="nieuweGroepFormulier">
                <input type="text" name="groepsNaam" required="required" placeholder="Naam Groep">
                <input id="groepAanmaken" type="submit" class="btn btn-primary" value="Maak groep aan">
            </form:form>
        </div>
    </div>

    <div class="container mt-3" >
        <div class="row">
            <form action="/logout" method="post">
                <input type="hidden"
                       name="${_csrf.parameterName}"
                       value="${_csrf.token}"/>
                <input type="submit" class="btn btn-primary" value="Logout">
            </form>
        </div>
    </div>
    <a href="/gebruikerWijzig"><button type="text" class="btn btn-primary mt-3" id="gebruikerWijzigen">Gegevens wijzigen</button></a>
</div>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>