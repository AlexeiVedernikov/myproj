<%@page pageEncoding="UTF-8" session="true"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../include/header.jsp"%>
    
        <title><fmt:message key="my3o.ui.menu.school.marks" /> - <fmt:message key="my3o.ui.title" /></title>

        <script src="${applicationScope.resourceServerUrl}js/page/marks.js"></script>
    </head>
    <body>
        <%@include file="../include/header-body.jsp"%>
        <%@include file="../include/menu-top.jsp"%>

        <div class="container min_H_400">
            <input type="hidden" id="userType" value="${requestScope.userRole}">
            <input type="hidden" id="userId" value="${requestScope.userId}">

            <h4 class="pull-left"><fmt:message key="my3o.ui.menu.school.marks" /></h4>

            <div class="clear"></div>
            <br>

            <form class="form-inline" role="form">

                <c:if test="${!(my3o:hasRole(pageContext.request, 'Parent')
                                    or my3o:hasRole(pageContext.request, 'Scholar'))}" >
                    <div class="form-group">
                        <label for="selGroupPage" class="control-label">Класс</label>
                        &nbsp
                        <select id="selGroupPage" class="selectpicker" data-size="5" data-live-search="true" data-width="190px">
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
                        <label for="selScholarPage" class="control-label">Ученик</label>
                        &nbsp
                        <select id="selScholarPage" class="selectpicker" data-size="5" data-live-search="true" data-width="190px">
                            <option>--Выберите класс--</option>
                        </select>
                    </div>
                    &nbsp
                </c:if>

                <c:if test="${my3o:hasRole(pageContext.request, 'Parent')}" >
                    <div class="form-group">
                        <label for="selScholarPage" class="control-label">Ученик</label>
                        &nbsp
                        <select id="selScholarPage" class="selectpicker" data-width="190px">
                            <c:forEach var="item" items="${userDtoList}">
                                <option value=${item.id}>${item.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    &nbsp
                </c:if>
                <div class="form-group">
                    <label for="selYearPage" class="control-label">Год</label>
                    &nbsp
                    <select id="selYearPage" class="selectpicker" data-width="100px">
                    </select>
                </div>
                &nbsp
                <div class="form-group">
                    <label for="selMonth" class="control-label">Месяц</label>
                    &nbsp
                    <select id="selMonth" class="selectpicker" data-size="5" data-width="150px">
                    </select>
                </div>
            </form>

            <div class="clear"></div>
            <br>

            <table class="table table-bordered table-striped" id="tblMarks">
            </table>

            <span class="label label-primary" style='font-size: 13px'>Оценка</span>
            <%--<span class="label label-info">Сочинение/изложение</span>--%>
            <%--<span class="label label-warning">Д/з</span>--%>
            <span class="label label-danger" style='font-size: 13px'>Пропуск</span>
            <span class="label label-warning" style='font-size: 13px'>Рубежная оценка</span>

            <div class="clear"></div>
            <br>

        </div> <!-- /container -->
    
        <%@include file="../include/menu-bottom.jsp"%>

    </body>
</html>


