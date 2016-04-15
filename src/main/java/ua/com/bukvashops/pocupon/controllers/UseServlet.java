package ua.com.bukvashops.pocupon.controllers;

import ua.com.bukvashops.pocupon.Entities.Certificate;
import ua.com.bukvashops.pocupon.Entities.User;
import ua.com.bukvashops.pocupon.Exceptions.NoDataDaoException;
import ua.com.bukvashops.pocupon.dao.MySqlCertificateDao;
import ua.com.bukvashops.pocupon.dao.MySqlDaoFactory;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;


/**
 * Created by Igor on 21-Mar-16.
 */
@WebServlet(name = "UseServlet", urlPatterns = "/usecertificate")
public class UseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Certificate certificate = (Certificate) request.getSession().getAttribute("certificate");
        User user = (User) request.getSession().getAttribute("user");
        MySqlCertificateDao certificateDao = null;
        String code = certificate.getCode();
        String error = "Сертификат применен.";

        if (certificate != null && code != null ){
            try {
                certificate.setActive(false);
                certificate.setUsedBy(user.getLogin());
                certificateDao = new MySqlDaoFactory().getMySqlCertificateDao();
                certificateDao.update(certificate);
                String message = "Сертификат применен.";
                request.setAttribute("message", message);
                getServletContext().getRequestDispatcher("/check.jsp").forward(request, response);
            } catch (SQLException e) {
                request.setAttribute("message", error);
                getServletContext().getRequestDispatcher("/check.jsp").forward(request, response);
                e.printStackTrace();
            } catch (NoDataDaoException e) {
                request.setAttribute("message", error);
                getServletContext().getRequestDispatcher("/check.jsp").forward(request, response);
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                request.setAttribute("message", error);
                getServletContext().getRequestDispatcher("/check.jsp").forward(request, response);
                e.printStackTrace();
            } catch (NamingException e) {
                request.setAttribute("message", error);
                getServletContext().getRequestDispatcher("/check.jsp").forward(request, response);
                e.printStackTrace();
            } finally {
                if (certificateDao != null) {
                    certificateDao.closeConnection();
                }
            }
        }
    }
}
