<%@page import="java.util.List" %>
<%@page import="java.lang.String" %>
<%@page import="com.jeido.hospital.entities.Patient" %>
<%
    String hostname = session.getServletContext().getInitParameter("hostname");
    List<Patient> patients = (List<Patient>) request.getAttribute("patients");
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Liste des Patients - <%=hostname%>
    </title>
</head>
<body>

<jsp:include page="hospital-header.jsp"/>
<main>
    <div>
        <form action="" style="">
            <label for="name">Rechercher un patient :</label>
            <input name="name" id="name"/>
            <button>Valider</button>
        </form>
        <hr/>
        <h2>Ajouter un patient :</h2>
        <% if ((boolean)request.getAttribute("session") && request.getAttribute("mode").equals("add")) {%>

        <%} else {%>
                <button><a>Se Connecter</a></button>
        <%}%>
    </div>
    <hr/>
    <div>
        <h2>Liste des patients :</h2>
    </div>
</main>
<jsp:include page="hospital-footer.jsp"/>
</body>
</html>
