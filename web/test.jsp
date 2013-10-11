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
    </head>
    <body>
        <h1>Hello World!</h1><br />
        <% UserDao ud = new UserDao(); %> 
        Pass = Bob;
        Get Encrypted Pass = <% ud.getEncryptedPassword("zs6sr", ud.generateSalt()); %>
    </body>
</html>


-- FROM newContacts.Detail.jsp
<%
                    BigDecimal f = new BigDecimal(7.180);
                    BandDao bd = new BandDao();
                    List<Band> bands = bd.getAllBands();

                    for (Band eachBand: bands) {
                        int s = f.compareTo(eachBand.getStartBand());
                        int e = f.compareTo(eachBand.getEndBand());
                        if ( ((s == 0) || (s == 1)) && ((e == 0) || (e == -1)) ) {
                            %>alert("BAND must be: " + <%=eachBand.getband()%> );<%
                        }
                    }
                        
                %>