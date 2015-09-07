<%@page pageEncoding="UTF-8" session="true"%>
<!DOCTYPE html>
<html>
<head>
    <%@include file="../include/header.jsp"%>
    
    <title><fmt:message key="my3o.ui.users.title" /> - <fmt:message key="my3o.ui.title" /></title>
    
    <script src="${applicationScope.resourceServerUrl}js/page/users.js"></script>
</head>
<body>
	<%@include file="../include/header-body.jsp"%>
	<%@include file="../include/menu-top.jsp"%>

    <div class="container min_H_400">
        <input type="hidden" id="userType" value="${requestScope.userRole}">

        <h4 class="pull-left"><fmt:message key="my3o.ui.users.title" /></h4>
        <c:if test="${my3o:hasRole(pageContext.request, 'SuperAdmin')
                        or my3o:hasRole(pageContext.request, 'SchoolAdmin')
                        or my3o:hasRole(pageContext.request, 'RegistrarAccounts')}" >
            <button class="btn btn-primary pull-right" id="add">Добавить</button>
        </c:if>

        <div class="clear"></div>
        <br>

        <form class="form-inline" role="form">
            <div class="form-group">
                <label for="selUserType" class="control-label">Тип пользователя</label>
                &nbsp
                <select id="selUserType" class="selectpicker" data-width="200px">
                    <c:if test="${selectedOrganization == 'disabled'}">
                        <option value="disabled">--Выберите организацию--</option>
                    </c:if>

                    <c:if test="${selectedOrganization != 'disabled'}">
                        <option value="disabled">--Выберите тип пользователя--</option>

                        <c:if test="${my3o:hasRole(pageContext.request, 'SuperAdmin')}">
                            <option value="SuperAdmin">Администратор</option>
                            <option value="RegistrarAccounts">Регистратор аккаунтов</option>
                            <option value="Onlooker">Наблюдатель</option>
                            <option value="ResponsibleForPayment">Ответственный за оплату</option>
                            <option value="SchoolAdmin">Школьный администратор</option>
                            <option value="OperatorMarks">Наборщик оценок</option>
                            <option value="Teacher">Учитель</option>
                            <option value="Parent">Родитель</option>
                            <option value="Scholar">Ученик</option>
                            <option value="Other">Другой</option>
                        </c:if>

                        <c:if test="${my3o:hasRole(pageContext.request, 'RegistrarAccounts')}">
                            <option value="RegistrarAccounts">Регистратор аккаунтов</option>
                            <option value="Onlooker">Наблюдатель</option>
                            <option value="ResponsibleForPayment">Ответственный за оплату</option>
                            <option value="SchoolAdmin">Школьный администратор</option>
                            <option value="OperatorMarks">Наборщик оценок</option>
                            <option value="Teacher">Учитель</option>
                            <option value="Parent">Родитель</option>
                            <option value="Scholar">Ученик</option>
                            <option value="Other">Другой</option>
                        </c:if>

                        <c:if test="${my3o:hasRole(pageContext.request, 'SchoolAdmin') or my3o:hasRole(pageContext.request, 'Onlooker')
                        or my3o:hasRole(pageContext.request, 'ResponsibleForPayment')}">
                            <option value="ResponsibleForPayment">Ответственный за оплату</option>
                            <option value="SchoolAdmin">Школьный администратор</option>
                            <option value="OperatorMarks">Наборщик оценок</option>
                            <option value="Teacher">Учитель</option>
                            <option value="Parent">Родитель</option>
                            <option value="Scholar">Ученик</option>
                            <option value="Other">Другой</option>
                        </c:if>

                        <c:if test="${my3o:hasRole(pageContext.request, 'OperatorMarks')}">
                            <option value="Teacher">Учитель</option>
                            <option value="Parent">Родитель</option>
                            <option value="Scholar">Ученик</option>
                        </c:if>
                    </c:if>
                </select>
            </div>

            &nbsp

            <div class="form-group">
                <label for="selGroupPage" class="control-label">Класс</label>
                &nbsp
                <select id="selGroupPage" class="selectpicker" data-size="5" data-live-search="true" data-width="200px" disabled>

                    <c:if test="${'selectedOrganization'} != 'disabled' ">
                        <option>--Выберите организацию--</option>
                    </c:if>

                    <c:if test="${groupList.size() == 0}">
                        <option>--Выберите организацию--</option>
                    </c:if>

                    <c:if test="${groupList.size() > 0}">
                        <option>--Выберите класс--</option>
                    </c:if>

                    <c:forEach var="item" items="${groupList}">
                        <option value=${item.id}>Класс ${item.name}</option>
                    </c:forEach>

                </select>
            </div>
        </form>
		
		<div class="clear"></div>
		<br>

    	<table class="table" id="tblUsers">
		    <thead>
                <tr>
                    <c:if test="${my3o:hasRole(pageContext.request, 'SuperAdmin')
                                or my3o:hasRole(pageContext.request, 'SchoolAdmin')
                                or my3o:hasRole(pageContext.request, 'RegistrarAccounts')}" >
                        <th><fmt:message key="my3o.ui.header.action"/></th>
                    </c:if>
                    <th><fmt:message key="my3o.ui.header.surname"/></th>
                    <th><fmt:message key="my3o.ui.header.email"/></th>
                    <th><fmt:message key="my3o.ui.header.phone"/></th>
                    <th><fmt:message key="my3o.ui.header.organization"/></th>
                    <th><fmt:message key="my3o.ui.header.group"/></th>
                    <th><fmt:message key="my3o.ui.header.description"/></th>
                    <th><fmt:message key="my3o.ui.header.userType"/></th>
                    <th><fmt:message key="my3o.ui.header.status"/></th>
                </tr>
		    </thead>
		    
		    <tbody>
		    </tbody>
    	</table>
    
    </div> <!-- /container -->

    <%@include file="../include/menu-bottom.jsp"%> 
    <%@include file="dialog/user-edit.jsp"%>
    
</body>
</html>