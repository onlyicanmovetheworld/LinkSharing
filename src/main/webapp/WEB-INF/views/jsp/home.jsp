<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Link Sharing</title>
</head>
<body>
<h1>Add New User</h1>
<form:form method="post" action="registerUser" modelAttribute="user" enctype="multipart/form-data">
    <table >
        <tr>
            <td>First Name : </td>

            <td><form:input type="text" path="firstName" required="true" /></td>
        </tr>
        <tr>
            <td>Last Name : </td>

            <td><form:input path="lastName"  /></td>
        </tr>
        <tr>
            <td>Username : </td>

            <td><form:input path="username"  /></td>
        </tr>
        <tr>
            <td>Email : </td>

            <td><form:input type="email" path="emailId"  /></td>
        </tr>
        <tr>
            <td>Password : </td>

            <td><form:input type ="password" path="password"  /></td>
        </tr>
        <tr>
            <td>Photo : </td>

            <td><form:input type ="file" path="" name="photo"   /></td>
        </tr>
        <tr>

            <td> </td>
            <td><input type="submit" value="Save" /></td>
        </tr>
    </table>
</form:form>
</body>
</html>