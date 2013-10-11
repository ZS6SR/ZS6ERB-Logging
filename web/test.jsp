<%-- 
    Document   : test
    Created on : 10 Sep 2013, 1:16:05 PM
    Author     : Seanr
--%>

<%@page import="za.co.zs6erb.dao.UserDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="css/epoch_styles.css" />
<script type="text/javascript" src="sj/epoch_classes.js"></script>
<script type="text/javascript">
/*You can also place this code in a separate file and link to it like epoch_classes.js*/
	var bas_cal,dp_cal,ms_cal;      
window.onload = function () {

	dp_cal  = new Epoch('epoch_popup','popup',document.getElementById('popup_container'));
	
};
</script>

    </head>
    <body>
        <h1>Hello World!</h1><br />
        <h2>Popup</h2>
<form id="placeholder" method="get" action="#">
  <input id="popup_container" type="text" />
</form>

    </body>
</html>