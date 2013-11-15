<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%-- 
    Document   : contests - Used to create a NEW Contest record
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
        <script src="sj/jquery-ui.js"></script>
        <link rel="stylesheet" href="css/jquery-ui-timepicker-addon.css">
        <script src="sj/jquery-ui-timepicker-addon.js"></script>
        <link rel="stylesheet" href="css/jquery-ui.css">
        <script src="sj/modernizr.js"></script>
        <script>
            $(function() {
                $("#contestStartDate").datetimepicker({dateFormat: 'yy-mm-dd', timeFormat: "HH:mm"});
                $("#contestEndDate").datetimepicker({dateFormat: 'yy-mm-dd', timeFormat: "HH:mm"});
            });
        </script>

        <!--[if IE]>
                <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
    </head>
    <body onload="countdown(year, month, day, hour, minute)" class="no-js">
        <jsp:include page="header.jsp" />
        <jsp:include page="menu.jsp" />
        <jsp:include page="countdown.jsp" />
        <br /><br />
        <form method="POST" action='ContestsController' name="frmAddContest">
            <table border="0" style="padding: 3px;">
                <tr>
                    <td style="text-align: right; width: 150px;">ID :</td>
                    <td style="width: 180px;"><input type="text" readonly="readonly" name="contest_id" value="<c:out value="${contest.ID}" />" /></td>
                </tr>
                <tr>
                    <td style="text-align: right; width: 150px;">Contest Name :</td>
                    <td style="width: 180px;"><input type="text" name="contestName" value="<c:out value="${contest.contestName}" />" /></td>
                </tr>
                <tr>
                    <td style="text-align: right; width: 150px;">Start Date :</td>
                    <td style="width: 180px;"><input type="text" name="contestStartDate" id="contestStartDate" value="<c:out value="${contest.startDate}" />" /></td>
                </tr>
                <tr>
                    <td style="text-align: right; width: 150px;">End Date :</td>
                    <td style="width: 180px;"><input type="text" name="contestEndDate" id="contestEndDate" value="<c:out value="${contest.endDate}" />" /></td>
                </tr>
                <tr>
                    <td style="text-align: right; width: 150px;">Default Power :</td>
                    <td style="width: 250px;">
                        <select name="power_id" id="power_id" style="font-size: 12px; width:175px; padding: 4px;">
                            <c:forEach items="${power.getAllPower()}" var="eachPower" >
                                <option value="<c:out value="${eachPower.ID}" />"> <c:out value="${eachPower.powerName}"/> </option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right; width: 150px;">Province :</td>
                    <td style="width: 250px;">
                        <select name="provinceCode_id" id="provinceCode_id" style="font-size: 12px; width:175px; padding: 4px;">
                            <c:forEach items="${provinces.getAllProvinceCode()}" var="eachProv" >
                                <option value="<c:out value="${eachProv.ID}" />"> (<c:out value="${eachProv.provinceCode}"/>) <c:out value="${eachProv.provinceName}"/> </option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right; width: 150px;">Station Class :</td>
                    <td style="width: 350px;">
                        <select name="stationClass_id" id="stationClass_id" style="font-size: 12px; width:175px; padding: 4px;">
                            <c:forEach items="${statClasses.getAllStationClasses()}" var="eachClass" >
                                <option value="<c:out value="${eachClass.ID}" />"> (<c:out value="${eachClass.classLetter}"/>) <c:out value="${eachClass.className}"/> </option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">&nbsp;</td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td style="text-align: left;"><input type="submit" value="Submit" class="myButton"/></td>
                    <!-- <td style="text-align: left;"><input type="reset" value="Return" class="myButton"/></td> -->
                </tr>

            </table>
        </form>
        <jsp:include page="footer.jsp" />
    </body>
</html>