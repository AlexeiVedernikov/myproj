<%@page pageEncoding="UTF-8" session="true"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../include/header.jsp"%>
    
        <title><fmt:message key="my3o.ui.menu.school.disciplines" /> - <fmt:message key="my3o.ui.title" /></title>

        <script src="${applicationScope.resourceServerUrl}js/page/disciplines.js"></script>
    </head>
    <body>
        <%@include file="../include/header-body.jsp"%>
        <%@include file="../include/menu-top.jsp"%>

        <div class="container min_H_400">
            <input type="hidden" id="userType" value="${requestScope.userRole}">

            <h4 class="pull-left"><fmt:message key="my3o.ui.menu.school.disciplines" /></h4>
            <c:if test="${my3o:hasRole(pageContext.request, 'SuperAdmin')
                                or my3o:hasRole(pageContext.request, 'SchoolAdmin')
                                or my3o:hasRole(pageContext.request, 'RegistrarAccounts')}" >
                <button class="btn btn-primary pull-right" id="add">Добавить предмет</button>
            </c:if>
            <div class="clear"></div>
            <br>
            <table class="table" id="tblDisciplines">
                <thead>
                <tr>
                    <c:if test="${my3o:hasRole(pageContext.request, 'SuperAdmin')
                                or my3o:hasRole(pageContext.request, 'SchoolAdmin')
                                or my3o:hasRole(pageContext.request, 'RegistrarAccounts')}" >
                        <th><fmt:message key="my3o.ui.header.action"/></th>
                    </c:if>
                    <th><fmt:message key="my3o.ui.header.discipline"/></th>
                    <th><fmt:message key="my3o.ui.header.description"/></th>
                    <th><fmt:message key="my3o.ui.header.status"/></th>
                </tr>
                </thead>
                <tbody>

                </tbody>
            </table>


        </div> <!-- /container -->
    
        <%@include file="../include/menu-bottom.jsp"%>
        <%@include file="dialog/disciplines-edit.jsp"%>

    </body>
</html>


