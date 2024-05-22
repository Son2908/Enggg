/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controler.admin;

import dal.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.AccountUser;

/**
 *
 * @author sodok
 */
public class adminAddUserController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet adminAddUserController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet adminAddUserController at " + request.getContextPath () + "</h1>");
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
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmpassword");
        
        AccountDAO dao = new AccountDAO();
        AccountUser account = dao.getAccountByEmail(email);
        
        if (account != null){
            request.setAttribute("name", name);
            request.setAttribute("email" , email);
            request.setAttribute("phone" , phone);
            request.setAttribute("password" , password);
            request.setAttribute("confirmpassword", confirmPassword);
            request.setAttribute("addError", "Email or password is exist !!!");
            request.getRequestDispatcher("adminAddUser.jsp").forward(request, response);
        }
        else if (password.equals(confirmPassword) ) {
            AccountDAO db = new AccountDAO();
            db.insertAccount(email, name, phone, password);
            request.setAttribute("name", name);
            request.setAttribute("email" , email);
            request.setAttribute("phone" , phone);
            request.setAttribute("password" , password);
            request.setAttribute("confirmpassword", confirmPassword);
            request.setAttribute("addSuccess", "You have successfully registered !");
            request.getRequestDispatcher("adminAddUser.jsp").forward(request, response);
        }
        else {
            request.setAttribute("name", name);
            request.setAttribute("email" , email);
            request.setAttribute("phone" , phone);
            request.setAttribute("password" , password);
            request.setAttribute("confirmpassword", confirmPassword);
            request.setAttribute("passError", "Password not matches !!!");
            request.getRequestDispatcher("adminAddUser.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
