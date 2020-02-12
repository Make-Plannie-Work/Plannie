<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag"%>
<%@ attribute name="reisDagen" type="java.util.ArrayList"%>
<%@ attribute name="reisActiviteiten" type="java.util.ArrayList"%>
<%@ attribute name="subReisItems" type="java.util.ArrayList"%>
<%@ attribute name="level" type="java.lang.Integer"%>

<!--Het juiste level wordt geset, als dit de eerste level is, en er geen dagen zijn, wordt het level gelijk op 3 gezet.-->
<c:set var="level" scope="page" value="${level + 1}"/>
<c:if test="${level == 1 && empty reisDagen}">
    <c:set var="level" scope="page" value="3"/>
</c:if>
<c:if test="${level == 1}">
    <c:forEach var="dag" items="${reisDagen}">
        <c:choose>
            <c:when test="${dag.dagNummer == 0}">
                <c:forEach var="dag0reisItem" items="${dag.reisItems}">
                    <div class="accordion" id="accordionReisItems${dag0reisItem.reisItemId}">
                        <div class="list-group">
                            <div class="accordion-header" id="headingOne${dag0reisItem.reisItemId}">
                                <!--Mits dit item 1, of meerdere sub items heeft, komt er een Accordion-uitklap knop-->
                                <c:if test="${not empty subReisItem.reisItems}">
                                    <button class="btn btn-danger btn-lg btn-block" type="button" data-toggle="collapse"
                                            data-target="#collapseOne${subReisItem.reisItemId}" aria-expanded="true"
                                            aria-controls="collapseOne${subReisItem.reisItemId}"><i class="far fa-list-alt"></i>
                                    </button>
                                </c:if>
                                <div class="card reisItem flex-row flex-wrap mb-2 mx-auto" id="dag0ItemCard">
                                    <!--Sub reisItem op een klein kaartje laten zien-->
                                    <tag:reisDetailSubReisItem subReisItem="${dag0reisItem}"/>
                                </div>
                            </div>

                            <c:if test="${not empty subReisItem.reisItems}">
                                <div id="collapseOne${dag0reisItem.reisItemId}" class="collapse"
                                     aria-labelledby="headingOne${subReisItem.reisItemId}"
                                     data-parent="#accordionReisItems${subReisItem.reisItemId}">
                                    <div class="card-body">
                                        <tag:reisItemAccordion subReisItems="${subReisItem.geefReisGesorteerdDatum()}"
                                                               level="${level}"/>
                                    </div>
                                </div>
                            </c:if>
                        </div>
                    </div>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <div class="accordion" id="accordionReisItems${dag.dagNummer}">
                    <div class="list-group">
                        <div class="accordion-header" id="headingOne${dag.dagNummer}">
                            <button class="btn btn-danger btn-lg btn-block" type="button" data-toggle="collapse"
                                    data-target="#collapseOne${dag.dagNummer}" aria-expanded="true"
                                    aria-controls="collapseOne${dag.dagNummer}"><i class="far fa-list-alt"></i>
                            </button>
                            <div class="card reisItem flex-row flex-wrap mb-2 mx-auto" id="dagItemCard">
                                <!--Dagen Overzicht tonen-->
                                <tag:reisDetailDag dag="${dag}"/>
                            </div>
                        </div>

                        <div id="collapseOne${dag.dagNummer}" class="collapse"
                             aria-labelledby="headingOne${dag.dagNummer}"
                             data-parent="#accordionReisItems${dag.dagNummer}">
                            <div class="card-body">
                                <tag:reisItemAccordion subReisItems="${dag.reisItems}"
                                                       level="${level}"/>
                            </div>
                        </div>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>
    </c:forEach>
</c:if>
<c:if test="${level == 2}">

    <c:forEach var="subReisItem" items="${subReisItems}">
        <div class="accordion" id="accordionActiviteitItems${subReisItem.reisItemId}">
            <div class="list-group">
                <div class="accordion-header" id="headingActiviteitOne${subReisItem.reisItemId}">
                    <!--Mits dit item 1, of meerdere sub items heeft, komt er een Accordion-uitklap knop-->
                    <c:if test="${not empty subReisItem.reisItems}">
                        <button class="btn btn-danger btn-lg btn-block" type="button" data-toggle="collapse"
                                data-target="#collapseActiviteitOne${subReisItem.reisItemId}" aria-expanded="true"
                                aria-controls="collapseActiviteitOne${subReisItem.reisItemId}"><i class="far fa-list-alt"></i>
                        </button>
                    </c:if>
                    <div class="card reisItem flex-row flex-wrap mb-2 mx-auto" id="activiteitItemCard">
                        <!--Activiteit Overzicht tonen-->
                        <tag:reisDetailActiviteit subReisItem="${subReisItem}"/>
                    </div>
                </div>

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
    </c:forEach>

</c:if>
<c:if test="${level >= 3}">
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
                    <div class="card reisItem flex-row flex-wrap mb-2 mx-auto" id="reisItemCard">
                        <!--Sub reisItem op een klein kaartje laten zien-->
                        <tag:reisDetailSubReisItem subReisItem="${subReisItem}"/>
                    </div>
                </div>

                <c:if test="${not empty subReisItem.reisItems}">
                    <div id="collapseOne${subReisItem.reisItemId}" class="collapse"
                         aria-labelledby="headingOne${subReisItem.reisItemId}"
                         data-parent="#accordionReisItems${subReisItem.reisItemId}">
                        <div class="card-body">
                            <tag:reisItemAccordion subReisItems="${subReisItem.geefReisGesorteerdDatum()}"
                                                   level="${level}"/>
                        </div>
                    </div>
                </c:if>
            </div>
        </div>
    </c:forEach>
</c:if>

