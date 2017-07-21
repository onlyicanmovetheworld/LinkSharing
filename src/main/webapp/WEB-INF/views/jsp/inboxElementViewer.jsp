<%--
  Created by IntelliJ IDEA.
  User: Shubham
  Date: 20-07-2017
  Time: 01:27 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:if test="${empty inbox}">
    <li class="list-group-item " >Nothing to show</li>
</c:if>
<li class="list-group-item" id="inboxDiv">
    <c:if test="${not empty inbox}">
        <c:forEach items="${inbox}" var="item">
            <div class="media" id="${item.id}">

                <div class="media-left">
                    <img src="imageFetch?username=${item.resource.createdBy.username}" width="120" height="120">
                </div>
                <div class="media-body">
                    <h4 class="media-heading">${item.resource.createdBy.firstName}   ${item.resource.createdBy.lastName}  <small><i>@${item.resource.createdBy.username} </i></small><a href="/searchTopic?topicName=${item.resource.topic.name}By${item.resource.topic.createdBy.username}&index=0" style="float:right; font-size:12px">${item.resource.topic.name}</a></h4>
                    <p>${item.resource.description}</p>
                    <div class="pgd">
                        <div class="soc">
                            <a href="#" class="fa fa-facebook"></a>
                            <a href="#" class="fa fa-twitter"></a>
                            <a href="#" class="fa fa-google-plus"></a>
                        </div>
                        <div class="pull-right d-inline-block">


                            <c:choose>
                                <c:when test="${item.resource.resourceType=='Document'}">
                                    <a class="p-5" style="float:left" href='/download?filePath="+${item.resource.link}+"' download>Download</a>
                                </c:when>
                                <c:otherwise>
                                    <a class="p-5" style="float:left" href="${item.resource.link}" target="_blank">View Link</a>
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
</body>
</html>
