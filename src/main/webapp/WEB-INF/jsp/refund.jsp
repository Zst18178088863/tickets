
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>退票</title>

    <%--BootStrop 美化界面--%>
    <!-- 引入 Bootstrap -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    <small>售票</small>
                </h1>
            </div>
        </div>

    </div>

    <form action="${pageContext.request.contextPath}/ticket/refund" method="post">

        <input type="hidden" name="tripClasses" value="${queryTrip.classes}"/>

        <div class="form-group">
            <label>班次号：</label>
            <input type="text" name="classes" class="form-control"
                   value="${queryTrip.classes}" readonly required>

        </div>

        <div class="form-group">
            <label>日期：</label>
            <input type="text" name="startTime" class="form-control" required readonly value="<fmt:formatDate value="${queryTrip.startTime}" pattern="yyyy-MM-dd HH:mm"/> " >
        </div>

        <div class="form-group">
            <label>额定载量：</label>
            <input type="text" name="capacity" class="form-control"
                   value="${queryTrip.capacity}" readonly required>
        </div>

        <div class="form-group">
            <label>已售车票：</label>
            <input type="text" name="capacity" class="form-control"
                   value="${queryTrip.soldTickets}" readonly required>
        </div>

        <div class="form-group">
            <label>退票数量：</label>
            <input type="text" name="quantity" class="form-control" required>
        </div>

        <div class="form-group">
            <input type="submit" class="form-control" value="售票">
        </div>
    </form>
</div>

</body>
</html>
