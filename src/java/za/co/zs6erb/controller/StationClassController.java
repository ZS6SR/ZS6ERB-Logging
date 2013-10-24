/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.zs6erb.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import za.co.zs6erb.dao.StationClassDao;
import za.co.zs6erb.model.StationClass;

/**
 *
 * @author SeanR
 */
public class StationClassController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "eStationClasses.jsp";
    private static String LIST_STATIONCLASSES = "listStationClasses.jsp";
    private StationClassDao dao;
    
    public StationClassController() {
        super();
        dao = new StationClassDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward = "";
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("delete")) {
            int scid = Integer.parseInt(request.getParameter("sc_id"));
            dao.deleteStationClass(scid);
            forward = LIST_STATIONCLASSES;
            request.setAttribute("stationClasses", dao.getAllStationClasses());
        } else if (action.equalsIgnoreCase("edit")) {
            forward = INSERT_OR_EDIT;
            int scid = Integer.parseInt(request.getParameter("sc_id"));
            StationClass sc = dao.getSCById(scid);
            request.setAttribute("stationClass", sc);
        } else if (action.equalsIgnoreCase("listStationClasses")) {
            forward = LIST_STATIONCLASSES;
            request.setAttribute("stationClasses", dao.getAllStationClasses());
        } else {
            forward = INSERT_OR_EDIT;
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    //When adding a new record or updating an existing record
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StationClass sc = new StationClass();
        sc.setclassLetter(request.getParameter("stationLetter"));
        sc.setclassName(request.getParameter("stationName"));
        String sc_id = request.getParameter("sc_id");
        if (sc_id == null || sc_id.isEmpty()) {
            dao.addStationClass(sc);
        } else {
            sc.setID(Integer.parseInt(sc_id));
            dao.updateStationClass(sc);
        }
        RequestDispatcher view = request.getRequestDispatcher(LIST_STATIONCLASSES);
        request.setAttribute("stationClasses", dao.getAllStationClasses());
        view.forward(request, response);
    }
}
