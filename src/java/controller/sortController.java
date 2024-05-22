/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.PlaceDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Room;

/**
 *
 * @author sodok
 */
public class sortController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet sortController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet sortController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        String selectedValue = request.getParameter("sort");
        PrintWriter out = response.getWriter();

        String[] key = selectedValue.split("/");
        String page = key[0];
        PlaceDAO dal = new PlaceDAO();
        int countPage = dal.getCountRooms();
        int pageSize = 12;
        int allPage;
        if (countPage % 12 == 0) {
            allPage = countPage / 12;
        } else {
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

        ArrayList<Room> rooms = dal.getRoomsSort(key[1], currentPage);
        request.setAttribute("pageCurrent", pageCurrent + "");
        request.setAttribute("sort", key[1]);
        request.setAttribute("pageSize", allPage + "");
        request.setAttribute("rooms", rooms);
        request.getRequestDispatcher("placeHomestay.jsp").forward(request, response);
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
