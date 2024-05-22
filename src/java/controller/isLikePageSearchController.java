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
public class isLikePageSearchController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet isLikePageSearchController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet isLikePageSearchController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String keyword = request.getParameter("keyword");
        PrintWriter out = response.getWriter();
        PlaceDAO dal = new PlaceDAO();

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
        String isLike = request.getParameter("isLike");
        String[] key = isLike.split("/");
        int id = Integer.parseInt(key[1]);
        if (account != null) {
            if (key[0].equals("true")) {
                dal.removeLikeRoomsByEmailAndIdRoom(account.getEmail(), id);
            } else {
                dal.insertLikeRoomsByEmailAndIdRoom(account.getEmail(), id);
            }
        }

        ArrayList<Room> rooms;
        if (keyword == null) {
            rooms = dal.searchRooms(key[2]);
            keyword = key[2];

        } else {
            rooms = dal.searchRooms(keyword);

        }

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

        if (rooms.isEmpty()) {
            request.setAttribute("keyword", keyword);
            request.setAttribute("notfound", "Không tin thấy khách sạn , địa điểm này");
            request.getRequestDispatcher("place.jsp").forward(request, response);
        } else {

            request.setAttribute("keyword", keyword);
            request.setAttribute("rooms", rooms);

            request.getRequestDispatcher("placeSearch.jsp").forward(request, response);
        }
    }

        @Override
        protected void doPost
        (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            processRequest(request, response);
        }

        @Override
        public String getServletInfo
        
            () {
        return "Short description";
        }// </editor-fold>

    }
