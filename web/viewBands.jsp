<%-- 
    Document   : index
    Created on : 18 Feb 2013, 5:10:20 PM
    Author     : SeanR
--%>

<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>ZS6ERB - Logging Software</title>
        <link rel="stylesheet" href="css/menu.css">
        <link rel="stylesheet" href="css/buttons.css">
        <script src="sj/jquery.js"></script>
        <script src="sj/modernizr.js"></script>

        <!--[if IE]>
                <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
    </head>
    <body class="no-js">
        <jsp:include page="header.jsp" />
        <jsp:include page="menu.jsp" />
        
        <br /><br />
        <% if (session.getAttribute("uGroup") == "7az") { %>
            <jsp:forward page="/BandsController?action=listBands" />
        <% } else { %>
            <jsp:forward page="/index.jsp" />
        <% } %>
        <jsp:include page="footer.jsp" />
    </body>
</html>
