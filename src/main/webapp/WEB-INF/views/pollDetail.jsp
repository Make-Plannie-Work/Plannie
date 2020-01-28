<!doctype html>
<html lang="en">

    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
              integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
              crossorigin="anonymous">
        <link href="/css/style.css" type="text/css" rel="stylesheet">
        <script src="https://kit.fontawesome.com/d450c035a5.js" crossorigin="anonymous"></script>
        <title>Plannie - Poll ${poll.naam}</title>
    </head>
    <body>

    <jsp:include page="header.jsp"/>

    <div class="container mt-3">
        <div class="row">

            <div class="col-sm-8">
                <div class="jumbotron shadow border">
                    <div class="row">

                        <span class="navbar-brand mb-0 h1"><a class="text-dark">${poll.naam}  -  ${poll.startDatum}</a></span>

                        <c:forEach items="${poll.getPollOpties()}" var="optie">
                            <div class="card flex-row flex-wrap mb-2 mx-auto" id="reisItemCard">

                                <div class="card-header border-0">
                                    ${optie.stemOptie}
                                </div>
                                <div class="card-block px-2">
                                    <a>Aantal stemmen: ${optie.geefAantalStemmen()}</a>

                                    <p class="card-text">

                                        <c:choose>
                                            <c:when test="${optie.gebruikerHeeftGestemd(currentUser.gebruikersId)}">
                                                <a id="gestemdOp${optie.stemOptie}">Jij hebt hierop gestemd.</a>
                                            </c:when>
                                            <c:otherwise>
                                                <a id="stemmenOp${optie.stemOptie}"
                                                   href="${poll.reisItemId}/StemmenOp/${optie.pollOptieId}">Stem nu!</a>
                                            </c:otherwise>
                                        </c:choose>

                                    </p>
                                </div>

                            </div>
                        </c:forEach>


                        <div class="container">

                            <div class="row">
                                <a href="/${groep.groepId}/reisItemDetail/${reis.reisItemId}">
                                    <button type="text" class="btn btn-primary mt-3" id="annuleren">Terug</button>
                                </a>
                            </div>

                        </div>
                    </div>
                </div>
            </div>

            <div class="col-sm-4">
                <div class="jumbotron shadow" id="wouter">
                    <div class="row">

                        <p class="lead text-white strong">Hebben al gestemd:</p>

                        <table class="table table-hover table-borderless text-white">
                            <thead>
                            <tr>
                                <th scope="col">Naam</th>
                                <th scope="col"></th>
                            </tr>
                            </thead>
                            <tbody>

                            <tr>

                                <c:forEach items="${groep.groepsleden}" var="groepsLid">
                            <tr>
                                <c:if test="${poll.gebruikerHeeftGestemd(groepsLid.gebruikersId)}">

                                    <td id="Gestemd${groepsLid.voornaam}" data-toggle="tooltip"
                                        data-placement="bottom" title="${groepsLid.email}">${groepsLid.voornaam}
                                        ${groepsLid.achternaam}
                                    </td>

                                </c:if>
                            </tr>
                            </c:forEach>

                            </tr>

                            </tbody>
                        </table>
                    </div>
                    <hr class="my-4">
                    <div class="row">

                        <p class="lead text-white mt-3">Moeten nog stemmen:</h3>
                        <hr class="my-4">

                        <table class="table table-hover table-borderless text-white">
                            <thead>
                            <tr>
                                <th scope="col">Naam</th>
                                <th scope="col"></th>
                            </tr>
                            </thead>
                            <tbody>

                            <tr>

                                <c:forEach items="${groep.groepsleden}" var="groepsLid">
                            <tr>
                                <c:if test="${not poll.gebruikerHeeftGestemd(groepsLid.gebruikersId)}">

                                    <td id="nietGestemd${groepsLid.voornaam}" data-toggle="tooltip"
                                        data-placement="bottom" title="${groepsLid.email}">${groepsLid.voornaam}
                                        ${groepsLid.achternaam}
                                    </td>

                                </c:if>
                            </tr>
                            </c:forEach>

                            </tr>

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Footer -->
<footer class="py-4 bg-dark text-white-50">

    <!-- Footer Elements -->
    <div class="container">

        <!-- Call to action -->
        <ul class="list-unstyled list-inline text-center py-2">


            </li>
        </ul>
        <!-- Call to action -->

    </div>
    <!-- Footer Elements -->
</footer>
<!-- Footer -->
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
        integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
        crossorigin="anonymous"></script>
</body>
</html>