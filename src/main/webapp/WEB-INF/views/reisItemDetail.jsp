<!doctype html>
<html lang="en">
<div class="view" style="background-image: url('https://images.unsplash.com/photo-1473496169904-658ba7c44d8a?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1950&q=80'); background-repeat: no-repeat; background-size: cover; background-attachment: fixed;">

    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
              integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <script src="https://kit.fontawesome.com/d450c035a5.js" crossorigin="anonymous"></script>
        <title>Plannie - ReisDetails ${reisItem.naam}</title>
    </head>
    <body>

    <nav class="navbar navbar-light bg-light shadow">
        <span class="navbar-brand mb-0 h1"><a id="gebruikerDetail" href="/gebruikerDetail" class="text-dark">${currentUser.voornaam}'s Plannie</a></span>

        <ul class="nav justify-content-end">
            <li class="nav-item">
                <a class="nav-link text-dark" method="post" id="gebruikerWijzigen" href="/gebruikerWijzig">Jouw gegevens</a>
            </li>
            <li class="nav-item">
                <form:form action="${pageContext.request.contextPath}/logout" method="POST">
                    <input id="logout" class="nav-link text-dark" style="border: none; background: transparent;" type="submit" value="Log uit" />
                </form:form>
            </li>
    </nav>
    <div class="container mt-3">
        <div class="row">

            <div class="col-sm-8">
                <div class="jumbotron shadow">
                    <div class="row">

                        <c:forEach items="${alleReisItemsVanReis}" var="reisItems">
                            <div class="card flex-row flex-wrap mb-2 mx-auto" style="width: 42rem;">

                                <c:set var = "soortReisItem" scope = "session" value = "${reisItems.getClass().name}"/>

                                <c:choose>
                                    <c:when test="${soortReisItem == 'MakePlannieWork.Plannie.model.reisitem.Notitie'}">

                                        <div class="card-header border-0">
                                            Notitie ${reisItems.startDatum}
                                        </div>
                                        <div class="card-block px-2">
                                            <h4 class="card-title"><a id="NotitieDetails${reisItems.reisItemId}" href="/${groep.groepId}/reisItemsDetail/${reisItems.reisItemId}" data-toggle="modal" data-target="#wijzigReisItems">${reisItems.naam}</a></h4>
                                            <p class="card-text">${reisItems.tekst}</p>
                                        </div>

                                    </c:when>
                                    <c:otherwise>
                                        <div class="card-header border-0">
                                            <img src="https://via.placeholder.com/100.jpg" alt="">
                                        </div>
                                        <div class="card-block px-2">
                                            <h4 class="card-title"><a id="reisItemDetails${reisItems.reisItemId}" href="/${groep.groepId}/reisItemsDetail/${reisItems.reisItemId}">${reisItems.naam}</a></h4>
                                            <p class="card-text">${reisItems.getClass().name}</p>
                                        </div>
                                    </c:otherwise>
                                </c:choose>

                            </div>
                        </c:forEach>

                    </div>
                </div>
                <div class="jumbotron shadow">
                    <div class="dropdown">
                        <button class="btn btn-secondary dropdown-toggle" type="button" id="keuzeReisItemMenu" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Wat voor soort reisitem wil je maken?
                        </button>
                        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                            <a class="dropdown-item" id="notitieKeuze" href="/${groep.groepId}/reisItemDetail/${reisItem.reisItemId}/NotitieAanmaken">Een Notitie</a>
                            <a class="dropdown-item" id="pollKeuze" href="#">Een Poll</a>
                            <a class="dropdown-item" id="activiteitKeuze" href="#">Een Activiteit</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-sm-4">
                <div class="jumbotron shadow" style="background-color: #666666;">
                    <div class="row" >
                        <p class="lead text-white">${reisItem.naam}</p> <a id="wijzigReisItem" type="button" class="text-white" data-toggle="modal" data-target="#wijzigReisItem2"><i class="far fa-edit"></i></a> <p class="lead text-white"> - <a class ="lead text-white" href="/groepDetail/${groep.groepId}">${groep.groepsNaam}</a></p>
                    </div>
                    <hr class="my-4">
                    <div class="row">
                        <p class="lead text-white mt-3">Locatie / Datum etc..</hp>
                        <hr class="my-4">
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Modal Reis wijzigen -->
<div class="modal fade" id="wijzigReisItem2" tabindex="-1" role="dialog" aria-labelledby="Wijzig ReisItem"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Reis Item Wijzigen</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <jsp:include page="reisItemWijzig.jsp"/>
            </div>
        </div>
    </div>
</div>

<!-- Modal ReisItem wijzigen -->

<div class="modal fade" id="wijzigReisItems" tabindex="-1" role="dialog" aria-labelledby="Wijzig ReisItems"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Notitie Wijzigen</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <jsp:include page="notitieWijzig.jsp"/>
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