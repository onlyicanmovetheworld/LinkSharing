<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Link Sharing</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.css" rel="stylesheet">
</head>
<body>

<h1>Welcome,<%=session.getAttribute("username")%></h1>
<h2>Add New Topic</h2>
<form method="post" action="javascript:void(0);" >
    <table >
        <tr>
            <td>Topic Name : </td>

            <td><input type="text" id="topicName" /></td>
        </tr>
        <tr>
            <td>Visibility : </td>

            <td><input type="text"  id="visibility"/></td>
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

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.js"></script>
<script>
    $(function () {
    var ambiquity = false;

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
                                label: item,
                                value: item
                            }
                        }));
                    }
                });
            },
            autoFocus: true,
            minLength: 0
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
                            topicName: $("#topicName").val(),
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
        });
</script>
</body>
</html>
