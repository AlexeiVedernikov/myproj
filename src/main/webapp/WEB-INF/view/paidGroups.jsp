<%--
  Created by IntelliJ IDEA.
  User: eugene
  Date: 12/3/14
  Time: 7:28 AM
  To change this template use File | Settings | File Templates.
--%>
<%@page pageEncoding="UTF-8" session="true"%>
<!DOCTYPE html>
<html>
<head>
    <%@include file="../include/header.jsp"%>

    <title><fmt:message key="my3o.ui.paid.title" /> - <fmt:message key="my3o.ui.title" /></title>

    <script src="${applicationScope.resourceServerUrl}js/page/paidGroups.js"></script>
</head>
<body>
<%@include file="../include/header-body.jsp"%>
<%@include file="../include/menu-top.jsp"%>

<div class="container min_H_400">

    <h4 class="pull-left"><fmt:message key="my3o.ui.paid.title" /></h4>
    <div class="clear"></div>
    <div class="row">
        <label>
            <span class="alert alert-danger col-md-1"></span> - Ни 1 ученик не зарегестрирован в классе
        </label>
        <label>
            <span class="alert alert-warning col-md-1"></span> - Меньше 10 учеников зарегестрированно в классе
        </label>
        <label>
            <span class="alert alert-success col-md-1"></span> - 10 или больше учеников зарегестрировано в классе
        </label>
    </div>
    <hr>

    <div class="row">
        <c:forEach var="item" items="${paidGroupMap}">
            <%--Key is ${item.key}--%>
            <%--Value is ${item.value}--%>
            <c:if test="${item.value == 0}">
                <a href="school/journals?groupId=${item.key.id}" class="thumbnail col-md-1 alert alert-danger"  title="${item.value} зарегестрировано учеников в классе">
                        ${item.key.name}
                        <span class="badge">${item.value}</span>
                </a>
            </c:if>

            <c:if test="${item.value > 0 && item.value < 10 }">
                <a href="school/journals?groupId=${item.key.id}" class="thumbnail col-md-1 alert alert-warning" title="${item.value} зарегестрировано учеников в классе">
                    ${item.key.name}
                    <span class="badge">${item.value}</span>
                </a>
            </c:if>

            <c:if test="${item.value >= 10}">
                <a href="school/journals?groupId=${item.key.id}" class="thumbnail col-md-1 alert alert-success" title="${item.value} зарегестрировано учеников в классе">
                        ${item.key.name}
                        <span class="badge">${item.value}</span>
                </a>
            </c:if>
        </c:forEach>

    </div>

    <%@include file="../include/menu-bottom.jsp"%>

</body>
</html>



