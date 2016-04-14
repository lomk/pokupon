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
 * Created by mater on 19-Mar-16.
 */
@WebServlet(name = "Check", urlPatterns = "/check")
public class CheckServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        String message = null;
        Certificate certificate = null;

        if (code.isEmpty()){
            message = "Пожалуйста, введите код сертификата";
            request.setAttribute("message", message);
            request.getRequestDispatcher("/check.jsp").forward(request, response);

        } else {
            MySqlCertificateDao certificateDao = null;
            try {
                certificateDao = new MySqlDaoFactory().getMySqlCertificateDao();
                certificate = (Certificate) certificateDao.getByUniqParameter("code", code);
                //certificateDao.closeConnection();
            } catch (NoDataDaoException e) {
                message = "Неверный код сертиффиката";
                request.setAttribute("message", message);
                request.getRequestDispatcher("/check.jsp").forward(request, response);
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
                message = "Неверный код сертиффиката";
                request.setAttribute("message", message);
                request.getRequestDispatcher("/check.jsp").forward(request, response);
            } catch (ClassNotFoundException e) {
                message="Проблема на сервере, попробуйте позже";
                request.setAttribute("message", message);
                request.getRequestDispatcher("/check.jsp").forward(request, response);
                e.printStackTrace();
            } catch (NamingException e) {
                message="Проблема на сервере, попробуйте позже";
                request.setAttribute("message", message);
                request.getRequestDispatcher("/check.jsp").forward(request, response);
                e.printStackTrace();
            } finally {
                if (certificateDao != null) {
                    certificateDao.closeConnection();
                }
            }

            if (certificate == null) {
//                HttpSession session = request.getSession();
//                session.setAttribute("certificate", certificate);
                message = "Неверный код сертификата";
                request.setAttribute("message", message);
                request.getRequestDispatcher("/check.jsp").forward(request, response);
            } else {
                System.out.println(certificate);

                if (certificate.isActive()) {
                    request.setAttribute("certificate", certificate);
                    request.getRequestDispatcher("/usecert.jsp").forward(request, response);

                } else {
                    message = "Сертификат уже был использован!";
                    request.setAttribute("message", message);
                    request.getRequestDispatcher("/check.jsp").forward(request, response);
                }
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
