<%@page pageEncoding="UTF-8" session="true"%>
<!DOCTYPE html>
<html>
<head>
    <%@include file="../include/header.jsp"%>

    <title><fmt:message key="my3o.ui.organizations.title" /> - <fmt:message key="my3o.ui.title" /></title>

    <script src="${applicationScope.resourceServerUrl}js/page/organization.js"></script>
</head>
<body>
<%@include file="../include/header-body.jsp"%>
<%@include file="../include/menu-top.jsp"%>

<div class="container min_H_400">

    <h4 class="pull-left"><fmt:message key="my3o.ui.organizations.title"/></h4>
    <c:if test="${my3o:hasRole(pageContext.request, 'SuperAdmin') or my3o:hasRole(pageContext.request, 'RegistrarAccounts')}">
        <button id="aOrganizationAdd" type="button" class="btn btn-primary pull-right">Добавить</button>
    </c:if>
    <div class="clear"></div>
    <br>

    <div id="result"></div>

    <table  class="table" id="tblOrganization">
        <thead>
        <tr>
            <c:if test="${my3o:hasRole(pageContext.request, 'SuperAdmin') or my3o:hasRole(pageContext.request, 'RegistrarAccounts')}" >
                <th><fmt:message key="my3o.ui.header.action"/></th>
            </c:if>
            <th><fmt:message key="my3o.ui.header.organization"/></th>
            <th><fmt:message key="my3o.ui.header.description"/></th>
            <th><fmt:message key="my3o.ui.header.status"/></th>
            <th><fmt:message key="my3o.ui.header.phone"/></th>
            <th><fmt:message key="my3o.ui.header.address"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${organizationList}" var="item">
            <tr trId="${item.id}">
                <c:if test="${my3o:hasRole(pageContext.request, 'SuperAdmin') or my3o:hasRole(pageContext.request, 'RegistrarAccounts')}" >
                    <td class="edit width_70" organizationId="${item.id}">

                        <button type="submit" class="btn btn-default aOrganizationEdit">
                            <span class="glyphicon glyphicon-pencil"></span>
                        </button>

                        <button type="button" class="btn btn-default aOrganizationDelete">
                            <span class="glyphicon glyphicon glyphicon-trash"></span>
                        </button>

                    </td>
                </c:if>
                <td class="name">${item.name}</td>
                <td class="description">${item.description}</td>
                <td class="status" status="${item.status}">${item.status}</td>
                <td class="phone">${item.phone}</td>
                <td class="address">${item.address}</td>

            </tr>
        </c:forEach>
        </tbody>
    </table>

</div> <!-- /container -->

<%@include file="../include/menu-bottom.jsp"%>
</body>
</html>