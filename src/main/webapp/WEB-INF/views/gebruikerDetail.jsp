<!doctype html>
<html lang="en">
<div class="view" style="background-image: url('https://images.unsplash.com/photo-1473496169904-658ba7c44d8a?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1950&q=80'); background-repeat: no-repeat; background-size: cover; background-attachment: fixed;">

    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        <%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
              integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <script src="https://kit.fontawesome.com/d450c035a5.js" crossorigin="anonymous"></script>
        <title>Welkom bij Plannie - ${currentUser.voornaam}</title>
    </head>
    <body>

    <nav class="navbar navbar-light bg-light shadow ">
        <span class="navbar-brand mb-0 h1"><a id="gebruikerDetail" href="/gebruikerDetail" class="text-dark">${currentUser.voornaam}'s Plannie</a></span>

        <ul class="nav justify-content-end">
            <li class="nav-item">
                <a class="nav-link text-dark" method="post" id="gebruikerWijzigen" href="/gebruikerWijzig">Jouw gegevens</a>
            </li>
            <security:authorize access="hasAuthority('ROLE_ADMIN')">
            <li class="nav-item">
                <a class="nav-link text-dark" method="post" id="admin" href="/admin">admin</a>
            </li>
            </security:authorize>
            <li class="nav-item">
                <form:form action="${pageContext.request.contextPath}/logout" method="POST">
                    <input id="logout" class="nav-link text-dark" style="border: none; background: transparent;" type="submit" value="Log uit" />
                </form:form>
            </li>
    </nav>

    <div class="container mt-3">
        <div class="row">
            <div class="col-sm-12 fixed-top">
            </div>
        </div>
        <div class="row">

            <div class="col-sm-8">
                <div class="jumbotron shadow">
                    <div class="row">
                        <p class="lead">Toekomstige Reizen...</p>
                        <p>
                            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse enim velit, tempor vel dolor et, porttitor vestibulum odio. Ut quis elit eu est viverra aliquam a ultricies leo. Curabitur non arcu hendrerit, vestibulum dui sit amet, suscipit diam. Integer suscipit dui tortor, euismod accumsan magna dictum ut. Curabitur sit amet nunc id sapien malesuada aliquet sit amet et arcu. In hendrerit porta justo vitae tempus. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Integer sit amet felis mauris. Proin vitae condimentum dolor. Mauris commodo porttitor mi, in consequat dui posuere ac. Praesent vitae enim magna. Sed egestas malesuada ligula, vel molestie tortor finibus at. Fusce vestibulum convallis tempus. Ut vel erat ultrices, placerat sapien a, ornare erat. Aliquam scelerisque convallis mauris a interdum. Etiam rhoncus in mi nec mollis. Cras malesuada elit et purus tincidunt varius. Suspendisse placerat lacus non libero pretium volutpat. Ut malesuada, ex non vehicula vulputate, quam ex ullamcorper dolor, id consectetur libero quam viverra ex. Aliquam blandit cursus ipsum consequat dictum. Cras vitae urna bibendum lorem maximus sollicitudin. Praesent consequat ultrices mi in bibendum. Nunc lorem dui, elementum id massa sed, dictum vehicula nulla. Maecenas in semper eros, eget vehicula risus. Integer vel lorem consequat ipsum auctor pretium imperdiet vitae sem. Sed volutpat, eros non eleifend mattis, lorem risus congue ante, et laoreet nulla felis ac augue. Curabitur rutrum dui tellus, dapibus eleifend augue hendrerit et. Phasellus efficitur urna tortor, et venenatis ipsum sagittis vitae. Nam tincidunt consectetur diam eget suscipit. Aenean at purus faucibus, venenatis lectus quis, maximus nisi.
                        </p>
                    </div>
                </div>
            </div>

            <div class="col-sm-4">
                <div class="jumbotron shadow" style="background-color: #666666;">
                    <div class="row" >
                        <c:forEach items="${lijstMetGroepen}" var="groep">

                            <div class="card flex-row flex-wrap mb-2 mx-auto" style="width: 18rem;">
                                <div class="card-header border-0">
                                    <img src="https://via.placeholder.com/50.jpg" alt="">
                                </div>
                                <div class="card-block px-2">
                                    <p class="card-text"><a id="details${groep.groepsNaam}" href="/groepDetail/${groep.groepId}">${groep.groepsNaam}</a></p>
                                </div>
                                <div class="w-100"></div>
                            </div>
                        </c:forEach>
                    </div>
                    <hr class="my-4">
                    <div class="row ">
                        <form:form action="/groepAanmaken" class="m-auto form-inline" method="post" modelAttribute="nieuweGroepFormulier">
                            <input type="text" class="form-control flex-row flex-wrap" name="groepsNaam" required="required" placeholder="Naam Groep">
                            <button id="groepAanmaken" class="ml-2 rounded-lg" type="submit"><i class="fas fa-plus"></i></button>
                        </form:form>
                        <hr class="my-4">
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-offset-1 col-sm-10">
                <div class="card w-75 mx-auto mb-5">
                    <div class="card-body">
                        <p>Contact</p>
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