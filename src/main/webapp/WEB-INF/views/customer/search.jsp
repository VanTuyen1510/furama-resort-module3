<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List Furama</title>

</head>
<body>
<h1>Danh sách bạn tìm kiếm là :</h1>

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
                </tr>
            </c:forEach>
        </table>

    </form>
</tr>


</body>

</html>

