<%@page pageEncoding="UTF-8" session="true"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../include/header.jsp"%>
    
        <title><fmt:message key="my3o.ui.menu.guides.groups" /> - <fmt:message key="my3o.ui.title" /></title>

        <script src="${applicationScope.resourceServerUrl}js/page/groups.js"></script>
    </head>
    <body>
        <%@include file="../include/header-body.jsp"%>
        <%@include file="../include/menu-top.jsp"%>

        <div class="container min_H_400">

            <h4 class="pull-left"><fmt:message key="my3o.ui.menu.guides.groups" /></h4>
            <c:if test="${my3o:hasRole(pageContext.request, 'SuperAdmin')
                                or my3o:hasRole(pageContext.request, 'SchoolAdmin')
                                or my3o:hasRole(pageContext.request, 'RegistrarAccounts')}" >
                <button class="btn btn-primary pull-right" id ="add">Добавить</button>
            </c:if>
            <div class="clear"></div>
            <br>

            <table class="table" id="tblGroups">
                <thead>
                    <tr>
                        <c:if test="${my3o:hasRole(pageContext.request, 'SuperAdmin')
                                or my3o:hasRole(pageContext.request, 'SchoolAdmin')
                                or my3o:hasRole(pageContext.request, 'RegistrarAccounts')}" >
                            <th><fmt:message key="my3o.ui.header.action"/></th>
                        </c:if>
                        <th><fmt:message key="my3o.ui.header.group"/></th>
                        <th><fmt:message key="my3o.ui.header.number"/></th>
                        <th><fmt:message key="my3o.ui.header.organization"/></th>
                        <th><fmt:message key="my3o.ui.header.description"/></th>
                        <th><fmt:message key="my3o.ui.header.status"/></th>
                    </tr>
                </thead>
            <tbody>
                <c:forEach items="${groupList }" var="item">
                    <tr trID = "${item.id }">
                        <c:if test="${my3o:hasRole(pageContext.request, 'SuperAdmin')
                                or my3o:hasRole(pageContext.request, 'SchoolAdmin')
                                or my3o:hasRole(pageContext.request, 'RegistrarAccounts')}" >
                            <td class = "edit" groupId = "${item.id }">
                                <button type="button" class="btn btn-default groupEdit">
                                    <span class="glyphicon glyphicon-pencil"></span>
                                </button>
                                <button type="button" class="btn btn-default groupDelete">
                                    <span class="glyphicon glyphicon-trash"></span>
                                </button>
                                <button type="button" class="btn btn-default groupShedule" title="Редактировать предметы">
                                    <span class="glyphicon glyphicon-list-alt"></span>
                                </button>
                            </td>
                        </c:if>
                        <td class="name">${item.name}</td>
                        <td class="number">${item.number}</td>
                        <td class="organizationName" organizationId="${item.organizationDto.id}">${item.organizationDto.name}</td>
                        <td class="description">${item.description}</td>
                        <td class="status" status="${item.status}"> ${item.status}</td>
                    </tr>
                </c:forEach>
            </tbody>
            </table>

        </div> <!-- /container -->
    
        <%@include file="../include/menu-bottom.jsp"%>
        <%@include file="dialog/group-edit.jsp"%>

    </body>
</html>


