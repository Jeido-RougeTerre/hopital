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
<jsp:include page="WEB-INF/hospital-header.jsp"/>
<main>
    <br/>
</main>
<jsp:include page="WEB-INF/hospital-footer.jsp"/>
</body>
</html>