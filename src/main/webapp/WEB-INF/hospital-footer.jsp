<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String hostname = session.getServletContext().getInitParameter("hostname");
%>
<footer>
  <p>© 2024 Hôpital <%=hostname%> - Tout droits réservés</p>
</footer>