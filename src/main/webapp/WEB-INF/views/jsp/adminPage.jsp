<html>
<head>
    <title>Title</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="/resources/css/home.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <link href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.css" rel="stylesheet">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
        .table{
            display: none;
            width:100%;
        }

    </style>
</head>
<body>
<div class="container-fluid">
    <div class="row ">


<%@include file="header.jsp"%>

    </div>
</div>

<div class="container-fluid">
    <div class="row ">
        <div class="form-group">

            <select class="form-control input-lg" id="type">

            </select>
        </div>
        <div class="col-md-2"></div>
        <div class="col-md-8">
            <table class="table User ">
                <thead class="head-color ">
                <tr >
                    <th >Id</th>
                    <th >Username</th>
                    <th >Email Id</th>
                    <th >First Name</th>
                    <th>Last Name</th>
                    <th >Active</th>
                    <th >Admin</th>
                </tr>
                </thead>
                <tbody id="display">
                </tbody>

            </table>
        </div>
        <div class="col-md-4"></div>
        <div class="col-md-8">
            <table class="table Topic">
                <thead class="head-color">
                <tr>
                    <th>Id</th>
                    <th>Topic Name</th>
                    <th>Creator</th>
                    <th>Visibility</th>
                    <th>Delete</th>

                </tr>
                </thead>
                <tbody id="display">
                </tbody>

            </table>
        </div>
        <div class="col-md-4"></div>
        <div class="col-md-8">
            <table class="table Resource">
                <thead class="head-color">
                <tr>
                    <th>Id</th>
                    <th>Creator</th>
                    <th>Topic</th>
                    <th>Resource Type</th>
                    <th>Delete</th>

                </tr>
                </thead>
                <tbody id="display">
                </tbody>

            </table>
        </div>
    </div>


</div>


    <script>

        $(function () {


            fetchData("all");
            addOption();
            $('.${classname}').css('display','block');

            function fetchData(type)
            {
                $.ajax({
                    url:"fetchDataForAdmin",
                    data:{className:"${classname}",
                        type:type
                    },
                    type:"post",
                    success:function(r)
                    {   $("#display").empty();
                        if("${classname}"=="User")
                        {
                            setAsUsers (r) ;
                        }
                        else if("${classname}"=="Topic")
                        {
                            setAsTopics(r);
                        }
                        else
                        {
                            setAsPosts(r);
                        }

                    },
                    error:function(e)
                    {
                        console.log(e);
                    }
                });
            }

            $("#type").change(function () {

                fetchData($(this).val());
            });


            function setAsUsers(data) {
                $(".${classname} > #display").empty();

                $.each(data,function (k,v) {

                    $(".${classname} > #display").append("<tr>\n" +
                        "            <th>"+v.userId+"</th>\n" +
                        "            <th>"+v.username+"</th>\n" +
                        "            <th>"+v.emailId+"</th>\n" +
                        "            <th>"+v.firstName+"</th>\n" +
                        "            <th>"+v.lastName+"</th>\n" +
                        "            <th>"+v.active+"</th>\n" +
                        "            <th>"+v.admin+"</th>\n" +
                        "        </tr>");


                })


            }
            function setAsTopics(data) {
                $(".${classname} > #display").empty();

                $.each(data, function (k, v) {

                    $(".${classname} > #display").append("<tr>\n" +
                        "            <th>"+v.topicId+"</th>\n" +
                        "            <th>"+v.name+"</th>\n" +
                        "            <th>"+v.createdBy.username+"</th>\n" +
                        "            <th>"+v.visibility+"</th>\n" +
                        "            <th>Delete</th>\n" +
                        "        </tr>");


                })


            }
            function setAsPosts(data) {


                $(".${classname} > #display").empty();

                $.each(data,function (k,v) {

                    $(".${classname} > #display").append("<tr>\n" +
                        "            <th>"+v.resourceId+"</th>\n" +
                        "            <th>"+v.createdBy.username+"</th>\n" +
                        "            <th>"+v.topic.name+"</th>\n"+
                        "            <th>"+v.resourceType+"</th>\n" +
                        "            <th>Delete</th>\n" +
                        "        </tr>");


                })


            }


            function addOption() {
                var option1,option2,option3;
                switch ("${classname}")
                {
                    case "User":
                    {
                        option1 =  $('<option></option>').attr("value", "all_users").text("All Users");
                        option2 = $('<option></option>').attr("value", "Active").text("Active");
                        option3 = $('<option></option>').attr("value", "inActive").text("Inactive");
                        break;
                    }
                    case "Topic":
                    {
                        option1 =  $('<option></option>').attr("value", "all_topics").text("All Topics");
                        option2 = $('<option></option>').attr("value", "Public").text("Public");
                        option3 = $('<option></option>').attr("value", "Private").text("Private");
                        break;
                    }
                    case "Resource":
                    {

                        option1 =  $('<option></option>').attr("value", "all_posts").text("All Posts");
                        option2 = $('<option></option>').attr("value", "Link").text("Link");
                        option3 = $('<option></option>').attr("value", "Document").text("Document");
                        break;
                    }
                }


                $("#type").append(option1);
                $("#type").append(option2);
                $("#type").append(option3);
            }


        });
    </script>

</body>
</html>
