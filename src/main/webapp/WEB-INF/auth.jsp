<%--
  Created by IntelliJ IDEA.
  User: Jeido
  Date: 04/09/2024
  Time: 00:32
  To change this template use File | Settings | File Templates.
--%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <title>Authentification - </title>
</head>
<body>
<jsp:include page="hospital-header.jsp"/>
<main>
    <h1>Se connecter</h1>
    <form action="auth" method="post">
        <div>
            <label for="username">Login</label>
            <input id="username" name="username"/>
        </div>
        <div>
            <label for="password">Password</label>
            <input id="password" name="password"/>
        </div>
        <div>
            <button>Se Connecter</button>
        </div>
    </form>
</main>
<jsp:include page="hospital-footer.jsp"/>
</body>
</html>
