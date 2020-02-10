<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tag" uri="/WEB-INF/taglibs/customTaglib.tld"%>

<jsp:include page="header.jsp"/>
<title>Plannie - Groepsdetails ${groep.groepsNaam}</title>
<body>

<div class="container mt-3">
    <div class="row">

        <div class="col-sm-8">
            <div class="jumbotron shadow border">
                <div class="row" >

                        <c:forEach items="${lijstMetReisItems}" var="reisItem">

                            <div class="card flex-row flex-wrap mb-2 mx-auto " id="reisItemCard">
                                <div class="card-header border-0">
                                    <img id="reisItemImg-klein" class="img-fluid card-img-top" src="${pageContext.request.contextPath}/images/${reisItem.imagePath}" alt="Card image cap">
                                </div>
                                <div class="card-block px-2">
                                    <h4 class="card-title"><a href="${pageContext.request.contextPath}/${groep.groepId}/reisItemDetail/${reisItem.reisItemId}">${reisItem.naam}</a></h4>
                                    <p class="card-text">Info over reis: ${reisItem.naam}</p>
                                </div>
                            </div>
                        </c:forEach>
                        <div class="container" >
                            <div class="row">
                                <form:form action="${pageContext.request.contextPath}/${groep.groepId}/reisItemAanmaken" class="m-auto" method="post" modelAttribute="nieuwReisItemFormulier">
                                    <input id="reisNaam" type="text" class="form-control" name="naam" required="required" placeholder="Naam Reis">
                                    <button class="text mt-2 btn-primary shadow" id="reisItemAanmaken" type="submit">Maak Reis Aan <i class="fas fa-plus"></i></button>
                                </form:form>
                            </div>
                        </div>

                </div>
            </div>
        </div>

        <div class="col-sm-4">
            <div class="jumbotron shadow" id="wouter">

                    <img id="groepImg" class="img-fluid card-img-top" src="${pageContext.request.contextPath}/images/groep/${groep.imagePath}" alt="Card image cap">


                    <h3 class="card-title lead text-white mt-3">${groep.groepsNaam} <c:if test = "${groep.aanmaker == currentUser.gebruikersId}">
                            <a id="wijzigGroepsNaam2" type="button" class="text-white" data-toggle="modal" data-target="#wijzigGroepsNaam"><i class="far fa-edit"></i></a>
                        </c:if></h3>

                <c:if test = "${groep.aanmaker == currentUser.gebruikersId}"><hr class="my-4">
                <jsp:include page="livesearch.jsp"/>

                <hr class="my-4">
                </c:if>

                    <table class="table table-hover table-borderless text-white">
                        <thead>
                        <tr>
                            <th scope="col">Groepsleden</th>
                        </tr>
                        </thead>
                        <tbody id="groepsLeden">

                        </tbody>
                    </table>



                </div>
            </div>
        </div>
    </div>

    <c:if test = "${groep.aanmaker == currentUser.gebruikersId}">
    <div class="row mx-auto">
        <div class="col-sm-offset-1 col-sm-10 mb-5 mx-auto">
            <div class="card w-75 mx-auto">
                <div class="card-body">
                    <form:form action="${pageContext.request.contextPath}/groepDetail/${groep.groepId}/voegLedenToeAanGroepViaEmail" class="form-inline" method="post" modelAttribute="groepslidEmail">
                        <h5 class="card-title ">Stuur een uitnodiging naar een nog niet bestaande gebruiker.</h5>
                        <p class="card-text">
                        <div class="form-group">
                            <div class="col">
                                <input id="voegLedenToeAanGroepViaEmail" type="email" class="form-control" name="email" required="required" placeholder="Email"/>
                            </div>
                        </div>
                        </p>
                        <div>
                            <button id="emailVersturen" type="submit" class="btn btn-primary">Stuur</button>
                        </div>
                    </form:form>
                </div>
            </div>

        </div>
    </div>
    </c:if>
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
</div>
<jsp:include page="footer.jsp"/>
