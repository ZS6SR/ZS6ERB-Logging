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

import za.co.zs6erb.dao.ModeDao;
import za.co.zs6erb.model.Mode;

/**
 *
 * @author SeanR
 */
public class ModesController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "modes.jsp";
    private static String LIST_MODES = "listModes.jsp";
    private ModeDao dao;
    
    public ModesController() {
        super();
        dao = new ModeDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward = "";
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("delete")) {
            int mid = Integer.parseInt(request.getParameter("modeid"));
            dao.deleteMode(mid);
            forward = LIST_MODES;
            request.setAttribute("modes", dao.getAllModes());
        } else if (action.equalsIgnoreCase("edit")) {
            forward = INSERT_OR_EDIT;
            int mid = Integer.parseInt(request.getParameter("modeid"));
            Mode m = dao.getModeById(mid);
            request.setAttribute("mode", m);
        } else if (action.equalsIgnoreCase("listModes")) {
            forward = LIST_MODES;
            request.setAttribute("modes", dao.getAllModes());
        } else {
            forward = INSERT_OR_EDIT;
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    //When adding a new record or updating an existing record
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Mode m = new Mode();
        m.setmode(request.getParameter("mode"));
        String mid = request.getParameter("mode_id");
        if (mid == null || mid.isEmpty()) {
            dao.addMode(m);
        } else {
            m.setID(Integer.parseInt(mid));
            dao.updateMode(m);
        }
        RequestDispatcher view = request.getRequestDispatcher(LIST_MODES);
        request.setAttribute("modes", dao.getAllModes());
        view.forward(request, response);
    }
}
