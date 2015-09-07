<%@page pageEncoding="UTF-8" session="true"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../include/header.jsp"%>
    
        <title><fmt:message key="my3o.ui.MyNotification.title" /> - <fmt:message key="my3o.ui.title" /></title>

        <script src="${applicationScope.resourceServerUrl}js/page/notification-user.js"></script>
    </head>
    <body>
        <%@include file="../include/header-body.jsp"%>
        <%@include file="../include/menu-top.jsp"%>

        <div class="container min_H_400">

            <h4 class="pull-left"><fmt:message key="my3o.ui.MyNotification.title" /></h4>

            <div class="clear"></div>
            <br>


            <table class="table" id="tblNotificationUser">
                <thead>
                    <tr>
                        <td>Название</td>
                        <td>Описание</td>
                        <td>Текст сообщения</td>
                        <td>Дата</td>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="item" items="${userNotification}">
                        <tr>
                            <td>${item.name}</td>
                            <td>${item.description}</td>
                            <td>${item.text}</td>
                            <td>${item.dateOn}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>


        </div> <!-- /container -->
    
        <%@include file="../include/menu-bottom.jsp"%>
        <%@include file="dialog/country-edit.jsp"%>

    </body>
</html>


