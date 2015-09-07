<%@page pageEncoding="UTF-8" session="true"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../include/header.jsp"%>
    
        <title><fmt:message key="my3o.ui.terms.title" /> - <fmt:message key="my3o.ui.title" /></title>

        <%--<script src="${applicationScope.resourceServerUrl}js/page/countries.js"></script>--%>
    </head>
    <body>
        <%@include file="../include/header-body.jsp"%>
        <%@include file="../include/menu-top.jsp"%>

        <div class="container min_H_400">

            <h4 class="pull-left"><fmt:message key="my3o.ui.terms.title" /></h4>

            <div class="clear"></div>
            <br>

            Проект «Вся школа» представляет собой единую информационную площадку для взаимодействия
            руководства образовательных учреждений, педагогов, родителей и учащихся.
            Весь функционал проекта доступен в сети Интернет посредством доступа через браузер ПК
            или мобильных устройств. Для работы с сайтом проекта будет достаточно скорости  доступа
            с мобильных устройств в режиме 3G.
            <br><br>Основой функционала является агрегация данных об успеваемости, посещаемости,
            расписании учащихся учебных заведений Ростовской области. Для каждого образовательного учреждения
            будет составлена база данных учащихся (на основе их заявлений) и их опекунов
            (родителей, бабушек, дедушек и т.п.), которые получат доступ к учетной записи учащегося.
            <br><br>Важной частью проекта является наличие модуля информирования (оповещения через email или СМС)
            всех заинтересованных лиц о плановых и внеплановых школьных мероприятиях, таких как:
            родительские собрания, линейки, отмена занятий  и т.п.
            <br><br>Все персональные данные будут храниться и обрабатываться на территории РФ,
            доступ к персональной информации будет строго разграничен. Сайт проекта построен
            на современных интернет технологиях, гарантирующих его стабильную и безопасную работу.
            <br><br>Масштаб использования проекта может быть расширен и при необходимости сможет
            покрыть образовательные учреждения всех регионов РФ.
            <br><br>На сайте проекта не планируется никакой рекламы и прочих вторичных источником дохода.
            Проект  будет поддерживаться и развиваться только за счет средств, переводимых
            пользователями (опекунами) за доступ к информации и своим учетным записям.
            <br><br>В будущем планируется развитие функционала по интеграции образовательных материалов
            в состав проекта (видео, лекции, презентации и т.п.). Планируется обеспечение обратной
            связи между оценкой учащегося и блоком учебного материала.
            <br><br>
            <h4>Организационные документы</h4>
            <a href="${applicationScope.resourceServerUrl}documents/mail-barishnikov.pdf">1. Письмо заместителя начальника УГИБДД ГУ МВД России по Ростовской области С.И. Барышникова</a>
            <br>
            <a href="${applicationScope.resourceServerUrl}documents/mail-mazaeva.pdf">2. Письмо руководителям муниципальных органов, осуществляющих управление в сфере образования (Заместитель министра образования Ростовской области М.А. Мазаева)</a>
            <br>&nbsp;&nbsp;&nbsp;&nbsp;<a href="${applicationScope.resourceServerUrl}documents/prilozhenie-1.pdf">Приложение №1</a>
            <br>&nbsp;&nbsp;&nbsp;&nbsp;<a href="${applicationScope.resourceServerUrl}documents/prilozhenie-2.pdf">Приложение №2</a>
            <br>&nbsp;&nbsp;&nbsp;&nbsp;<a href="${applicationScope.resourceServerUrl}documents/prilozhenie-3.pdf">Приложение №3</a>
            <br>&nbsp;&nbsp;&nbsp;&nbsp;<a href="${applicationScope.resourceServerUrl}documents/prilozhenie-4.pdf">Приложение №4</a>
            <br><a href="${applicationScope.resourceServerUrl}documents/dogovor.pdf">3. Договор о оказании услуг (публичная оферта)</a>
            <br><br>
        </div> <!-- /container -->
    
        <%@include file="../include/menu-bottom.jsp"%>
        <%@include file="dialog/country-edit.jsp"%>

    </body>
</html>


