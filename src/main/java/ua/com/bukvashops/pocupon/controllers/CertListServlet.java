package ua.com.bukvashops.pocupon.controllers;

import ua.com.bukvashops.pocupon.Entities.Certificate;
import ua.com.bukvashops.pocupon.dao.MySqlCertificateDao;
import ua.com.bukvashops.pocupon.dao.MySqlDaoFactory;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by mater on 26-Mar-16.
 */
@WebServlet(name = "CertListServlet", urlPatterns = "/admin/certlist")
public class CertListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MySqlCertificateDao certificateDao = null;
        try {
            certificateDao = new MySqlDaoFactory<Certificate>().getMySqlCertificateDao();
            List<Certificate> CertList = certificateDao.getAll();
            if (!CertList.isEmpty()){
                request.setAttribute("CertList", CertList);
                request.getRequestDispatcher("/certlist.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            String message = "Something wrong";
            request.setAttribute("message", message);
            request.getRequestDispatcher("/admin.jsp").forward(request, response);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        } finally {
            if (certificateDao != null) {
                certificateDao.closeConnection();
            }
        }
    }
}
