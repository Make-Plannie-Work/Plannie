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

                <h5 class="mt-3">
                    <button id="stuurUitnodigingKnop" class="btn btn-primary mb-5" type="button" data-toggle="collapse"
                            data-target="#stuurUitnodiging" aria-expanded="false"
                            aria-controls="collapseExample">
                        Of stuur iemand een uitnodiging
                    </button>
                </h5>

                <div class="collapse" id="stuurUitnodiging">
                <c:if test="${groep.aanmaker == currentUser.gebruikersId}">
                    <div class="row ml-1 mb-5">
                        <form:form
                                action="${pageContext.request.contextPath}/groepDetail/${groep.groepId}/voegLedenToeAanGroepViaEmail"
                                class="form-inline" method="post" modelAttribute="groepslidEmail">
                                    <input id="voegLedenToeAanGroepViaEmail" type="email" class="form-control" name="email"
                                           required="required" placeholder="Email"/>

                            <div>
                                <button id="emailVersturen" type="submit" class="ml-2 text mt-2 btn-primary shadow">Stuur</button>
                            </div>
                        </form:form>
                    </div>
                </c:if>
                </div>
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
                        <div class="card flex-row flex-wrap mb-2" id="reisItemCard" >
                            <div class="plannieCard-header border-0">
                                <img id="reisItemImg-klein" class="img-fluid card-img-top"
                                     src="${pageContext.request.contextPath}/images/${reisItem.imagePath}"
                                     alt="Card image cap">
                            </div>
                            <a href="${pageContext.request.contextPath}/${groep.groepId}/reisItemDetail/${reisItem.reisItemId}">
                            <div class="plannieCard-block px-2" style="width:100%; height: 100%;">
                                <h5>${reisItem.naam}
                                </h5>
                                <p class="lead-text">Wanneer: ${reisItem.geefGeformatteerdeReisDuratie()}</p>
                                <p class="lead-text">Waar: ${reisItem.vindLocatie().geefGeformatteerdAdres()}</p>
                                <p class="lead-text">Kosten: ${reisItem.berekenTotaalBudget()} euro</p>
                            </div>
                            </a>
                        </div>
                    </c:forEach>

                    <div class="row mb-5 ml-1">
                        <form:form action="${pageContext.request.contextPath}/${groep.groepId}/reisItemAanmaken"
                                    method="post" class="form-inline" modelAttribute="nieuwReisItemFormulier">
                            <form:input id="reisNaam" type="text" class="form-control mr-2" path="naam"
                                        required="required" placeholder="Naam Reis"/>
                            <form:input id="reisDatum" type="datetime-local" class="form-control mr-2" path="startDatum"
                                        required="required"/>
                            <form:button class="text mt-2 btn-primary shadow" id="reisItemAanmaken" type="submit">
                                Maak reis aan <i class="fas fa-plus"></i></form:button>
                        </form:form>
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