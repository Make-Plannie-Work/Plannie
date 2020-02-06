<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<jsp:include page="header.jsp"/>
<title>Welkom bij Plannie - ${currentUser.voornaam}</title>
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
                        <p class="lead">Toekomstige Reizen...</p>
                        <p>
                            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse enim velit, tempor vel dolor et, porttitor vestibulum odio. Ut quis elit eu est viverra aliquam a ultricies leo. Curabitur non arcu hendrerit, vestibulum dui sit amet, suscipit diam. Integer suscipit dui tortor, euismod accumsan magna dictum ut. Curabitur sit amet nunc id sapien malesuada aliquet sit amet et arcu. In hendrerit porta justo vitae tempus. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Integer sit amet felis mauris. Proin vitae condimentum dolor. Mauris commodo porttitor mi, in consequat dui posuere ac. Praesent vitae enim magna. Sed egestas malesuada ligula, vel molestie tortor finibus at. Fusce vestibulum convallis tempus. Ut vel erat ultrices, placerat sapien a, ornare erat. Aliquam scelerisque convallis mauris a interdum. Etiam rhoncus in mi nec mollis. Cras malesuada elit et purus tincidunt varius. Suspendisse placerat lacus non libero pretium volutpat. Ut malesuada, ex non vehicula vulputate, quam ex ullamcorper dolor, id consectetur libero quam viverra ex. Aliquam blandit cursus ipsum consequat dictum. Cras vitae urna bibendum lorem maximus sollicitudin. Praesent consequat ultrices mi in bibendum. Nunc lorem dui, elementum id massa sed, dictum vehicula nulla. Maecenas in semper eros, eget vehicula risus. Integer vel lorem consequat ipsum auctor pretium imperdiet vitae sem. Sed volutpat, eros non eleifend mattis, lorem risus congue ante, et laoreet nulla felis ac augue. Curabitur rutrum dui tellus, dapibus eleifend augue hendrerit et. Phasellus efficitur urna tortor, et venenatis ipsum sagittis vitae. Nam tincidunt consectetur diam eget suscipit. Aenean at purus faucibus, venenatis lectus quis, maximus nisi.
                        </p>
                    </div>
                </div>
            </div>

            <div class="col-sm-4">
                <div class="jumbotron shadow" id="wouter">
                    <div class="row" >

                        <table class="table table-hover table-borderless text-white">
                            <thead>
                            <tr>
                                <th scope="col">Jouw Groepen:</th>

                            </tr>
                            </thead>
                        </table>
                        <c:forEach items="${lijstMetGroepen}" var="groep">

                            <a id="details${groep.groepsNaam}" href="${pageContext.request.contextPath}/groepDetail/${groep.groepId}" class="mx-auto">
                            <div class="card flex-row flex-wrap mb-2 mx-auto" id="groepCard" style="width: 18rem;">
                                <div class="card-header border-0">
                                    <img id="groepImg-klein" src="${pageContext.request.contextPath}/images/groep/${groep.imagePath}" alt="">
                                </div>
                                <div class="card-block px-2 mx-auto flex-wrap flex-row align-items-center d-flex">
                                    <p id="groepCardText" class="card-text align-items-center d-flex">${groep.groepsNaam}</p>
                                </div>

                            </div>
                            </a>
                        </c:forEach>
                    </div>
                    <hr class="my-4">
                    <jsp:include page="groepNieuw.jsp"/>
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