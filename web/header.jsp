<header>
    <table border="0" width="100%">
        <tr>
            <td class="headerImg" rowspan="2" style="width: 40px;"><div align=left><a href="http://zs6erb.co.za/"><img src="resources/zs6erb-logo.png" width="80"/></a></div></td>
            <!-- <td class="headerTitle" style="height: 80px;"> -->
            <td class="headerTitle" style="height: 80px;">
                <div style="position: relative;">
                    ZS6ERB - Logging Portal
                    <div id="holder">
                        <div id="timer">
                            <div id="note"></div>
                            <div id="countdown">
                                <img height=21 src="digital-numbers/bkgd.gif" width=16 name="day1">
                                <img height=21 src="digital-numbers/bkgd.gif" width=16 name="day2">
                                <img height=21 src="digital-numbers/bkgd.gif" width=16 name="day3">
                                <img height=21 id="colon1" src="digital-numbers/colon.png" width=9 name="d1">
                                <img height=21 src="digital-numbers/bkgd.gif" width=16 name="h1">
                                <img height=21 src="digital-numbers/bkgd.gif" width=16 name="h2">
                                <img height=21 id="colon2" src="digital-numbers/colon.png" width=9 name="g1">
                                <img height=21 src="digital-numbers/bkgd.gif" width=16 name="m1">
                                <img height=21 src="digital-numbers/bkgd.gif" width=16 name="m2">
                                <img height=21 id="colon3" src="digital-numbers/colon.png" width=9 name="j1">
                                <img height=21 src="digital-numbers/bkgd.gif" width=16 name="s1">
                                <img height=21 src="digital-numbers/bkgd.gif" width=16 name="s2">
                                <div id="title">
                                        <div class="title" style="position: absolute; top: 36px; left: 50px">DAYS</div>
                                        <div class="title" style="position: absolute; top: 36px; left: 104px">HRS</div>
                                        <div class="title" style="position: absolute; top: 36px; left: 161px">MIN</div>
                                        <div class="title" style="position: absolute; top: 36px; left: 220px">SEC</div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </td>
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
