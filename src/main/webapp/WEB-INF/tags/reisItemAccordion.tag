<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag"%>
<%@ attribute name="subReisItems" type="java.util.ArrayList"%>
<%@ attribute name="level" type="java.lang.Integer"%>

<c:set var="level" scope="page" value="${level + 1}"/>
<c:forEach var="subReisItem" items="${subReisItems}">

    <div class="accordion" id="accordionReisItems${subReisItem.reisItemId}">
        <div class="list-group">
            <div class="accordion-header" id="headingOne${subReisItem.reisItemId}">
                <!--Mits dit item 1, of meerdere sub items heeft, komt er een Accordion-uitklap knop-->
                <c:if test="${not empty subReisItem.reisItems}">
                    <button class="btn btn-danger btn-lg btn-block" type="button" data-toggle="collapse"
                            data-target="#collapseOne${subReisItem.reisItemId}" aria-expanded="true"
                            aria-controls="collapseOne${subReisItem.reisItemId}"><i class="far fa-list-alt"></i>
                    </button>
                </c:if>
                <!--Afhankelijk van het soort item, wordt de passende card getoond-->
                <div class="card reisItem flex-row flex-wrap mb-2 mx-auto" id="reisItemCard">
                    <c:set var="soortReisItem" scope="request" value="${subReisItem.getClass().name}"/>
                    <c:choose>
                        <c:when test="${soortReisItem == 'MakePlannieWork.Plannie.model.reisitem.Notitie'}">
                            <div class="card-header border-0">
                                Notitie ${subReisItem.startDatum}
                            </div>
                            <div class="card-block px-2">
                                <h4 class="card-title"><a id="NotitieDetails${subReisItem.naam}"
                                                          href="${pageContext.request.contextPath}/${groepId}/${reisItemId}/${subReisItem.reisItemId}/NotitieWijzigen">${subReisItem.naam}</a>
                                    <tag:dropDownNieuwReisItem reisItem="${subReisItem}"/>
                                </h4>
                                <p class="card-text">${subReisItem.tekst}</p>
                                <p class="card-text">${subReisItem.budget}</p>
                            </div>
                        </c:when>
                        <c:when test="${soortReisItem == 'MakePlannieWork.Plannie.model.reisitem.Poll'}">
                            <div class="card-header border-0">
                                Poll ${subReisItem.startDatum}
                            </div>
                            <div class="card-block px-2">
                                <h4 class="card-title"><a id="PollDetails${subReisItem.naam}"
                                                          href="${pageContext.request.contextPath}/${groepId}/${reisItemId}/PollDetail/${subReisItem.reisItemId}">${subReisItem.naam}</a>
                                    <tag:dropDownNieuwReisItem reisItem="${subReisItem}"/>
                                </h4>
                                <p class="card-text">
                                <p class="card-text">
                                    <c:if test="${!subReisItem.gebruikerHeeftGestemd(currentUser.gebruikersId)}">
                                        <a class="lead text-blue">Stem nu!</a>
                                    </c:if>
                                    <a id="pollOptieTekst${subReisItem.reisItemId}">${subReisItem.pollStatus()}</a>
                                </p>
                                </p>
                            </div>
                        </c:when>
                        <c:when test="${soortReisItem == 'MakePlannieWork.Plannie.model.reisitem.Locatie'}">
                            <div class="card-header border-0">
                                Locatie ${subReisItem.startDatum}
                            </div>
                            <div class="card-block px-2">
                                <h4 class="card-title"><a id="LocatieDetails${subReisItem.naam}"
                                                          href="${pageContext.request.contextPath}/${groepId}/${reisItemId}/${subReisItem.reisItemId}/LocatieWijzigen">${subReisItem.naam}</a>
                                    <tag:dropDownNieuwReisItem reisItem="${subReisItem}"/>
                                </h4>
                                <p class="card-text">${subReisItem.adres}</p>
                                <div class="googleMapSmall" id="map${subReisItem.reisItemId}"
                                     data-latitude="${subReisItem.latitude}"
                                     data-longitude="${subReisItem.longitude}"
                                     data-id="${subReisItem.reisItemId}"></div>
                            </div>
                        </c:when>
                        <c:when test="${soortReisItem == 'MakePlannieWork.Plannie.model.reisitem.Activiteit'}">

                            <div class="card-header border-0">
                                ${subReisItem.soortActiviteit} ${subReisItem.startDatum}
                            </div>
                            <div class="card-block px-2">
                                <h4 class="card-title"><a id="ActiviteitDetails${subReisItem.naam}"
                                                          href="${pageContext.request.contextPath}/${groepId}/${reisItemId}/${subReisItem.reisItemId}/activiteitWijzigen">${subReisItem.naam}</a>
                                    <tag:dropDownNieuwReisItem reisItem="${subReisItem}"/>
                                </h4>
                                <p class="card-text">${subReisItem.omschrijving}</p>
                                <p class="card-text">Budget: ${subReisItem.budget} euro</p>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="card-header border-0">
                                <img src="https://via.placeholder.com/100.jpg" alt="">
                            </div>
                            <div class="card-block px-2">
                                <h4 class="card-title"><a id="reisItemDetails${subReisItem.reisItemId}"
                                                          href="${pageContext.request.contextPath}/${groep.groepId}/reisItemsDetail/${subReisItem.reisItemId}">${subReisItem.naam}</a>
                                    <tag:dropDownNieuwReisItem reisItem="${subReisItem}"/>
                                </h4>
                                <p class="card-text">${subReisItem.getClass().name}</p>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>

            <c:if test="${not empty subReisItem.reisItems}">
                <div id="collapseOne${subReisItem.reisItemId}" class="collapse"
                     aria-labelledby="headingOne${subReisItem.reisItemId}"
                     data-parent="#accordionReisItems${subReisItem.reisItemId}">
                    <div class="card-body">
                        <tag:reisItemAccordion subReisItems="${subReisItem.geefReisGesorteerdDatum()}" level="${level}"/>
                    </div>
                </div>
            </c:if>
        </div>
    </div>

</c:forEach>