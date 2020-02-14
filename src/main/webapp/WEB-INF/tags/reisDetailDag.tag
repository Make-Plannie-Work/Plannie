<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag"%>
<%@ attribute name="dag" type="MakePlannieWork.Plannie.model.reisitem.Dag"%>
<%@ attribute name="level" type="java.lang.Integer"%>
<!--Een dagDetail kaart laat in grote lijnen zien wat de activiteiten eronder aan informatie bevatten-->
<!--Locaties, poll status, budget, etc?-->

<!--Accordion kop-->
<div class="accordion" id="accordionReisItems${dag.dagNummer}">
    <div class="list-group">
        <div class="accordion-header" id="headingDag${dag.dagNummer}">
            <!--Inhoud begin, is altijd een Dag, en de gegevens van de dag staan in de knop.-->
            <button class="btn btn-danger btn-lg btn-block" type="button" data-toggle="collapse"
                    data-target="#collapseDag${dag.dagNummer}" aria-expanded="true"
                    aria-controls="collapseDag${dag.dagNummer}">
                <div class="row">
                    <p class="card-title m-auto"><strong>${dag.geefDagTitel()}</strong> - ${dag.geefDatum()}</p>
                </div>
                <div><tag:dropDownNieuwReisItem dag="${dag}"/></div>
            </button>
            <!--Inhoud eind-->
        </div>

        <!--Accordion uitklapveld-->
        <div id="collapseDag${dag.dagNummer}" class="collapse show"
             aria-labelledby="headingDag${dag.dagNummer}"
             data-parent="#accordionReisItems${dag.dagNummer}">
            <div class="card-body">
                <tag:reisItemAccordion subReisItems="${dag.reisItems}" level="${level}"/>
            </div>
        </div>
    </div>
</div>




