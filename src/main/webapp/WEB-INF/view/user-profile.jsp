<%@page pageEncoding="UTF-8" session="true"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../include/header.jsp"%>

        <title><fmt:message key="my3o.ui.menu.guides.user-profile" /> - <fmt:message key="my3o.ui.title" /></title>

        <script src="${applicationScope.resourceServerUrl}js/page/user-profile.js"></script>
        <script src="${applicationScope.resourceServerUrl}js/page/parents.js"></script>
        <script src="${applicationScope.resourceServerUrl}js/page/child.js"></script>

    </head>
    <body>
        <%@include file="../include/header-body.jsp"%>
        <%@include file="../include/menu-top.jsp"%>

<div class="container min_H_400">

<h4 class="pull-left"><fmt:message key="my3o.ui.menu.guides.user-profile"/></h4>

<div class="clear"></div>
<br>
<div id="tabs">
<ul class="nav nav-tabs" role="tablist">
    <li class="active"><a href="#information" role="tab" data-toggle="tab">Информация</a></li>
    <c:if test="${user.userType == 'Scholar'}">
        <li><a href="#parents" role="tab" data-toggle="tab">Родители</a></li>
    </c:if>
    <c:if test="${user.userType == 'Parent'}">
        <li><a href="#childs" role="tab" data-toggle="tab">Дети</a></li>
    </c:if>
    <%--<li><a href="#groups" role="tab" data-toggle="tab">Группы</a></li>--%>
</ul>
</div>

<div class="tab-content">
    <div class="tab-pane active" id="information">
        <form class="form-horizontal">
            <br>
            <input type="hidden" id="userId" value="${user.id}">

            <div class="form-group">
                <label for="userNameDlg" class="col-sm-2 control-label"> ФИО</label>
                <div class="col-sm-2">
                    <input type="text" id="userNameDlg" readonly value="${user.name}"/>
                </div>
            </div>

            <div class="form-group">
                <label for="userDescriptionDlg" class="col-sm-2 control-label">Примечание</label>
                <div class="col-sm-2">
                    <input type="text" id="userDescriptionDlg" readonly value="${user.description}"/>
                </div>
            </div>

            <div class="form-group">
                <label for="userEmailDlg" class="col-sm-2 control-label">Email</label>
                <div class="col-sm-2">
                    <input type="text" id="userEmailDlg" readonly value="${user.email}"/>
                </div>
            </div>

            <div class="form-group">
                <label for="userPasswordDlg" class="col-sm-2 control-label">Пароль</label>
                <div class="col-sm-2">
                    <input type="password" readonly id="userPasswordDlg"/>
                </div>
            </div>

            <div class="form-group">
                <label for="userPhoneDlg" class="col-sm-2 control-label">Телефон</label>
                <div class="col-sm-2">
                    <input type="text" id="userPhoneDlg" readonly value="${user.phone}"/>
                </div>
            </div>

            <div class="modal-footer">
                <button id="userProfileEdit" class="btn btn-default" disabled>Редактировать</button>
                <button id="userProfileSaveDlg" class="btn btn-success" disabled>Сохранить</button>
            </div>
        </form>
    </div>
