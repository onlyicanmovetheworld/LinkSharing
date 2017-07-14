<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Title</title>
</head>
<body>

<div id="topicData">
<c:forEach items="${topicList}" var="item">
    <c:choose>
    <c:when test="${item.resourceType=='Document'}">
        <a href='/download?filePath="+${item.link}+"' download>${item.link}</a>
    </c:when>
    <c:otherwise>
        <a href="${item.link}" target="_blank">${item.description}</a>
    </c:otherwise>
    </c:choose>


</c:forEach>

</div>


<button id="prev">Prev</button><button id="next">Next</button>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
    $(function () {

        var index = 0;


        $('#prev').attr('disabled',true);

        $('#next').click(function () {
            index+=2;

            if(index>0)
            {
                $('#prev').attr('disabled',false);
            }
            fetchData();
        });
        $('#prev').click(function () {
            index-=2;
            if(index==0)
            {
                $('#prev').attr('disabled',true);
            }
            fetchData();
        });

        function fetchData()

        {
        $.ajax({
            url: "searchAjaxTopic",
            data: {topicName:"${topicName}",
                    index:index
            },
            type: "post",
            success: function (r) {

              $("#topicData").html("");
                $.each(r, function(k,v) {
                    console.log(k+""+v);
                    if(v.resourceType=="Document")
                    { $("#topicData").append( "<a href='/download?filePath=\"+"+v.link+"+\"' download>"+v.link+"</a><br>");}
                    else
                    {
                        $("#topicData").append("<a href="+v.link+" target='_blank'>"+v.link+"</a><br>");
                    }
                });

            },
            error: function (e) {
                console.log(e);
            }
        });
    }


    });

</script>
</body>
</html>
