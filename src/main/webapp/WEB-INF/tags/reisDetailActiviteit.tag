<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag"%>
<%@ attribute name="subReisItem" type="MakePlannieWork.Plannie.model.reisitem.ReisItem"%>
<%@ attribute name="level" type="java.lang.Integer"%>
<!--Een activiteitDetail kaart laat in grote lijnen zien wat de subItems eronder aan informatie bevatten-->
<!--Locaties, poll status, budget, etc?-->

<c:set var="soortReisItem" scope="request" value="${subReisItem.getClass().name}"/>

<!--Accordion kop-->
<div class="accordionActiviteit" id="accordionActiviteit${subReisItem.reisItemId}">
    <div class="list-group">
        <div class="accordionActiviteit-header" id="headingActiviteit${subReisItem.reisItemId}">

            <!--Inhoud begin, is altijd activiteit-->
            <div class="activiteitDetailContainer-container">
                <div class="activiteitDetailContainer-locatie">
                    <c:set var="locatie" scope="request" value="${subReisItem.vindLocatie()}"/>
                    <c:choose>
                        <c:when test="${not empty locatie}">
                            <div class="googleMapMedium" id="mapMedium${locatie.reisItemId}"
                                 data-latitude="${locatie.latitude}"
                                 data-longitude="${locatie.longitude}"
                                 data-id="${locatie.reisItemId}"></div>
                        </c:when>
                        <c:otherwise>
                            Er is geen locatie
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="activiteitDetailContainer-soort">
                    <h4 class="cardActiviteit-title">
                        <a id="ActiviteitDetails${subReisItem.naam}"
                           href="${pageContext.request.contextPath}/${groepId}/${reisItemId}/${subReisItem.reisItemId}/activiteitWijzigen">${subReisItem.soortActiviteit}</a>
                    </h4>
                    <p style="font-size:20px;">${subReisItem.naam}</p>
                </div>
                <div class="activiteitDetailContainer-omschrijving">${subReisItem.omschrijving}</div>
                <div class="activiteitDetailContainer-tijd">${subReisItem.geefGeformatteerdeTijd()}</div>
                <div class="activiteitDetailContainer-nieuw">
                    <button class="btn btn-primary mt-3" type="button" data-toggle="collapse"
                            data-target="#collapseActiviteit${subReisItem.reisItemId}" aria-expanded="true"
                            aria-controls="collapseActiviteit${subReisItem.reisItemId}">Items toevoegen / wijzigen
                        </i>
                    </button>
                </div>
            </div>
            <!--Inhoud eind-->

        </div>
        <!--Accordion uitklapveld-->
        <div id="collapseActiviteit${subReisItem.reisItemId}" class="collapse"
             aria-labelledby="headingActiviteit${subReisItem.reisItemId}"
             data-parent="#accordionActiviteit${subReisItem.reisItemId}">
            <div class="col">
                <div class="activiteitDetailBody-body">
                    <div class="row">
                        <tag:nieuwReisItemKnoppen reisItem="${subReisItem}" optie="1"/>
                    </div>
                    <tag:reisItemAccordion subReisItems="${subReisItem.geefReisGesorteerdDatum()}"
                                           level="${level}"/>
                </div>
            </div>
        </div>
    </div>
</div>