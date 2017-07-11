<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Link Sharing</title>

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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
    $(function () {

            $('#addTopic').click(function () {
                $.ajax({
                    url:"addTopic",
                    data:{topicName:$("#topicName").val(),
                        visibility:$("#visibility").val()},
                    type:"post",
                    success:function(r)
                    {
                       console.log(r);

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
