<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Customer</title>
    <style>
        p {
          color: red;
        }
    </style>
</head>
<body>
<h1>Create new Customer</h1>
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
                <td><input type="text" name="name" value="${customer.getName()}"></td>
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
                <td><input type="text" name="idCard" value="${customer.getIdCard()}"></td>
                <td>
                    <p>
                        <c:if test="${idCardError != null}">
                            ${idCardError}
                        </c:if>
                    </p>
                </td>

            </tr>
            <tr>
                <th>Phone</th>
                <td><input type="text" name="phone" value="${customer.getPhone()}"></td>
                <td>
                    <p>
                        <c:if test="${phoneError != null}">
                            ${phoneError}
                        </c:if>
                    </p>
                </td>

            </tr>
            <tr>
                <th>Email</th>
                <td><input type="text" name="email" value="${customer.getEmail()}"></td>
            </tr>
            <tr>
                <th>Address</th>
                <td><input type="text" name="address" value="${customer.getAddress()}"></td>
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
                    <input type="submit" value="Add">
                </td>
            </tr>
        </table>
        <input type="hidden" name="action" value="create">
    </table>
</form>

</body>
</html>
