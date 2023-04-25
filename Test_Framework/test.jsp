<%@ page import="modele.Employer" %>
<% 
    Employer[] list_emp = (Employer[])request.getAttribute("listes");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>test</title>
</head>
<body>

	<h1>Listes Employer</h1>
	<% for(int j=0;j<list_emp.length ;j++){ %>
		<% out.print(list_emp[j].getId()); %>
		<% out.print(list_emp[j].getNom()); %>
		<br>
	<% } %>
</body>
</html>



