<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<div class="container mt-4">
    <div class="col">
        <div class="row">
            <form:form
                    action="${pageContext.request.contextPath}/${groepId}/reisItemDetail/${reisItemId}/reisNaamWijzigen"
                    method="post" modelAttribute="reisNaamWijzigingsFormulier">

                <label for="reisNaamWijzigenVeld">Naam van de reis</label>
                <label for="reisDatumWijzigenVeld">Startdatum</label>
                <form:input id="reisNaamWijzigenVeld" type="text" path="naam" required="required"/>
                <form:input id="reisDatumWijzigenVeld" type="datetime-local" path="startDatum" required="required"/>
                <form:button id="reisNaamWijzigen" type="submit" class="btn btn-primary" value="Wijzig reisitem">Wijzig
                    reis gegevens
                </form:button>
            </form:form>
        </div>
        <hr class="my-4">
        <div class="row">
            <form:form method="POST" enctype="multipart/form-data"
                       action="${pageContext.request.contextPath}/${groepId}/reisItemDetail/${reisItem.reisItemId}/uploadReisItemImage">

                <td><input type="file" name="imageFile" accept="image/*"/></td>

                <td><input type="submit" name="uploadImage" value="Upload"/></td>

            </form:form>
        </div>
        <hr class="my-4">
        <a href="${pageContext.request.contextPath}/${groepId}/reisItemDetail/${reisItemId}">
            <button type="text" class="btn btn-primary mt-3" id="annuleren">Annuleren</button>
        </a>
    </div>
</div>