<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<jsp:include page="header.jsp"/>
<title>Welkom bij Plannie - ${currentUser.voornaam}</title>
    <body>
    <div class="wrapper">
        <div class="container shadow-lg mb-3">
            <div class="row">
                <div class="col-sm-12">
                    <div class="jumbotron">
                        <div class="container">
                            <h1 class="display-4">Hallo ${currentUser.voornaam}</h1>
                            <p class="lead">Welkom bij Plannie! Hieronder vind je een overzicht van jouw groepen.</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">

                <div class="col-sm-12">
                    <div class="box">
                        <div class="row">
                        <div class="kaart">
                            <div class="imgBx">
                                <img id="" src="${pageContext.request.contextPath}/images/static/placeholdernieuwegroep.png" alt="">
                            </div>
                            <div class="details">
                                <jsp:include page="groepNieuw.jsp"/>
                            </div>
                        </div>

                        <c:forEach items="${lijstMetGroepen}" var="groep">
                            <a id="details${groep.groepsNaam}" href="${pageContext.request.contextPath}/groepDetail/${groep.groepId}" class="mx-auto">
                                <div class="kaart mb-3">
                                    <div class="imgBx plannierood">
                                        <img id="" src="${pageContext.request.contextPath}/images/groep/${groep.imagePath}" alt="">
                                    </div>
                                    <div class="details">
                                        <h5 class="ml-1">Groepsnaam: ${groep.groepsNaam}</h5>
                                        <h5 class="ml-1">Groepsleden: ${groep.geefAantalGebruikersInGroep()}</h5>
                                    </div>
                                </div>
                            </a>
                        </c:forEach>
                        </div>
                    </div>
                </div>
                <div class="col-sm-12">
                    <div class="jumbotron">
                        <div class="container">
                            <h1 class="display-4"></h1>
                            <p class="lead">Klik een van de bovenstaande cards aan om naar de groepspagina te gaan.</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>

<jsp:include page="footer.jsp"/>