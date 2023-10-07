<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Customer</title>
</head>
<body>
<h1>Edit Customer</h1>
<form action="/customer" method="post">
    <table>
        <%--//  int id, String name, Date birthday, int gender, String idCard, String phone, String email, String address, CustomerType customerType--%>
        <table>
            <tr>
                <th>ID</th>
                <td><input type="text" name="id"></td>
            </tr>
            <tr>
                <th>Name</th>
                <td><input type="text" name="name"></td>
            </tr>
            <tr>
                <th>Birthday</th>
                <td><input type="text" name="birthday" placeholder="dd/MM/yyyy"></td>
            </tr>
            <tr>
                <th>Gender</th>
                <td>

                    <select name="gender">
                        <option value="1">Male</option>
                        <option value="0">Famle</option>
                    </select>
                </td>
            </tr>
            <tr>
                <th>Id Card</th>
                <td><input type="text" name="idCard"></td>
            </tr>
            <tr>
                <th>Phone</th>
                <td><input type="text" name="phone"></td>
            </tr>
            <tr>
                <th>Email</th>
                <td><input type="text" name="email"></td>
            </tr>
            <tr>
                <th>Address</th>
                <td><input type="text" name="address"></td>
            </tr>
            <tr>
                <th>Customer Type</th>
                <td>
                    <select name="customerTypeId">
                        <c:forEach items="${customerTypes}" var="c">
                            <option value="${c.id}">${c.name}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="Edit">
                </td>
            </tr>
        </table>
        <input type="hidden" name="action" value="edit">
    </table>
</form>

</body>
</html>
