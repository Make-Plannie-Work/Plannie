<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<div class="container mt-4">
    <div class="row">
        <form:form id="groepsNaamWijzigenForm" action= "${pageContext.request.contextPath}/groepDetail/${groep.groepId}/groepWijzig" method="post" modelAttribute="groepsNaamWijzigingsFormulier">
            <input type="text" name="groepsNaam" required="required" value="${groep.groepsNaam}">
            <input id="groepsNaamWijzigen" type="submit" class="btn btn-primary" value="Wijzig groepsnaam">
        </form:form>

    </div>
    <hr class="my-4">
    <div class="row">
        <form:form id="groepBeheerderWijzigenForm" action = "${pageContext.request.contextPath}/groepDetail/${groep.groepId}/groepBeheerderWijzig" method="post">
            <select name="beheerderEmail" size="5" style="width: 100%">
                <c:forEach items="${groepsLedenLijst}" var="groepslid">
                    <c:if test="${groepslid.gebruikersId != groep.aanmaker}">
                        <option value="${groepslid.email}">${groepslid.voornaam} ${groepslid.achternaam}</option>
                    </c:if>
                </c:forEach>
            </select>
            <br>
            <input id="groepBeheerderWijzigen" type="submit" class="btn btn-primary" value="Wijzig groep beheerder">
        </form:form>

    </div>
    <hr class="my-4">
    <div class="row">
        <form:form id="groepsAfbeeldingWijzigenForm" method="POST" enctype="multipart/form-data" action="${pageContext.request.contextPath}/${groep.groepId}/uploadImage" >

                    <td><input type="file" name="imageFile" accept="image/*"/></td>

                    <td><input type="submit" name="uploadImage" value="Upload" /></td>

        </form:form>
    </div>

    <a href="${pageContext.request.contextPath}/groepDetail/${groep.groepId}"><button type="text" class="btn btn-primary mt-3" id="annuleren">Annuleren</button></a>
</div>