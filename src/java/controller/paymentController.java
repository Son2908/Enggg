/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.AccountDAO;
import dal.BookingDAO;
import dal.PlaceDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.AccountUser;
import model.Room;

/**
 *
 * @author sodok
 */
public class paymentController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        /* TODO output your page here. You may use following sample code. */
        String idGet = request.getParameter("id");
        int id= Integer.parseInt(idGet);
        String quantity = request.getParameter("quantity");
        String dobBefore = request.getParameter("dobBefore");
        String dobAfter = request.getParameter("dobAfter");


        PlaceDAO dal = new PlaceDAO();
        Room room = dal.getDetailRoom(id);

        Cookie cookie = null;
        Cookie[] cookies = null;
        String email = "";
        String password = "";
        String phone = "";
        // Get an array of Cookies associated with this domain
        cookies = request.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                cookie = cookies[i];
                if (cookie.getName().equals("email")) {
                    email = cookie.getValue();
                }
                if (cookie.getName().equals("password")) {
                    password = cookie.getValue();
                }
                if (cookie.getName().equals("phone")) {
                    phone = cookie.getValue();
                }

            }
        }
        AccountDAO dao = new AccountDAO();
        AccountUser account = dao.getAccountByUP(email, password);

        if (account != null) {
            request.setAttribute("name", account.getName());
            request.setAttribute("email", account.getEmail());
            request.setAttribute("phone", account.getPhone());
            int quantityparse = Integer.parseInt(quantity);
            int price = room.getPrice() * quantityparse;

            BookingDAO book = new BookingDAO();
            book.insertNewBooking(email, id, room.getImageRoom(), dobBefore, dobAfter, quantityparse, price);
            request.setAttribute("bookingRoom", room);
            request.setAttribute("totalprice", price + "");
            request.setAttribute("dayBetween", quantityparse + "");
            request.setAttribute("sum", price + "");
            request.setAttribute("checkin", dobBefore);
            request.setAttribute("checkout", dobAfter);
            request.setAttribute("success", "Bạn đã đặt phòng thành công");
            request.getRequestDispatcher("bookingHandler.jsp").forward(request, response);
        }
        
        else {
            response.sendRedirect("login.jsp");
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
