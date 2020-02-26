<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag"%>
<%@ attribute name="subReisItem" type="MakePlannieWork.Plannie.model.reisitem.ReisItem"%>
<%@ attribute name="level" type="java.lang.Integer"%>
<!--Een subReisItemDetail kaartje laat alleen de informatie zien die dit subReisItem bevat.-->
<!--Ook kan de gebruiker geen nieuwe items toevoegen op dit level, en alleen deze items wijzigen.-->

<c:set var="soortReisItem" scope="request" value="${subReisItem.getClass().name}"/>

<!--Accordion kop-->
<div class="accordion" id="accordionReisItems${subReisItem.reisItemId}">
        <div class="accordion-header" id="headingOne${subReisItem.reisItemId}">
            <c:if test="${not empty subReisItem.reisItems}">
                <button class="btn btn-danger btn-lg btn-block" type="button" data-toggle="collapse"
                        data-target="#collapseOne${subReisItem.reisItemId}" aria-expanded="true"
                        aria-controls="collapseOne${subReisItem.reisItemId}"><i class="far fa-list-alt"></i>
                </button>
            </c:if>

            <!--Inhoud begin, kan elk soort reisItem zijn.-->
                <c:choose>
                    <c:when test="${soortReisItem == 'MakePlannieWork.Plannie.model.reisitem.Notitie'}">

                        <div class="notitieDetailContainer-container">
                            <div class="notitieDetailContainer-soort">
                                Notitie
                            </div>
                            <div class="notitieDetailContainer-naam">
                                ${subReisItem.naam}
                            </div>
                            <div class="notitieDetailContainer-tekst">
                                <div>${subReisItem.geefGeformatteerdeTekst()}</div>
                            </div>
                            <div class="notitieDetailContainer-budget">
                                Kosten: ${subReisItem.budget}
                                <br/>
                                ${subReisItem.geefGeformatteerdeStartDatumEnTijd()}
                            </div>
                            <div class="notitieDetailContainer-wijzig">
                                <button class="btn btn-primary mt-3" id="notitieWijzigen${subReisItem.reisItemId}"
                                        onclick="window.location.href = '${pageContext.request.contextPath}/${groepId}/${reisItemId}/${subReisItem.reisItemId}/NotitieWijzigen'">
                                    Wijzigen
                                </button>
                            </div>
                        </div>

                    </c:when>
                    <c:when test="${soortReisItem == 'MakePlannieWork.Plannie.model.reisitem.Poll'}">

                        <div class="pollDetailContainer-container">
                            <div class="pollDetailContainer-soort">
                                Poll
                            </div>
                            <div class="pollDetailContainer-naam">
                                ${subReisItem.naam}
                            </div>
                            <div class="pollDetailContainer-tekst">
                                <div>
                                    <c:if test="${!subReisItem.gebruikerHeeftGestemd(currentUser.gebruikersId)}">
                                        <a class="lead text-blue">Stem nu!</a>
                                    </c:if>
                                    <a id="pollOptieTekst${subReisItem.reisItemId}">${subReisItem.pollStatus()}</a>
                                </div>
                            </div>
                            <div class="pollDetailContainer-budget">
                                Kosten: ${subReisItem.budget}
                                <br/>
                                ${subReisItem.geefGeformatteerdeStartDatumEnTijd()}
                            </div>
                            <div class="pollDetailContainer-wijzig">
                                <button class="btn btn-primary mt-3" id="pollWijzigen2${subReisItem.reisItemId}"
                                        onclick="window.location.href = '${pageContext.request.contextPath}/${groepId}/${reisItemId}/PollDetail/${subReisItem.reisItemId}'">
                                    Stemmen
                                </button>
                            </div>
                        </div>

                    </c:when>
                    <c:when test="${soortReisItem == 'MakePlannieWork.Plannie.model.reisitem.Locatie'}">

                        <div class="locatieDetailContainer-container">
                            <div class="locatieDetailContainer-soort">
                                Locatie
                            </div>
                            <div class="locatieDetailContainer-naam">
                                ${subReisItem.naam}
                            </div>
                            <div class="locatieDetailContainer-kaart">
                                <div class="googleMapSmall" id="map${subReisItem.reisItemId}"
                                     data-latitude="${subReisItem.latitude}"
                                     data-longitude="${subReisItem.longitude}"
                                     data-id="${subReisItem.reisItemId}"></div>
                            </div>
                            <div class="locatieDetailContainer-adresgegevens">
                                ${subReisItem.geefGeformatteerdAdres()}
                            </div>
                            <div class="locatieDetailContainer-budget">
                                Kosten: ${subReisItem.budget}
                                <br/>
                                ${subReisItem.geefGeformatteerdeStartDatumEnTijd()}
                            </div>
                            <div class="locatieDetailContainer-wijzig">
                                <button class="btn btn-primary mt-3" id="locatieWijzigen${subReisItem.reisItemId}"
                                        onclick="window.location.href = '${pageContext.request.contextPath}/${groepId}/${reisItemId}/${subReisItem.reisItemId}/LocatieWijzigen'">
                                    Wijzigen
                                </button>
                            </div>
                        </div>

                    </c:when>
                </c:choose>
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