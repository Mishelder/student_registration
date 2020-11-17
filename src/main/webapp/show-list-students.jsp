<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>Student Tracker App</title>
    <link  rel="stylesheet" href="style.css">
    <link  rel="stylesheet" href="add-student-style.css">
</head>
<body>

    <div id="wrapper">
        <div id="header">
            <h2>FooBar University</h2>
        </div>
    </div>

    <div id="container">
        <div id="content">
            <input type="button" value="Add Student"
                   onclick="window.location.href='add-student-form.jsp'; return false;"
                   class="add-student-button"
            />
            <table>
                <tr>
                    <th>First name</th>
                    <th>Last name</th>
                    <th>Email</th>
                    <th>Action</th>
                </tr>
                <c:forEach var="stud" items="${LIST_STUDENT}">
                    <tr>
                        <td>${stud.firstName}</td>
                        <td>${stud.lastName}</td>
                        <td>${stud.email}</td>
                        <td><a href="load_student?id=${stud.id}">Update</a>
                            |
                            <a href="delete_student?id=${stud.id}"
                               onclick="if (!(confirm('Are you sure you want to delete this student?'))) return false">
                                Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</body>
</html>
