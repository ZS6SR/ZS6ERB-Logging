<%-- 
    Document   : index
    Created on : 29 Aug 2013, 2:03:23 PM
    Author     : Seanr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>ZS6ERB - Logging Software</title>
        <link rel="stylesheet" href="css/menu.css">
        <link rel="stylesheet" href="css/buttons.css">
        <link rel="stylesheet" href="css/summary.css">
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
        <br />
    <center>
        <div class="sumTable">
            <div class="Summary">Summary</div>
            <div class="qWrap">
                <div class="qHead">Total Records</div>
                <div class="uHead">Top User records</div>
            </div>
            <div class="uWrap">
                <% za.co.zs6erb.dao.SummaryDao sd = new za.co.zs6erb.dao.SummaryDao(); %>
                <div class="qTot"><%=sd.getTotalQSO()%></div>
                <div class="uTot" style="color: green;"><%=sd.getTopUser()%></div>
                <div class="uTot"><%=sd.getTopUserQSO()%></div>
            </div>
        </div> 
    </center>
    <jsp:include page="footer.jsp" />
    </body>
</html>


