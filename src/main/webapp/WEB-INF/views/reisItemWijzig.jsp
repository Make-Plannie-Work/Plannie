<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<div class="container mt-4">
    <div class="row">
        <form:form action= "${pageContext.request.contextPath}/${groepId}/reisItemDetail/${reisItemId}/reisNaamWijzigen" method="post" modelAttribute="reisNaamWijzigingsFormulier">
            <input type="text" name="naam" required="required" value="${reisItem.naam}">
            <input id="reisNaamWijzigen" type="submit" class="btn btn-primary" value="Wijzig reisitem">
        </form:form>
    </div>

    <div class="row">
        <form:form method="POST" enctype="multipart/form-data" action="${pageContext.request.contextPath}/${groepId}/reisItemDetail/${reisItem.reisItemId}/uploadReisItemImage" >

            <td><input type="file" name="imageFile" accept="image/*"/></td>

            <td><input type="submit" name="uploadImage" value="Upload" /></td>

        </form:form>
    </div>

    <a href="${pageContext.request.contextPath}/{groepId}/reisItemDetail/{reisItemId}"><button type="text" class="btn btn-primary mt-3" id="annuleren">Annuleren</button></a>
</div>