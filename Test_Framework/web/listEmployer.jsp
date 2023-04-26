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
	<ul>
		<% for(int j=0;j<list_emp.length ;j++){ %>
			<li>
				<% out.print(list_emp[j].getNom()); %>
				<a href="employer_by_Id?id=<% out.print(list_emp[j].getId()); %>"> Details </a>
			</li>
		<% } %>
	</ul>
</body>
</html>



