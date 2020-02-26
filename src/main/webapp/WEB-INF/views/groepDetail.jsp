<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<jsp:include page="header.jsp"/>
<title>Plannie - Groepsdetails ${groep.groepsNaam}</title>
<body>
<div class="wrapper">
    <div class="container shadow">
        <div class="row" style="background-color: ">
            <div class="col-sm-4">
                <img id="groepImg" class="img-fluid card-img-top"
                     src="${pageContext.request.contextPath}/images/groep/${groep.imagePath}" alt="Card image cap">
                <div class="row">
                    <table class="table table-hover table-borderless">
                        <thead>
                        <tr>
                            <th scope="col"><h5>Wie?</h5></th>
                        </tr>
                        </thead>
                        <tbody id="groepsLeden">
                        </tbody>
                    </table>
                </div>
                <c:if test="${groep.aanmaker == currentUser.gebruikersId}">
                    <hr class="my-8">
                    <jsp:include page="livesearch.jsp"/>
                </c:if>

                <c:if test="${groep.aanmaker == currentUser.gebruikersId}">
                    <h5 class="mt-3">Of</h5>
                    <div class="row">
                        <form:form
                                action="${pageContext.request.contextPath}/groepDetail/${groep.groepId}/voegLedenToeAanGroepViaEmail"
                                class="form-inline" method="post" modelAttribute="groepslidEmail">
                            <h5 class="card-title ">Stuur een uitnodiging naar een nog niet bestaande gebruiker.</h5>
                                    <input id="voegLedenToeAanGroepViaEmail" type="email" class="form-control" name="email"
                                           required="required" placeholder="Email"/>

                            <div>
                                <button id="emailVersturen" type="submit" class="btn btn-primary">Stuur</button>
                            </div>
                        </form:form>
                    </div>
                </c:if>
            </div>
            <div class="col-sm-8">
                <div class="row">
                    <h3 class="mt-5">${groep.groepsNaam}
                        <c:if test="${groep.aanmaker == currentUser.gebruikersId}">
                            <a id="wijzigGroepsNaam2" type="button" class="" data-toggle="modal"
                               data-target="#wijzigGroepsNaam"><i class="far fa-edit"></i></a>
                        </c:if>
                    </h3>
                </div>

                <div class="row mt-5">
                    <c:forEach items="${lijstMetReisItems}" var="reisItem">
                        <div class="card flex-row flex-wrap mb-2 mx-auto " id="reisItemCard">
                            <div class="plannieCard-header border-0">
                                <img id="reisItemImg-klein" class="img-fluid card-img-top"
                                     src="${pageContext.request.contextPath}/images/${reisItem.imagePath}"
                                     alt="Card image cap">
                            </div>
                            <div class="plannieCard-block px-2">
                                <h4 class="card-title"><a
                                        href="${pageContext.request.contextPath}/${groep.groepId}/reisItemDetail/${reisItem.reisItemId}">${reisItem.naam}</a>
                                </h4>
                                <p class="card-text">Info over reis: ${reisItem.naam}</p>
                            </div>
                        </div>
                    </c:forEach>
                    <div class="container">
                        <div class="row">
                            <form:form action="${pageContext.request.contextPath}/${groep.groepId}/reisItemAanmaken"
                                       class="m-auto" method="post" modelAttribute="nieuwReisItemFormulier">
                                <form:input id="reisNaam" type="text" class="form-control" path="naam"
                                            required="required" placeholder="Naam Reis"/>
                                <form:input id="reisDatum" type="datetime-local" class="form-control" path="startDatum"
                                            required="required"/>
                                <form:button class="text mt-2 btn-primary shadow" id="reisItemAanmaken" type="submit">
                                    Maak reis aan <i class="fas fa-plus"></i></form:button>
                            </form:form>
                        </div>
                    </div>
                </div>

            </div>
        </div>




    </div>





<!-- Modal -->
<div class="modal fade" id="wijzigGroepsNaam" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Groepsnaam Wijzigen</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <jsp:include page="groepWijzig.jsp"/>
            </div>
        </div>
    </div>
</div>


<jsp:include page="footer.jsp"/>
<script src="${pageContext.request.contextPath}/js/main.js"></script>