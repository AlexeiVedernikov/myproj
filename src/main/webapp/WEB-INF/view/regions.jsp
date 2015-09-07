<%@page pageEncoding="UTF-8" session="true"%>
<!DOCTYPE html>
<html>
<head>
    <%@include file="../include/header.jsp"%>
    
    <title><fmt:message key="my3o.ui.menu.guides.regions" /> - <fmt:message key="my3o.ui.title" /></title>
    
    <script src="${applicationScope.resourceServerUrl}js/page/regions.js"></script>
    <script src="${applicationScope.resourceServerUrl}js/page/selects.js"></script>
</head>
<body>
	<%@include file="../include/header-body.jsp"%>
	<%@include file="../include/menu-top.jsp"%>

    <div class="container min_H_400">

        <h4 class="pull-left"><fmt:message key="my3o.ui.menu.guides.regions"/></h4>
        <button type="button" class="btn btn-primary pull-right addRow">Добавить</button>
        <div class="clear"></div>
        <br>
        <table class="table" id="tblRegions">
            <thead>
                <tr>
                    <th><fmt:message key="my3o.ui.header.action"/></th>
                    <th><fmt:message key="my3o.ui.header.region"/></th>
                    <th><fmt:message key="my3o.ui.header.country"/></th>
                    <th><fmt:message key="my3o.ui.header.description"/></th>
                    <th><fmt:message key="my3o.ui.header.status"/></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="item" items="${regionList}">
                    <tr trId="${item.id}">
                        <td class="edit">
                            <button type="button" class="btn btn-default editRow">
                                <span class="glyphicon glyphicon-pencil"></span>
                            </button>
                            <button type="button" class="btn btn-default deleteRow">
                                <span class="glyphicon glyphicon glyphicon-trash"></span>
                            </button>
                        </td>
                        <td class="name">${item.name}</td>
                        <td class="country" countryId="${item.countryDto.id}">${item.countryDto.name}</td>
                        <td class="description">${item.description}</td>
                        <td class="status" status="${item.status}">${item.status}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

    </div> <!-- /container -->

    <%@include file="../include/menu-bottom.jsp"%>
    <%@include file="dialog/region-edit.jsp"%>

</body>
</html>


