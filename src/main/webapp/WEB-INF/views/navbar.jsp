
<div class="container">
    <div class="panel-heading">
        <div class="row">
            <a href="<c:url value='/home'/>"><h4 class="col-lg-3">Product Management System</h4></a>
            <div class="btn-group pull-right">
                <a class="btn btn-primary" role="button" href="<c:url value='/home'/>">Products</a>
                <a class="btn btn-primary" role="button" href="<c:url value='/users'/>">Users</a>
                <c:if test="${is_admin ne true}">
                    <a class="btn btn-primary" role="button" href="<c:url value='/login'/>">Login</a>
                </c:if>
                <c:if test="${is_admin}">
                    <a class="btn btn-primary" role="button" href="<c:url value='/logout'/>">Logout</a>
                </c:if>
            </div>
        </div>
        <hr>
    </div>
</div>