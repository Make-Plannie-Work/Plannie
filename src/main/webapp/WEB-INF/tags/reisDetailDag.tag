<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag"%>
<%@ attribute name="dag" type="MakePlannieWork.Plannie.model.reisitem.Dag"%>
<%@ attribute name="level" type="java.lang.Integer"%>
<!--Een dagDetail kaart laat in grote lijnen zien wat de activiteiten eronder aan informatie bevatten-->
<!--Locaties, poll status, budget, etc?-->

<!--Accordion kop-->
<div class="accordion" id="accordionReisItems${dag.dagNummer}">
    <div class="list-group">
        <div class="accordion-header" id="headingOne${dag.dagNummer}">
            <button class="btn btn-danger btn-lg btn-block" type="button" data-toggle="collapse"
                    data-target="#collapseOne${dag.dagNummer}" aria-expanded="true"
                    aria-controls="collapseOne${dag.dagNummer}"><i class="far fa-list-alt"></i>
            </button>

            <!--Inhoud begin-->
            <div class="card reisItem flex-row flex-wrap mb-2 mx-auto" id="dagItemCard">
                <div class="card-header border-0">
                    ${dag.geefDagTitel()} ${dag.geefDatum()}
                </div>
                <div class="card-block px-2">
                    <h4 class="card-title"><a id="NotitieDetails${subReisItem.naam}">${dag.geefDagTitel()}</a>
                        <tag:dropDownNieuwReisItem dag="${dag}"/>
                    </h4>
                    <p class="card-text">${dag.geefOmschrijving()}</p>
                    <p class="card-text">Budget: ${dag.geefBudget()} euro</p>
                </div>
            </div>
            <!--Inhoud eind-->

        </div>
        <!--Accordion uitklapveld-->
        <div id="collapseOne${dag.dagNummer}" class="collapse"
             aria-labelledby="headingOne${dag.dagNummer}"
             data-parent="#accordionReisItems${dag.dagNummer}">
            <div class="card-body">
                <tag:reisItemAccordion subReisItems="${dag.reisItems}" level="${level}"/>
            </div>
        </div>
    </div>
</div>




