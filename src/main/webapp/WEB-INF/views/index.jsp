<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<jsp:include page="header.jsp"/>
<title>Welkom bij Plannie</title>
<body>

<div class="container mt-3">
    <div class="row">
    <div class="col-sm-8">
      <div class="jumbotron shadow border">
        <div class="row">
          <p class="lead">Info 3</p>
          <p>
            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse enim velit, tempor vel dolor et, porttitor vestibulum odio. Ut quis elit eu est viverra aliquam a ultricies leo. Curabitur non arcu hendrerit, vestibulum dui sit amet, suscipit diam. Integer suscipit dui tortor, euismod accumsan magna dictum ut. Curabitur sit amet nunc id sapien malesuada aliquet sit amet et arcu. In hendrerit porta justo vitae tempus. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Integer sit amet felis mauris. Proin vitae condimentum dolor. Mauris commodo porttitor mi, in consequat dui posuere ac. Praesent vitae enim magna. Sed egestas malesuada ligula, vel molestie tortor finibus at. Fusce vestibulum convallis tempus. Ut vel erat ultrices, placerat sapien a, ornare erat. Aliquam scelerisque convallis mauris a interdum. Etiam rhoncus in mi nec mollis. Cras malesuada elit et purus tincidunt varius. Suspendisse placerat lacus non libero pretium volutpat. Ut malesuada, ex non vehicula vulputate, quam ex ullamcorper dolor, id consectetur libero quam viverra ex. Aliquam blandit cursus ipsum consequat dictum. Cras vitae urna bibendum lorem maximus sollicitudin. Praesent consequat ultrices mi in bibendum. Nunc lorem dui, elementum id massa sed, dictum vehicula nulla. Maecenas in semper eros, eget vehicula risus. Integer vel lorem consequat ipsum auctor pretium imperdiet vitae sem. Sed volutpat, eros non eleifend mattis, lorem risus congue ante, et laoreet nulla felis ac augue. Curabitur rutrum dui tellus, dapibus eleifend augue hendrerit et. Phasellus efficitur urna tortor, et venenatis ipsum sagittis vitae. Nam tincidunt consectetur diam eget suscipit. Aenean at purus faucibus, venenatis lectus quis, maximus nisi.
          </p>
          <p>
            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse enim velit, tempor vel dolor et, porttitor vestibulum odio. Ut quis elit eu est viverra aliquam a ultricies leo. Curabitur non arcu hendrerit, vestibulum dui sit amet, suscipit diam. Integer suscipit dui tortor, euismod accumsan magna dictum ut. Curabitur sit amet nunc id sapien malesuada aliquet sit amet et arcu. In hendrerit porta justo vitae tempus. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Integer sit amet felis mauris. Proin vitae condimentum dolor. Mauris commodo porttitor mi, in consequat dui posuere ac. Praesent vitae enim magna. Sed egestas malesuada ligula, vel molestie tortor finibus at. Fusce vestibulum convallis tempus. Ut vel erat ultrices, placerat sapien a, ornare erat. Aliquam scelerisque convallis mauris a interdum. Etiam rhoncus in mi nec mollis. Cras malesuada elit et purus tincidunt varius. Suspendisse placerat lacus non libero pretium volutpat. Ut malesuada, ex non vehicula vulputate, quam ex ullamcorper dolor, id consectetur libero quam viverra ex. Aliquam blandit cursus ipsum consequat dictum. Cras vitae urna bibendum lorem maximus sollicitudin. Praesent consequat ultrices mi in bibendum. Nunc lorem dui, elementum id massa sed, dictum vehicula nulla. Maecenas in semper eros, eget vehicula risus. Integer vel lorem consequat ipsum auctor pretium imperdiet vitae sem. Sed volutpat, eros non eleifend mattis, lorem risus congue ante, et laoreet nulla felis ac augue. Curabitur rutrum dui tellus, dapibus eleifend augue hendrerit et. Phasellus efficitur urna tortor, et venenatis ipsum sagittis vitae. Nam tincidunt consectetur diam eget suscipit. Aenean at purus faucibus, venenatis lectus quis, maximus nisi.
          </p>
        </div>
      </div>
    </div>

    <div class="col-sm-4">
      <div class="jumbotron shadow" style="background-color: #FF3B56;">
        <div class="row" >
          <p class="lead text-white">Info 1</p>
        </div>
        <hr class="my-4">
        <div class="row">
          <p class="lead text-white mt-3">Info 2</hp>
          <hr class="my-4">
        </div>
      </div>
    </div>
  </div>
  <div class="row mb-3">
    <div class="col-sm-offset-1 col-sm-10 mb-5">
      <div class="card w-75 mx-auto">
        <div class="card-body">
          <p>Contact</p>
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

      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Annuleer</button>
        <button id="login" type="submit" class="btn btn-primary">Login</button>
        </form:form>
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
<jsp:include page="footer.jsp"/>