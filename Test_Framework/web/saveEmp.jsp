<%@ page import="modele.Employer" %>
<% 
    Employer emp = (Employer)request.getAttribute("employer");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>test</title>
</head>
<body>

	<h5>Ajout Employer</h5>
	<p>
        <%= emp.getId() %>
        <%= emp.getNom() %>
        <%= emp.getDate() %>
        <% for(int i=0 ; i<emp.getDiplome().length ; i++){ %>   
            <%= emp.getDiplome()[i] %>
        <% } %>
        <% for(int i=0 ; i<emp.getChoix().length ; i++){ %>   
            <%= emp.getChoix()[i] %>
        <% } %>
        <%= emp.getImage().getName() %>
        <%= emp.getImage().getBytes().length %>
    </p>
</body>
</html>



