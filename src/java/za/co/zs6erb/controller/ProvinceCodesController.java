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

import za.co.zs6erb.dao.ProvinceCodeDao;
import za.co.zs6erb.model.ProvinceCode;

/**
 *
 * @author SeanR
 */
public class ProvinceCodesController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "eProvinces.jsp";
    private static String LIST_PROVINCES = "listProvinceCodes.jsp";
    private ProvinceCodeDao dao;
    
    public ProvinceCodesController() {
        super();
        dao = new ProvinceCodeDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward = "";
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("delete")) {
            int pcid = Integer.parseInt(request.getParameter("pc_id"));
            dao.deleteProvinceCode(pcid);
            forward = LIST_PROVINCES;
            request.setAttribute("provinces", dao.getAllProvinceCode());
        } else if (action.equalsIgnoreCase("edit")) {
            forward = INSERT_OR_EDIT;
            int pcid = Integer.parseInt(request.getParameter("pc_id"));
            ProvinceCode pc = dao.getPCById(pcid);
            request.setAttribute("provinceCode", pc);
        } else if (action.equalsIgnoreCase("listProvinces")) {
            forward = LIST_PROVINCES;
            request.setAttribute("provinces", dao.getAllProvinceCode());
        } else {
            forward = INSERT_OR_EDIT;
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    //When adding a new record or updating an existing record
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProvinceCode pc = new ProvinceCode();
        pc.setprovinceName(request.getParameter("provinceName"));
        pc.setprovinceCode(request.getParameter("provinceCode"));
        String pc_id = request.getParameter("pc_id");
        if (pc_id == null || pc_id.isEmpty()) {
            dao.addProvinceCode(pc);
        } else {
            pc.setID(Integer.parseInt(pc_id));
            dao.updateProvinceCode(pc);
        }
        RequestDispatcher view = request.getRequestDispatcher(LIST_PROVINCES);
        request.setAttribute("provinces", dao.getAllProvinceCode());
        view.forward(request, response);
    }
}
