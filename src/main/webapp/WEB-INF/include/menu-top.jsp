<%@page pageEncoding="UTF-8" session="true"%>

<div class="navbar navbar-default" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button data-target=".navbar-collapse" data-toggle="collapse" class="navbar-toggle" type="button">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a href="" class="navbar-brand"><fmt:message key="my3o.ui.title.short"/></a>
        </div>
        <div class="navbar-collapse collapse">
            <c:if test="${my3o:isAuthenticated(pageContext.request)}" >
                <p class="navbar-text navbar-right"><a href="user-profile?id=${requestScope.userId}" class="navbar-link">${requestScope.userName} <span class="glyphicon glyphicon glyphicon-user"></span></a></p>
            </c:if>
        </div>
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav">

<!-- <li><a href="contacts"><fmt:message key="my3o.ui.menu.guides.contacts"/></a></li>  -->

                <c:if test="${my3o:isAuthenticated(pageContext.request)}" >

                    <c:if test="${my3o:hasRole(pageContext.request, 'SuperAdmin')
                                or my3o:hasRole(pageContext.request, 'RegistrarAccounts')}" >
                        <li class="dropdown">
                            <a data-toggle="dropdown" class="dropdown-toggle" href="#"><fmt:message key="my3o.ui.menu.guides"/> <span class="caret"></span></a>
                            <ul role="menu" class="dropdown-menu">
                                <li><a href="reference/countries"><fmt:message key="my3o.ui.menu.guides.countries"/></a></li>
                                <li><a href="reference/regions"><fmt:message key="my3o.ui.menu.guides.regions"/></a></li>
                                <li><a href="reference/districts"><fmt:message key="my3o.ui.menu.guides.districts"/></a></li>
                                <li><a href="reference/towns"><fmt:message key="my3o.ui.menu.guides.towns"/></a></li>
                                <li><a href="reference/towns-area"><fmt:message key="my3o.ui.menu.guides.towns-area"/></a></li>
                                <%--<li><a href="reference/schools"><fmt:message key="my3o.ui.menu.guides.schools"/></a></li>--%>
                            </ul>
                        </li>
                    </c:if>

                    <li class="dropdown">
                        <a data-toggle="dropdown" class="dropdown-toggle" href="#"><fmt:message key="my3o.ui.menu.school.title"/> <span class="caret"></span></a>
                        <ul role="menu" class="dropdown-menu">
                            <c:if test="${my3o:hasRole(pageContext.request, 'SuperAdmin')
                                        or my3o:hasRole(pageContext.request, 'ResponsibleForPayment')
                                        or my3o:hasRole(pageContext.request, 'SchoolAdmin')
                                        or my3o:hasRole(pageContext.request, 'OperatorMarks')
                                        or my3o:hasRole(pageContext.request, 'Onlooker')
                                        or my3o:hasRole(pageContext.request, 'Teacher')
                                        or my3o:hasRole(pageContext.request, 'RegistrarAccounts')}" >
                                <li><a href="school/journals"><fmt:message key="my3o.ui.menu.school.journal"/></a></li>
                            </c:if>
                            <%--<li><a href="school/shedules"><fmt:message key="my3o.ui.menu.school.shedules"/></a></li>--%>
                            <%--<li><a href="school/visits"><fmt:message key="my3o.ui.menu.school.visits"/></a></li>--%>
                            <c:if test="${!my3o:hasRole(pageContext.request, 'ResponsibleForPayment')}" >
                                <li><a href="school/marks"><fmt:message key="my3o.ui.menu.school.marks"/></a></li>
                            </c:if>
                            <%--<li class="divider"></li>--%>
                            <c:if test="${my3o:hasRole(pageContext.request, 'SuperAdmin')
                                or my3o:hasRole(pageContext.request, 'SchoolAdmin')
                                or my3o:hasRole(pageContext.request, 'OperatorMarks')
                                or my3o:hasRole(pageContext.request, 'Onlooker')
                                or my3o:hasRole(pageContext.request, 'RegistrarAccounts')}" >
                                <%--<li class="dropdown-header"><fmt:message key="my3o.ui.menu.school.head"/></li>--%>
                                <%--<li><a href="groups"><fmt:message key="my3o.ui.menu.school.class"/></a></li>--%>
                                <%--<li><a href="school/rooms"><fmt:message key="my3o.ui.menu.school.rooms"/></a></li>--%>
                                <%--<li><a href="school/plans"><fmt:message key="my3o.ui.menu.school.plans"/></a></li>--%>
                                <li><a href="school/disciplines"><fmt:message key="my3o.ui.menu.school.disciplines"/></a></li>
                            </c:if>
                        </ul>
                    </li>

                    <c:if test="${my3o:hasRole(pageContext.request, 'SuperAdmin')
                                or my3o:hasRole(pageContext.request, 'SchoolAdmin')
                                or my3o:hasRole(pageContext.request, 'ResponsibleForPayment')
                                or my3o:hasRole(pageContext.request, 'OperatorMarks')
                                or my3o:hasRole(pageContext.request, 'Onlooker')
                                or my3o:hasRole(pageContext.request, 'Teacher')
                                or my3o:hasRole(pageContext.request, 'RegistrarAccounts')}" >
                    <li><a href="users"><fmt:message key="my3o.ui.users.title"/></a></li>
                    <li><a href="groups"><fmt:message key="my3o.ui.groups.title"/></a></li>
                    </c:if>

                    <c:if test="${my3o:hasRole(pageContext.request, 'SuperAdmin')
                                or my3o:hasRole(pageContext.request, 'SchoolAdmin')
                                or my3o:hasRole(pageContext.request, 'ResponsibleForPayment')
                                or my3o:hasRole(pageContext.request, 'OperatorMarks')
                                or my3o:hasRole(pageContext.request, 'Onlooker')
                                or my3o:hasRole(pageContext.request, 'RegistrarAccounts')}" >
                        <li><a href="organizations"><fmt:message key="my3o.ui.organizations.title"/></a></li>
                    </c:if>

                    <c:if test="${my3o:hasRole(pageContext.request, 'SuperAdmin')
                                or my3o:hasRole(pageContext.request, 'SchoolAdmin')}" >
                        <li><a href="notifications"><fmt:message key="my3o.ui.header.notifications"/></a></li>
                    </c:if>

                    <c:if test="${my3o:hasRole(pageContext.request, 'SuperAdmin')
                                or my3o:hasRole(pageContext.request, 'ResponsibleForPayment')}">
                        <li><a href="paid-groups"><fmt:message key="my3o.ui.paid.title"/></a></li>
                    </c:if>

                    <c:if test="${my3o:hasRole(pageContext.request, 'Parent')}" >
                        <li><a href="bill-user"><fmt:message key="my3o.ui.MyBill.title"/></a></li>
                    </c:if>
                    <c:if test="${my3o:hasRole(pageContext.request, 'Scholar')
                                or my3o:hasRole(pageContext.request, 'Parent')}" >
                        <li><a href="notifications-user"><fmt:message key="my3o.ui.MyNotification.title"/></a></li>
                    </c:if>
                </c:if>
            </ul>

            <ul class="nav navbar-nav navbar-right">
                <c:if test="${!my3o:isAuthenticated(pageContext.request)}" >
                    <li>
                        <a id="aLoginLink" class="navbar-link" href="login">Вход</a>
                    </li>
                </c:if>
                <c:if test="${my3o:isAuthenticated(pageContext.request)}" >
                    <c:if test="${my3o:hasRole(pageContext.request, 'SuperAdmin')
                                or my3o:hasRole(pageContext.request, 'ResponsibleForPayment')
                                or my3o:hasRole(pageContext.request, 'SchoolAdmin')
                                or my3o:hasRole(pageContext.request, 'RegistrarAccounts')
                                or my3o:hasRole(pageContext.request, 'OperatorMarks')
                                or my3o:hasRole(pageContext.request, 'Onlooker')
                                or my3o:hasRole(pageContext.request, 'Teacher')}" >
                        <li>
                            <c:if test="${! empty requestScope.listOrg}">
                                <select id="selOrganizationForMenu" class="form-control">
                                    <%--<option value="disabled">--Выбрать организацию--</option>--%>
                                    <c:forEach var="item" items="${requestScope.listOrg}">
                                        <c:choose>
                                            <c:when test="${cookie.organizationId.value == item.key}">
                                                <option selected value="${item.key}">${item.value}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="${item.key}">${item.value}</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </select>
                            </c:if>
                        </li>
                    </c:if>

                    <li>
                        <a class="navbar-link" href="logout">Выход</a>
                    </li>
                </c:if>
            </ul>
        </div><!--/.nav-collapse -->
    </div>
</div>
<div class="clear"></div>

<div id="wrapper" style="z-index: 999; width: 1366px; height: 718px; display:none;
	background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.8); position: absolute; top: 0px; left: 0px;" >
    <p style="text-align: center; color: rgb(255, 255, 255); margin-top: 100px;">Идет обработка. Пожалуйста подождите.</p>
</div>