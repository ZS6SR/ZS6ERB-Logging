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

import za.co.zs6erb.dao.PowerDao;
import za.co.zs6erb.model.Power;

/**
 *
 * @author SeanR
 */
public class PowerController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "power.jsp";
    private static String LIST_POWER = "listPower.jsp";
    private PowerDao dao;
    
    public PowerController() {
        super();
        dao = new PowerDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward = "";
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("delete")) {
            int power_id = Integer.parseInt(request.getParameter("power_id"));
            dao.deletePower(power_id);
            forward = LIST_POWER;
            request.setAttribute("power", dao.getAllPower());
        } else if (action.equalsIgnoreCase("edit")) {
            forward = INSERT_OR_EDIT;
            int power_id = Integer.parseInt(request.getParameter("power_id"));
            Power power = dao.getPowerById(power_id);
            request.setAttribute("power", power);
        } else if (action.equalsIgnoreCase("listPower")) {
            forward = LIST_POWER;
            request.setAttribute("power", dao.getAllPower());
        } else {
            forward = INSERT_OR_EDIT;
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    //When adding a new record or updating an existing record
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Power power = new Power();
        power.setpower(request.getParameter("power"));
        String power_id = request.getParameter("power_id");
        if (power_id == null || power_id.isEmpty()) {
            dao.addPower(power);
        } else {
            power.setID(Integer.parseInt(power_id));
            dao.updatePower(power);
        }
        RequestDispatcher view = request.getRequestDispatcher(LIST_POWER);
        request.setAttribute("power", dao.getAllPower());
        view.forward(request, response);
    }
}
