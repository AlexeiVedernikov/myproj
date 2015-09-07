<%@page pageEncoding="UTF-8" session="true"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../include/header.jsp"%>
    
        <title><fmt:message key="my3o.ui.amount.title" /> - <fmt:message key="my3o.ui.title" /></title>

        <%--<script src="${applicationScope.resourceServerUrl}js/page/countries.js"></script>--%>
    </head>
    <body>
        <%@include file="../include/header-body.jsp"%>
        <%@include file="../include/menu-top.jsp"%>

        <div class="container min_H_400">

            <h3 class="pull-left">Услуги и стоимость</h3>

            <div class="clear"></div>
            <br>

            <h4>Основные услуги www.my3o.ru для школы</h4>
            <p>1. Информирование о посещаемости, успеваемости, «точки» в журнале.</p>
            &emsp;Предоставляется родителям на основе данных классных журналов согласно выбранной интенсивности:<br>

            &emsp;&emsp;   Тариф "Стандартный" - уведомление 1 раз в неделю - 100 руб. в месяц<br>

            &emsp;&emsp;   Тариф "Полный" - уведомления 2 раза в неделю - 150 руб. в месяц<br>

            <br>
            <span class="text-muted">
                <p>2. Информирование о домашнем задании и возможность исправления плохих оценок. (в разработке)</p>

                &emsp; На основе учебного плана школы обычный тариф - 50 руб. в месяц<br>

                &emsp; На основе учебного плана учителя тариф учитель - 100 руб. в месяц<br>

                &emsp; По индивидуальному плану учителя - заполняется учителем.<br>
                <br>
                <p>3. Контроль «вход – выход» из школы. (в разработке)</p>
                &emsp;Осуществляется по индивидуальному для каждой школы тарифу с использованием установленного в школе оборудования.
                Оценивается индивидуально
                при совместимости этого оборудования с www.my3o.ru Возможна установка нашего оборудования.
            </span>
            <br>
            <br>
            <a href="${applicationScope.resourceServerUrl}documents/dogovor1110.pdf">Договор на оказание информационных услуг</a><br>
            <a href="${applicationScope.resourceServerUrl}documents/Obrazets_platyozhnogo_poruchenia.pdf">Образец платёжного поручения</a>
            <%--<br>--%>
            <%--<a href="${applicationScope.resourceServerUrl}documents/osnovnie-uslugi.doc">Основные услуги www.my3o.ru</a>--%>
            <br><br>
        </div> <!-- /container -->

        <%@include file="../include/menu-bottom.jsp"%>
        <%@include file="dialog/country-edit.jsp"%>

    </body>
</html>


