/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controler.admin;

import dal.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.AccountUser;

/**
 *
 * @author sodok
 */
public class adminListUserController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
        }else{
            ArrayList<AccountUser> listUser = dao.listAcc();
            request.setAttribute("listUser", listUser);
            request.getRequestDispatcher("adminListUser.jsp").forward(request, response);
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
