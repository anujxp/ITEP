<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Contact Us - Need Connect</title>
<%@ include file="__bootstrap_link.jsp" %>
</head>
<body class="bg-light d-flex flex-column min-vh-100">

    <%@ include file="__header.jsp" %>

    <div class="container mt-5 mb-5">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card border-0 shadow-lg" style="border-radius: 20px; overflow: hidden;">
                    <div class="row no-gutters">
                        <div class="col-md-5 bg-primary text-white p-5 d-flex flex-column justify-content-center text-center">
                            <i class="fa-solid fa-envelope-open-text fa-4x mb-3"></i>
                            <h4>Get in Touch</h4>
                            <p class="small">Have questions? We are here to help you 24/7.</p>
                        </div>
                        <div class="col-md-7 p-5 bg-white">
                            <h4 class="font-weight-bold mb-4">Send a Message</h4>
                            <form>
                                <div class="form-group">
                                    <input type="text" class="form-control rounded-pill bg-light border-0" placeholder="Your Name">
                                </div>
                                <div class="form-group">
                                    <input type="email" class="form-control rounded-pill bg-light border-0" placeholder="Your Email">
                                </div>
                                <div class="form-group">
                                    <textarea class="form-control bg-light border-0" rows="4" style="border-radius: 20px;" placeholder="How can we help?"></textarea>
                                </div>
                                <button type="button" onclick="alert('Message Sent! We will contact you shortly.')" class="btn btn-primary btn-block rounded-pill font-weight-bold shadow-sm">Send Message</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <%@ include file="__footer.jsp" %>

</body>
</html>