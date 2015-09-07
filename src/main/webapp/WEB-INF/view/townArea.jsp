<%@page pageEncoding="UTF-8" session="true"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../include/header.jsp"%>

        <title><fmt:message key="my3o.ui.menu.guides.towns-area" /> - <fmt:message key="my3o.ui.title" /></title>

        <script src="${applicationScope.resourceServerUrl}js/page/townArea.js"></script>
        <script src="${applicationScope.resourceServerUrl}js/page/selects.js"></script>
    </head>
    <body>
        <%@include file="../include/header-body.jsp"%>
        <%@include file="../include/menu-top.jsp"%>

        <div class="container min_H_400">

            <h4 class="pull-left"><fmt:message key="my3o.ui.menu.guides.towns-area" /></h4>
            <button id="aTownAreaAdd" type="button" class="btn btn-primary pull-right">Добавить</button>
            <div class="clear"></div>
            <br>

            <table  class="table" id="tblTownArea">
                <thead>
                    <tr>
                        <th><fmt:message key="my3o.ui.header.action"/></th>
                        <th><fmt:message key="my3o.ui.header.townsArea"/></th>
                        <th><fmt:message key="my3o.ui.header.town"/></th>
                        <th><fmt:message key="my3o.ui.header.district"/></th>
                        <th><fmt:message key="my3o.ui.header.region"/></th>
                        <th><fmt:message key="my3o.ui.header.country"/></th>
                        <th><fmt:message key="my3o.ui.header.description"/></th>
                        <th><fmt:message key="my3o.ui.header.status"/></th>
                    </tr>
                </thead>

                <tbody>
                    <c:forEach items="${townAreaList}" var="item">
                        <tr trID = "${item.id }">
                            <td class="edit width_70" townAreaId="${item.id}">
                                <button type="button" class="btn btn-default aTownAreaEdit">
                                    <span class="glyphicon glyphicon-pencil"></span>
                                </button>
                                <button type="button" class="btn btn-default aTownAreaDelete">
                                    <span class="glyphicon glyphicon glyphicon-trash"></span>
                                </button>
                            </td>
                            <td class="name">${item.name}</td>
                            <td class="town" townId ="${item.townDto.id}">${item.townDto.name}</td>
                            <td class="district" districtId="${item.townDto.districtDto.id}">${item.townDto.districtDto.name}</td>
                            <td class="region" regionId ="${item.townDto.regionDto.id}">${item.townDto.regionDto.name}</td>
                            <td class="country" countryId ="${item.townDto.regionDto.countryDto.id}">${item.townDto.regionDto.countryDto.name}</td>
                            <td class="description">${item.description}</td>
                            <td class="status" status="${item.status}">${item.status}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

        </div> <!-- /container -->

        <%@include file="../include/menu-bottom.jsp"%>
        <%@include file="dialog/townArea-edit.jsp"%>
    </body>
</html>