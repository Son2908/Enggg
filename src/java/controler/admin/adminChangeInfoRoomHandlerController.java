/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controler.admin;

import dal.AdminDAO;
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
public class adminChangeInfoRoomHandlerController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet adminChangeInfoRoomHandlerController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet adminChangeInfoRoomHandlerController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        String idGet = request.getParameter("id");
        String imageRoom = request.getParameter("imageRoom");
        String roomName = request.getParameter("roomName");
        String starGet = request.getParameter("star");
        String priceGet = request.getParameter("price");
        String place = request.getParameter("place");
        String quantityBedGet = request.getParameter("quantityBed");
        String descriptions = request.getParameter("descriptions");
        String detailImageRoom1 = request.getParameter("detailImageRoom1");
        String detailImageRoom2 = request.getParameter("detailImageRoom2");
        String detailImageRoom3 = request.getParameter("detailImageRoom3");

        int id = Integer.parseInt(idGet);
        int star = Integer.parseInt(starGet);
        int price = Integer.parseInt(priceGet);
        int quantityBed = Integer.parseInt(quantityBedGet);

//        out.print(id + imageRoom + roomName + star + price + quantityBed + descriptions + detailImageRoom1 + detailImageRoom2 + detailImageRoom3);
        AdminDAO dao = new AdminDAO();
        dao.updateRoomInfo(id, imageRoom, roomName, place, price, quantityBed, star, descriptions, detailImageRoom1, detailImageRoom2, detailImageRoom3);
        ArrayList<Room> rooms = dao.getRooms();
        request.setAttribute("rooms", rooms);
        request.getRequestDispatcher("adminListHomestay.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