<c:if test="${user.userType == 'Scholar'}">
    <div class="tab-pane" id="parents">
    <div class="clear"></div>
    <br>
    <table class="table" id="tblParent">
    <thead>
    <tr>
    <th><fmt:message key="my3o.ui.header.action"/></th>
    <th><fmt:message key="my3o.ui.header.surname"/></th>
    <th><fmt:message key="my3o.ui.header.email"/></th>
    <th><fmt:message key="my3o.ui.header.phone"/></th>
    <th><fmt:message key="my3o.ui.header.description"/></th>
    <th><fmt:message key="my3o.ui.header.userType"/></th>
    <th><fmt:message key="my3o.ui.header.status"/></th>
    </tr>
    </thead>

    <tbody>
    <c:forEach items="${userList}" var="item">
        <tr trParentID = "${item.id}">
            <td class = "edit">
                <c:if test="${my3o:hasRole(pageContext.request, 'SuperAdmin')
                                            or my3o:hasRole(pageContext.request, 'SchoolAdmin')
                                            or my3o:hasRole(pageContext.request, 'RegistrarAccounts')}" >
                    <button type="button" class="btn btn-default parentDelete">
                        <span class="glyphicon glyphicon glyphicon-trash"></span>
                    </button>
                </c:if>
                <button type="button" class="btn btn-default parentProfile">
                    <span class="glyphicon glyphicon glyphicon-user"></span>
                </button>
            </td>
            <td class="name">${item.name}</td>
            <td class="email">${item.email}</td>
            <td class="phone">${item.phone}</td>
            <td class="description">${item.description}</td>
            <td class="userType" userType ="${item.userType}">${item.userType}</td>
            <td class="status" status ="${item.status}">${item.status}</td>
        </tr>
    </c:forEach>
    </tbody>
    </table>
        <c:if test="${my3o:hasRole(pageContext.request, 'SuperAdmin')
                                    or my3o:hasRole(pageContext.request, 'SchoolAdmin')
                                    or my3o:hasRole(pageContext.request, 'RegistrarAccounts')}" >
            <button type="button" class="btn btn-primary" id="addParent">Добавить родителя</button>
        </c:if>

        <div id="findAndAddParent">
            <br>
            <hr>

            <h4>Поиск родителей</h4>
            <br>
            <label for="parentName">Фамилия</label>
            <input type="text" id="parentName">
            <button type="button" class="btn btn-default" value="Find" id="FindParent">Найти</button>
            <div class="clear"></div>
            <br>
            <table class="table" id="tblParentOut">
                <thead>
                <tr>
                    <th><fmt:message key="my3o.ui.header.action"/></th>
                    <th><fmt:message key="my3o.ui.header.surname"/></th>
                    <th><fmt:message key="my3o.ui.header.email"/></th>
                    <th><fmt:message key="my3o.ui.header.phone"/></th>
                    <th><fmt:message key="my3o.ui.header.description"/></th>
                    <th><fmt:message key="my3o.ui.header.userType"/></th>
                    <th><fmt:message key="my3o.ui.header.status"/></th>
                </tr>
                </thead>

                <tbody class="bodyParents">

                </tbody>
            </table>
            <button type="button" class="btn btn-primary" id="parentSave">Добавить родителя</button>
            <div class="clear"></div>
            <br>
        </div>
        </div>
    </c:if>

    <c:if test="${user.userType == 'Parent'}">
        <div class="tab-pane" id="childs">
            <div class="clear"></div>
            <br>
            <table class="table" id="tblChild">
                <thead>
                <tr>
                    <th><fmt:message key="my3o.ui.header.action"/></th>
                    <th><fmt:message key="my3o.ui.header.surname"/></th>
                    <th><fmt:message key="my3o.ui.header.email"/></th>
                    <th><fmt:message key="my3o.ui.header.phone"/></th>
                    <th><fmt:message key="my3o.ui.header.description"/></th>
                    <th><fmt:message key="my3o.ui.header.userType"/></th>
                    <th><fmt:message key="my3o.ui.header.status"/></th>
                </tr>
                </thead>

                <tbody>
                <c:forEach items="${userList}" var="item">
                    <tr trChildID = "${item.id}">
                        <td class = "edit">
                            <c:if test="${my3o:hasRole(pageContext.request, 'SuperAdmin')
                                        or my3o:hasRole(pageContext.request, 'SchoolAdmin')
                                        or my3o:hasRole(pageContext.request, 'RegistrarAccounts')}" >
                                <button type="button" class="btn btn-default childDelete">
                                    <span class="glyphicon glyphicon glyphicon-trash"></span>
                                </button>
                            </c:if>
                            <button type="button" class="btn btn-default childProfile">
                                <span class="glyphicon glyphicon glyphicon-user"></span>
                            </button>
                        </td>
                        <td class="name">${item.name}</td>
                        <td class="email">${item.email}</td>
                        <td class="phone">${item.phone}</td>
                        <td class="description">${item.description}</td>
                        <td class="userType" userType ="${item.userType}">${item.userType}</td>
                        <td class="status" status ="${item.status}">${item.status}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <c:if test="${my3o:hasRole(pageContext.request, 'SuperAdmin')
                                or my3o:hasRole(pageContext.request, 'SchoolAdmin')
                                or my3o:hasRole(pageContext.request, 'RegistrarAccounts')}" >
                <button type="button" class="btn btn-primary" id="addChild">Добавить ребенка</button>
            </c:if>

            <div id="findAndAddChild">
                <br>
                <hr>

                <h4>Поиск детей</h4>
                <br>
                <label for="childName">Фамилия</label>
                <input type="text" id="childName">
                <button type="button" class="btn btn-default" value="Find" id="FindChild">Найти</button>
                <div class="clear"></div>
                <br>
                <table class="table" id="tblChildOut">
                    <thead>
                    <tr>
                        <th><fmt:message key="my3o.ui.header.action"/></th>
                        <th><fmt:message key="my3o.ui.header.surname"/></th>
                        <th><fmt:message key="my3o.ui.header.email"/></th>
                        <th><fmt:message key="my3o.ui.header.phone"/></th>
                        <th><fmt:message key="my3o.ui.header.description"/></th>
                        <th><fmt:message key="my3o.ui.header.userType"/></th>
                        <th><fmt:message key="my3o.ui.header.status"/></th>
                    </tr>
                    </thead>

                    <tbody class="bodyChild">

                    </tbody>
                </table>
                <button type="button" class="btn btn-primary" id="childSave">Добавить ребенка</button>
                <div class="clear"></div>
                <br>
            </div>
        </div>
    </c:if>
    <%--<div class="tab-pane" id="groups">--%>

    <%--</div>--%>
    </div>

    </div> <!-- /container -->

    <%@include file="../include/menu-bottom.jsp"%>

    </body>
</html>