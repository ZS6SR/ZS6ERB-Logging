<%-- 
    Document   : allContactsDetail.jsp
    Created on : 02 Sep 2013, 2:40:48 PM
    Author     : Seanr
--%>

<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@page import="java.util.List"%>
<%@page import="za.co.zs6erb.dao.ContactDao"%>
<%@page import="za.co.zs6erb.dao.UserDao"%>
<%@page import="za.co.zs6erb.dao.BandDao"%>
<%@page import="za.co.zs6erb.dao.ModeDao"%>
<%@page import="za.co.zs6erb.model.Contact"%>

<%

    ContactDao cd = new ContactDao();
    List<Contact> contacts = cd.getAllContacts();
    pageContext.setAttribute("contacts", contacts);
    
    UserDao ud = new UserDao();
    BandDao bd = new BandDao();
    ModeDao md = new ModeDao();
    
%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>ZS6ERB - Logging Software</title>
        <link rel="stylesheet" href="css/menu.css">
        <link rel="stylesheet" href="css/buttons.css">
        <!--[if IE]>
                <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
    </head>
    <body class="no-js">
        <%@ include file="header.jsp" %>
        <%@ include file="menu.jsp" %>
        <table style="border-collapse: collapse; background-color: #C2D1E0;" width="100%" >
            <thead>
                <tr style="border: 1px solid #999; background-color: #C2D1E0;">
                    <th style="width: 80px;">Contact ID</th>
                    <th style="width: 100px;">Callsign</th>
                    <th style="width: 150px;">Start</th>
                    <th style="width: 150px;">End</th>
                    <th style="width: 80px;">Frequency</th>
                    <th style="width: 80px;">Band</th>
                    <th style="width: 80px;">Mode</th>
                    <th style="width: 100px;">User</th>
                </tr>
            </thead>
            <tbody>
                <% for (Contact eachContact: contacts) { %>
                    <tr style="background-color: #FFFFFF; font-size: small; height: 16px; border-bottom: 1px solid #999; border-left: 1px solid #999;">
                        <td style="text-align: center;"><%=eachContact.getID()%></td>
                        <td style="text-align: center;"><%=eachContact.getcallSign() %></td>
                        <td style="text-align: center;"><%=eachContact.getstartTime() %></td>
                        <td style="text-align: center;"><%=eachContact.getendTime() %></td>
                        <td style="text-align: center;"><%=eachContact.getfreq() %></td>
                        <td style="text-align: center;"><%=bd.getBandById(eachContact.getbandId()).getband() %></td>
                        <td style="text-align: center;"><%=md.getModeById(eachContact.getmodeId()).getmode() %></td>
                        <td style="text-align: center;"><%=ud.getUserById(eachContact.getuserID()).getcallSign() %></td>
                    </tr>
                <% } %>
            </tbody>
        </table>
    <%@ include file="footer.jsp" %>
    </body>
</html>
