<header>
    <table border="0" width="100%">
        <tr>
            <td class="headerImg" rowspan="2" style="width: 40px;"><div align=left><a href="http://zs6erb.co.za/"><img src="resources/zs6erb-logo.png" width="80"/></a></div></td>
            <td class="headerTitle" style="height: 80px;">ZS6ERB - Logging Portal</td>
        </tr>
        <tr>
            <%
                if ((session.getAttribute("callsign") == null) || (session.getAttribute("callsign") == "")) {
            %>
            <form action="loginController" method="post">
                <td style="text-align: right;">
                    Callsign : &nbsp;
                    <input type="text" name="callsign"> &nbsp;
                    Password : &nbsp;
                    <input type="password" name="password"> &nbsp;
                    <input type="submit" value="Login" class="myButton">
                </td>
            </form>
            <%  } else {
            %>
                <td style="text-align: right;">
                    <form action="logout.jsp" method="post">
                    Welcome <b><%= session.getAttribute("callsign").toString().toUpperCase() %></b> &nbsp;
                    <input type="submit" value="Logout" class="myButton"></form>
                </td>
            <%  } %>
        </tr>
    </table>    
</header>
