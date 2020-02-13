<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag"%>
<%@ attribute name="reisDagen" type="java.util.ArrayList"%>
<%@ attribute name="reisActiviteiten" type="java.util.ArrayList"%>
<%@ attribute name="subReisItems" type="java.util.ArrayList"%>
<%@ attribute name="level" type="java.lang.Integer"%>

<!--Het juiste level wordt geset, als dit het eerste level is, en er minder dan 2 dagen zijn, wordt het level gelijk op 3 gezet.-->
<c:set var="level" scope="page" value="${level + 1}"/>
<c:if test="${level == 1 && fn:length(reisDagen) < 2}">
    <c:set var="level" scope="page" value="2"/>
</c:if>
<c:if test="${level <= 1}">
    <c:forEach var="dag" items="${reisDagen}">
        <c:choose>
            <c:when test="${dag.dagNummer == 0}">
                <c:forEach var="dag0reisItem" items="${dag.reisItems}">
                    <!--Sub reisItem op een klein kaartje laten zien-->
                    <tag:reisDetailSubReisItem subReisItem="${dag0reisItem}" level="2"/>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <!--Dagen Overzicht tonen-->
                <tag:reisDetailDag dag="${dag}" level="${level}"/>
            </c:otherwise>
        </c:choose>
    </c:forEach>
</c:if>
<c:if test="${level == 2}">
    <c:forEach var="subReisItem" items="${subReisItems}">
        <!--Activiteit Overzicht tonen-->
        <tag:reisDetailActiviteit subReisItem="${subReisItem}" level="${level}"/>
    </c:forEach>
</c:if>
<c:if test="${level >= 3}">
    <c:forEach var="subReisItem" items="${subReisItems}">
        <!--Sub reisItem op een klein kaartje laten zien-->
        <tag:reisDetailSubReisItem subReisItem="${subReisItem}" level="${level}"/>
    </c:forEach>
</c:if>

