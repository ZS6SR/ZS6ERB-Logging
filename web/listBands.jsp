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
        
        <a href="BandsController?action=insert" class="myButton">Add Band</a>
        <br /><br />
        <table border=1>
            <thead>
                <tr style="background-color: #C2D1E0;">
                    <th style="width: 50px;">Id</th>
                    <th style="width: 100px;">Band</th>
                    <th style="width: 100px;">Start Band</th>
                    <th style="width: 100px;">End Band</th>
                    <th colspan=2 style="width: 200px;">Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${bands}" var="b">
                    <tr>
                        <td><c:out value="${b.ID}" /></td>
                        <td style="text-align: center;"><c:out value="${b.band}" /></td>
                        <td style="text-align: center;"><c:out value="${b.startBand}" /> Mhz</td>
                        <td style="text-align: center;"><c:out value="${b.endBand}" /> Mhz</td>
                        <td style="text-align: center;"><a href="BandsController?action=edit&band_id=<c:out value="${b.ID}" />">Update</a></td>
                        <td style="text-align: center;"><a href="BandsController?action=delete&band_id=<c:out value="${b.ID}" />">Delete</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    <jsp:include page="footer.jsp" />
    </body>
</html>
