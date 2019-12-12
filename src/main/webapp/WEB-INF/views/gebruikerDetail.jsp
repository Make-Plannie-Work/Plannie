<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<body>
<h2>Hee Hallo</h2>
<p>Time: ${time}</p>
<form action="/logout" method="post">
    <input type="hidden"
           name="${_csrf.parameterName}"
           value="${_csrf.token}"/>
    <input type="submit" value="Logout">
</form>

</body>
</html>