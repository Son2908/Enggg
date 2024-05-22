<%-- 
    Document   : login
    Created on : Jan 20, 2024, 11:00:56 AM
    Author     : sodok
--%>

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
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    </head>

    <body>

        <section>
            <nav class="navbar navbar-expand-sm navbar-light bg-light">
                <div class="container">
                    <a class="navbar-brand" href="login.jsp"><img src="./image/logo.png" class="logo__brand" style="width: 45px; height: 40px"></a>
                    <button class="navbar-toggler d-lg-none" type="button" data-toggle="collapse" data-target="#collapsibleNavId" aria-controls="collapsibleNavId" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <h2 style="margin-left: 33%">Booking Homestay</h2>
                    <div class="collapse navbar-collapse" id="collapsibleNavId">
                        <ul class="navbar-nav ml-auto mt-2 mt-lg-0">
                            <li class="nav-item active">
                                <a class="nav-link" href="./signup.jsp">Đăng Kí </a>
                            </li>
                            <li class="nav-item active">
                                <a class="nav-link" href="./login.jsp">Đăng Nhập</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
        </section>

        <section class="section__login__form">
            <div class="container">
                <div class="row">
                    <div class="col-md-5 mx-auto">
                        <div id="first">
                            <div class="myform form ">
                                <div class="logo mb-3">
                                    <div class="col-md-12 text-center">
                                        <h1>Đăng Nhập</h1>
                                    </div>
                                </div>
                                <form action="./loginController" method="post">
                                    <div class="form-group">

                                        <input type="email" name="email" class="form-control" id="email" aria-describedby="emailHelp" placeholder="Enter email" value="${email}" required />
                                    </div>
                                    <div class="form-group">

                                        <input type="password" name="password" id="password" class="form-control" aria-describedby="emailHelp" placeholder="Enter Password" value="${password}" required/>
                                    </div>
                                    <div class="form-group">
                                        <a style="font-size: 15px" class="text-center" href="forgotPass.jsp">Forgot Password?</a>
                                    </div> 

                                    <div class="col-md-12 text-center ">
                                        <h6 style="color:red">${accountLoginError}</h6>
                                        <button type="submit" class=" btn btn-block mybtn btn-primary tx-tfm">Login</button>
                                    </div>
                                    <div class="col-md-12 ">
                                        <div class="login-or">
                                            <hr class="hr-or">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <p class="text-center">Don't have account? <a href="./signup.jsp" id="signup">Sign up here</a></p>
                                    </div>
                                       
                                </form>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <!-- Optional JavaScript -->
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    </body>

</html>