package controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
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
public class searchRooms extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet searchRooms</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet searchRooms at " + request.getContextPath() + "</h1>");
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
        ArrayList<Room> rooms = dal.searchRooms(keyword);
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
        if (rooms.isEmpty()) {
            request.setAttribute("notfound", "Cannot found your homestay or places !!! Please try again !!!");
        }
        request.setAttribute("keyword", keyword);
        request.setAttribute("rooms", rooms);
        request.getRequestDispatcher("placeSearch.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
