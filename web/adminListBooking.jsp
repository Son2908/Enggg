<%-- 
    Document   : adminListBooking
    Created on : Jan 22, 2024, 11:59:20 PM
    Author     : sodok
--%>

<%@page import="dal.PlaceDAO"%>
<%@page import="dal.AdminDAO"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.Locale"%>
<%@page import="model.*"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!doctype html>
<html lang="en">

    <head>
        <title>Title</title>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="./css/style.css">
        <link rel="stylesheet" href="./css/login.css">
        <link rel=stylesheet type="/text/css" href="http://localhost:8080/HomeMie/css/style.css">
        <link rel=stylesheet type="/text/css" href="http://localhost:8080/HomeMie/css/form.css">
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/css/all.min.css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    </head>

    <body>

        <%
    String role = (String) session.getAttribute("role");

    if (role != null && role.equals("user")) {
        response.sendRedirect("login.jsp");
    } else if(role == "admin") {
    request.getRequestDispatcher("admin.jsp").forward(request, response);
        %>

        <%
            }
        %>

        <section>
            <nav class="navbar navbar-expand-sm navbar-light bg-light">
                <div class="container">
                    <a class="navbar-brand" href="adminController"><img src="./image/logo.png" class="logo__brand" style="width: 45px; height: 40px"></a>
                    <button class="navbar-toggler d-lg-none" type="button" data-toggle="collapse" data-target="#collapsibleNavId" aria-controls="collapsibleNavId" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="collapsibleNavId">
                        <h2 style="margin-left: 33%">Booking Homestay</h2>
                        <ul class="navbar-nav ml-auto mt-2 mt-lg-0">
                            <li class="nav-item active">
                                <a class="nav-link" href="./adminInformation.jsp">Admin </a>
                            </li>
                            <li class="nav-item active">
                                <a class="nav-link" href="./login.jsp">Đăng Xuất</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
        </section>


        <section style="margin-top: 120px">
            <div class="container">
                <table class="table">
                    <thead class="thead-light">
                        <tr>
                            <th scope="col">Ảnh</th>
                            <th scope="col">Tên địa điểm</th>
                            <th scope="col">Ngày thuê</th>
                            <th scope="col">Ngày trả</th>
                            <th scope="col">Giá</th>
                            <th scope="col">Người Thuê</th>

                        </tr>
                    </thead>
                    <tbody>

                        <%
                            ArrayList<Booking> Histories = (ArrayList<Booking>) request.getAttribute("booking");
                             PlaceDAO pDao = new PlaceDAO();

                        %> 

                        <% for (Booking s : Histories) {
                            AdminDAO dao = new AdminDAO();
                            AccountUser user = dao.getAccountByEmail(s.getEmail());
                            if(user != null){
                            String name = user.getName();
                        %>


                        <tr>
                            <th scope="row"><img src="<%=s.getImageRoom()%>" style="height: 50px"/></th>
                            <th scope="row">
                                <% Room r = pDao.getRoomById(s.getIdRoom()); %>
                                <%=r.getRoomName()%>
                            </th>
                            <td><%=s.getDobBefore()%></td>
                            <td><%=s.getDobAfter()%></td>
                            <td>
                                <% String price = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"))
                                            .format(s.getPrice());
                                    out.print(price);
                                %>/đêm 
                            </td>
                            <th scope="col"><a href="adminIndividualtInfoUser?email=<%=s.getEmail()%>"> <%=name%></a></th>

                        </tr>

                        <%}%>
                        <%}%>


                    </tbody>
                </table>


            </div>
        </section>

        <!-- Optional JavaScript -->
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    </body>

</html>

