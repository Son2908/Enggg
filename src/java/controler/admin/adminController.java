/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controler.admin;

import dal.AccountDAO;
import dal.AdminDAO;
import dal.BookingDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.AccountUser;
import model.Booking;
import model.Room;

/**
 *
 * @author sodok
 */
public class adminController extends HttpServlet {

    // kiểm tra xem người dùng đã đăng nhập với vai trò admin chưa bằng cách kiểm tra cookie.
    // Nếu không có cookie hoặc nếu vai trò của người dùng không phải là admin, nó sẽ hiển thị thông báo "Access Denied". 
    // Ngược lại, nó sẽ thu thập dữ liệu cần thiết từ cơ sở dữ liệu và chuyển hướng người dùng đến trang admin.jsp
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        Cookie cookie = null;
        Cookie[] cookies = null;
        String email = "";
        String passwordCookie = "";
        int check = 0;
        // check xem ng dùng có đăng nhập vs vai trò admin ko
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

        AccountDAO dao = new AccountDAO();
        AccountUser account = dao.getAccountByUP(email, passwordCookie);

        // nếu người dùng ko phải admin hoặc ko có dữ liệu trên cookie thì báo lỗi
        if (account == null || account.getRole().equals("user")) {
            out.print("Access Denied!!!");
        // nếu đúng là admin thì thu thập dữ liệu và chuyển hướng đến admin.jsp
        } else {
            ArrayList<AccountUser> listUser = dao.listAcc();
            BookingDAO dal = new BookingDAO();
            ArrayList<Booking> history = dal.getBookingHistory();
            AdminDAO dalPlace = new AdminDAO();
            ArrayList<Room> rooms = dalPlace.getRooms();
            ArrayList<Room> restoreRooms = dalPlace.getRestoreRooms();
            request.setAttribute("rooms", rooms);
            request.setAttribute("restoreRooms", restoreRooms);
            request.setAttribute("listUser", listUser);
            request.setAttribute("history", history);
            request.getRequestDispatcher("admin.jsp").forward(request, response);
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
