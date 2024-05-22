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
 *
 * @author sodok
 */
public class listLikeController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        /* TODO output your page here. You may use following sample code. */
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
        AccountDAO dao = new AccountDAO();
        AccountUser account = dao.getAccountByUP(email, password);
        PlaceDAO dal = new PlaceDAO();

        ArrayList<LikeRoom> history = dal.getLikeRoomsByEmail(account.getEmail());
        ArrayList<Room> rooms = dal.getRooms();
        
        for (int counter = 0; counter < rooms.size(); counter++) {
                for (int i = 0; i < history.size(); i++) {
                    if (rooms.get(counter).getId() == history.get(i).getIdRoom()) {
                        history.get(i).setImageRoom(rooms.get(counter).getImageRoom());
                        history.get(i).setPrice(rooms.get(counter).getPrice());
                       
                    }
                }
            }
        request.setAttribute("history", history);
        request.getRequestDispatcher("listLike.jsp").forward(request, response);
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
