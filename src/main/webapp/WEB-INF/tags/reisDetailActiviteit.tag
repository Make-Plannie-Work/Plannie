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
            <c:if test="${not empty subReisItem.reisItems}">
                <button class="btn btn-danger btn-lg btn-block" type="button" data-toggle="collapse"
                        data-target="#collapseActiviteit${subReisItem.reisItemId}" aria-expanded="true"
                        aria-controls="collapseActiviteit${subReisItem.reisItemId}"><i class="far fa-list-alt"></i>
                </button>
            </c:if>

            <!--Inhoud begin, is altijd activiteit-->
            <div class="cardActiviteit reisItem flex-row flex-wrap mb-2 mx-auto" id="activiteitItemCard">
                <div class="cardActiviteit-header border-0">
                    ${subReisItem.soortActiviteit} ${subReisItem.geefGeformatteerdeStartDatumEnTijd()}
                </div>
                <div class="cardActiviteit-block px-2">
                    <h4 class="cardActiviteit-title"><a id="ActiviteitDetails${subReisItem.naam}"
                                              href="${pageContext.request.contextPath}/${groepId}/${reisItemId}/${subReisItem.reisItemId}/activiteitWijzigen">${subReisItem.naam}</a>
                        <tag:dropDownNieuwReisItem reisItem="${subReisItem}" optie="-1"/>
                    </h4>
                    <p class="cardActiviteit-text">${subReisItem.omschrijving}</p>
                    <p class="cardActiviteit-text">Budget: ${subReisItem.budget} euro</p>
                </div>
            </div>
            <!--Inhoud eind-->

        </div>
        <!--Accordion uitklapveld-->
        <c:if test="${not empty subReisItem.reisItems}">
            <div id="collapseActiviteit${subReisItem.reisItemId}" class="collapse"
                 aria-labelledby="headingActiviteit${subReisItem.reisItemId}"
                 data-parent="#accordionActiviteit${subReisItem.reisItemId}">
                <div class="cardActiviteit-body">
                    <tag:reisItemAccordion subReisItems="${subReisItem.geefReisGesorteerdDatum()}"
                                           level="${level}"/>
                </div>
            </div>
        </c:if>
    </div>
</div>