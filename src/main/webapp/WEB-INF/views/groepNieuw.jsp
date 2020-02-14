<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<div class="row ">
    <form:form action="${pageContext.request.contextPath}/groepAanmaken" class="m-auto" method="post" modelAttribute="nieuweGroepFormulier">
        <input id="groepNaam" type="text" class="form-control " name="groepsNaam" required="required" placeholder="Naam Groep">
        <button id="groepAanmaken" class="text mt-2 btn-primary groepAanmaakbtn" type="submit">Maak groep aan <i class="fas fa-plus"></i></button>
    </form:form>
    <hr class="my-4">
</div>