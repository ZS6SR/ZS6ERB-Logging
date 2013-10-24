/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.zs6erb.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import za.co.zs6erb.dao.ContactDao;
import za.co.zs6erb.model.Contact;

/**
 *
 * @author SeanR
 */
public class ContactController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "contacts.jsp";
    private static String LIST_CONTACTS = "allContactsDetail.jsp";
    private ContactDao dao;
    
    public ContactController() {
        super();
        dao = new ContactDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward = "";
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("delete")) {
            int contact_id = Integer.parseInt(request.getParameter("contact_id"));
            dao.deleteContact(contact_id);
            forward = LIST_CONTACTS;
            request.setAttribute("contacts", dao.getAllContacts());
        } else if (action.equalsIgnoreCase("edit")) {
//            forward = INSERT_OR_EDIT;
//            int contact_id = Integer.parseInt(request.getParameter("contact_id"));
//            Contact contact = dao.getContactById(contact_id);
//            request.setAttribute("contact", contact);
            //====================================================
            //Change this when you have done the update functions
            //====================================================
            forward = LIST_CONTACTS;
            request.setAttribute("contacts", dao.getAllContacts());
        } else if (action.equalsIgnoreCase("listBands")) {
            forward = LIST_CONTACTS;
            request.setAttribute("contacts", dao.getAllContacts());
        } else {
            forward = INSERT_OR_EDIT;
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    //When adding a new record or updating an existing record
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        Date date = new Date();
        Timestamp tDate = new Timestamp(date.getTime());
        Timestamp tsDate = null;
        
        SimpleDateFormat formatter; //Example: 2012-03-04
        formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date sDate;
        try {
            sDate = formatter.parse(request.getParameter("startTime"));
            tsDate = new Timestamp(sDate.getTime());
            //System.out.println("START TIME = " + tsDate.toString());
        } catch (ParseException ex) {
            System.out.println("ERROR Parsing startdate " + ex.getMessage());
        }
        
        Contact contact = new Contact();
        
        HttpSession ss = request.getSession();
        contact.setuserID(Integer.parseInt(ss.getAttribute("userId").toString()));
        contact.setstartTime(tsDate);
        contact.setendTime(tDate);
        contact.setcallSign(request.getParameter("callsign").toUpperCase());
        contact.setname(request.getParameter("name"));
        contact.setlocation(request.getParameter("qth"));
        contact.setfreq(request.getParameter("freq"));
        contact.setbandId(Integer.parseInt(request.getParameter("bandid")));
        contact.setmodeId(Integer.parseInt(request.getParameter("modeid")));
        contact.setcpowerId(1);
        contact.setlocalRST(59);
        contact.setremoteRST(59);
        contact.setnotes(request.getParameter("notes"));
        
        //if (band_id == null || band_id.isEmpty()) {
            dao.addContact(contact);
        //} else {
//            band.setID(Integer.parseInt(band_id));
//            dao.updateBand(band);
//        }
        RequestDispatcher view = request.getRequestDispatcher(LIST_CONTACTS);
        request.setAttribute("contacts", dao.getAllContacts());
        view.forward(request, response);
    }
}
