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
        
        <a href="StationClassController?action=insert" class="myButton">Add Station Type</a>
        <br /><br />
        <table border=1>
            <thead>
                <tr style="background-color: #C2D1E0;">
                    <th style="width: 50px;">Id</th>
                    <th style="width: 200px;">Station Type</th>
                    <th style="width: 100px;">Station Code</th>
                    <th colspan=2 style="width: 200px;">Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${stationClasses}" var="sts">
                    <tr>
                        <td><c:out value="${sts.ID}" /></td>
                        <td style="text-align: center;"><c:out value="${sts.className}" /></td>
                        <td style="text-align: center;"><c:out value="${sts.classLetter}" /></td>
                        <td style="text-align: center;"><a href="StationClassController?action=edit&sc_id=<c:out value="${sts.ID}" />">Update</a></td>
                        <td style="text-align: center;"><a href="StationClassController?action=delete&sc_id=<c:out value="${sts.ID}" />">Delete</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    <jsp:include page="footer.jsp" />
    </body>
</html>
