<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag"%>
<%@ attribute name="subReisItem" type="MakePlannieWork.Plannie.model.reisitem.ReisItem"%>
<%@ attribute name="level" type="java.lang.Integer"%>
<!--Een activiteitDetail kaart laat in grote lijnen zien wat de subItems eronder aan informatie bevatten-->
<!--Locaties, poll status, budget, etc?-->

<c:set var="soortReisItem" scope="request" value="${subReisItem.getClass().name}"/>

<!--Accordion kop-->
<div class="accordion" id="accordionActiviteitItems${subReisItem.reisItemId}">
    <div class="list-group">
        <div class="accordion-header" id="headingActiviteitOne${subReisItem.reisItemId}">
            <c:if test="${not empty subReisItem.reisItems}">
                <button class="btn btn-danger btn-lg btn-block" type="button" data-toggle="collapse"
                        data-target="#collapseActiviteitOne${subReisItem.reisItemId}" aria-expanded="true"
                        aria-controls="collapseActiviteitOne${subReisItem.reisItemId}"><i class="far fa-list-alt"></i>
                </button>
            </c:if>

            <!--Inhoud begin, is altijd activiteit-->
            <div class="card reisItem flex-row flex-wrap mb-2 mx-auto" id="activiteitItemCard">
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
            </div>
            <!--Inhoud eind-->

        </div>
        <!--Accordion uitklapveld-->
        <c:if test="${not empty subReisItem.reisItems}">
            <div id="collapseActiviteitOne${subReisItem.reisItemId}" class="collapse"
                 aria-labelledby="headingActiviteitOne${subReisItem.reisItemId}"
                 data-parent="#accordionActiviteitItems${subReisItem.reisItemId}">
                <div class="card-body">
                    <tag:reisItemAccordion subReisItems="${subReisItem.geefReisGesorteerdDatum()}"
                                           level="${level}"/>
                </div>
            </div>
        </c:if>
    </div>
</div>