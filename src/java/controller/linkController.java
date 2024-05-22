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
public class linkController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");

        Cookie cookie = null;
        Cookie[] cookies = null;
        String email = "";
        String passwordCookie = "";
        int check = 0;
        // Get an array of Cookies associated with this domain
        cookies = request.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                cookie = cookies[i];
                if (cookie.getName().equals("email")) {
                    email = cookie.getValue();
                    check = 1;
                }
                if (cookie.getName().equals("password")) {
                    passwordCookie = cookie.getValue();
                }

            }
        }

        AccountDAO accDAO = new AccountDAO();

        AccountUser acc = accDAO.getAccountByUP(email, passwordCookie);

        if (acc != null) {
            PlaceDAO dao = new PlaceDAO();
            ArrayList <LikeRoom> likeList = dao.getLikeRoomsByEmail(email);           
            ArrayList <Room> rooms = dao.getRoomByPlace(name);
            
            for(int i= 0 ; i < rooms.size(); i++ ){
                for(int j= 0 ; j < likeList.size() ; j++){
                    if(likeList.get(j).getIdRoom() == rooms.get(i).getId()){
                        rooms.get(i).setIsLike(true);
                    }
                }
            }
            
            request.setAttribute("rooms", rooms);
            request.getRequestDispatcher("placeCategory.jsp").forward(request, response);
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
