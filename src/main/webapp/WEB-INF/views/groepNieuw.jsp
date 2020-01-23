<div class="row ">
    <form:form action="/groepAanmaken" class="m-auto" method="post" modelAttribute="nieuweGroepFormulier">
        <input type="text" class="form-control " name="groepsNaam" required="required" placeholder="Naam Groep">
        <button id="groepAanmaken" class="text mt-2 shadow" type="submit">Maak groep aan <i class="fas fa-plus"></i></button>
    </form:form>
    <hr class="my-4">
</div>