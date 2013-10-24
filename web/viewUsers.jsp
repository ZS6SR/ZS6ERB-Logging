<%-- 
    Document   : index
    Created on : 18 Feb 2013, 5:10:20 PM
    Author     : SeanR
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>ZS6ERB - Logging Software</title>
        <link rel="stylesheet" href="css/menu.css">
        <!--[if IE]>
                <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
    </head>
    <body class="no-js">
        <%@ include file="header.jsp" %>
        <%@ include file="menu.jsp" %>
        <br /><br />
        <% if (session.getAttribute("uGroup") == "7az") { %>
            <jsp:forward page="/UsersController?action=listUsers" />
        <% } else { %>
            <jsp:forward page="/index.jsp" />
        <% } %>
    <%@ include file="footer.jsp" %>
    </body>
</html>
