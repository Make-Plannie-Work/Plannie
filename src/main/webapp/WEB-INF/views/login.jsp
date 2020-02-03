<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
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
        <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-dismiss="modal">Annuleer</button>
            <button id="login" type="submit" class="btn btn-primary">Login</button>
            </form:form>
        </div>
    </div>
</div>
