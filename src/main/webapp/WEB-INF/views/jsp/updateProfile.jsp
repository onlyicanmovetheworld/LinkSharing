<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Title</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="/resources/css/home.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <link href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.css" rel="stylesheet">
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="modal fade" id="photoModal" role="dialog">
            <div class="modal-dialog">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header head-color" style="border-radius:6px 6px 0 0;">
                        <button type="button" class="close" style="color:#efefef !important;" data-dismiss="modal">X</button>
                        <h3 >Add Topic</h3>
                    </div>
                    <div class="modal-body">
                        <ul class="list-group">

                            <li class="list-group-item">
                                <form method="post" action="updatePhoto"  enctype="multipart/form-data">
                                    <div class="form-group">
                                        <label for="file">Image*</label>
                                        <input type="file" class="form-control" accept="image/*"  id="file" name="file" placeholder="Browse" required="true">
                                    </div>
                                    <button type="submit"  class="btn btn-primary"  style="margin-left: 78%;">Update Image</button>
                                </form>
                            </li>
                        </ul>

                    </div>
    </div>
</div>
        </div>

    </div>
</div>
<div class="container-fluid">
    <div class="row ">

        <%@include file="header.jsp"%>

    </div>
</div>
<div class="container-fluid">
<div class="row">
    <div class="col-md-1">

    </div>
    <div class="col-md-3">
        <img src="imageFetch?username=<%=session.getAttribute("username")%>" style="width: 100%; "/>
        <img id="edit"src="/resources/assets/edit.png" style="background:rgba(0,0,0,0.2);position: absolute; bottom:0;right:5%; width: 20%;" data-toggle="modal" data-target="#photoModal">
    </div>
    <div class="col-md-1">

    </div>
    <div class="col-md-7">
<ul class="list-group">
<li class="list-group-item head-color">Update Details </li>
<li class="list-group-item " id="child-disabled">
    <form method="post" action="updateUserProfileDetails"  id="updateDetails" >
        <div class="form-group">
            <label >First name*</label>
            <input type="text" name="firstName"  required="true"  class="form-control" value="${userData.firstName}" title="Click to edit" />

        </div>
        <div class="form-group">
            <label >Last name*</label>
            <input type="text" name="lastName"  required="true"  class="form-control" value="${userData.lastName}" title="Click to edit" />

        </div>

        <div class="form-group">
            <label >Username</label>
            <input type="text" name="username" id="username" required="true" class="form-control"  value="${userData.username}" title="Click to edit" />

        </div>
        <div class="checkbox">
            <button type="submit" id="detailBtn"class="btn btn-primary" style="margin-left: 78%; clear: both;">Update Details</button>
        </div>
    </form>
</li>
</ul>


        <ul class="list-group">
            <li class="list-group-item head-color">Update Password</li>
            <li class="list-group-item">
                <form method="post" action="updateUserProfilePassword"  id="updatePassword" >
                    <div class="form-group">
                        <label for="oldPassword">Old Password*</label>
                        <input type ="password" name="oldPassword" id="oldPassword" required="true" class="form-control" placeholder="Enter password"/>
                    </div>
                    <div class="form-group">
                        <label for="oldPassword">New Password*</label>
                        <input type ="password" name="newPassword" id="newPassword" required="true" class="form-control" placeholder="Enter password"/>
                    </div>
                    <div class="form-group">
                        <label for="confirmPassword">Confirm password*</label>
                        <input type ="password"  name="confirmPassword" id="confirmPassword"  required="true" class="form-control"  placeholder="Confirm password">
                    </div>
                    <div class="checkbox">
                        <button type="submit" id="passwordBtn"class="btn btn-primary" style="margin-left: 75%; clear: both;">Update Password</button>
                    </div>
                </form>
            </li>
        </ul>
</div>
</div>
</div>

<script>
    $(function () {






        $('#confirmPassword').focusout(function () {
            if($("#newPassword").val()!==$("#confirmPassword").val())
            {
                alert("password mismatch");
                $('#passwordBtn').attr('disabled',true);
            }
            else
            {
                $('#passwordBtn').attr('disabled',false);
            }


        });


        $('#child-disabled').on('focusout','input',function (e) {



            if($(this).val()=="")
            {
                switch($(this).attr('name'))
                {
                    case "firstName": {
                        $(this).val("${userData.firstName}");
                        break;
                    }
                    case "lastName": {
                        $(this).val("${userData.lastName}");
                        break;
                    }
                    case "username": {
                        $(this).val("${userData.username}");
                        break;
                    }
                }
            }
        });
$("#username").focusout(function () {

    $.ajax({
        url:"validateUsername",
        data:{credential:$("#username").val()},
        type:"post",
        success:function(r)
        {
            if(r==="true"&&$("#username").val()!="${userData.username}")
            {

               alert("username acquired");
               $('#detailBtn').attr('disabled',true);
            }
            else
            {
                $('#detailBtn').attr('disabled',false);
            }


        },
        error:function(e)
        {
            console.log(e);
        }
    });

});


    });
</script>


</body>
</html>
