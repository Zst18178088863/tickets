<%--
  Created by IntelliJ IDEA.
  User: 张胜通
  Date: 2023/6/13
  Time: 15:26
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>

<head>

    <title>系统首页</title>

    <!-- 引入 Bootstrap -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

    <style>

        *{
            margin: 0;
            padding: 0;
        }
        body{
            width: 1902px;
            height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
        }
        .menus{
            width: 10%;
            height: 80%;
            border-right: 1.5px solid rgba(0,0,0,0.1);
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
        }
        .menu-item{
            width: 100%;
            height: 50px;
            display: flex;
            align-items: center;
            justify-content: center;
        }
        .container{
            height: 100%;
        }
        .control{
            display: flex;
        }
        .control-col-1{
            width: 20%;
            display: flex;
            align-items: center;
            justify-content: space-evenly;
        }
        .control-col-2{
            width: 80%;
        }
        .form-inline{
            display: flex;
            align-items: center;
            justify-content: space-between;
            width: 100%;
            height: 100%;
        }

        /* 样式用于居中弹框 */
        .modal {
            position: fixed;
            left: 50%;
            top: 50%;
            transform: translate(-50%, -50%);
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.3);
        }

    </style>

    <script>
        // 打开弹框
        function countTicket() {
            var modal = document.getElementById("myModal");
            modal.style.display = "block";
        }

        // 提交表单
        function submitForm() {
            var form = document.getElementById("myForm");
            // 获取表单数据并进行处理
            var date = form.elements["date"].value;

            // 进行表单验证和处理逻辑
            if (date) {

                // 使用 AJAX 请求向后端发送统计票数的请求
                var xhr = new XMLHttpRequest();
                xhr.open("GET", "${pageContext.request.contextPath}/ticket/countTickets/" + date);

                // alert("date:" + date);
                xhr.onreadystatechange = function () {
                    if (xhr.readyState === 4 && xhr.status === 200) {
                        var totalSoldTickets = xhr.responseText;

                        // 使用弹框显示统计结果
                        alert("查询的当天日期：" + date  + "总售票数：" + totalSoldTickets);
                    }
                };
                xhr.send();

                // 关闭弹框
                var modal = document.getElementById("myModal");
                modal.style.display = "none";
            } else {
                alert("请输入统票日期");
            }
        }
    </script>

</head>

