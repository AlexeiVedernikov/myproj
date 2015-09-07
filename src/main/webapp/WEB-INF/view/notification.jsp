<%@page pageEncoding="UTF-8" session="true"%>
<!DOCTYPE html>
<html>
    <head>
	    <%@include file="../include/header.jsp"%>

	    <title><fmt:message key="my3o.ui.header.notifications" /> - <fmt:message key="my3o.ui.title" /></title>

        <script src="${applicationScope.resourceServerUrl}js/page/notification.js"></script>
    </head>
    <body>
        <%@include file="../include/header-body.jsp"%>
        <%@include file="../include/menu-top.jsp"%>

        <div class="container min_H_400">

            <h4 class="pull-left"><fmt:message key="my3o.ui.header.notifications" /></h4>

            <button id="aNotificationAdd" type="button" class="btn btn-primary pull-right">Добавить</button>
            <span class="pull-right">&nbsp&nbsp</span>
            <button id="openSettingMail" type="button" class="btn btn-default pull-right">Настроить почту</button>
            <div class="clear"></div>
            <br>

            <div class="progress">
                <div id="progressNotification" class="progress-bar progress-bar-striped active" role="progressbar" style="width: 0%">
                </div>
            </div>

            <div class="clear"></div>
            <br>

            <table  class="table" id="tblNotification">
                <thead>
                    <tr>
                        <th><fmt:message key="my3o.ui.header.action"/></th>
                        <th><fmt:message key="my3o.ui.header.settingMail"/></th>
                        <th><fmt:message key="my3o.ui.header.name"/></th>
                        <th><fmt:message key="my3o.ui.header.description"/></th>
                        <th><fmt:message key="my3o.ui.header.text"/></th>
                        <th><fmt:message key="my3o.ui.header.date"/></th>
                        <th><fmt:message key="my3o.ui.header.notificationType"/></th>
                        <th><fmt:message key="my3o.ui.header.status"/></th>
                        <th>Получатели</th>
                    </tr>
                </thead>
                <tbody>

                </tbody>
            </table>

        </div> <!-- /container -->

        <%@include file="../include/menu-bottom.jsp"%>
         <%@include file="dialog/notification-edit.jsp"%>
    </body>
</html>