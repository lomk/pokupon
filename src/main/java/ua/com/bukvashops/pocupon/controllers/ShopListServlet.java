package ua.com.bukvashops.pocupon.controllers;

import ua.com.bukvashops.pocupon.Entities.Shop;
import ua.com.bukvashops.pocupon.dao.MySqlDaoFactory;
import ua.com.bukvashops.pocupon.dao.MySqlShopDao;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
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
@WebServlet(name = "ShopListServlet", urlPatterns = "/admin/shoplist")
public class ShopListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MySqlShopDao shopDao = null;
        try {
            shopDao = new MySqlDaoFactory<Shop>().getMySqlShopDao();
            List<Shop> ShopList = shopDao.getAll();
            if (!ShopList.isEmpty()){
                request.setAttribute("ShopList", ShopList);
                RequestDispatcher rd = request.getRequestDispatcher("/shoplist.jsp");
                rd.forward(request, response);
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
            shopDao.closeConnection();
        }
    }
}
