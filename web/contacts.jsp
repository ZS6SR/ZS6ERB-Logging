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
        <script src="sj/jquery.js"></script>
        <link rel="stylesheet" href="css/menu.css">
        <link rel="stylesheet" href="css/buttons.css">
        <script src="sj/jquery.js"></script>
        <script src="sj/modernizr.js"></script>
        
        <script type="text/javascript">
            //<!--
            $(document).ready(function(){
                $("#modeid").change(function(){
                    if ("4" === this.value) {
                        $("#localT").show();
                        $("#remoteT").show();
                    }
                    else {
                        $("#localT").hide();
                        $("#remoteT").hide();
                    }
                });
                
                $("#callsign").blur(function(){
                    var csToCheck = $("#callsign").val().toString().toUpperCase();
                    var currentMode = parseInt($("#modeid").val());
                    var csm = ${cs};
                    
                    var keysArray = new Array();
                    for (var key in csm) {
                        keysArray.push(key);
                    }  

                    var i = 0;
                    while (i < keysArray.length) {
                        if ( (csToCheck === keysArray[i]) && (currentMode === csm[keysArray[i]]) ) {
                            alert("\t**WARNING**\n\nYou have worked \"" + csToCheck + "\" using this mode previously!");
                            break;
                        }
                        i++;
                    }
                });
                
                
                $("#freq").change(function() {
                    
                    var bandArray = ${bd};
                    var bandName  = "Not a Valid Frequency";
                    var bandId    = "1";
                    
                    var floatVal = parseFloat($("#freq").val());
                    var bandFound = false;
                    var bandSize = bandArray.length;
                    var index = 0;
                    while (index < bandSize && !bandFound) {
                        var bandObject = bandArray[index++];
                        if (floatVal >= bandObject.startBand && floatVal <= bandObject.endBand) {
                            bandFound = true;
                            bandName = bandObject.band;
                            bandId   = bandObject.band_id;
                        }
                    }
                    document.getElementById("band").value = bandName;
                    document.getElementById("bandid").value = bandId;
                    
                });                
            });

            function myStartTime() {
                
                var now = new Date();
                var datetime = ((now.getUTCDate() < 10) ? ("0" + now.getUTCDate()) : (now.getUTCDate())) 
                    + "/" + (((now.getUTCMonth()+1) < 10) ? ("0" + (now.getUTCMonth()+1)) : now.getUTCMonth()+1) 
                    + "/" + now.getUTCFullYear() + " " 
                    + now.getUTCHours() + ":" 
                    + ((now.getUTCMinutes() < 10) ? ("0" + now.getUTCMinutes()) : (now.getUTCMinutes())) + ":" 
                    + ((now.getUTCSeconds() < 10) ? ("0" + now.getUTCSeconds()) : (now.getUTCSeconds()));
                document.getElementById("startTime").value = datetime;
            }
            //-->
        </script>
        <!--[if IE]>
                <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
    </head>
    <body onload="countdown(year,month,day,hour,minute)" class="no-js">
        <jsp:include page="header.jsp" />
        <jsp:include page="menu.jsp" />
        <jsp:include page="countdown.jsp" />
        <br />
        <form method="POST" action='ContactController' name="frmAddContact">
            <table style="border-width: 0px; border: hidden; width: 70%;">
                <tr>
                    <td id="contactCap" style="width: 200px; text-align: right;">Frequency&nbsp;</td>
                    <td style="width: 10px;">&nbsp;</td>
                    <td style="width: 200px;"><input type="text" id="freq" name="freq" tabindex="1" style="width:200px; border: solid #C0C0C0; font-size:20px; padding: 4px;"></td>
                </tr><tr>
                    <td id="contactCap" style="width: 200px; text-align: right;">Band&nbsp;</td>
                    <td style="width: 10px;">&nbsp;</td>
                    <td>
                        <input type="text" id="band" name="band" readonly style="width:200px; border: solid #C0C0C0; font-size:18px; padding: 4px;">
                        <input type="hidden" id="bandid" name="bandid" value="1" />
                    </td>
                </tr><tr>
                    <td id="contactCap" style="width: 200px; text-align: right;">Mode&nbsp;</td>
                    <td style="width: 10px;">&nbsp;</td>
                    <td><select name="modeid" id="modeid" tabindex="2" style="font-size: 18px; width:200px; border: hidden; padding: 4px;">
                            <c:forEach items="${modes.getAllModes()}" var="eachMode" >
                                <option value="<c:out value="${eachMode.ID}" />"> <c:out value="${eachMode.mode}"/> </option>
                            </c:forEach>
                        </select>
                    </td>
                </tr><tr>
                    <td id="contactCap" style="width: 200px; text-align: right;">Callsign&nbsp;</td>
                    <td style="width: 10px;">&nbsp;</td>
                    <td><input type="text" id="callsign" name="callsign" tabindex="3" style="width:200px; border: solid #C0C0C0; font-size:20px; padding: 4px;"></td>
                </tr><tr>
                    <td style="width:200px; text-align: right;">&nbsp;<input type="button" class="myButton" tabindex="4" onclick="myStartTime()" value="Start Time" /></td>
                    <td style="width: 10px;">&nbsp;</td>
                    <td><input type="text" id="startTime" name="startTime" readonly style="width:200px; border: solid #C0C0C0; font-size:18px; text-align: center; padding: 4px;"></td>
                </tr><tr>
                    <td id="contactCap" style="width: 200px; text-align: right;">Name&nbsp;</td>
                    <td style="width: 10px;">&nbsp;</td>
                    <td><input type="text" id="name" name="name" tabindex="5" style="width:200px; border: solid #C0C0C0; font-size:20px; padding: 4px;"></td>
                </tr><tr>
                    <td id="contactCap" style="width: 200px; text-align: right;">Local (My) RST&nbsp;</td>
                    <td style="width: 10px;">&nbsp;</td>
                    <td id="contactCap" style="width: 200px; text-align: left;">
                        <select name="localR" id="localR">
                            <c:forEach var="i" begin="1" end="4">
                                <option value="${i}">${i}</option>
                            </c:forEach>
                            <option value="5" selected="1">5</option>
                        </select>
                        <select name="localS" id="localS">
                            <c:forEach var="i" begin="1" end="8">
                                <option value="${i}">${i}</option>
                            </c:forEach>
                            <option value="9" selected="1">9</option>
                        </select>
                        <select name="localT" id="localT" hidden="1">
                            <c:forEach var="i" begin="1" end="8">
                                <option value="${i}">${i}</option>
                            </c:forEach>
                            <option value="9" selected="1">9</option>
                        </select>
                    </td>
                </tr><tr>
                    <td id="contactCap" style="width: 200px; text-align: right;">Remote (His) RST&nbsp;</td>
                    <td style="width: 10px;">&nbsp;</td>
                    <td id="contactCap" style="width: 200px; text-align: left;">
                        <select name="remoteR" id="remoteR">
                            <c:forEach var="i" begin="1" end="4">
                                <option value="${i}">${i}</option>
                            </c:forEach>
                            <option value="5" selected="1">5</option>
                        </select>
                        <select name="remoteS" id="remoteS">
                            <c:forEach var="i" begin="1" end="8">
                                <option value="${i}">${i}</option>
                            </c:forEach>
                            <option value="9" selected="1">9</option>
                        </select>
                        <select name="remoteT" id="remoteT" hidden="1">
                            <c:forEach var="i" begin="1" end="8">
                                <option value="${i}">${i}</option>
                            </c:forEach>
                            <option value="9" selected="1">9</option>
                            </select>
                    </td>
                </tr><tr>
                    <td id="contactCap" style="width: 200px; text-align: right;">Location (QTH)&nbsp;</td>
                    <td style="width: 10px;">&nbsp;</td>
                    <td><input type="text" id="qth" name="qth" tabindex="6" style="width:200px; border: solid #C0C0C0; font-size:20px; padding: 4px;"></td>
                </tr><tr>
                    <td colspan="3" style="text-align: center;"><textarea name="notes" rows="4" cols="60"></textarea></td>
                </tr><tr>
                    <td>&nbsp;</td>
                    <td style="width: 10px;">&nbsp;</td>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td style="width:250px; text-align: right;" colspan="2"><input type="submit"  tabindex="7" class="myBigButton" value="End and Log" />&nbsp;</td>
                    <td style="width:150px; text-align: left;">&nbsp;&nbsp;&nbsp;<input type="reset" class="myBigButton" value="Reset" /></td>
                </tr>
            </table>
        </form>
    <jsp:include page="footer.jsp" />
    </body>
</html>
