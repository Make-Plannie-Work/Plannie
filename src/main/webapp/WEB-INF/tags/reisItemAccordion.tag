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

                    <!--Sub reisItem op een klein kaartje laten zien-->
                    <tag:reisDetailSubReisItem subReisItem="${subReisItem}"/>

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