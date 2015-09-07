<%@page pageEncoding="UTF-8" session="true"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../include/header.jsp"%>
    
        <title><fmt:message key="my3o.ui.menu.school.journal"/> - <fmt:message key="my3o.ui.title" /></title>

        <script src="${applicationScope.resourceServerUrl}js/page/journals.js"></script>
    </head>
    <body>
        <%@include file="../include/header-body.jsp"%>
        <%@include file="../include/menu-top.jsp"%>

        <div class="container min_H_400">
            <input type="hidden" id="userType" value="${requestScope.userRole}">
            <input type="hidden" id="userId" value="${requestScope.userId}">

            <h4 class="pull-left"><fmt:message key="my3o.ui.menu.school.journal"/></h4>

            <div class="clear"></div>
            <br>
            <form class="form-inline" role="form">
                <div class="form-group" style="vertical-align: top">
                    <label for="selGroupPage" class="control-label">Класс</label>
                    &nbsp
                    <select id="selGroupPage" class="selectpicker" data-size="5" data-live-search="true" data-width="190px">
                        <c:if test="${groupList.size() == 0}">
                            <option>--Выберите организацию--</option>
                        </c:if>
                        <c:if test="${groupList.size() > 0}">
                            <option value="disabled" id="optionSelectGroup">--Выберите класс--</option>
                        </c:if>
                        <c:forEach var="item" items="${groupList}">
                            <option value=${item.id}>Класс ${item.name}</option>
                        </c:forEach>
                    </select>
                </div>
                &nbsp
                <div class="form-group" style="vertical-align: top">
                    <label for="selDisciplinePage" class="control-label">Предмет</label>
                    &nbsp
                    <select id="selDisciplinePage" class="selectpicker" data-size="5" data-live-search="true" data-width="190px">
                        <option>--Выберите класс--</option>
                    </select>
                </div>
                &nbsp
                <div class="form-group">
                    <label for="selYearPage" class="control-label">Год</label>
                    &nbsp
                    <select id="selYearPage" class="selectpicker" data-width="100px">
                    </select>
                </div>
                &nbsp
                <div class="form-group">
                    <label for="selMonthPage" class="control-label">Месяц</label>
                    &nbsp
                    <select id="selMonthPage" class="selectpicker" data-size="5" data-width="120px">
                    </select>
                </div>
                <c:if test="${my3o:hasRole(pageContext.request, 'SuperAdmin')
                            or my3o:hasRole(pageContext.request, 'SchoolAdmin')
                            or my3o:hasRole(pageContext.request, 'OperatorMarks')}" >
                    &nbsp
                    <div class="form-group">
                        <div class="btn-group">
                            <button type="button" id="buttonMarkTypePage" class="btn btn-default dropdown-toggle" data-toggle="dropdown" disabled>Добавить рубежную оценку <span class="caret"></span></button>
                            <ul class="dropdown-menu" role="menu">
                                <li><a class="markTypePage" type="quater">Четверть</a></li>
                                <li><a class="markTypePage" type="trimester">Триместр</a></li>
                                <li><a class="markTypePage" type="semester">Полугодие</a></li>
                                <li><a class="markTypePage" type="annual">Годовая</a></li>
                                <li><a class="markTypePage" type="exam">Экзамен</a></li>
                                <li><a class="markTypePage" type="total">Итоговая</a></li>
                            </ul>
                        </div>
                    </div>
                </c:if>
            </form>

            <div class="clear"></div>
            <br>

            <table class="table table-bordered" id="tblJournal">
            </table>

        </div> <!-- /container -->
    
        <%@include file="../include/menu-bottom.jsp"%>
        <%@include file="dialog/journal-edit.jsp"%>

    </body>
</html>


