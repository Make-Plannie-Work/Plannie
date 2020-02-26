<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<jsp:include page="header.jsp"/>
<title>Welkom bij Plannie</title>
<body>

<div class="container shadow-lg mb-3">
  <div class="row">
        <span class="navbar-brand mx-auto mb-0 mt-3"><a id="gebruikerDetail" href="${pageContext.request.contextPath}/gebruikerDetail"><img
                src="${pageContext.request.contextPath}/images/PlannieLogo.png" class="img-fluid" alt="Responsive image"></a></span>
  </div>
    <div class="row mt-6 mb-6">
      <hr class="my-4">
    </div>
    <div class="row">

      <div class="col-sm-4">
        <div class="jumbotron shadow-sm">
          <div class="row" >
            <i class="fas fa-users svg-inline--fa fa-users fa-w-20 fa-5x mx-auto plannierood"></i>
          </div>
          <hr class="my-4">
          <div class="row">
            <h5 class="mx-auto">Maak groepen aan</hp>

          </div>
        </div>
      </div>

      <div class="col-sm-4">
        <div class="jumbotron shadow-sm ">
          <div class="row" >
            <i class="fas fa-suitcase svg-inline--fa fa-w-20 fa-5x mx-auto planniezalm"></i>
          </div>
          <hr class="my-4">
          <div class="row">
            <h5 class="mx-auto">Maak reizen aan</h5>

          </div>
        </div>
      </div>

    <div class="col-sm-4">
      <div class="jumbotron shadow-sm ">
        <div class="row" >
          <i class="fas fa-tasks svg-inline--fa fa-w-20 fa-5x mx-auto planniegeel"></i>
        </div>
        <hr class="my-4">
        <div class="row">
          <h5 class="mx-auto">Beheer je reizen</h5>
        </div>
      </div>
    </div>
  </div>
</div>

<div class="modal fade" id="loginmodal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="inloggenmodal">Inloggen</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">

        <form:form id="loginForm" action="${pageContext.request.contextPath}/index" modelAttribute="loginForm" method="post">
          <div class="form-group">
            <form:input type="email" class="form-control" id="username" path="username" placeholder="Email"/>
          </div>
          <div class="form-group">
            <form:input type="password" id="password" path="password" class="form-control" placeholder="Wachtwoord"/>
            <a id="resetten" class="nav-link text-dark" data-toggle="modal" data-target="#resetmodal">Wachtwoord resetten</a>
          </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Annuleer</button>
        <button id="login" type="submit" class="btn btn-primary">Login</button>
      </div></form:form>
      </div>
    </div>
  </div>
</div>

<div class="modal fade" id="resetmodal" tabindex="-1" role="dialog" aria-labelledby="resetmodal" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" class="close" id="resetmodal2"><p>Wachtwoord Resetten</p></h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <form:form id="updatePasswordForm" action="${pageContext.request.contextPath}/wachtwoordReset" modelAttribute="updatePasswordForm" method="post">
        <div class="modal-body">


            <div class="form-group">
              <form:input type="email" class="form-control" id="usernameReset" path="email" placeholder="Email"/>
            </div>

        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-dismiss="modal">Annuleer</button>
          <button id="resetWachtwoord" type="submit" class="btn btn-primary">Reset Wachtwoord</button>

        </div>
      </form:form>
    </div>
  </div>
</div>
</body>
<jsp:include page="footer.jsp"/>