<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<div class="container mt-4">
    <div class="row">
        <form:form action = "/groepDetail/${groep.groepId}/groepWijzig" method="post" modelAttribute="groepsNaamWijzigingsFormulier">
            <input type="text" name="groepsNaam" required="required" value="${groep.groepsNaam}">
            <input id="groepsNaamWijzigen" type="submit" class="btn btn-primary" value="Wijzig naam">
        </form:form>
    </div>
    <br>
    <div class="row">
        <form:form method="POST" enctype="multipart/form-data" action="${pageContext.request.contextPath}/${groep.groepId}/uploadImage" >

                    <td><input type="file" name="imageFile" accept="image/*"/></td>

                    <td><input type="submit" name="uploadImage" value="Upload" /></td>

        </form:form>
    </div>

    <a href="${pageContext.request.contextPath}/groepDetail/${groep.groepId}"><button type="text" class="btn btn-primary mt-3" id="annuleren">Annuleren</button></a>
</div>