<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag"%>
<%@ attribute name="subReisItem" type="MakePlannieWork.Plannie.model.reisitem.ReisItem"%>
<%@ attribute name="level" type="java.lang.Integer"%>
<!--Een subReisItemDetail kaartje laat alleen de informatie zien die dit subReisItem bevat.-->
<!--Ook kan de gebruiker geen nieuwe items toevoegen op dit level, en alleen deze items wijzigen.-->

<c:set var="soortReisItem" scope="request" value="${subReisItem.getClass().name}"/>

<!--Accordion kop-->
<div class="accordion" id="accordionReisItems${subReisItem.reisItemId}">
    <div class="list-group">
        <div class="accordion-header" id="headingOne${subReisItem.reisItemId}">
            <c:if test="${not empty subReisItem.reisItems}">
                <button class="btn btn-danger btn-lg btn-block" type="button" data-toggle="collapse"
                        data-target="#collapseOne${subReisItem.reisItemId}" aria-expanded="true"
                        aria-controls="collapseOne${subReisItem.reisItemId}"><i class="far fa-list-alt"></i>
                </button>
            </c:if>

            <!--Inhoud begin, kan elk soort reisItem zijn.-->
            <div class="card reisItem flex-row flex-wrap mb-2 mx-auto" id="reisItemCard">
                <c:choose>
                    <c:when test="${soortReisItem == 'MakePlannieWork.Plannie.model.reisitem.Notitie'}">
                        <div class="plannieCard-header border-0">
                            Notitie ${subReisItem.geefGeformatteerdeStartDatumEnTijd()}
                        </div>
                        <div class="plannieCard-block px-2">
                            <h4 class="card-title"><a id="NotitieDetails${subReisItem.naam}"
                                                      href="${pageContext.request.contextPath}/${groepId}/${reisItemId}/${subReisItem.reisItemId}/NotitieWijzigen">${subReisItem.naam}</a>
                                <c:if test="${level < 3}"><tag:dropDownNieuwReisItem reisItem="${subReisItem}"/></c:if>
                            </h4>

                            <p class="card-text">${subReisItem.tekst}</p>
                            <p class="card-text">${subReisItem.budget}</p>
                        </div>
                    </c:when>
                    <c:when test="${soortReisItem == 'MakePlannieWork.Plannie.model.reisitem.Poll'}">
                        <div class="plannieCard-header border-0">
                            Poll ${subReisItem.geefGeformatteerdeStartDatumEnTijd()}
                        </div>
                        <div class="plannieCard-block px-2">
                            <h4 class="card-title"><a id="PollDetails${subReisItem.naam}"
                                                      href="${pageContext.request.contextPath}/${groepId}/${reisItemId}/PollDetail/${subReisItem.reisItemId}">${subReisItem.naam}</a>
                                <c:if test="${level < 3}"><tag:dropDownNieuwReisItem reisItem="${subReisItem}"/></c:if>
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
                        <div class="plannieCard-header border-0">
                            Locatie ${subReisItem.geefGeformatteerdeStartDatumEnTijd()}
                        </div>
                        <div class="plannieCard-block px-2">
                            <h4 class="card-title"><a id="LocatieDetails${subReisItem.naam}"
                                                      href="${pageContext.request.contextPath}/${groepId}/${reisItemId}/${subReisItem.reisItemId}/LocatieWijzigen">${subReisItem.naam}</a>
                                <c:if test="${level < 3}"><tag:dropDownNieuwReisItem reisItem="${subReisItem}"/></c:if>
                            </h4>
                            <p class="card-text">${subReisItem.adres}</p>
                            <div class="googleMapSmall" id="map${subReisItem.reisItemId}"
                                 data-latitude="${subReisItem.latitude}"
                                 data-longitude="${subReisItem.longitude}"
                                 data-id="${subReisItem.reisItemId}"></div>
                        </div>
                    </c:when>
                    <c:when test="${soortReisItem == 'MakePlannieWork.Plannie.model.reisitem.Activiteit'}">

                        <div class="plannieCard-header border-0">
                            ${subReisItem.soortActiviteit} ${subReisItem.geefGeformatteerdeStartDatumEnTijd()}
                        </div>
                        <div class="plannieCard-block px-2">
                            <h4 class="card-title"><a id="ActiviteitDetails${subReisItem.naam}"
                                                      href="${pageContext.request.contextPath}/${groepId}/${reisItemId}/${subReisItem.reisItemId}/activiteitWijzigen">${subReisItem.naam}</a>
                                <c:if test="${level < 3}"><tag:dropDownNieuwReisItem reisItem="${subReisItem}"/></c:if>
                            </h4>
                            <p class="card-text">${subReisItem.omschrijving}</p>
                            <p class="card-text">Budget: ${subReisItem.budget} euro</p>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="plannieCard-header border-0">
                            <img src="https://via.placeholder.com/100.jpg" alt="">
                        </div>
                        <div class="plannieCard-block px-2">
                            <h4 class="card-title"><a id="reisItemDetails${subReisItem.reisItemId}"
                                                      href="${pageContext.request.contextPath}/${groep.groepId}/reisItemsDetail/${subReisItem.reisItemId}">${subReisItem.naam}</a>
                                <c:if test="${level < 3}"><tag:dropDownNieuwReisItem reisItem="${subReisItem}"/></c:if>
                            </h4>
                            <p class="card-text">${subReisItem.getClass().name}</p>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
            <!--Inhoud eind-->

        </div>
        <!--Accordion uitklapveld-->
        <c:if test="${not empty subReisItem.reisItems}">
            <div id="collapseOne${subReisItem.reisItemId}" class="collapse"
                 aria-labelledby="headingOne${subReisItem.reisItemId}"
                 data-parent="#accordionReisItems${subReisItem.reisItemId}">
                <div class="plannieCard-body">
                    <tag:reisItemAccordion subReisItems="${subReisItem.geefReisGesorteerdDatum()}"
                                           level="${level}"/>
                </div>
            </div>
        </c:if>
    </div>
</div>