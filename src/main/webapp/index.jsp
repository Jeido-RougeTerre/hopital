<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String hostname = session.getServletContext().getInitParameter("hostname");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Accueil - <%=hostname%></title>
</head>
<body>
<header>
    <h1>Bienvenue à l'Hôpital <%=hostname%></h1>
    <nav><a href="${pageContext.request.contextPath}/index.jsp">Acceuil</a><a href="${pageContext.request.contextPath}/list">Liste des Patients</a></nav>
</header>
<main>
    <br/>
</main>
<footer>
    <p>© 2024 Hôpital <%=hostname%> Tout droits réservés</p>
</footer>
</body>
</html>