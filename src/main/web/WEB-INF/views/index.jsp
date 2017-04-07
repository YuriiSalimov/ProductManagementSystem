<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<html>
<head>
    <title>Items</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

<body>
<%@include file="/WEB-INF/views/navbar.jsp" %>

<div class="container">
    <div class="panel-heading">
        <div class="row">
            <h4>User: ${pageContext.request.userPrincipal.name}</h4>
            <div class="btn-group">
                <a class="btn btn-primary" role="button" href="/welcome">Welcome Page</a>
                <a class="btn btn-primary" role="button" onclick="document.forms['logoutForm'].submit()">Logout</a>
            </div>
            <form id="logoutForm" method="POST" action="${contextPath}/logout">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
        </div>
        <hr>
    </div>

    <div class="table-responsive">
        <table class="table table-striped">
            <tr>
                <th>Product Name</th>
                <th>Manufacturer</th>
                <th>Price</th>
                <th>Description</th>
                <th>Show Detail Info</th>
                <c:if test="${is_admin== true}">
                    <th>Edit</th>
                </c:if>
                <c:if test="${is_admin== true}">
                    <th>Delete</th>
                </c:if>
            </tr>
            <c:forEach items="${products}" var="product">
                <tr>
                    <td>${product.name}</td>
                    <td>${product.manufacturer.name}</td>
                    <td>${product.price}</td>
                    <td>${product.description}</td>
                    <td><a class="btn btn-xs btn-primary active" role="button" style="margin: 5px"
                           href="<c:url value='/product/${product.id}'/>">Details</a></td>
                    <c:if test="${is_admin== true}">
                        <td><a class="btn btn-xs btn-primary active" role="button" style="margin: 5px"
                               href="<c:url value='/admin/product/edit/${product.id}'/>">Edit</a></td>
                    </c:if>
                    <c:if test="${is_admin== true}">
                        <td><a class="btn btn-xs btn-danger active" role="button" style="margin: 5px"
                               href="<c:url value='/admin/product/delete/${product.id}'/>">Delete</a></td>
                    </c:if>
                </tr>
            </c:forEach>
        </table>
    </div>
    <c:if test="${is_admin== true}">
        <a class="btn btn-xs btn-block btn-primary active" role="button" href="/admin/product/new">Add an Item</a>
    </c:if>
</div>
</body>
</html>
