<%@page import="java.util.List" %>
<%@page import="java.lang.String" %>
<%@page import="com.jeido.hospital.entities.Patient" %>
<%
    String hostname = session.getServletContext().getInitParameter("hostname");
    List<Patient> patients = (List<Patient>) request.getAttribute("patients");
    Patient patient = (Patient) request.getAttribute("patient");
    String mode = (String)request.getAttribute("mode");
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
        <form action="<%=!mode.equals("add")? "search" : ""%>" method="post">
            <label for="searchedName">Rechercher un patient :</label>
            <input name="searchedName" id="searchedName" type="text"/>
            <button>🔍</button>
        </form>
        <hr/>
        <h2>Ajouter un patient :</h2>
        <% if ((boolean)request.getAttribute("session") && request.getAttribute("mode").equals("add")) {%>
                <form action="<%=mode.equals("add") ? "add" : ""%>" method="post">
                    <div>
                        <label for="name">Nom :</label>
                        <input type="text" name="name" id="name" value="<%=patient.getName()%>" <% if(mode.equals("add")) {%> required <% } else { %> readonly <% } %>>
                    </div>
                    <div>
                        <label for="phone">Téléphone :</label>
                        <input type="text" name="phone" id="phone" value="<%=patient.getPhone()%>" <% if(mode.equals("add")) { %> required <% } else { %> readonly <% } %>>
                    </div>
                    <div>
                        <label for="dateOfBirth">Téléphone :</label>
                        <input type="text" name="dateOfBirth" id="dateOfBirth" value="<%=patient.getBirthDate()%>" <% if(mode.equals("add")) { %> required <% } else { %> readonly <% } %>>
                    </div>
                    <hr/>
                    <div>
                        <% if (mode.equals("add")) {%>
                        <button>Ajouter Patient</button>
                        <%}%>
                    </div>
                </form>
        <%} else {%>
                <a href="${pageContext.request.contextPath}/auth">Se Connecter</a>
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
