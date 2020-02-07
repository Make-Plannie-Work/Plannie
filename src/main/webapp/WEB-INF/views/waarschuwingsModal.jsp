<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<div class="modal fade" id="waarschuwingsModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="inloggenmodal">Notitie verwijderen</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">

                <form:form id="notitieVerwijderenForm" action="${pageContext.request.contextPath}/${groep.groepId}/${reisItem.reisItemId}/${reisItems.reisItemId}/subReisItemVerwijderen"
                           modelAttribute="subReisItemVerwijderFormulier" method="post">
                    Weet u zeker dat u deze notitie wil verwijderen?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Annuleer</button>
                <button id="verwijderNotitie" type="submit" class="btn btn-primary">Verwijder notitie</button>
                </form:form>
            </div>
        </div>
    </div>
</div>