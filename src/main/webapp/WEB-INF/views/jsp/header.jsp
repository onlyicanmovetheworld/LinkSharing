

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Link Sharing</title>
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
        <img class="s_orgLogo pull-left"  style="padding: 5px;" src="/resources/assets/ttn.png">
        <ul class="pull-right list-inline ">
            <li ><h4>Home</h4></li>
            <li><h4>Quick Help</h4></li>
        </ul>

    </div>
    <% if(session.getAttribute("username")!=null){%>
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
                <input type="text" id="search" class="form-control border-none" style="height: 30px; width:150px;" placeholder="Search" >
                <div class="input-group-btn">
                    <button  id="clearSearch"class="btn btn-default border-none" style="height: 30px">
                        <i class="glyphicon glyphicon-remove"></i>
                    </button>
                </div>
            </div>


            <div class="dropdown" style="float: right;  " >
                <button class="btn  dropdown-toggle d-inline-block" style="background: #aaa;" type="button" data-toggle="dropdown">
                    <img class="pull-left" src="imageFetch?username=<%=session.getAttribute("username")%>" width="20" height="20"/>
                    <p class="pull-left " style="font-size:15px;margin: 0px 5px;"><%=session.getAttribute("username")%></p>
                    <span class="caret pull-left" style="margin:10px 0 0 0;"></span></button>
                <ul class="dropdown-menu ui-front">
                    <li><a href="#">Profile</a></li>
                    <li id="logout"><a href="#">LogOut</a></li>

                </ul>
            </div>

            <div style="float: right;">
                <i class="glyphicon glyphicon-comment s_icon" data-toggle="modal" data-target="#addTopic"></i>
                <i class="glyphicon glyphicon-envelope s_icon" data-toggle="modal" data-target="#sendInvite"></i>
                <i class="glyphicon glyphicon-link s_icon" data-toggle="modal" data-target="#addLink"></i>
                <i class="glyphicon glyphicon-open-file s_icon" data-toggle="modal" data-target="#addDocument"></i>

            </div>

        </div>
    </div>
</nav>


    <div class="modal fade" id="addTopic" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header head-color" style="border-radius:6px 6px 0 0;">
                    <button type="button" class="close" style="color:#efefef !important;" data-dismiss="modal">X</button>
                    <h3 >Add Topic</h3>
                </div>
                <div class="modal-body">
                    <ul class="list-group">

                        <li class="list-group-item">
                            <form method="post" action="javascript:void(0)"  id="topicForm">
                                <div class="form-group">
                                    <label for="name">Topic Name*</label>
                                    <input type="text" class="form-control"  id="name" name="name" placeholder="Enter Topic Name" required>
                                </div>
                                <div class="form-group">
                                    <label for="visibility">Visibility*</label>
                                    <select class="form-control" id="visibility" name="visibility">
                                        <option value="Public" selected>Public</option>
                                        <option value="Private">Private</option>
                                    </select>
                                </div>

                            </form>
                        </li>
                    </ul>
                    <button type="submit" id="addingTopic" class="btn btn-primary" data-dismiss="#addTopic" style="margin-left: 83%;">Add Topic</button>
                </div>





            </div>

        </div>
    </div>


    <div class="modal fade" id="sendInvite" role="dialog">
        <div class="modal-dialog ui-front">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header head-color" style="border-radius:6px 6px 0 0;">
                    <button type="button" class="close" style="color:#efefef !important;" data-dismiss="modal">X</button>
                    <h3 >Send Invite</h3>
                </div>
                <div class="modal-body">
                    <ul class="list-group">

                        <li class="list-group-item">
                            <form method="post" action="javascript:void(0)"  id="inviteForm">
                                <div class="form-group">
                                    <label for="email">Email Id*</label>
                                    <input type="email" class="form-control"  id="email" name="email" placeholder="Enter Email" required>
                                </div>
                                <div class="form-group">
                                    <label for="inviteTopic">Topic*</label>
                                    <input type="text" class="form-control "  id="inviteTopic" name="topic" placeholder="Enter Topic Name" required>
                                </div>

                            </form>
                        </li>
                    </ul>
                    <button type="submit" id="sendingInvite" class="btn btn-primary" data-dismiss="#sendInvite"style="margin-left: 83%;">Send Invite</button>
                </div>

            </div>

        </div>
    </div>

    <div class="modal fade" id="addLink" role="dialog">
        <div class="modal-dialog ui-front">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header head-color" style="border-radius:6px 6px 0 0;">
                    <button type="button" class="close" style="color:#efefef !important;" data-dismiss="modal">X</button>
                    <h3 >Add Link Resource</h3>
                </div>
                <div class="modal-body">
                    <ul class="list-group">

                        <li class="list-group-item">
                            <form method="post" action="javascript:void(0)"  id="linkForm">
                                <div class="form-group">
                                    <label for="link">Link*</label>
                                    <input type="url" class="form-control"  id="link" name="link" placeholder="Enter Link" required>
                                </div>
                                <div class="form-group">
                                    <label for="desc">Description*</label>
                                    <textarea class="form-control"  id="descp" name="descp"  required></textarea>
                                </div>
                                <div class="form-group">
                                    <label for="linkTopic">Topic*</label>
                                    <input type="text" class="form-control "  id="linkTopic" name="topic" placeholder="Enter Topic Name" required>
                                </div>

                            </form>
                        </li>
                    </ul>
                    <button type="submit" id="addingLink" class="btn btn-primary" data-dismiss="#addLink" style="margin-left: 83%;">Add Link</button>
                </div>
            </div>

        </div>
    </div>

    <div class="modal fade" id="addDocument" role="dialog">
        <div class="modal-dialog ui-front">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header head-color" style="border-radius:6px 6px 0 0;">
                    <button type="button" class="close" style="color:#efefef !important;" data-dismiss="modal">X</button>
                    <h3 >Add Document Resource</h3>
                </div>
                <div class="modal-body">
                    <ul class="list-group">

                        <li class="list-group-item">
                            <form method="post" action="addDocument" enctype="multipart/form-data" id="documentForm">
                                <div class="form-group">
                                    <label for="file">Document*</label>
                                    <input type="file" class="form-control"  id="file" name="file" placeholder="Browse" required>
                                </div>
                                <div class="form-group">
                                    <label for="desc">Description*</label>
                                    <textarea class="form-control"  id="desc" name="desc"  required></textarea>
                                </div>
                                <div class="form-group">
                                    <label for="linkTopic">Topic*</label>
                                    <input type="text" class="form-control "  id="documentTopic" name="topic" placeholder="Enter Topic Name" required>
                                </div>
                                <button type="submit" id="addingDocument" class="btn btn-primary" data-dismiss="#addDocument" style="margin-left: 78%;">Add Document</button>
                            </form>
                        </li>
                    </ul>

                </div>
            </div>

        </div>
    </div>
<%}%>
</div>





<spring:url value="/resources/js/dashboard.js" var="coreJs" />
<spring:url value="/resources/js/jquery.js" var="jqueryJs" />
<script src="${jqueryJs}"></script>
<script src="${coreJs}"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.form/4.2.1/jquery.form.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

<script>


</script>

</body>
</html>