<body>

    <div class="menus">
         <a class="menu-item" href="${pageContext.request.contextPath}/ticket/findByPage">系统首页</a>
         <a class="menu-item" href="${pageContext.request.contextPath}/trip/findAllTrip">查询车次信息</a>
         <a class="menu-item" href="${pageContext.request.contextPath}/ticket/getAllTicket">查询车票信息</a>
    </div>

    <div class="container">
    <div class="page-header">
        <h1>
            <small>总览表</small>
        </h1>
    </div>

    <div class="control">

        <div class="control-col-1">
            <a class="btn btn-primary" onclick="countTicket()">统票</a>
        </div>
        <div class="control-col-2">

            <%-- 查询 --%>
            <form class="form-inline" action="${pageContext.request.contextPath}/ticket/findByPage" method="post" style="float: right">
                <span style="color: red;font-weight: bold">${error}</span>

                <label>班次号：</label>
                <input type="text" name="classes" class="form-control" id="classes" value="${hashMap.classes}" placeholder="请输入要查询班次的班次号：">
                <label>起始站：</label>
                <input type="text" name="startStation" class="form-control" id="startStation" value="${hashMap.startStation}" placeholder="请输入要查询班次的起始站：">
                <label>终点站：</label>
                <input type="text" name="endStation" class="form-control" id="endStation" value="${hashMap.endStation}" placeholder="请输入要查询班次的终点站：">
                <input type="submit" value="查询" class="btn btn-primary">
            </form>

        </div>
    </div>

    <div class="row clearfix">
        <form id="form" method="post">
            <table border="1" class="table table-bordered table-hover">
                <tr class="success">
                    <th>编号</th>
                    <th>班次号</th>
                    <th>发车时间</th>
                    <th>起始站</th>
                    <th>终点站</th>
                    <th>行车时间（h）</th>
                    <th>额定载量(人)</th>
                    <th>已售车票（张）</th>
                </tr>

                <c:forEach var="trip" items="${pageInfo.list}" varStatus="b">
                    <tr>
                        <td>${b.count}</td>
                        <td>${trip.classes}</td>
                        <td>
                            <c:if test="${not empty trip.startTime}">
                                <fmt:formatDate value="${trip.startTime}" pattern="yyyy-MM-dd HH:mm" />
                            </c:if>
                        </td>

                        <td>
                            <c:if test="${not empty trip.startStation}">
                                ${trip.startStation}
                            </c:if>
                        </td>

                       <td>
                           <c:if test="${not empty trip.endStation}">
                               ${trip.endStation}
                           </c:if>
                       </td>
                        <td>
                            <c:if test="${not empty trip.travelTime}">
                                ${trip.travelTime}
                            </c:if>
                        </td>
                        <td>${trip.capacity}</td>
                        <td>
                            <c:if test="${not empty trip.soldTickets}">
                                ${trip.soldTickets}
                            </c:if>
                        </td>

                    </tr>
                </c:forEach>
            </table>
        </form>

        <%-- 分页 --%>
        <div>
            <nav aria-label="Page navigation">
                <ul class="pagination">

                    <%-- 判断当前页是否是第一页，决定左箭头禁用 --%>
                    <li>
                        <c:if test="${pageInfo.pageNum == 1}">

                        </c:if>
                        <c:if test="${pageInfo.pageNum != 1}">
                            <a href="${pageContext.request.contextPath}/ticket/findByPage?pageNum=${pageInfo.pageNum-1}&pageSize=${pageInfo.pageSize}&classes=${hashMap.classes}" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </c:if>
                    </li>

                    <%-- 遍历总页数 --%>
                    <c:forEach begin="1" end="${pageInfo.pages}" var="i">
                        <%-- 判断当前循环的数字是否与当前页数的数字相同，相同加上蓝色背景属性 --%>
                        <c:if test="${pageInfo.pageNum == i}">
                            <li class="active">
                                <a href="${pageContext.request.contextPath}/ticket/findByPage?pageNum=${i}&pageSize=${pageInfo.pageSize}&classes=${hashMap.classes}">${i}</a>
                            </li>
                        </c:if>

                        <c:if test="${pageInfo.pageNum != i}">
                            <li>
                                <a href="${pageContext.request.contextPath}/ticket/findByPage?pageNum=${i}&pageSize=${pageInfo.pageSize}&classes=${hashMap.classes}">${i}</a>
                            </li>
                        </c:if>
                    </c:forEach>

                    <%-- 判断当前页是否为最后一页，决定右箭头是否禁用 --%>
                    <li>
                        <c:if test="${pageInfo.pageNum == pageInfo.pages}">

                        </c:if>
                        <c:if test="${pageInfo.pageNum != pageInfo.pages}">
                            <a href="${pageContext.request.contextPath}/ticket/findByPage?pageNum=${pageInfo.pageNum+1}&pageSize=${pageInfo.pageSize}&classes=${hashMap.classes}" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                        </c:if>
                    </li>

                    <span style="font-size: 24px;margin-left: 5px">
                            共<span style="color: red">${pageInfo.total}</span>条记录，共<span style="color: red">${pageInfo.pages}</span>页
                        </span>

                </ul>
            </nav>
        </div>

    </div>

    </div>

    <div id="myModal" class="modal" style="display: none;">
        <form id="myForm">
            <!-- 表单输入项 -->
            <label for="date">请输入统票日期：</label>
            <input type="text" id="date" name="date">

            <!-- 确认按钮 -->
            <button type="button" onclick="submitForm()">确认</button>
        </form>
    </div>

</body>

</html>

