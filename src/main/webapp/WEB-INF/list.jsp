<%@page import="java.util.List" %>
<%@page import="java.lang.String" %>
<%@page import="com.jeido.hospital.entities.Patient" %>
<%
    String hostname = session.getServletContext().getInitParameter("hostname");
    List<Patient> patients = (List<Patient>) request.getAttribute("patients");
    Patient patient = (Patient) request.getAttribute("patient");
    String mode = (String)request.getAttribute("mode");
    boolean isLogged = session.getAttribute("isLogged") != null && (boolean)session.getAttribute("isLogged");
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
        <form action="<%=(mode.equals("search")? "" : "list/search")%>" method="post">
            <label for="searchedName">Rechercher un patient :</label>
            <input name="searchedName" id="searchedName" type="text"/>
            <button>🔍</button>
        </form>
        <hr/>
        <h2>Ajouter un patient :</h2>
        <% if (isLogged && request.getAttribute("mode").equals("add")) {%>
                <form action="add" method="post">
                    <div>
                        <label for="name">Nom :</label>
                        <input type="text" name="name" id="name" value="<%=patient.getName()%>"  required>
                    </div>
                    <div>
                        <label for="phone">Téléphone :</label>
                        <input type="text" name="phone" id="phone" value="<%=patient.getPhone()%>" required>
                    </div>
                    <div>
                        <label for="birthDate">Date de Naissance :</label>
                        <input type="date" name="birthDate" id="birthDate" value="<%=patient.getBirthDate()%>" required>
                    </div>
                    <hr/>
                    <div>
                        <% if (mode.equals("add")) {%>
                        <button>Ajouter Patient</button>
                        <%}%>
                    </div>
                </form>
        <%} else if (!isLogged){%>
                <a href="${pageContext.request.contextPath}/auth">Se Connecter</a>
        <%} else {%>
                <a href="list/add">Ajouter un Patient</a>
        <%}%>
    </div>
    <hr/>
    <div>
        <h2>Liste des patients :</h2>
        <div class="card">
            <%for (Patient p : patients) {%>
                <div>
                    <p><%=p.getName()%></p>
                    <p><%=p.getPhone()%></p>
                    <a href="detail/<%=p.getId()%>">Details</a>
                </div>
            <%}%>
        </div>
    </div>
</main>
<jsp:include page="hospital-footer.jsp"/>
</body>
</html>
