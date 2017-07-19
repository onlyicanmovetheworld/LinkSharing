<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Bootstrap Demo</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="/resources/css/home.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <link href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.css" rel="stylesheet">
    <meta name="viewport" content="width=device-width, initial-scale=1">

</head>
<body>

<div class="container-fluid">
    <div class="row s_header">
			<pre class="s_orgName pull-left">TO
THE
NEW</pre>
        <img class="s_orgLogo pull-left" src="/resources/assets/ttn.png">
        <ul class="pull-right list-inline ">
            <li ><h4>Home</h4></li>
            <li><h4>Quick Help</h4></li>
        </ul>

    </div>
    <nav class="navbar head-color">
        <div class="container-fluid">
            <div class="navbar-header">
                <p class="navbar-brand m-0 " href="#">Link Sharing</p>
            </div>
            <div class="navbar-form navbar-right" role="search">
                <div class="input-group border-none">
                    <div class="input-group-btn border-none">
                        <button  id="searchButton"class="btn btn-default border-none" style="height: 30px">
                            <i class="glyphicon glyphicon-search"></i>
                        </button>
                    </div>
                    <input type="text" id="search" class="form-control border-none" style="height: 30px" placeholder="Search" >
                    <div class="input-group-btn">
                        <button  id="clearSearch"class="btn btn-default border-none" style="height: 30px">
                            <i class="glyphicon glyphicon-remove"></i>
                        </button>
                    </div>
                </div>

            </div>
        </div>
    </nav>
    <div class="row">

        <div class="col-md-7 ">
            <ul class="list-group">
                <li class="list-group-item head-color" >Recent shares</li>


                <li class="list-group-item">
                  <c:if test="${not empty recentShares}">
                    <c:forEach items="${recentShares}" var="item">
                    <div class="media">
                        <div class="media-left">
                            <img src="imageFetch?username=${item.createdBy.username}" width="120" height="120">
                        </div>
                        <div class="media-body">
                            <h4 class="media-heading">${item.createdBy.firstName}   ${item.createdBy.lastName}  <small><i>@${item.createdBy.username} </i></small><a href="" style="float:right; font-size:12px">${item.topic.name}</a></h4>
                            <p>${item.description}</p>
                            <div class="pgd">
                                <div class="soc">
                                    <a href="#" class="fa fa-facebook"></a>
                                    <a href="#" class="fa fa-twitter"></a>
                                    <a href="#" class="fa fa-google-plus"></a>
                                </div>
                                <a href="" style="float:right">View post</a>
                            </div>
                        </div>
                    </div>
                        <hr>
                    </c:forEach>
                </c:if>

                </li>
            </ul>

            <ul class="list-group">
                <li class="list-group-item head-color" >
                    <div class="top">
                        <h4 style="margin:0%;padding:0%">Top Posts</h4>
                        <div class="dropdown float-lg-right" style="float:right;;padding:0%;margin-top:-4% ;margin-right:4%">
                            <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Today
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu" >
                                <li><a href="#">Today</a></li>
                                <li><a href="#">1 week</a></li>
                                <li><a href="#">1 month</a></li>
                            </ul>
                        </div>
                    </div>
                </li>
                <li class="list-group-item">
                    <div class="media" style="padding-top :10px">
                        <div class="media-left">
                            <img src="/resources/assets/unknown.png" width="120" height="120">
                        </div>
                        <div class="media-body">
                            <h4 class="media-heading">Uday Pratap singh  <small><i>@uday 10min</i></small><a href="" style="float:right;font-size:12px">Grails</a></h4>
                            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
                            <div class="pgd">
                                <div class="soc">
                                    <a href="#" class="fa fa-facebook"></a>
                                    <a href="#" class="fa fa-twitter"></a>
                                    <a href="#" class="fa fa-google-plus"></a>
                                </div>
                                <a href="" style="float:right">View post</a>
                            </div>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
        <div class="col-md-5">
            <ul class="list-group">
                <li class="list-group-item head-color">Login</li>
                <li class="list-group-item">
                    <form method="post" action="loginUser">
                        <div class="form-group">
                            <label for="credentials">Credential*</label>
                            <input type="text" class="form-control" id="credentials" name="credentials" placeholder="Enter credential" required>
                        </div>
                        <div class="form-group">
                            <label for="password">Password*</label>
                            <input type="password" class="form-control" name="password" placeholder="Enter password" required>
                        </div>
                        <div class="checkbox">
                            <a href="forgotPassword">Forgot password</a>
                            <button type="submit" class="btn btn-primary" style="float:right">Submit</button>
                        </div>
                    </form>
                </li>
            </ul>
<ul class="list-group">
<li class="list-group-item head-color">Register</li>
<li class="list-group-item">
<form:form method="post" action="registerUser"  modelAttribute="user" enctype="multipart/form-data">
    <div class="form-group">
    <label >First name*</label>
    <form:input type="text" path="firstName"  required="true"  class="form-control" placeholder="firstname"/>
        </div>
        <div class="form-group">
        <label >Last name*</label>
        <form:input type="text" path="lastName"  required="true"  class="form-control" placeholder="Lastname"/>
            </div>
            <div class="form-group">
            <label >Email*</label>
            <form:input type="email" path="emailId"  id="emailId" required="true" class="form-control" placeholder="Enter email"/>
                </div>
                <div class="form-group">
                <label >User name</label>
                <form:input type="text" path="username" id="username" required="true" class="form-control"  placeholder="Username"/>
                    </div>
                    <div class="form-group">
                    <label for="password">Password*</label>
                    <form:input type ="password" path="password" id="password" required="true" class="form-control" placeholder="Enter password"/>
                    </div>
                    <div class="form-group">
                    <label for="confirmPassword">Confirm password*</label>
                    <input type ="password"  id="confirmPassword" required="true" class="form-control"  placeholder="Confirm password">
                    </div>
                    <div class="form-group">
                    <label >Photo*</label>

                    <input class="form-control"  placeholder="Browse"  type ="file" class="form-control"  name="photo" />


                        </div>
                        <div class="checkbox">
                        <button type="submit" class="btn btn-primary" style="margin-left: 83%; clear: both;">Register</button>
                        </div>
                        </form:form>
                        </li>
                        </ul>
        </div>
    </div>
</div>


<spring:url value="/resources/js/home.js" var="coreJs" />
<spring:url value="/resources/js/jquery.js" var="jqueryJs" />
<script src="${jqueryJs}"></script>
<script src="${coreJs}"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

</body>
</html>
