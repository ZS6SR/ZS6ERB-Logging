<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%-- 
    Document   : index
    Created on : 18 Feb 2013, 5:10:20 PM
    Author     : SeanR
--%>

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
    <body onload="countdown(year,month,day,hour,minute)" class="no-js">
        <jsp:include page="header.jsp" />
        <jsp:include page="menu.jsp" />
        <jsp:include page="countdown.jsp" />
        
        <a href="PowerController?action=insert" class="myButton">Add Power</a>
        <br /><br />
        <table border=1>
            <thead>
                <tr style="background-color: #C2D1E0;">
                    <th style="width: 50px;">Id</th>
                    <th style="width: 200px;">Power</th>
                    <th style="width: 50px;">Multiplier</th>
                    <th colspan=2 style="width: 200px;">Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${power}" var="p">
                    <tr>
                        <td><c:out value="${p.ID}" /></td>
                        <td><c:out value="${p.powerName}" /></td>
                        <td><c:out value="${p.pmultiplier}" /></td>
                        <td style="text-align: center;"><a href="PowerController?action=edit&power_id=<c:out value="${p.ID}" />">Update</a></td>
                        <td style="text-align: center;"><a href="PowerController?action=delete&power_id=<c:out value="${p.ID}" />">Delete</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    <jsp:include page="footer.jsp" />
    </body>
</html>
