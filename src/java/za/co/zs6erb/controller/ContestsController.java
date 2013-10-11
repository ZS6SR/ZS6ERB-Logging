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
import za.co.zs6erb.model.Contest;

/**
 *
 * @author SeanR
 */
public class ContestsController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "contests.jsp";
    private static String LIST_CONTESTS = "listContests.jsp";
    private ContestDao dao;
    
    public ContestsController() {
        super();
        dao = new ContestDao();
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
        } else if (action.equalsIgnoreCase("listContests")) {
            forward = LIST_CONTESTS;
            request.setAttribute("contests", dao.getAllContests());
        } else {
            forward = INSERT_OR_EDIT;
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    //When adding a new record or updating an existing record
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        Contest contest = new Contest();
        
        //TODO: Check that the end date is after the start date otherwise return error
        
        SimpleDateFormat formatter; //Example: 2012-03-04 23:11:32
        formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        System.out.println("Start Date:" + request.getParameter("contestStartDate"));
        
        Date sDate, eDate;
        try {
            sDate = formatter.parse(request.getParameter("contestStartDate"));
            eDate = formatter.parse(request.getParameter("contestEndDate"));
            contest.setstartDate(sDate);
            contest.setendDate(eDate);
        } catch (ParseException ex) {
            System.out.println("ERROR Parsing startdate " + ex.getMessage());
            RequestDispatcher view = request.getRequestDispatcher(LIST_CONTESTS);
            request.setAttribute("contests", dao.getAllContests());
            view.forward(request, response);
        }
        
        contest.setcontestName(request.getParameter("contestName"));
        
        String contest_id = request.getParameter("contest_id");
        
        if (contest_id == null || contest_id.isEmpty()) {
            dao.addContest(contest);
        } else {
            contest.setID(Integer.parseInt(contest_id));
            dao.updateContest(contest);
        }
        RequestDispatcher view = request.getRequestDispatcher(LIST_CONTESTS);
        request.setAttribute("contests", dao.getAllContests());
        view.forward(request, response);
    }
}
