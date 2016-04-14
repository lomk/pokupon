package ua.com.bukvashops.pocupon.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//import com.materynko.servlet2.dao.UserDao;
import ua.com.bukvashops.pocupon.Exceptions.NoDataDaoException;
import ua.com.bukvashops.pocupon.dao.MySqlDaoFactory;
import ua.com.bukvashops.pocupon.dao.MySqlUserDao;

import ua.com.bukvashops.pocupon.Entities.User;

/**
 * Created by Igor on 2/16/2016.
 */
@WebServlet(name = "Login", urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String message = null;
        if(login == null || login.equals("")){
            message ="Имя пользователя на может быть пустым";
        }
        if(password == null || password.equals("")){
            message = "Пароль не может быть пустым";
        }
        if(message != null){
            request.setAttribute("message", message);
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        } else {
            MySqlUserDao userDao = null;
            User user = null;
            try {
                userDao = new MySqlDaoFactory().getMySqlUserDao();
                user = (User) userDao.getByUniqParameter("login", login);
            }  catch (NoDataDaoException e) {
                message="Попробуйте еще раз";
                request.setAttribute("message", message);
                request.getRequestDispatcher("/login.jsp").forward(request, response);
                e.printStackTrace();
            } catch (SQLException e) {
                message="Попробуйте еще раз";
                request.setAttribute("message", message);
                request.getRequestDispatcher("/login.jsp").forward(request, response);
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                message="Проблема на сервере, попробуйте позже";
                request.setAttribute("message", message);
                request.getRequestDispatcher("/login.jsp").forward(request, response);
                e.printStackTrace();
            } catch (NamingException e) {
                message="Проблема на сервере, попробуйте позже";
                request.setAttribute("message", message);
                request.getRequestDispatcher("/login.jsp").forward(request, response);
                e.printStackTrace();
            } finally {
                if (userDao != null) {
                    userDao.closeConnection();
                }
            }
            if (user == null) {
                //RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
                message="Неверный логин. Попробуйте еще раз";
                request.setAttribute("message", message);
                request.getRequestDispatcher("/login.jsp").forward(request, response);
                //rd.include(request, response);
            } else {
                if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                    request.getSession().setAttribute("user", user);
                    RequestDispatcher rd = request.getRequestDispatcher("/check.jsp");
                    rd.forward(request, response);
                } else {
                    message="Неверный пароль. Попробуйте еще раз";
                    request.setAttribute("message", message);
                    request.getRequestDispatcher("/login.jsp").forward(request, response);
                }
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
        rd.forward(request, response);
    }
}
