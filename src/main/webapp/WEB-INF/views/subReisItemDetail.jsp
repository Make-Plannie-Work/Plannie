<c:set var="soortReisItem" scope="session" value="${subReisItem.getClass().name}"/>

<div class="accordion" id="accordionExample">
    <div class="card">
        <div class="card-header" id="headingOne">
            <h2 class="mb-0">
                <button class="btn" type="button" data-toggle="collapse"
                        data-target="#collapseOne${subReisItem.reisItemId}" aria-expanded="true"
                        aria-controls="collapseOne">
                    <div class="card reisItem flex-row flex-wrap mb-2 mx-auto" id="reisItemCard">
                        <c:choose>
                            <c:when test="${soortReisItem == 'MakePlannieWork.Plannie.model.reisitem.Notitie'}">

                                <div class="card-header border-0">
                                    Notitie ${subReisItem.startDatum}
                                </div>
                                <div class="card-block px-2">
                                    <h4 class="card-title"><a id="NotitieDetails${subReisItem.naam}"
                                                              href="/${groepId}/${reisItemId}/${subReisItem.reisItemId}/NotitieWijzigen">${subReisItem.naam}</a>
                                    </h4>
                                    <p class="card-text">${subReisItem.tekst}</p>
                                    <p class="card-text">${subReisItem.budget}</p>
                                </div>

                            </c:when>
                            <c:when test="${soortReisItem == 'MakePlannieWork.Plannie.model.reisitem.Poll'}">

                                <div class="card-header border-0">
                                    Poll ${subReisItem.startDatum}
                                </div>
                                <div class="card-block px-2">
                                    <h4 class="card-title"><a id="PollDetails${subReisItem.naam}"
                                                              href="/${groepId}/${reisItemId}/PollDetail/${subReisItem.reisItemId}">${subReisItem.naam}</a>
                                    </h4>
                                    <p class="card-text">
                                    <p class="card-text">
                                        <c:if test="${!subReisItem.gebruikerHeeftGestemd(currentUser.gebruikersId)}">
                                            <a class="lead text-blue">Stem nu!</a>
                                        </c:if>
                                        <a id="pollOptieTekst${subReisItem.reisItemId}">${subReisItem.pollStatus()}</a>
                                    </p>
                                    </p>
                                </div>
                            </c:when>
                            <c:when test="${soortReisItem == 'MakePlannieWork.Plannie.model.reisitem.Locatie'}">
                                <div class="card-header border-0">
                                    Locatie ${subReisItem.startDatum}
                                </div>
                                <div class="card-block px-2">
                                    <h4 class="card-title"><a id="LocatieDetails${subReisItem.naam}"
                                                              href="/${groepId}/${reisItemId}/${subReisItem.reisItemId}/LocatieWijzigen">${subReisItem.naam}</a>
                                    </h4>
                                    <p class="card-text">${subReisItem.adres}</p>
                                    <div class="googleMapSmall" id="map${subReisItem.reisItemId}"
                                         data-latitude="${subReisItem.latitude}"
                                         data-longitude="${subReisItem.longitude}"
                                         data-id="${subReisItem.reisItemId}"></div>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="card-header border-0">
                                    <img src="https://via.placeholder.com/100.jpg" alt="">
                                </div>
                                <div class="card-block px-2">
                                    <h4 class="card-title"><a id="reisItemDetails${subReisItem.reisItemId}"
                                                              href="/${groep.groepId}/reisItemsDetail/${subReisItem.reisItemId}">${subReisItem.naam}</a>
                                    </h4>
                                    <p class="card-text">${subReisItem.getClass().name}</p>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </button>
            </h2>
        </div>

        <c:if test="${subReisItem.reisItems.size() > 0}">

            <div id="collapseOne${subReisItem.reisItemId}" class="collapse" aria-labelledby="headingOne"
                 data-parent="#accordionExample">
                <div class="card-body">

                    <c:forEach items="${subReisItem.reisItems}" var="subReisItem">


                        <div class="card reisItem flex-row flex-wrap mb-2 mx-auto" id="reisItemCard">
                            <div class="card-header border-0">
                                Notitie ${subReisItem.startDatum}
                            </div>
                            <div class="card-block px-2">
                                <h4 class="card-title">
                                    <a>${subReisItem.naam}</a>
                                </h4>
                                <p class="card-text">${subReisItem.naam}</p>
                                <p class="card-text">${subReisItem.naam}</p>
                            </div>
                        </div>

                    </c:forEach>

                </div>
            </div>

        </c:if>

    </div>
</div>

