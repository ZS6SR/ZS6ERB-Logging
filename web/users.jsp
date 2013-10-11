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
        <script type="text/javascript" language="JavaScript">
            function checkPwd(theForm) {
                var pwdMatch = false;
                if (theForm.pwd.value === theForm.pwd1.value)
                {
                    pwdMatch = true;
                } else {
                    alert('Your passwords don\'t match!\n\nPlease Try Again!');
                }
                
                var formComplete = false;
                if ((theForm.callsign.value === "") || (theForm.firstname.value === "") || (theForm.lastname.value === "")) {
                    alert('Your form is not Complete!\n\nPlease Try Again!');
                }
                else {
                    formComplete = true;
                }
                
                if (pwdMatch && formComplete) {
                    return true;
                }
                else {
                    return false;
                }
            }
        </script>
    </head>
    <body onload="countdown(year,month,day,hour,minute)" class="no-js">
        <jsp:include page="header.jsp" />
        <jsp:include page="menu.jsp" />
        <jsp:include page="countdown.jsp" />
        <br /><br />
        <form method="POST" action='UsersController' name="frmAddUser" onsubmit="return checkPwd(this);">
            <table border="0" style="padding: 3px;">
                <tr>
                    <td style="text-align: right; width: 150px;">ID :</td>
                    <td style="width: 250px;"><input type="text" readonly="readonly" name="usrid" value="<c:out value="${usr.ID}" />" /></td>
                </tr>
                <tr>
                    <td style="text-align: right; width: 150px;">Callsign :</td>
                    <td style="width: 250px;"><input type="text" name="callsign" value="<c:out value="${usr.callSign}" />" /></td>
                </tr>
                <tr>
                    <td style="text-align: right; width: 150px;">First Name :</td>
                    <td style="width: 250px;"><input type="text" name="firstname" value="<c:out value="${usr.firstName}" />" /></td>
                </tr>
                <tr>
                    <td style="text-align: right; width: 150px;">Last Name :</td>
                    <td style="width: 250px;"><input type="text" name="lastname" value="<c:out value="${usr.lastName}" />" /></td>
                </tr>
                <tr>
                    <td style="text-align: right; width: 150px;">Password :</td>
                    <td style="width: 250px;"><input type="password" name="pwd" value="<c:out value="${usr.pwd}" />" /></td>
                </tr>
                <tr>
                    <td style="text-align: right; width: 150px;">Re-Enter Password :</td>
                    <td style="width: 250px;"><input type="password" name="pwd1" value="" /></td>
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