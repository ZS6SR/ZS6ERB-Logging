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

import za.co.zs6erb.dao.UserDao;
import za.co.zs6erb.model.User;

/**
 *
 * @author SeanR
 */
public class UsersController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "users.jsp";
    private static String LIST_USERS = "listUsers.jsp";
    private UserDao dao;
    
    public UsersController() {
        super();
        dao = new UserDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward = "";
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("delete")) {
            int usrid = Integer.parseInt(request.getParameter("usrid"));
            dao.deleteUser(usrid);
            forward = LIST_USERS;
            request.setAttribute("users", dao.getAllUsers());
        } else if (action.equalsIgnoreCase("edit")) {
            forward = INSERT_OR_EDIT;
            int usrid = Integer.parseInt(request.getParameter("usrid"));
            User usr = dao.getUserById(usrid);
            request.setAttribute("usr", usr);
        } else if (action.equalsIgnoreCase("listUsers")) {
            forward = LIST_USERS;
            request.setAttribute("users", dao.getAllUsers());
        } else {
            forward = INSERT_OR_EDIT;
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    //When adding a new record or updating an existing record
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        User usr = new User();
        String usrid = request.getParameter("usrid");
        usr.setcallSign(request.getParameter("callsign"));
        usr.setfirstName(request.getParameter("firstname"));
        usr.setlastName(request.getParameter("lastname"));
        usr.setpwd(request.getParameter("pwd"));
        
        if (usrid.isEmpty() || usrid.isEmpty()) {
            dao.addUser(usr);
        } else {
            usr.setID(Integer.parseInt(usrid));
            dao.updateUser(usr);
        }
        RequestDispatcher view = request.getRequestDispatcher(LIST_USERS);
        request.setAttribute("users", dao.getAllUsers());
        view.forward(request, response);
    }
}
