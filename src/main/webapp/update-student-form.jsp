<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Update student</title>

        <link type="text/css" rel="stylesheet" href="style.css">
        <link type="text/css" rel="stylesheet" href="add-student-style.css">
    </head>
    <body>
        <div id="wrapper">
            <div id="header">
                <h2>FooBar University</h2>
            </div>
        </div>
        <div id="container">
            <h3>Update Student</h3>
            <form action="update_student" method="get">

                <input type="hidden" name="id" value="${THE_STUDENT.id}" />
                <table>
                    <tr>
                        <td><label>First name: </label></td>
                        <td><input type="text" name="firstName" value="${THE_STUDENT.firstName}"></td>
                    </tr>
                    <tr>
                        <td><label>Last name: </label></td>
                        <td><input type="text" name="lastName" value="${THE_STUDENT.lastName}"></td>
                    </tr>
                    <tr>
                        <td><label>Email: </label></td>
                        <td><input type="text" name="email" value="${THE_STUDENT.email}"></td>
                    </tr>
                    <tr>
                        <td><label></label></td>
                        <td><input type="submit" value="Save" class="save"></td>
                    </tr>
                </table>
            </form>
        </div>
    </body>
</html>