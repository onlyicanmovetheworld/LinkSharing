<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:forEach items="${topicList}" var="item">
    <c:choose>
    <c:when test="${item.resourceType=='Document'}">
        <a href="download?filePath=${item.link}" >${item.description}</a>
    </c:when>
    <c:otherwise>
        <a href="${item.link}" target="_blank">${item.description}</a>
    </c:otherwise>
    </c:choose>
</c:forEach>
</body>
</html>
