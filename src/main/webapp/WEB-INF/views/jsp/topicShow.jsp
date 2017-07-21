<%--
  Created by IntelliJ IDEA.
  User: Shubham
  Date: 20-07-2017
  Time: 11:57 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <div class="row ">

        <%@include file="header.jsp"%>

    </div>
</div>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-4 ">
            <ul class="list-group">
                <li class="list-group-item" >
                    <div class="media">
                        <div class="media-left">
                            <img src="imageFetch?username=${topic.createdBy.username}" width="100" height="100">
                        </div>
                        <div class="media-body">
                            <h3 class="media-heading">${topic.name}  <i>(${topic.visibility})</i><br><small><i>@${topic.createdBy.username} </i></small></h3>



                            <div class="pull-left " style="width:80px;">

                                <h4 style="width:100%;text-align:center;"><i>Subscriptions</i></h4>
                                <h4 style="width:100%;text-align:center;">${subNumber}</h4>

                            </div>


                            <div class="pull-right " style="width:80px;">

                                <h4 style="width:100%;text-align:center;"><i>Posts</i></h4>
                                <h4 style="width:100%;text-align:center;">${postNumber}</h4>

                            </div>
                        </div>
                    </div>


                </li>
            </ul>
        </div>
        <div class="col-md-1 ">
        </div>
        <div class="col-md-7 ">
            <ul class="list-group">
                <li class="list-group-item head-color" >Posts:${topic.name}</li>


                <c:if test="${empty resourceList}">
                    <li class="list-group-item " >Nothing to show</li>
                </c:if>
                <li class="list-group-item" id="inboxDiv">
                    <c:if test="${not empty resourceList}">
                        <c:forEach items="${resourceList}" var="item">
                            <div class="media" id="${item.resourceId}">

                                <div class="media-left">
                                    <img src="imageFetch?username=${item.createdBy.username}" width="120" height="120">
                                </div>
                                <div class="media-body">
                                     <p>${item.description}</p>
                                    <div class="pgd">
                                        <div class="soc">
                                            <a href="#" class="fa fa-facebook"></a>
                                            <a href="#" class="fa fa-twitter"></a>
                                            <a href="#" class="fa fa-google-plus"></a>
                                        </div>
                                        <div class="pull-right d-inline-block">


                                            <c:choose>
                                                <c:when test="${item.resourceType=='Document'}">
                                                    <a class="p-5" style="float:left" href='/download?filePath="+${item.link}+"' download>Download</a>
                                                </c:when>
                                                <c:otherwise>
                                                    <a class="p-5" style="float:left" href="${item.link}" target="_blank">View Link</a>
                                                </c:otherwise>
                                            </c:choose>
                                            <a   class="p-5 read" style="float:left">Mark as read</a>
                                            <a href="" class="p-5" style="float:left">View post</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <hr>
                        </c:forEach>
                    </c:if>
                </li>
                <li class="list-group-item">
                    <div>
                        <button type="submit" id="prev" class="btn btn-primary" style="width: 80px;" >Previous</button>
                        <button type="submit" id="next" class="btn btn-primary pull-right" style="width: 80px;" >Next</button>
                    </div>
                </li>

            </ul>
        </div>
    </div>
</div>
<script>
    $(function () {

        var index = 0;
        var maxSize="${maxSize}";

        setReadListener();

        function setReadListener() {
            $('.read').on('click',function (e) {

                $.ajax({
                    url: "markAsRead",
                    data: {
                        id:$(e.target).closest('div.media').attr('id')
                    },
                    type: "post",
                    success: function (r) {

                        fetchData();
                    },
                    error: function (e) {
                        console.log(e);
                    }
                });
            });
        }






        $('#prev').attr('disabled',true);

        if(index >= maxSize-10)
        {
            $('#next').attr('disabled',true);
        }

        $('#next').click(function () {
            index+=10;

            if(index>0)
            {
                $('#prev').attr('disabled',false);
            }
            if(index >= maxSize-10)
            {
                $('#next').attr('disabled',true);
            }
            fetchData();
        });
        $('#prev').click(function () {
            index-=10;
            if(index==0)
            {
                $('#prev').attr('disabled',true);
            }
            if(index < maxSize-10)
            {
                $('#next').attr('disabled',false);
            }
            fetchData();
        });

        function fetchData()

        {
            $.ajax({
                url: "fetchAjaxInbox",
                data: {
                    index:index
                },
                type: "post",
                success: function (r) {

                    $("#inboxDiv").html("");
                    $.each(r, function(k,v) {
                        var anchor;
                        if(v.resourseType=="Document")
                        {
                            anchor = "<a class='p-5' style='float:left' href='/download?filePath=\"+"+v.link+"+\"' download>Download</a>";
                        }
                        else
                        {
                            anchor= "<a class='p-5' style='float:left' href='"+v.link+"' target='_blank'>View Link</a>";
                        }
                        $('#inboxDiv').append("<div class='media' id='"+v.resourceId+"'> <div class='media-left'> " +
                            "<img src='imageFetch?username="+v.createdBy.username+"' width='120' height='120'></div>"+
                            " <div class='media-body'>"+
                            " <h4 class='media-heading'>"+v.createdBy.firstName+"  "+v.createdBy.lastName+" <small><i>@"+v.createdBy.username+
                            " </i></small><a href='' style='float:right; font-size:12px'>"+v.topic.name+"</a></h4>"+
                            "<p>"+v.description+"</p>"+
                            "<div class='pgd'>"+
                            "<div class='soc'>"+
                            "<a href='#' class='fa fa-facebook'></a>"+
                            "<a href='#' class='fa fa-twitter'></a>"+
                            "<a href='#' class='fa fa-google-plus'></a></div>"+
                            "<div class='pull-right d-inline-block'>"+anchor+


                            "<a  class='p-5 read' style='float:left'>Mark as read</a>"+
                            "<a href='' class='p-5' style='float:left'>View post</a>"+
                            " </div> </div> </div> </div> <hr>");

                    });
                    setReadListener();
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
