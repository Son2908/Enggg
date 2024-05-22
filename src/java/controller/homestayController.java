/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.AccountDAO;
import dal.PlaceDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.AccountUser;
import model.LikeRoom;
import model.Room;

/**
 * trang HomeStay
 * @author sodok
 */
public class homestayController extends HttpServlet {
   protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String page = request.getParameter("page");
        PlaceDAO dal = new PlaceDAO();
        int countPage = dal.getCountRooms();
         int pageSize = 12;
         int allPage ;
         if(countPage % 12 ==0 ){
             allPage = countPage / 12;
         }
         else {
              allPage = (countPage / 12) + 1;
         }
        
       
        int pageCurrent = 1;

        int currentPage;
        if (page == null) {
            currentPage = 0;
        } else {
            currentPage = Integer.parseInt(page);
            pageCurrent = currentPage;
            currentPage = (currentPage - 1) * pageSize;
        }
        
        ArrayList<Room> rooms = dal.getRoomsPagination(currentPage);

        Cookie cookie = null;
        Cookie[] cookies = null;
        String email = "";
        String password = "";
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

            }
        }
        PrintWriter out = response.getWriter();
        AccountDAO dao = new AccountDAO();
        AccountUser account = dao.getAccountByUP(email, password);
        if (account != null) {
            ArrayList<LikeRoom> likeRooms = dal.getLikeRoomsByEmail(account.getEmail());
            for (int counter = 0; counter < rooms.size(); counter++) {
                for (int i = 0; i < likeRooms.size(); i++) {
                    if (rooms.get(counter).getId() == likeRooms.get(i).getIdRoom()) {
                        rooms.get(counter).setIsLike(true);
                       
                    }
                }
            }
        }

        request.setAttribute("pageCurrent", pageCurrent + "");
        request.setAttribute("pageSize", allPage + "");
        request.setAttribute("rooms", rooms);
        request.getRequestDispatcher("placeHomestay.jsp").forward(request, response);
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
