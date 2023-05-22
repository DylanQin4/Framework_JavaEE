<%-- 
    Document   : test.jsp
    Created on : 20 juin 2023, 08:58:41
    Author     : hp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="sign_up" method="post" enctype="multipart/form-data">
            User Name : <input type="text" name="userName">
            <br>
            Password : <input type="password" name="password">
            <br>
            <input type="submit" value="Valider">
        </form>
    </body>
</html>
