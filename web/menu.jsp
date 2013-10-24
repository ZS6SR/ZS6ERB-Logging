<nav id="topNav">
    <ul>
        <li><a href="index.jsp" title="Home">Home</a></li>
        <% if ((session.getAttribute("uGroup") == "7za") || (session.getAttribute("uGroup") == "7az")) { %>
            <li><a href="QSOController?action=new" title="Add New QSO">New QSO</a></li>
            <li><a href="#" title="Reports">Reports</a>
                <ul>
                    <li><a href="QSOController?action=listQSOs" title="All QSOs">All QSOs</a></li>
                    <li><a href="report_1.jsp" title="All Contests">All Contests</a></li>
                    <li><a href="report_1.jsp" title="QSO details per User">QSOs per User</a></li>
                    <li class="last"><a href="report_1.jsp" title="Details of Band coverage per Station">Bands per Station</a></li>
                </ul>
            </li>
        <% } 
        
           if (session.getAttribute("uGroup") == "7az") { %>
                <li><a href="#" title="Admin">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Admin&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
                    <ul>
                        <li><a href="ContestsController?action=listContests" title="Contests">Contests</a></li>
                        <li><a href="UsersController?action=listUsers" title="Users">Users</a></li>
                        <li><a href="BandsController?action=listBands" title="Bands">Bands</a></li>
                        <li><a href="ModesController?action=listModes" title="Modes">Modes</a></li>
                        <li><a href="PowerController?action=listPower" title="Power">Power</a></li>
                        <li><a href="ProvinceCodesController?action=listProvinces" title="Provinces">Provinces</a></li>
                        <li class="last"><a href="StationClassController?action=listStationClasses" title="Station Types">Station Types</a></li>
                    </ul>
                </li>
        <% } %>
    </ul>
    
</nav>
