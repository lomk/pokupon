package ua.com.bukvashops.pocupon.controllers;

import ua.com.bukvashops.pocupon.Entities.User;
import ua.com.bukvashops.pocupon.dao.MySqlDaoFactory;
import ua.com.bukvashops.pocupon.dao.MySqlUserDao;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by mater on 25-Mar-16.
 */
@WebServlet(name = "UserListServlet", urlPatterns = "/admin/userlist")
public class UserListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MySqlUserDao userDao = null;
        try {
            userDao = new MySqlDaoFactory<User>().getMySqlUserDao();
            List<User> UserList = (List<User>) userDao.getAll();
            if (!UserList.isEmpty()){
                request.setAttribute("UserList", UserList);
                getServletContext().getRequestDispatcher("/userlist.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            String message = "Something wrong";
            request.setAttribute("message", message);
            getServletContext().getRequestDispatcher("/admin.jsp").forward(request, response);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        } finally {
            if (userDao != null) {
                userDao.closeConnection();
            }
        }
    }
}
