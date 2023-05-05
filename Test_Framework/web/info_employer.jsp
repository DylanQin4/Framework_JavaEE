<%@ page import="modele.Employer" %>
<% 
    Employer employer = (Employer)request.getAttribute("employer");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>test</title>
</head>
<body>

	<h1>Informations Employer</h1>	
    <% out.print(employer.getId()); %>
    <% out.print(employer.getNom()); %>
    <% out.print(employer.getDate()); %>
    <% for(int i=0 ; i<employer.getDiplome().length ; i++){ %>
        <% out.print(employer.getDiplome()[i]); %>
    <% } %>

</body>
</html>
