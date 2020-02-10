<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<footer class="py-4 bg-dark text-white-50">
    <!-- Footer Elements -->
    <div class="container">
        <!-- Call to action -->
        <ul class="list-unstyled list-inline text-center py-2">
            <security:authorize access="isAnonymous()">
                <h6 class="mb-1"><small>Registreer je snel: <a id="registreren" href="${pageContext.request.contextPath}/registreren" class="text-white">klik hier!</a></small></h6>
            </security:authorize>
            <security:authorize access="isAuthenticated()">
            </security:authorize>
        </ul>
        <!-- Call to action -->
    </div>

</footer>
<!-- Footer -->
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
        integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
        crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="//code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="${pageContext.request.contextPath}/js/vullenModal.js"> </script>
<script src="${pageContext.request.contextPath}/js/main.js"> </script>
<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.1/dist/jquery.validate.min.js"></script>
<script src="${pageContext.request.contextPath}\js/validation.js" type="text/javascript"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
        crossorigin="anonymous"></script>
<!-- Replace the value of the key parameter with your own API key. -->

<script src="${pageContext.request.contextPath}\js/googleKaart.js"></script>
</body>
</html>