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
</head>
<body>
    Email:<br>
    <input type="email" id="email" name="email"><br>
    <input type="submit" value="Send OTP" id="sendOTP" /><br><br>
    Email:<br>
    <input type="email" id="confirmEmail" name="email"><br>
    OTP:<br>
    <input type="number" id="otp" ><br>
    New Password:<br>
    <input type="password" id="password" ><br>
    Confirm New Password:<br>
    <input type="password" id="confirmPassword" ><br>
    <input type="submit" value="Update" id="updatePassword" />



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
