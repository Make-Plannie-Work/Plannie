<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag"%>
<%@ attribute name="reisItem" type="MakePlannieWork.Plannie.model.reisitem.ReisItem"%>

<p>

    <a id="nieuwReisItemKeuze${reisItem.reisItemId}" class="button" data-toggle="collapse"
       href="#collapseExample${reisItem.reisItemId}" role="button" aria-expanded="false"
       aria-controls="collapseExample">
        <i class="far fa-plus-square"></i></a>

</p>
<div class="collapse" id="collapseExample${reisItem.reisItemId}">
    <div class="card card-body">

        <p class="card-text">Nieuw item toevoegen</p>
        <a class="btn btn-primary dropDownbtn" id="notitieKeuze${reisItem.reisItemId}/NotitieAanmaken"
           href="${pageContext.request.contextPath}/${groep.groepId}/reisItemDetail/${reisItem.reisItemId}/NotitieAanmaken">Een
            Notitie</a>
        <a class="btn btn-primary dropDownbtn" id="pollKeuze${reisItem.reisItemId}/PollAanmaken"
           href="${pageContext.request.contextPath}/${groep.groepId}/reisItemDetail/${reisItem.reisItemId}/PollAanmaken">Een
            Poll</a>
        <a class="btn btn-primary dropDownbtn" id="locatieKeuze${reisItem.reisItemId}/LocatieAanmaken"
           href="${pageContext.request.contextPath}/${groep.groepId}/reisItemDetail/${reisItem.reisItemId}/LocatieAanmaken">Een
            Locatie</a>
        <a class="btn btn-primary dropDownbtn" id="activiteitKeuze${reisItem.reisItemId}/ActiviteitAanmaken"
           href="${pageContext.request.contextPath}/${groep.groepId}/reisItemDetail/${reisItem.reisItemId}/ActiviteitAanmaken">Een
            Activiteit</a>

    </div>
</div>