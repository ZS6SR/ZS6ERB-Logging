<%
    session.setAttribute("callsign", null);
    session.setAttribute("uGroup", null);
    session.invalidate();
    response.sendRedirect("index.jsp");
%>