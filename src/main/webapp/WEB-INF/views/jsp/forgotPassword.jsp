<%--
  Created by IntelliJ IDEA.
  User: Shubham
  Date: 13-07-2017
  Time: 08:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="/resources/css/home.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
</head>
<body>
<div class="container-fluid">
    <div class="row s_header">
			<pre class="s_orgName pull-left">TO
THE
NEW</pre>
        <img class="s_orgLogo pull-left" src="/resources/assets/ttn.png">
        <ul class="pull-right list-inline ">
            <li ><h4><a href="/">Home</a></h4></li>
            <li><h4>Quick Help</h4></li>
        </ul>

    </div>
</div>


    <div class="container " >
        <div class="row">
            <div class="col-md-5" style="margin-top:5%;">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="text-center">

                            <h2 class="text-center">Forgot Password?</h2>
                            <p>Please provide us your Email Address. We are here to help you</p>
                            <div class="panel-body">

                                <form id="otpForm" action="javascript:void(0)" role="form" autocomplete="off" class="form" method="post">

                                    <div class="form-group">
                                        <div class="input-group">
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-envelope color-blue"></i></span>
                                            <input id="email" name="email" placeholder="Email Address" class="form-control" type="email">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <input name="recover-submit" class="btn btn-lg btn-primary btn-block" value="Send OTP" id="sendOTP" type="submit">
                                    </div>
                                    <input type="hidden" class="hide" name="token" id="token" value="">
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>




            <div class="col-md-7">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="text-center">

                            <h2 class="text-center">Update Password</h2>
                            <p>You can reset your password here.</p>
                            <div class="panel-body">
                                <form id="passwordEntryform" role="form" action="javascript:void(0)" method="post">
                                    <div class="form-group">
                                        <div class="input-group">
                                    <span class="input-group-addon">
                                <label>Email</label>
                                    </span>
                                            <input type="email" id="confirmEmail" placeholder="Enter email" class="form-control">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="input-group">
                                    <span class="input-group-addon">
                                        <label>OTP</label></span>
                                            <input type="text" id="otp" class="form-control" placeholder="Enter otp">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="input-group">
                                    <span class="input-group-addon">
                                        <label>Password</label></span>
                                            <input type="password" id="password" placeholder="Enter password" class="form-control">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="input-group">
                                    <span class="input-group-addon">
                                        <label>Confirm password</label></span>
                                            <input type="password" name="confirm_password" id="confirmPassword" placeholder="Enter password again" class="form-control">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <input type="submit" form="passwordEntryform"  id="updatePassword" class="btn btn-lg btn-primary btn-block">
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>


    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
    $(function(){

        var available=true;
        var similar = true;
        $("#email").focusout(function () {
            $.ajax({
                url:"validateUsername",
                data:{credential:$("#email").val()},
                type:"post",
                success:function(r)
                {
                    if(r==="true")
                    {

                        available=true;

                    }
                    else
                    {
                        available=false;
                        alert("Email doesn't exist");
                    }


                },
                error:function(e)
                {
                    console.log(e);
                }
            });
        });

        $("#sendOTP").click(function () {
            if(available) {
                $.ajax({
                    url: "sendOTP",
                    data: {email: $("#email").val()},
                    type: "post",
                    success: function (r) {
                        if (r === "true") {
                            $('#otpForm')[0].reset();
                            alert("OTP sent to \n Please use it to reset password");
                        }
                        else {

                        }


                    },
                    error: function (e) {
                        console.log(e);
                    }
                });
            }
        });


        $('#confirmPassword').focusout(function () {
            if($("#password").val()!==$("#confirmPassword").val())
            {
                alert("password mismatch");
                similar=false;
            }
            else
            {
                similar=true;
            }


        });


        $("#updatePassword").click(function () {
            if(similar) {
                $.ajax({
                    url: "updatePassword",
                    data: {otp: $("#otp").val(),
                        password:$("#password").val(),
                        email:$("#confirmEmail").val()
                    },
                    type: "post",
                    success: function (r) {
                        if (r === "true") {

                            window.location.replace("/");

                        }
                        else {

                            alert("Wrong OTP..!!");
                        }


                    },
                    error: function (e) {
                        console.log(e);
                    }
                });
            }

        });


    });
</script>

</body>
</html>
