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
                    <img src="imageFetch?username=<%=session.getAttribute("username")%>" width="100" height="100">
                </div>
                <div class="media-body">
                    <h3 class="media-heading">${user.firstName}   ${user.lastName}  <br><small><i>@${user.username} </i></small></h3>



                        <div class="pull-left " style="width:80px;">

                            <h4 style="width:100%;text-align:center;"><i>Subscriptions</i></h4>
                            <h4 style="width:100%;text-align:center;">${subNumber}</h4>

                        </div>


                        <div class="pull-right " style="width:80px;">

                        <h4 style="width:100%;text-align:center;"><i>Topics</i></h4>
                        <h4 style="width:100%;text-align:center;">${topicNumber}</h4>

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
            <li class="list-group-item head-color" >Inbox</li>


        <%@include file="inboxElementViewer.jsp"%>

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

        if(index >= maxSize-5)
        {
            $('#next').attr('disabled',true);
        }

        $('#next').click(function () {
            index+=5;

            if(index>0)
            {
                $('#prev').attr('disabled',false);
            }
            if(index >= maxSize-5)
            {
                $('#next').attr('disabled',true);
            }
            fetchData();
        });
        $('#prev').click(function () {
            index-=5;
            if(index==0)
            {
                $('#prev').attr('disabled',true);
            }
            if(index < maxSize-5)
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
                        if(v.resource.resourseType=="Document")
                        {
                            anchor = "<a class='p-5' style='float:left' href='/download?filePath="+v.resource.link+"' download>Download</a>";
                        }
                        else
                        {
                            anchor= "<a class='p-5' style='float:left' href='"+v.resource.link+"' target='_blank'>View Link</a>";
                        }
                        $('#inboxDiv').append("<div class='media' id='"+v.id+"'> <div class='media-left'> " +
                            "<img src='imageFetch?username="+v.resource.createdBy.username+"' width='120' height='120'></div>"+
                           " <div class='media-body'>"+
                           " <h4 class='media-heading'>"+v.resource.createdBy.firstName+"  "+v.resource.createdBy.lastName+" <small><i>@"+v.resource.createdBy.username+
                            " </i></small><a href='' style='float:right; font-size:12px'>"+v.resource.topic.name+"</a></h4>"+
                            "<p>"+v.resource.description+"</p>"+
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