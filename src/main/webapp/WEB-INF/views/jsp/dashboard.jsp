<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Link Sharing</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.css" rel="stylesheet">
</head>
<style>
    p{
        color:deepskyblue;
    }

</style>
<body>

<h1>Welcome,<%=session.getAttribute("username")%></h1>
<h2>Add New Topic</h2>
<form method="post" action="javascript:void(0);" >
    <table >
        <tr>
            <td>Topic Name : </td>

            <td><input type="text" id="topicName" name="topicName" ></td>
        </tr>
        <tr>
            <td>Visibility : </td>

            <td><select  id="visibility" name="visibility">
                <option value="Public">Public</option>
                <option value="Private">Private</option>
            </select></td>
        </tr>
        <tr>

            <td> </td>
            <td><input type="submit" value="Save" id="addTopic" /></td>
        </tr>
    </table>
</form>


<input type="text" id="search">
<list id="result">
</list>

    <form method="post" action="javascript:void(0);">
        <table >
            <tr>
                <td>Link : </td>

                <td><input type="text" id="link" name="link" ></td>
            </tr>
            <tr>
                <td>Link : </td>

                <td><input type="text" id="descrip" name="desc" ></td>
            </tr>
            <tr>
                <td>Topic : </td>

                <td><input type="text" id="topi" name="topic">

               </td>
            </tr>
            <tr>

                <td> </td>
                <td><input type="submit" value="Save" id="addLinkResource" /></td>
            </tr>
        </table>
    </form>


    <form method="post" action="addDocument" enctype="multipart/form-data" id="documentUpload">
        <table >
            <tr>
                <td>Link : </td>

                <td><input type="file" id="file" name="file" ></td>
            </tr>
            <tr>
                <td>Link : </td>

                <td><input type="text" id="desc" name="desc" ></td>
            </tr>
            <tr>
                <td>Topic : </td>

                <td><input type="text" id="topic" name="topic"></td>
            </tr>
            <tr>

                <td> </td>
                <td><input type="submit" value="Save" id="addDocumentResource" /></td>
            </tr>
        </table>
    </form>
<table>
<tr>
    <td>Link : </td>

    <td><input type="email" id="email" name="desc" ></td>
</tr>
<tr>
    <td>Topic : </td>

    <td><input type="text" id="inviteTopic" name="topic"></td>
</tr>
<tr>

    <td> </td>
    <td><input type="submit" value="Send" id="sendInvite" /></td>
</tr>
</table>

<br>
<br>



<br>
<br>
<button id="logout"value="logout">LogOut</button>



<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.form/4.2.1/jquery.form.min.js"></script>

<script>
    $(function () {
    var ambiquity = false;


    $('#logout').click(function () {
        window.location.replace("/logout");

    });



        $('#documentUpload').ajaxForm({
            success: function(msg) {
                alert("File has been uploaded successfully");
            },
            error: function(msg) {
               alert("Couldn't upload file");
            }
        });


        $('#sendInvite').click(function () {


            $.ajax({
                url : "sendInvite",
                type:"post",
                accept:"application/json",
                data: {
                    email:$("#email").val(),
                    topic:$("#inviteTopic").val()
                },
                success: function( data ) {
                    alert("Send Invitation");
                    },
                error:function(e)
                {
                    console.log(e);
                }

            });


        });

        $('#search').autocomplete({
            source: function( request, response ) {
                $.ajax({
                    url : "fetchTopics",
                    type:"post",
                    accept:"application/json",
                    data: {
                        topicLike:$("#search").val()
                    },
                    success: function( data ) {

                        response( $.map( data, function( item ) {
                            return {
                                label: item[0]+"By"+item[1],
                                value: item[0]+"By"+item[1],
                                targetUrl:"/searchTopic?topicName="+item[0]+"By"+item[1]+"&index=0"
                            }
                        }));},
                        error:function(e)
                        {
                            console.log(e);
                        }

                });
            },
            select: function (event, ui) {


                window.location.href=ui.item.targetUrl;


            },
            autoFocus: true,
            minLength: 1
        }).data("ui-autocomplete")._renderItem = function (ul, item) {
            return $("<li>")
                .data("item.autocomplete", item)
                .append("<p style='color:blue;'>" + item.label + "</p>" + "<p style='color:red;+'>"+item.value + "</p>")
                .appendTo(ul);
                    };


        $('#topic').autocomplete({
            source: function( request, response ) {
                $.ajax({
                    url : "fetchSubscribedTopics",
                    type:"post",
                    accept:"application/json",
                    data: {
                        topicLike:$("#topic").val()
                    },
                    success: function( data ) {

                        response( $.map( data, function( item ) {
                            return {
                                label: item[0]+"By"+item[1],
                                value: item[0]+"By"+item[1]
                            }
                        }));},
                    error:function(e)
                    {
                        console.log(e);
                    }

                });
            },
            autoFocus: true,
            minLength: 1
        });


        $('#inviteTopic').autocomplete({
            source: function( request, response ) {
                $.ajax({
                    url : "fetchSubscribedTopics",
                    type:"post",
                    accept:"application/json",
                    data: {
                        topicLike:$("#inviteTopic").val()
                    },
                    success: function( data ) {

                        response( $.map( data, function( item ) {
                            return {
                                label: item[0]+"By"+item[1],
                                value: item[0]+"By"+item[1]
                            }
                        }));},
                    error:function(e)
                    {
                        console.log(e);
                    }

                });
            },
            autoFocus: true,
            minLength: 1
        });


        $('#topi').autocomplete({
            source: function( request, response ) {
                $.ajax({
                    url : "fetchSubscribedTopics",
                    type:"post",
                    accept:"application/json",
                    data: {
                        topicLike:$("#topi").val()
                    },
                    success: function( data ) {

                        response( $.map( data, function( item ) {
                            return {
                                label: item[0]+"By"+item[1],
                                value: item[0]+"By"+item[1]
                            }
                        }));},
                    error:function(e)
                    {
                        console.log(e);
                    }

                });
            },
            autoFocus: true,
            minLength: 1
        });




        $('#topicName').focusout(function () {

            $.ajax({
                url:"validateTopicName",
                data:{topicName:$("#topicName").val()},
                type:"post",
                success:function(r)
                {
                    if(r==="true")
                    {

                        ambiquity=true;
                        console.log("match found");
                    }
                    else
                    {
                        ambiquity=false;
                    }


                },
                error:function(e)
                {
                    console.log(e);
                }
            });

        });


            $('#addTopic').click(function () {

                if(!ambiquity) {
                    $.ajax({
                        url: "addTopic",
                        data: {
                            name: $("#topicName").val(),
                            visibility: $("#visibility").val()
                        },
                        type: "post",
                        success: function (r) {
                            console.log(r);

                        },
                        error: function (e) {
                            console.log(e);
                        }
                    });

                }
            });

        $('#addLinkResource').click(function () {


                $.ajax({
                    url: "addLink",
                    data: {
                        topic: $("#topi").val(),
                        link:$("#link").val(),
                        desc:$("#descrip").val()
                    },
                    type: "post",
                    success: function (r) {
                        console.log(r);

                    },
                    error: function (e) {
                        console.log(e);
                    }
                });


        });

        $(window).on('popstate', function(e) {
            if($.session.get("username")!=null)
            {   alert("hello");
                e.preventDefault();

            }
            else
            {
                alert("hello1");
            }

        });


        });


</script>
</body>
</html>
