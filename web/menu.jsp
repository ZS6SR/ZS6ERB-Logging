<nav id="topNav">
    <ul>
        <li><a href="index.jsp" title="Home">Home</a></li>
        <% if ((session.getAttribute("uGroup") == "7za") || (session.getAttribute("uGroup") == "7az")) { %>
            <li><a href="QSOController?action=new" title="qso">New QSO</a></li>
            <li><a href="#" title="Reports">Reports</a>
                <ul>
                    <li><a href="QSOController?action=listQSOs" title="AllQSOs">All QSOs</a></li>
                    <li><a href="report_1.jsp" title="Report2">Report 2</a></li>
                    <li class="last"><a href="report_1.jsp" title="Report4">Report 3</a></li>
                </ul>
            </li>
        <% } 
        
           if (session.getAttribute("uGroup") == "7az") { %>
                <li><a href="#" title="Admin">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Admin&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
                    <ul>
                        <li><a href="viewContests.jsp" title="contests">Contests</a></li>
                        <li><a href="viewUsers.jsp" title="users">Users</a></li>
                        <li><a href="viewBands.jsp" title="bands">Bands</a></li>
                        <li><a href="viewModes.jsp" title="modes">Modes</a></li>
                        <li class="last"><a href="viewPower.jsp" title="power">Power</a></li>
                    </ul>
                </li>
        <% } %>
    </ul>
    
</nav>
