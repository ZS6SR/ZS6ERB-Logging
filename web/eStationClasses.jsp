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
        
        <br /><br />
        <form method="POST" action='StationClassController' name="frmAddSC">
            <table border="0" style="padding: 3px;">
                <tr>
                    <td style="text-align: right; width: 150px;">ID :</td>
                    <td style="width: 250px;"><input type="text" readonly="readonly" name="sc_id" value="<c:out value="${stationClass.ID}" />" /></td>
                </tr>
                <tr>
                    <td style="text-align: right; width: 150px;">Station Type :</td>
                    <td style="width: 250px;"><input type="text" name="stationName" value="<c:out value="${stationClass.stationName}" />" /></td>
                </tr>
                <tr>
                    <td style="text-align: right; width: 150px;">Station Code :</td>
                    <td style="width: 250px;"><input type="text" name="stationLetter" value="<c:out value="${stationClass.stationLetter}" />" /></td>
                </tr>
                <tr>
                    <td colspan="2">&nbsp;</td>
                </tr>
                <tr>
                    <td style="text-align: right;"><input type="submit" value="Submit" class="myButton"/></td>
                    <!-- <td style="text-align: left;"><input type="reset" value="Return" class="myButton"/></td> -->
                </tr>
            
            </table>
        </form>
        <jsp:include page="footer.jsp" />
    </body>
</html>