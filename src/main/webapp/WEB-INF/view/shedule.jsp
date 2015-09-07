<%@page pageEncoding="UTF-8" session="true"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../include/header.jsp"%>
    
        <title><fmt:message key="my3o.ui.menu.school.shedulesGroup"/> - <fmt:message key="my3o.ui.title" /></title>

        <script src="${applicationScope.resourceServerUrl}js/page/shedule.js"></script>
    </head>
    <body>
        <%@include file="../include/header-body.jsp"%>
        <%@include file="../include/menu-top.jsp"%>

        <div class="container min_H_400">

            <div class="pull-left">
                <h4><fmt:message key="my3o.ui.menu.school.shedulesGroup"/> ${groupName.name} (${groupName.organizationDto.name})</h4>
                <select id="selYearPage" class="selectpicker" data-width="150px" data-style="btn-primary">
                    <option value="2014" selected>2014-15 год</option>
                    <option value="2015">2015-16 год</option>
                    <option value="2016">2016-17 год</option>
                    <option value="2017">2017-18 год</option>
                </select>
            </div>
            <button class="btn btn-primary pull-right" id ="add">Добавить</button>

            <div class="clear"></div>
            <br>

            <table class="table" id="tblShedule">
                <thead>
                    <tr>
                        <th><fmt:message key="my3o.ui.header.action"/></th>
                        <th>Предмет</th>
                        <th>Учитель</th>
                        <th>Год</th>
                        <%--<th>Кабинет</th>--%>
                        <%--<th>Время</th>--%>
                        <th><fmt:message key="my3o.ui.header.description"/></th>
                    </tr>
                </thead>
            <tbody>

            </tbody>
            </table>

        </div> <!-- /container -->
    
        <%@include file="../include/menu-bottom.jsp"%>
        <%@include file="dialog/shedule-edit.jsp"%>

    </body>
</html>


