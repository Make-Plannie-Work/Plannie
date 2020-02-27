<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<jsp:include page="header.jsp"/>
<title>Error!!!</title>
<body >

<div class="container mt-3">

    <div class="row">
        <div class="col-sm-12 fixed-top">
        </div>
    </div>
    <div class="row">

        <div class="col-sm-8">
            <div class="jumbotron shadow border">
                <div class="row">
                    <p class="lead">${errorNaam}</p>
                </div>
                <div class="row">
                    <p>
                        ${error}
                    </p>
                </div>
            </div>
        </div>

        <div class="col-sm-4">
            <div class="jumbotron shadow" style="background-color: #FF3B56;">
                <div class="row" >
                    <p class="lead text-white"></p>
                </div>
                <hr class="my-4">
                <div class="row">
                    <p class="lead text-white mt-3"></hp>
                    <hr class="my-4">
                </div>
            </div>
        </div>
    </div>

</div>
<jsp:include page="footer.jsp"/>
