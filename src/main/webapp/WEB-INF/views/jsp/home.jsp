<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Link Sharing</title>

</head>
<body>
<h1>Add New User</h1>
<form method="post" action="loginUser" >
    <table >
        <tr>
            <td>Credentials : </td>

            <td><input type="text" name="credentials" /></td>
        </tr>
        <tr>
            <td>Password : </td>

            <td><input type="password"  name="password"/></td>
        </tr>
        <tr>

            <td> </td>
            <td><input type="submit" name="login" value="Save" /></td>
        </tr>
    </table>
</form>
<form:form method="post" action="registerUser"  modelAttribute="user" enctype="multipart/form-data">
    <table >
        <tr>
            <td>First Name : </td>

            <td><form:input type="text" path="firstName"  required="true" /></td>
        </tr>
        <tr>
            <td>Last Name : </td>

            <td><form:input type="text" path="lastName"  required="true" /></td>
        </tr>
        <tr>
            <td>Username : </td>

            <td><form:input type="username" path="username" id="username" required="true" /></td>
        </tr>
        <tr>
            <td>Email : </td>

            <td><form:input type="email" path="emailId"  id="emailId" required="true"/></td>
        </tr>
        <tr>
            <td>Password : </td>

            <td><form:input type ="password" path="password" id="password" required="true" /></td>
        </tr>
        <tr>
            <td>Confirm Password : </td>

            <td><input type ="password"  id="confirmPassword" required="true" /></td>
        </tr>
        <tr>
            <td>Photo : </td>

            <td><form:input type ="file" path="" name="photo"   /></td>
        </tr>
        <tr>

            <td> </td>
            <td><input type="submit" id="register" value="Save" /></td>
        </tr>
    </table>
</form:form>



<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
    $(function () {
    var check=[0,0,0];
            $('#confirmPassword').focusout(function () {
                if($("#password").val()!==$("#confirmPassword").val())
                {
                    alert("password mismatch");
                    check[0]=1;
                }
                else
                {
                    check[0]=0;
                }


            });

$("#username").focusout(function () {
        ajaxCall($("#username").val(),this,1);
});
$("#emailId").focusout(function () {
            ajaxCall($("#emailId").val(),this,2);
        });
function ajaxCall(credential, tag,index) {
    $.ajax({
        url:"validateUsername",
        data:{credential:credential},
        type:"post",
        success:function(r)
        {
            if(r==="true")
            {

                console.log(tag);
                check[index]=1;
            }
            else
            {
                check[index]=0;
            }


        },
        error:function(e)
        {
            console.log(e);
        }
    });
}

       $("#register").on("click",function (e) {
            if(check[0]!=0||check[1]!=0||check[2]!=0)
            {
                e.preventDefault();
                console.log("Error");
            }
            else
            {
                console.log("Success");
            }
        });


    });

</script>
</body>
</html>
