<%-- 
    Document   : login
    Created on : Oct 3, 2017, 1:21:05 PM
    Author     : 715060
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <h1>Remember Me Login Page</h1>
        <form action="login" method ="post">
        Username: <input type="text" name ="username" value =${username}>
        <br>
        Password: <input type ="text" name ="password">
        <br>
        <input type ="submit" value ="Login">
        <br>
        <input type ="checkbox" name ="remember">Remember Me
        </form>
        <br>
        ${noInput}
        ${invalidLogin}
    </body>
</html>
