<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag"%>
<%@ attribute name="dag" type="MakePlannieWork.Plannie.model.reisitem.Dag"%>
<%@ attribute name="level" type="java.lang.Integer"%>
<!--Een dagDetail kaart laat in grote lijnen zien wat de activiteiten eronder aan informatie bevatten-->
<!--Locaties, poll status, budget, etc?-->

<!--Accordion kop-->
<section class="accordionDag" id="accordionDag${dag.dagNummer}">
    <div class="card">
        <div class="accordionDag-header card-body" id="headingDag${dag.dagNummer}">

            <!--Inhoud begin, is altijd een Dag, en de gegevens van de dag staan in de knop.-->
            <button class="btn dagKnop btn-lg btn-block" type="button" data-toggle="collapse"
                    data-target="#collapseDag${dag.dagNummer}" aria-expanded="true"
                    aria-controls="collapseDag${dag.dagNummer}">
                <div class="row">
                    <p class="card-title m-auto"><strong>${dag.geefDagTitel()}</strong> - ${dag.geefDatum()}</p>
                </div>
            </button>
        </div>
        <!--Inhoud eind-->

        <!--Accordion uitklapveld-->
        <div id="collapseDag${dag.dagNummer}" class="collapse show card-footer"
             aria-labelledby="headingDag${dag.dagNummer}"
             data-parent="#accordionDag${dag.dagNummer}">
            <div>
                <tag:reisItemAccordion subReisItems="${dag.reisItems}" level="${level}"/>
            </div>
        </div>
    </div>
</section>




