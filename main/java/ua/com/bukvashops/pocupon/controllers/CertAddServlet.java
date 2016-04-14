package ua.com.bukvashops.pocupon.controllers;

import ua.com.bukvashops.pocupon.Entities.Shop;
import ua.com.bukvashops.pocupon.Exceptions.NoDataDaoException;
import ua.com.bukvashops.pocupon.dao.MySqlDaoFactory;
import ua.com.bukvashops.pocupon.dao.MySqlShopDao;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Igor on 25-Mar-16.
 */
@WebServlet(name = "CertAddServlet", urlPatterns = "/admin/certadd")
public class CertAddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String message = null;

        if(name == null || name.equals("")) {
            message = "Название не может быть пустым";
            request.setAttribute("message", message);
            request.getRequestDispatcher("/addcert.jsp").forward(request, response);
        } else {
            Shop shop = new Shop(name);
            try {
                MySqlShopDao mySqlShopDao = (MySqlShopDao) new MySqlDaoFactory<Shop>().getMySqlShopDao();
                mySqlShopDao.update(shop);
                message = "Магазин создан";
                request.setAttribute("message", message);
                request.getRequestDispatcher("/addcert.jsp").forward(request, response);

            } catch (NoDataDaoException e){

            } catch (SQLException e) {

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NamingException e) {
                e.printStackTrace();
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
