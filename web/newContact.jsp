<%-- 
    Document   : allContactsDetail.jsp
    Created on : 02 Sep 2013, 2:40:48 PM
    Author     : Seanr
--%>

<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@page import="java.util.List"%>
<%@page import="za.co.zs6erb.dao.BandDao"%>
<%@page import="za.co.zs6erb.model.Band"%>
<%@page import="za.co.zs6erb.dao.ModeDao"%>
<%@page import="za.co.zs6erb.model.Mode"%>

<%

    BandDao bd = new BandDao();
    List<Band> bands = bd.getAllBands();
    pageContext.setAttribute("bands", bands);
    
    ModeDao md = new ModeDao();
    List<Mode> modes = md.getAllModes();
    pageContext.setAttribute("modes", modes);
    
%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>ZS6ERB - Logging Software</title>
        <link rel="stylesheet" href="css/menu.css">
        <link rel="stylesheet" href="css/buttons.css">
        <script>
            function myStartTime() {
                var now = new Date();
                var datetime = ((now.getDate() < 10) ? ("0" + now.getDate()) : (now.getDate())) 
                    + "/" + (((now.getMonth()+1) < 10) ? ("0" + (now.getMonth()+1)) : now.getMonth()) 
                    + "/" + now.getFullYear() + " " 
                    + now.getHours() + ":" 
                    + ((now.getMinutes() < 10) ? ("0" + now.getMinutes()) : (now.getMinutes())) + ":" 
                    + ((now.getSeconds() < 10) ? ("0" + now.getSeconds()) : (now.getSeconds()));
                document.getElementById("startTime").value = datetime;
            }
        </script>
        <!--[if IE]>
                <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
    </head>
    <body class="no-js">
        <%@ include file="header.jsp" %>
        <%@ include file="menu.jsp" %>
        <br />
        <form method="POST" action='ContactController' name="frmAddContact">
            <table style="border-width: 0px; border: hidden; width: 70%;">
                <tr>
                    <td id="contactCap" style="width: 200px; text-align: right;">Frequency&nbsp;</td>
                    <td style="width: 10px;">&nbsp;</td>
                    <td style="width: 200px;"><input type="text" id="freq" name="freq" style="width:200px; border: solid #C0C0C0; font-size:20px; padding: 4px;"></td>
                </tr><tr>
                    <td id="contactCap" style="width: 200px; text-align: right;">Band&nbsp;</td>
                    <td style="width: 10px;">&nbsp;</td>
                    <td><select name="bandid" id="bandid" style="font-size: 18px; width:200px; border: hidden; padding: 4px;">
                            <c:forEach items="${bands}" var="eachBand" >
                                <option value="<c:out value="${eachBand.ID}" />"> <c:out value="${eachBand.band}"/> </option>
                            </c:forEach>
                        </select>
                    </td>
                </tr><tr>
                    <td id="contactCap" style="width: 200px; text-align: right;">Mode&nbsp;</td>
                    <td style="width: 10px;">&nbsp;</td>
                    <td><select name="modeid" id="modeid" style="font-size: 18px; width:200px; border: hidden; padding: 4px;">
                            <c:forEach items="${modes}" var="eachMode" >
                                <option value="<c:out value="${eachMode.ID}" />"> <c:out value="${eachMode.mode}"/> </option>
                            </c:forEach>
                        </select>
                    </td>
                </tr><tr>
                    <td id="contactCap" style="width: 200px; text-align: right;">Callsign&nbsp;</td>
                    <td style="width: 10px;">&nbsp;</td>
                    <td><input type="text" id="callsign" name="callsign" style="width:200px; border: solid #C0C0C0; font-size:20px; padding: 4px;"></td>
                </tr><tr>
                    <td style="width:200px; text-align: right;">&nbsp;<input type="button" class="myButton" onclick="myStartTime()" value="Start Time" /></td>
                    <td style="width: 10px;">&nbsp;</td>
                    <td><input type="text" id="startTime" name="startTime" readonly style="width:200px; border: solid #C0C0C0; font-size:18px; text-align: center; padding: 4px;"></td>
                </tr><tr>
                    <td id="contactCap" style="width: 200px; text-align: right;">Name&nbsp;</td>
                    <td style="width: 10px;">&nbsp;</td>
                    <td><input type="text" id="name" name="name" style="width:200px; border: solid #C0C0C0; font-size:20px; padding: 4px;"></td>
                </tr><tr>
                    <td id="contactCap" style="width: 200px; text-align: right;">Location (QTH)&nbsp;</td>
                    <td style="width: 10px;">&nbsp;</td>
                    <td><input type="text" id="qth" name="qth" style="width:200px; border: solid #C0C0C0; font-size:20px; padding: 4px;"></td>
                </tr><tr>
                    <td colspan="3" style="text-align: center;"><textarea name="notes" rows="4" cols="60"></textarea></td>
                </tr><tr>
                    <td>&nbsp;</td>
                    <td style="width: 10px;">&nbsp;</td>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td style="width:200px; text-align: center;" colspan="3">&nbsp;<input type="submit" class="myBigButton" value="End and Log" /></td>
                </tr>
            </table>
        </form>
    <%@ include file="footer.jsp" %>
    </body>
</html>
