/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.zs6erb.controller;

import com.google.gson.Gson;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import za.co.zs6erb.dao.BandDao;
import za.co.zs6erb.dao.ContestDao;
import za.co.zs6erb.dao.QSODao;
import za.co.zs6erb.dao.ModeDao;
import za.co.zs6erb.dao.UserDao;
import za.co.zs6erb.model.QSO;

/**
 *
 * @author SeanR
 */
public class QSOController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "newQsos.jsp";
    private static String LIST_QSOS = "listQSOs.jsp";
    private QSODao dao;
    private BandDao bDao;
    private UserDao uDao;
    private ModeDao mDao;
    
    public QSOController() {
        super();
        dao = new QSODao();
        bDao = new BandDao();
        uDao = new UserDao();
        mDao = new ModeDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward = "";
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("delete")) {
            int qso_id = Integer.parseInt(request.getParameter("qso_id"));
            dao.deleteQSO(qso_id);
            forward = LIST_QSOS;
            request.setAttribute("qsos", dao.getAllQSOs());
        } else if (action.equalsIgnoreCase("edit")) {
            //====================================================
            //Change this when you have done the update functions
            //====================================================
            forward = LIST_QSOS;
            request.setAttribute("qsos", dao.getAllQSOs());
        } else if (action.equalsIgnoreCase("listQSOs")) {
            forward = LIST_QSOS;
            request.setAttribute("qsos", dao.getAllQSOs());
            request.setAttribute("bd", bDao);
            request.setAttribute("ud", uDao);
            request.setAttribute("modes", mDao);
            
        } else {
            forward = INSERT_OR_EDIT;
            request.setAttribute("bd", new Gson().toJson(bDao.getAllBands(), List.class));
            request.setAttribute("csbd", new Gson().toJson(dao.getAllCS_Bands(), HashMap.class));
            request.setAttribute("cs", new Gson().toJson(dao.getAllCS_Modes(), HashMap.class));
            request.setAttribute("modes", mDao);
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    //When adding a new record or updating an existing record
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        Calendar c = Calendar.getInstance();
        TimeZone z = c.getTimeZone();
        int offset = z.getRawOffset();
        if(z.inDaylightTime(new Date())){
            offset = offset + z.getDSTSavings();
        }
        int offsetHrs = offset / 1000 / 60 / 60;
        int offsetMins = offset / 1000 / 60 % 60;

        c.add(Calendar.HOUR_OF_DAY, (-offsetHrs));
        c.add(Calendar.MINUTE, (-offsetMins));

        Date date = c.getTime();
        Timestamp tDate = new Timestamp(date.getTime());
        Timestamp tsDate = null;
        
        SimpleDateFormat formatter; //Example: 2012-03-04 23:11:32
        
        formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date sDate;
        try {
            sDate = formatter.parse(request.getParameter("startTime"));
            tsDate = new Timestamp(sDate.getTime());
            //System.out.println("START TIME = " + tsDate.toString());
        } catch (ParseException ex) {
            System.out.println("ERROR Parsing startdate " + ex.getMessage());
        }
        
        String sFreq = request.getParameter("freq");
        String sCallsign = request.getParameter("callsign");
        
        if ((sFreq.isEmpty()) || (sCallsign.isEmpty())) {
            System.out.println("ERROR - I MUSTN'T ADD THIS RECORD");
        }
        else {
        
            QSO qso = new QSO();

            HttpSession ss = request.getSession();
            qso.setuserID(Integer.parseInt(ss.getAttribute("userId").toString()));
            qso.setstartTime(tsDate);
            qso.setendTime(tDate);
            qso.setcallSign(sCallsign.toUpperCase());
            qso.setname(request.getParameter("name"));
            qso.setlocation(request.getParameter("qth"));
            qso.setfreq(sFreq);
            int bandId = Integer.parseInt(request.getParameter("bandid"));
            qso.setbandId(bandId);
            int modeId = Integer.parseInt(request.getParameter("modeid"));
            qso.setmodeId(modeId);

            qso.setcpowerId(1);

            String lR = request.getParameter("localR");
            String lS = request.getParameter("localS");
            String lT = request.getParameter("localT");
            String rR = request.getParameter("remoteR");
            String rS = request.getParameter("remoteS");
            String rT = request.getParameter("remoteT");

            String lRST, rRST;
            if (modeId == 3) {
                lRST = (lR + lS + lT);
                rRST = (rR + rS + rT);
            } else {
                lRST = (lR + lS);
                rRST = (rR + rS);
            }
            qso.setlocalRST(Integer.parseInt(lRST));
            qso.setremoteRST(Integer.parseInt(rRST));

            qso.setnotes(request.getParameter("notes"));
            
            ContestDao cdao = new ContestDao();
            int contId = cdao.getcurrentContestID();
            System.out.println("ContestID = " + contId);
            qso.setcontestID(contId);

            dao.addQSO(qso);
            
        }
        RequestDispatcher view = request.getRequestDispatcher(LIST_QSOS);
        request.setAttribute("qsos", dao.getAllQSOs());
        request.setAttribute("bd", bDao);
        request.setAttribute("ud", uDao);
        request.setAttribute("modes", mDao);
        request.setAttribute("csbd", new Gson().toJson(dao.getAllCS_Bands(), HashMap.class));
        request.setAttribute("cs", new Gson().toJson(dao.getAllCS_Modes(), HashMap.class));
        view.forward(request, response);
        
    }
}
