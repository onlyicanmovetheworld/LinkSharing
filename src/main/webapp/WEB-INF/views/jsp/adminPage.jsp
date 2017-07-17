<%--
  Created by IntelliJ IDEA.
  User: Shubham
  Date: 16-07-2017
  Time: 06:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>


<h1>Admin</h1>
<select id="className">
    <option value="User" selected>Users</option>
    <option value="Topic">Topics</option>
    <option value="Resource">Posts</option>
</select>
<select id="type">
    <option value="All Users" selected>Users</option>
    <option value="Active">Active</option>
    <option value="inActive">InActive</option>
</select>
<div id="display"></div>



<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>

    $(function () {

        fetchData("User","all");


        $("#className").change(function () {

            fetchData($('#className').val(),"all");

        });


        function fetchData(className,type)
        {
            $.ajax({
                url:"fetchDataForAdmin",
                data:{className:className,
                        type:type
                },
                type:"post",
                success:function(r)
                {   $("#display").empty();
                    if(className=="User")
                    {
                       setAsUsers (r) ;
                    }
                    else if(className=="Topic")
                    {
                        setAsTopics(r);
                    }
                    else
                    {
                        setAsPosts(r);
                    }
                console.log(className);
                },
                error:function(e)
                {
                    console.log(e);
                }
            });
        }

        $("#type").change(function () {
            fetchData($("#className").val(),$(this).val());
        });


function setAsUsers(data) {

            var option1,option2,option3;
    option1 =  $('<option></option>').attr("value", "all_users").text("All Users");
    option2 = $('<option></option>').attr("value", "Active").text("Active");
    option3 = $('<option></option>').attr("value", "inActive").text("Inactive");
    addOption(option1,option2,option3);


            $.each(data,function (k,v) {

                $("#display").append("<p>"+v.username+"</p>");


            })


}
function setAsTopics(data) {

    var option1,option2,option3;
    option1 =  $('<option></option>').attr("value", "all_topics").text("All Topics");
    option2 = $('<option></option>').attr("value", "Public").text("Public");
    option3 = $('<option></option>').attr("value", "Private").text("Private");
    addOption(option1,option2,option3);

    $.each(data, function (k, v) {

        $("#display").append("<p>" + v.name + "</p>");


    })


}
function setAsPosts(data) {

    var option1,option2,option3;
    option1 =  $('<option></option>').attr("value", "all_posts").text("All Posts");
    option2 = $('<option></option>').attr("value", "Link").text("Link");
    option3 = $('<option></option>').attr("value", "Document").text("Document");

    addOption(option1,option2,option3);

            $.each(data,function (k,v) {

                $("#display").append("<p>"+v.link+"</p>");


            })


        }


        function addOption(option1,option2,option3) {

            $("#type").empty();

            $("#type").append(option1);
            $("#type").append(option2);
            $("#type").append(option3);
        }


    });
</script>

</body>
</html>
