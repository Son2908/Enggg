/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.AccountUser;

/**
 * xác thực thông tin đăng nhập từ người dùng và thực hiện các hành động tương ứng dựa trên thông tin đó, 
 * bao gồm việc tạo cookie và chuyển hướng người dùng đến các trang khác nhau.
 * @author sodok
 */
public class loginController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet loginController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet loginController at " + request.getContextPath() + "</h1>");
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
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        AccountDAO dal = new AccountDAO();
        AccountUser account = dal.getAccountByUP(email, password);

        if (account == null) {
            request.setAttribute("email", email);
            request.setAttribute("password", password);
            request.setAttribute("accountLoginError", "Email or Pasword is incorrect ");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            Cookie userCookies = new Cookie("email", email);
            Cookie passwordCookies = new Cookie("password", password);
            Cookie phoneCookies = new Cookie("phone", account.getPhone());
            // 15 ngày, tính bằng giây.
            userCookies.setMaxAge(3600 * 24 * 15);
            passwordCookies.setMaxAge(3600 * 24 * 15);
            response.addCookie(userCookies);
            response.addCookie(passwordCookies);
            response.addCookie(phoneCookies);

            if (account.getRole().equals("admin")) {
                response.sendRedirect("adminController");
            } else {
                request.setAttribute("UserLogin", account.getName());
                response.sendRedirect("indexController");
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
