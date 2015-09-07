<%@page pageEncoding="UTF-8" session="true"%>
<!DOCTYPE html>
<html>
    <head>
	    <%@include file="../include/header.jsp"%>

	    <title><fmt:message key="my3o.ui.header.settingMail" /> - <fmt:message key="my3o.ui.title" /></title>

        <script src="${applicationScope.resourceServerUrl}js/page/setting-mail.js"></script>
    </head>
    <body>
        <%@include file="../include/header-body.jsp"%>
        <%@include file="../include/menu-top.jsp"%>

        <div class="container min_H_400">

            <h4 class="pull-left"><fmt:message key="my3o.ui.header.settingMail" /></h4>
            <button id="aSettingMailAdd" type="button" class="btn btn-primary pull-right">Добавить</button>
            <div class="clear"></div>
            <br>

            <table  class="table" id="tblSettigMail">
                <thead>
                    <tr>
                        <th><fmt:message key="my3o.ui.header.action"/></th>
                        <th><fmt:message key="my3o.ui.header.name"/></th>
                        <th><fmt:message key="my3o.ui.header.description"/></th>
                        <th><fmt:message key="my3o.ui.header.host"/></th>
                        <th hidden="hidden"><fmt:message key="my3o.ui.header.port"/></th>
                        <th><fmt:message key="my3o.ui.header.login"/></th>
                        <th hidden="hidden"><fmt:message key="my3o.ui.header.password"/></th>
                        <th><fmt:message key="my3o.ui.header.defaultSender"/></th>
                        <th><fmt:message key="my3o.ui.header.defaultSubjectPrefix"/></th>
                        <th><fmt:message key="my3o.ui.header.status"/></th>
                        <th hidden="hidden"><fmt:message key="my3o.ui.header.organization"/></th>
                    </tr>
                </thead>
                <tbody>
                
                   <c:forEach items="${settingMailList}" var="item">
                        <tr trId="${item.id}">
                            <td class="edit width_70" settingMailId="${item.id}">
                                <button type="button" class="btn btn-default aSettingMailEdit">
                                    <span class="glyphicon glyphicon-pencil"></span>
                                </button>
                                <button type="button" class="btn btn-default aSettingMailDelete">
                                    <span class="glyphicon glyphicon glyphicon-trash"></span>
                                </button>
                            </td>
                            <td class="name">${item.name}</td>
                            <td class="description">${item.description}</td>
                            <td class="host">${item.host}</td>
                            <td class="port" hidden="hidden">${item.port}</td>
                            <td class="login">${item.userName}</td>
                            <td class="password" hidden="hidden">${item.userPassword}</td>
                            <td class="defaultSender">${item.defaultSender}</td>
                            <td class="defaultSubjectPrefix">${item.defaultSubjectPrefix}</td>
                            <td class="status" status="${item.status}">${item.status}</td>
                            <td class="organization" organizationId="${item.organizationDto.id}" hidden="hidden">${item.organizationDto.name}</td>
                        </tr>
                    </c:forEach>
                    
                </tbody>
            </table>

        </div> <!-- /container -->

        <%@include file="../include/menu-bottom.jsp"%>
         <%@include file="dialog/settingMail-edit.jsp"%>
    </body>
</html>