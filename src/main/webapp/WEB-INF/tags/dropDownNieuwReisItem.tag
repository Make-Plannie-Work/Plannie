<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag"%>
<%@ attribute name="reisItem" type="MakePlannieWork.Plannie.model.reisitem.ReisItem"%>
<%@ attribute name="optie" type="java.lang.Integer"%>
<div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
    <c:if test="${optie < 1}">
        <div class="btn-group mr-2" role="group" aria-label="First group">
            <button class="btn btn-primary mt-3" id="notitieKeuze${reisItem.reisItemId}/NotitieAanmaken"
                    onclick="window.location.href = '${pageContext.request.contextPath}/${groep.groepId}/reisItemDetail/${reisItem.reisItemId}/NotitieAanmaken'">
                Notitie
            </button>
            <button class="btn btn-primary mt-3" id="pollKeuze${reisItem.reisItemId}/PollAanmaken"
                    onclick="window.location.href = '${pageContext.request.contextPath}/${groep.groepId}/reisItemDetail/${reisItem.reisItemId}/PollAanmaken'">
                Poll
            </button>
            <button class="btn btn-primary mt-3" id="locatieKeuze${reisItem.reisItemId}/LocatieAanmaken"
                    onclick="window.location.href = '${pageContext.request.contextPath}/${groep.groepId}/reisItemDetail/${reisItem.reisItemId}/LocatieAanmaken'">
                Locatie
            </button>
        </div>
    </c:if>
    <c:if test="${optie >= 0}">
    <div class="btn-group mr-2" role="group" aria-label="Second group">
        <button class="btn btn-primary mt-3" id="activiteitKeuze${reisItem.reisItemId}/ActiviteitAanmaken"
                onclick="window.location.href = '${pageContext.request.contextPath}/${groep.groepId}/reisItemDetail/${reisItem.reisItemId}/ActiviteitAanmaken'">
            Activiteit
        </button>
    </div>
    </c:if>
</div>