<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Set" %>
<% 
    HashMap<String,Object> liste_session = (HashMap<String,Object>)request.getAttribute("listes");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>test</title>
</head>
<body>
	<h1>Listes Session</h1>
	<ul>
        <% Set<String> keys = liste_session.keySet(); %>
        <% for(String key : keys){ %> 
        <li>
            <% out.print(key); %> : 
            <% out.print(String.valueOf(liste_session.get(key))); %>
        </li>    
        <% } %>
            
	</ul>
</body>
</html>



