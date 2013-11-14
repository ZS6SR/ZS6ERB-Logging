/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.zs6erb.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import za.co.zs6erb.dao.ContestDao;
import za.co.zs6erb.dao.ProvinceCodeDao;
import za.co.zs6erb.dao.StationClassDao;
import za.co.zs6erb.model.Contest;

/**
 *
 * @author SeanR
 */
public class ContestsController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "eContests.jsp";
    private static String LIST_CONTESTS = "listContests.jsp";
    private ContestDao dao;
    private ProvinceCodeDao pdao;
    private StationClassDao sType;
    
    public ContestsController() {
        super();
        dao = new ContestDao();
        pdao = new ProvinceCodeDao();
        sType = new StationClassDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward = "";
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("delete")) {
            int contest_id = Integer.parseInt(request.getParameter("contest_id"));
            dao.deleteContest(contest_id);
            forward = LIST_CONTESTS;
            request.setAttribute("contests", dao.getAllContests());
        } else if (action.equalsIgnoreCase("edit")) {
            forward = INSERT_OR_EDIT;
            int contest_id = Integer.parseInt(request.getParameter("contest_id"));
            Contest contest = dao.getContestById(contest_id);
            request.setAttribute("contest", contest);
            request.setAttribute("provinces", pdao);
            request.setAttribute("statClasses", sType);
        } else if (action.equalsIgnoreCase("listContests")) {
            forward = LIST_CONTESTS;
            request.setAttribute("contests", dao.getAllContests());
            request.setAttribute("provinces", pdao);
            request.setAttribute("statClasses", sType);
        } else {
            forward = INSERT_OR_EDIT;
            request.setAttribute("provinces", pdao);
            request.setAttribute("statClasses", sType);
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    //When adding a new record or updating an existing record
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        Contest contest = new Contest();
    
        SimpleDateFormat formatter; //Example: 2012-03-04 23:11:32
        formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        
        Date sDate, eDate;
        try {
            sDate = formatter.parse(request.getParameter("contestStartDate"));
            eDate = formatter.parse(request.getParameter("contestEndDate"));
            
            if (sDate.before(eDate)) {
                contest.setstartDate(sDate);
                contest.setendDate(eDate);
                
                contest.setcontestName(request.getParameter("contestName"));
        
                String contest_id = request.getParameter("contest_id");
                //use power Id and insert it into db
                contest.setprovinceCode(Integer.parseInt(request.getParameter("provinceCode_id")));
                
                //use Station Class Id and insert it into db
                contest.setstationClass(Integer.parseInt(request.getParameter("stationClass_id")));
                
                if (contest_id == null || contest_id.isEmpty()) {
                    dao.addContest(contest);
                } else {
                    contest.setID(Integer.parseInt(contest_id));
                    dao.updateContest(contest);
                }
                RequestDispatcher view = request.getRequestDispatcher(LIST_CONTESTS);
                request.setAttribute("contests", dao.getAllContests());
                request.setAttribute("provinces", pdao);
                request.setAttribute("statClasses", sType);
                view.forward(request, response);
            }
            else {
                System.out.println("Startdate:" + sDate.toString() + "Cannot be AFTER endDate:" + eDate.toString());
                RequestDispatcher view = request.getRequestDispatcher(LIST_CONTESTS);
                request.setAttribute("contests", dao.getAllContests());
                request.setAttribute("provinces", pdao);
                request.setAttribute("statClasses", sType);
                view.forward(request, response);
            }
        } catch (ParseException ex) {
            System.out.println("ERROR Parsing startdate " + ex.getMessage());
            RequestDispatcher view = request.getRequestDispatcher(LIST_CONTESTS);
            request.setAttribute("contests", dao.getAllContests());
            request.setAttribute("provinces", pdao);
            request.setAttribute("statClasses", sType);
            view.forward(request, response);
        }
    }
}
