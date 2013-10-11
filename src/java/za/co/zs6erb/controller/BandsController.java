/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.zs6erb.controller;

import java.io.IOException;
import java.math.BigDecimal;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import za.co.zs6erb.dao.BandDao;
import za.co.zs6erb.model.Band;

/**
 *
 * @author SeanR
 */
public class BandsController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "bands.jsp";
    private static String LIST_BANDS = "listBands.jsp";
    private BandDao dao;
    
    public BandsController() {
        super();
        dao = new BandDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward = "";
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("delete")) {
            int band_id = Integer.parseInt(request.getParameter("band_id"));
            dao.deleteBand(band_id);
            forward = LIST_BANDS;
            request.setAttribute("bands", dao.getAllBands());
        } else if (action.equalsIgnoreCase("edit")) {
            forward = INSERT_OR_EDIT;
            int band_id = Integer.parseInt(request.getParameter("band_id"));
            Band band = dao.getBandById(band_id);
            request.setAttribute("band", band);
        } else if (action.equalsIgnoreCase("listBands")) {
            forward = LIST_BANDS;
            request.setAttribute("bands", dao.getAllBands());
        } else {
            forward = INSERT_OR_EDIT;
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    //When adding a new record or updating an existing record
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Band band = new Band();
        band.setband(request.getParameter("band"));
        band.setStartBand(new BigDecimal(request.getParameter("StartBand")));
        band.setEndBand(new BigDecimal(request.getParameter("EndBand")));
        String band_id = request.getParameter("band_id");
        if (band_id == null || band_id.isEmpty()) {
            dao.addBand(band);
        } else {
            band.setID(Integer.parseInt(band_id));
            dao.updateBand(band);
        }
        RequestDispatcher view = request.getRequestDispatcher(LIST_BANDS);
        request.setAttribute("bands", dao.getAllBands());
        view.forward(request, response);
    }
}
