<!doctype html>
<html lang="en">
<div class="view" style="background-image: url('https://images.unsplash.com/photo-1473496169904-658ba7c44d8a?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1950&q=80'); background-repeat: no-repeat; background-size: cover; background-attachment: fixed;">

  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://kit.fontawesome.com/d450c035a5.js" crossorigin="anonymous"></script>
    <title>Welkom bij Plannie</title>
  </head>
  <body>

  <nav class="navbar navbar-light bg-light shadow">
    <span class="navbar-brand mb-0 h1"><a id="gebruikerDetail" href="/gebruikerDetail" class="text-dark">Plannie</a></span>

    <ul class="nav justify-content-end">
      <li class="nav-item">
        <a class="nav-link text-dark" href="/registreren">Registreer</a>
      </li>
      <li class="nav-item">
        <a id="inloggen" class="nav-link text-dark" data-toggle="modal" data-target="#loginmodal">Login</a>
      </li>
  </nav>

  <div class="container mt-3">
    <div class="row">
      <div class="col-sm-12 fixed-top">
      </div>
    </div>
    <div class="row">

      <div class="col-sm-8">
        <div class="jumbotron shadow">
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
        <div class="jumbotron shadow" style="background-color: #666666;">
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

          <form:form id="loginForm" action="/index" modelAttribute="loginForm" method="post">
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
        <form:form id="updatePasswordForm" action="/wachtwoordReset" modelAttribute="updatePasswordForm" method="post">
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
  <!-- Footer -->
  <footer class="py-4 bg-dark text-white-50">

    <!-- Footer Elements -->
    <div class="container">

      <!-- Call to action -->
      <ul class="list-unstyled list-inline text-center py-2">

        <h6 class="mb-1"><small>Registreer je snel: <a id="registreren" href="/registreren" class="text-white">klik hier!</a></small></h6>


        </li>
      </ul>
      <!-- Call to action -->

    </div>
    <!-- Footer Elements -->
  </footer>
  <!-- Footer -->
  <!-- Optional JavaScript -->
  <!-- jQuery first, then Popper.js, then Bootstrap JS -->
  <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
          integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
          crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
          integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
          crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
          integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
          crossorigin="anonymous"></script>
  </body>
</html>