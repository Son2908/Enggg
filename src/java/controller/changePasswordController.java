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
 * user
 * @author sodok
 */
public class changePasswordController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet changePasswordController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet changePasswordController at " + request.getContextPath() + "</h1>");
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
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmpassword");

        Cookie cookie = null;
        Cookie[] cookies = null;
        String email = "";
        String passwordCookie = "";
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
                    passwordCookie = cookie.getValue();
                }
                if (cookie.getName().equals("phone")) {
                    phone = cookie.getValue();
                }

            }
        }
        AccountDAO dao = new AccountDAO();
        AccountUser account = dao.getAccountByUP(email, passwordCookie);

        
        if (password.equals(confirmPassword)) {       
            request.setAttribute("password", password);
            request.setAttribute("confirmpassword", confirmPassword);
            request.setAttribute("UserLogin", account.getName());
            dao.changePassword(email, password);
            Cookie userCookies = new Cookie("email", email);
            Cookie passwordCookies = new Cookie("password", password);
            Cookie phoneCookies = new Cookie("phone", account.getPhone());
            userCookies.setMaxAge(3600 * 24 * 15);
            passwordCookies.setMaxAge(3600 * 24 * 15);
            response.addCookie(userCookies);
            response.addCookie(passwordCookies);
            response.addCookie(phoneCookies);
            request.setAttribute("signupSuccess", "Bạn đã thay đổi mật khẩu thành công !");
            request.getRequestDispatcher("information.jsp").forward(request, response);
        } else { 
            request.setAttribute("password", password);
            request.setAttribute("confirmpassword", confirmPassword);
            request.setAttribute("signupError", "Mật khẩu không khớp");
            request.getRequestDispatcher("information.jsp").forward(request, response);
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
