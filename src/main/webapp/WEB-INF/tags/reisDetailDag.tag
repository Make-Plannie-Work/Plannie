<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag"%>
<%@ attribute name="dag" type="MakePlannieWork.Plannie.model.reisitem.Dag"%>


<div class="card-header border-0">
    ${dag.geefDagTitel()}
</div>
<div class="card-block px-2">
    <h4 class="card-title"><a id="NotitieDetails${subReisItem.naam}"
                              href="${pageContext.request.contextPath}/${groepId}/${reisItemId}/${subReisItem.reisItemId}/NotitieWijzigen">${subReisItem.naam}</a>
        <tag:dropDownNieuwReisItem reisItem="${subReisItem}"/>
    </h4>
    <p class="card-text">${dag.geefOmschrijving()}</p>
    <p class="card-text">${dag.geefBudget()}</p>
</div>