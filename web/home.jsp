<%-- 
    Document   : index
    Created on : Feb 3, 2024, 10:52:54 AM
    Author     : sodok
--%>

<%@page import="model.Place"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.AccountUser"%>
<%@page import="dal.AccountDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">

    <head>
        <title>Booking Homestay</title>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="./css/style.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/css/all.min.css">
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    </head>

    <body>

        <section>S
            <nav class="navbar navbar-expand-sm navbar-light bg-light">
                <div class="container">
                    <a class="navbar-brand" href="indexController"><img src="./image/logo.png" class="logo__brand" style="width: 45px; height: 40px"></a>
                    <button class="navbar-toggler d-lg-none" type="button" data-toggle="collapse" data-target="#collapsibleNavId" aria-controls="collapsibleNavId" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <form style="width: 500px; display: flex" action="searchRooms" method="get">

                        <input class="form-control " type="text" name="keyword" placeholder="Tìm kiếm tên khách sạn, địa danh" aria-label="Search">

                        <button type="submit" class=" btn btn-outline-secondary" style="margin-left:  3px">Search</button>
                    </form> 
                    <div class="collapse navbar-collapse" id="collapsibleNavId">

                        <ul class="navbar-nav ml-auto mt-2 mt-lg-0">

                            <%
                                Cookie cookie = null;
                                Cookie[] cookies = null;
                                String email = "";
                                String password = "";
                                String name = (String) request.getAttribute("UserLogin");
                                // Get an array of Cookies associated with this domain
                                cookies = request.getCookies();
                                if (cookies != null) {
                                    for (int i = 0; i < cookies.length; i++) {
                                        cookie = cookies[i];
                                        if (cookie.getName().equals("email")) {
                                            email = cookie.getValue();
                                        }
                                        if (cookie.getName().equals("password")) {
                                            password = cookie.getValue();
                                        }

                                    }
                                }
                                AccountDAO dal = new AccountDAO();
                                AccountUser account = dal.getAccountByUP(email, password);

                                if (account != null) {
                                    name = account.getName();
                                }


                            %>            

                            <%      if (name != null) {


                            %>
                            <div class="dropdown">
                                <a class="btn btn-secondary dropdown-toggle btn-sm"  href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <%= name%>
                                </a>

                                <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                                    <a class="dropdown-item" href="indexController">Trang chủ</a>
                                    <a class="dropdown-item" href="information.jsp">Thông tin</a>
                                    <a class="dropdown-item" href="bookingHistoryController">Lịch sử Booking</a>                                
                                    <a class="dropdown-item" href="listLikeController">Danh sách yêu thích</a>
                                    <a class="dropdown-item" href="logoutController" style="color: Red">Đăng xuất</a>
                                </div>
                            </div>


                            <%
                            } else {
                            %>
                            <li class="nav-item active">
                                <a class="nav-link" href="./signup.jsp">Đăng Kí </a>
                            </li>
                            <li class="nav-item active">
                                <a class="nav-link" href="./login.jsp">Đăng Nhập</a>
                            </li>
                            <%
                                }
                            %>

                        </ul>
                    </div>
                </div>
            </nav>
        </section>
                            <br>
        <section class="greeting__wrap__section">
            <div class="container">
                <h4 class="greeting__wrapper">Chào mừng bạn đến với Booking Homestay</h4>
                <p class="greeting__text__wrapper"> Đặt chỗ ở, homestay, hotel , trải nghiệm và nhiều hơn nữa trên Booking Homestay
                </p>
            </div>
        </section>      

        <section>
            <div class="container service__homemie__wrap ">
                <div class="row">
                    <div class="col-md-4" style="padding-left: 0px; padding-right: 0px">
                    <a href="homestayController" style="text-decoration: none ; color: black">
                        <div class="card service__item shadow" >
                            <img class="card-img-top image__service img-fluid" src="https://images2.thanhnien.vn/thumb_w/640/528068263637045248/2024/2/22/h1-1708601000205961198307.jpg" alt="Card image cap">
                            <div class="card-body">
                                <h5 class="card-title">Homestay</h5>    
                                <p class="card-text">Căn hộ dịch vụ & Biệt thự</p>
                            </div>
                        </div>
                    </a>
                    </div>
                     <div class="col-md-4" style="padding-left: 0px; padding-right: 0px">
                    <a href="#" style="text-decoration: none ; color: black">
                        <div class="card service__item shadow" >
                            <img class="card-img-top image__service img-fluid" src="https://media.istockphoto.com/id/1337257093/vi/anh/c%E1%BA%B7p-v%E1%BB%A3-ch%E1%BB%93ng-tr%E1%BA%BB-h%E1%BA%A1nh-ph%C3%BAc-v%E1%BB%9Bi-b%C3%A1nh-r%C3%A1n-b%C6%A1m-h%C6%A1i-tr%C3%AAn-b%C3%A3i-bi%E1%BB%83n.jpg?s=612x612&w=0&k=20&c=6tPrVnHx45cBEltMtNPBiwT2qQ_fGbg2g19TNfJISaQ=" alt="Card image cap">
                            <div class="card-body">
                                <h5 class="card-title">Kì Nghỉ</h5>
                                <p class="card-text">Du lịch ,thăm quan</p>
                            </div>
                        </div>
                     </div>
                    </a>
                     <div class="col-md-4" style="padding-left: 0px; padding-right: 0px">
                    <a href="#" style="text-decoration: none ; color: black">
                        <div class="card service__item shadow" >
                            <img class="card-img-top img-fluid image__service" src="http://centerhotelbacninh.com/sites/c/ce/centerhotel/uploads/ckfinder/images/1.jpg" alt="Card image cap">
                            <div class="card-body">
                                <h5 class="card-title">Hotel</h5>
                                <p class="card-text">Đặt phòng khách sạn</p>
                            </div>
                        </div>
                    </a>
                     </div>
                </div>
            </div>
        </section>
        <br>
        <section class="place__travel__section">
            <div class="container ">
                <h4 class="greeting__wrapper">Địa điểm nổi bật</h4>
                <p>Cùng Booking Homestay bắt đầu chuyến hành trình chinh phục mọi nẻo đường cùng bạn</p>
                <hr/>

                <%
                    ArrayList<Place> Places = (ArrayList<Place>) request.getAttribute("places");
                %> 

                <div class="row">

                    <% for (Place s : Places) {
                    %>


                    <div class="col-lg-3 no__padding__right travel__card__column">
                        <a href="linkController1?name=<%=s.getName()%>">
                            <div class="card travel__card__wrap box">
                                <img class="card-img-top travel__card__image img-fluid" style="height: 200px" src=<%=s.getImageLink()%> alt="Card image cap">
                                     <p class="travel__text__wrap card-text"><span class="travel__text__item" > <%=s.getName()%> </span></p>
                            </div>
                        </a>
                    </div>


                    <%}%>




                </div>
            </div>
        </section>

        <section class="footer__section">
            <!-- Footer -->
            <footer class="page-footer font-small mdb-color lighten-3 pt-4 footer__wrapper">

                <!-- Footer Links -->
                <div class="container text-center text-md-left">

                    <!-- Grid row -->
                    <div class="row">

                        <!-- Grid column -->
                        <div class="col-md-4 col-lg-3 mr-auto my-md-4 my-0 mt-4 mb-1">

                            <!-- Content -->
                            <h5 class="font-weight-bold text-uppercase mb-4">Booking HomeStay</h5>
                            <p>Booking Homestay hiện là nền tảng đặt phòng trực tuyến hàng đầu Việt Nam</p>
                            <p>Đồng hành cùng chúng tôi, bạn sẽ có những chuyến đi mang đầy trải nghiệm. Với Booking Homestay, việc đặt chỗ ở, biệt thự nghỉ dưỡng, khách sạn, nhà riêng, chung cư... trở nên nhanh chóng, thuận tiện và dễ dàng.</p>

                        </div>
                        <!-- Grid column -->

                        <hr class="clearfix w-100 d-md-none">

                        <!-- Grid column -->
                        <div class="col-md-2 col-lg-2 mx-auto my-md-4 my-0 mt-4 mb-1">

                            <!-- Links -->
                            <h5 class="font-weight-bold text-uppercase mb-4">About</h5>

                            <ul class="list-unstyled">
                                <li>
                                    <p>
                                        <a href="#!" class="link__footer">Home</a>
                                    </p>
                                </li>
                                <li>
                                    <p>
                                        <a href="#!" class="link__footer">About Us</a>
                                    </p>
                                </li>
                                <li>
                                    <p>
                                        <a href="#!" class="link__footer">Contact</a>
                                    </p>
                                </li>
                                <li>
                                    <p>
                                        <a href="#!" class="link__footer">Help</a>
                                    </p>
                                </li>
                            </ul>

                        </div>
                        <!-- Grid column -->

                        <hr class="clearfix w-100 d-md-none">

                        <!-- Grid column -->
                        <div class="col-md-4 col-lg-3 mx-auto my-md-4 my-0 mt-4 mb-1">

                            <!-- Contact details -->
                            <h5 class="font-weight-bold text-uppercase mb-4">Address</h5>

                            <ul class="list-unstyled">
                                <li>
                                    <p> <i class="fas fa-home mr-3"></i> FPT University </p>
                                </li>
                                <li>
                                    <p> <i class="fas fa-envelope mr-3"></i> sonpthe170741@fpt.edu.vn </p>
                                </li>
                                <li>
                                    <p>
                                        <i class="fas fa-phone mr-3"></i> 0985173658</p>
                                </li>
                            </ul>

                        </div>


                        <hr class="clearfix w-100 d-md-none">


                        <div class="col-md-2 col-lg-2 text-center mx-auto my-4">

                            <h5 class="font-weight-bold text-uppercase mb-4">Follow Us</h5>


                            <a type="button" class="btn-floating btn-fb">
                                <i class="fab fa-facebook-f"></i>
                            </a>

                            <a type="button" class="btn-floating btn-gplus">
                                <i class="fab fa-google-plus-g"></i>
                            </a>


                        </div>


                    </div>


                </div>
                
                <div class="footer-copyright text-center py-3">© 2024 Copyright: Booking Homestay</div>

            </footer>

        </section>


        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    </body>

</html>
