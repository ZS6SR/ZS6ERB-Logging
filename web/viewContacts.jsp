<%-- 
    Document   : allContactsDetail.jsp
    Created on : 02 Sep 2013, 2:40:48 PM
    Author     : Seanr
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
    <body onload="countdown(year,month,day,hour,minute)" class="no-js">
        <jsp:include page="header.jsp" />
        <jsp:include page="menu.jsp" />
        <jsp:include page="countdown.jsp" />
        
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
                    <th style="width: 80px;">My RST</th>
                    <th style="width: 80px;">His RST</th>
                    <th style="width: 100px;">User</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${contacts}" var="eachContact">
                    <tr style="background-color: #FFFFFF; font-size: small; height: 16px; border-bottom: 1px solid #999; border-left: 1px solid #999;">
                        <td style="text-align: center;">${eachContact.ID}</td>
                        <td style="text-align: center;">${eachContact.callSign}</td>
                        <td style="text-align: center;">${eachContact.startTimeStr}</td>
                        <td style="text-align: center;">${eachContact.endTimeStr}</td>
                        <td style="text-align: center;">${eachContact.freq}</td>
                        <td style="text-align: center;">${bd.getBandById(eachContact.bandId).band}</td>
                        <td style="text-align: center;">${modes.getModeById(eachContact.modeId).mode}</td>
                        <td style="text-align: center;">${eachContact.localRST}</td>
                        <td style="text-align: center;">${eachContact.remoteRST}</td>
                        <td style="text-align: center;">${ud.getUserById(eachContact.userID).callSign}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    <jsp:include page="footer.jsp" />
    </body>
</html>
