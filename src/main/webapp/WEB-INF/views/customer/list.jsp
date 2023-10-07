<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List Furama</title>

</head>
<body>
<h1>List Customer</h1>
<h2><a href="/customer?action=create">Add new Customer</a></h2>

<tr>
    <form method="post" action="/customer">
        <label>Customer Name:</label>
        <input type="text" name="customer_name">
        <%-- name="name" là lấy thuộc tính--%>
        <input type="submit" value="SEARCH">
        <input type="hidden" name="action" value="search">
        <%--hidden là ẩn khi truyền action vào thì vào value "search" vào search để gọi hàm--%>
    </form>
</tr>
<%--int id, String name, Date birthday, int gender, String idCard, String phone, String email, String address, CustomerType customerType--%>
<tr>
    <form action="/customer">
        <table border="1" >
            <tr>
                <th>STT</th>
                <th>ID</th>
                <th>Name</th>
                <th>Birthday</th>
                <th>Gender</th>
                <th>IDCard</th>
                <th>Phone</th>
                <th>Email</th>
                <th>Address</th>
                <th>Customer Type</th>
                <th>Delete</th>
                <th>Edit</th>

            </tr>
            <c:forEach items="${customers}" var="p" varStatus="index">
                <tr>
                    <td>${index.count}</td>
                    <td>${p.id}</td>
                    <td>${p.name}</td>
                    <td>
                        <fmt:formatDate value="${p.birthday}" pattern="dd/MM/yyyy"/>
                    </td>
                    <td>
                        <c:if test="${p.gender == 1}">
                            Male
                        </c:if>
                        <c:if test="${p.gender == 0}">
                            Female
                        </c:if>
                    </td>
                    <td>${p.idCard}</td>
                    <td>${p.phone}</td>
                    <td>${p.email}</td>
                    <td>${p.address}</td>
                    <td>${p.customerType.name}</td>
                    <td><a href="/customer?action=delete&id=${p.id}" onclick="return deleteCustomer();">Delete customer</a></td>
                    <td><a href="/customer?action=edit&id=${p.id}">Edit customer</a> </td>

                </tr>
            </c:forEach>
        </table>

    </form>
</tr>

<%--<div class="container">--%>
<%--    <div class="jumbotron text-center">--%>
<%--        <h1 class="display-4"></h1>--%>
<%--        <p class="lead">Subtitle</p>--%>
<%--        <hr class="my-4">--%>
<%--&lt;%&ndash;        <p>Content</p>&ndash;%&gt;--%>
<%--        <div class="panel panel-default">--%>
<%--            <div class="panel-heading">--%>
<%--                <div class="panel-title">Customer</div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>
<script >
    function deleteCustomer() {
        return confirm("Bạn có muốn xóa item này không ?");
    }
</script>
</body>

</html>
