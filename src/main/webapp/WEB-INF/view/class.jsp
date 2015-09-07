<%@page pageEncoding="UTF-8" session="true"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../include/header.jsp"%>
    
        <title><fmt:message key="my3o.ui.menu.school.class" /> - <fmt:message key="my3o.ui.title" /></title>

        <%--<script src="${applicationScope.resourceServerUrl}js/page/countries.js"></script>--%>
    </head>
    <body>
        <%@include file="../include/header-body.jsp"%>
        <%@include file="../include/menu-top.jsp"%>

        <div class="container min_H_400">

            <h4 class="pull-left"><fmt:message key="my3o.ui.menu.school.class" /></h4>

            <div class="clear"></div>
            <br>

        </div> <!-- /container -->
    
        <%@include file="../include/menu-bottom.jsp"%>
        <%@include file="dialog/country-edit.jsp"%>

    </body>
</html>


