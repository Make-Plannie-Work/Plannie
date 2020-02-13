<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<jsp:include page="header.jsp"/>
<title>Plannie - Poll ${poll.naam}</title>
<body>

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
                                <div class="progress">
                                    <div class="progress-bar" role="progressbar" style="width: ${Math.round (optie.geefAantalStemmen() / poll.geefTotaalAantalStemmen() * 100)}%;" aria-valuenow="10" aria-valuemin="0" aria-valuemax="100">
                                        <c:out value="${Math.round (optie.geefAantalStemmen() / poll.geefTotaalAantalStemmen() * 100)}"/>%</div>
                                </div>

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

                     <button type="button" class="btn btn-primary mt-3" data-toggle="modal" data-target="#waarschuwingsModal"
                            onclick="vullenModal(
                            'Poll sluiten',
                            'Weet u zeker dat u deze poll wil sluiten?',
                            'Sluiten',
                            '${pageContext.request.contextPath}/${groep.groepId}/${reis.reisItemId}/${poll.reisItemId}/subReisItemVerwijderen')">
                        Sluit poll
                    </button>

                    <div class="container">

                        <div class="row">
                            <a href="${pageContext.request.contextPath}/${groep.groepId}/reisItemDetail/${reis.reisItemId}">
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
<jsp:include page="waarschuwingsModal.jsp"/>
<jsp:include page="footer.jsp"/>
