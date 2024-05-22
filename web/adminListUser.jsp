<%-- 
    Document   : adminListUser
    Created on : Jan 26, 2024, 3:02:05 PM
    Author     : sodok
--%>

<%@page import="model.AccountUser"%>
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

        <section style="padding-top: 120px">
            <div class="container">
                <div style="display: flex; align-items: center">
                    <a type="button" href="./adminAddUser.jsp" class="btn btn-secondary" style="margin-top: 10px ; margin-bottom: 10px; " role="button">Add User</a>
                    <h5 style="color: green; margin-left: 15px">${deleteSuccess}</h5>
                </div>
                <table class="table">
                    <thead class="thead-light">
                        <tr>
                            <th scope="col">Email</th>
                            <th scope="col">Name</th>
                            <th scope="col">Phone</th>
                            <th scope="col">Password</th>
                            <th scope="col" colspan="2">Action</th>
                        </tr>
                    </thead>
                    <tbody>

                        <%
                            ArrayList<AccountUser> users = (ArrayList<AccountUser>) request.getAttribute("listUser");

                        %> 


                        <% for (AccountUser s : users) {
                                if (s.getRole().equals("user")) {
                        %>


                        <tr>
                            <th scope="row"><%=s.getEmail() %></th>
                            <td><%=s.getName()%></td>
                            <td><%=s.getPhone()%></td>
                            <td><%=s.getPassword()%></td>
                            <td>
                                <a class="btn btn-sm" onclick="return confirmDelete('<%=s.getEmail()%>')" type="submit" href="adminDeleteUser?email=<%=s.getEmail()%>" role="button" style="background-color:#dc3545; color: white;">Xoá</a>
                            </td>
                        </tr>

                        <%}%>
                        <%}%>

                    </tbody>
                </table>
            </div>

        </section>

        <script>
            function confirmDelete(email) {
                return confirm("Are you sure you want to delete user '" + email + "' ?");
            }
        </script>

        <!-- Optional JavaScript -->
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    </body>

</html>
