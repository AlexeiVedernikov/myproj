<%@page pageEncoding="UTF-8" session="true"%>
<!DOCTYPE html>
<html>
<head>
    <%@include file="../include/header.jsp"%>
    
    <title><fmt:message key="my3o.ui.title" /></title>
</head>
<body>
	<%@include file="../include/header-body.jsp"%>
	<%@include file="../include/menu-top.jsp"%>

    <div class="container min_H_400">



		<h4>Проект «Вся школа»</h4>
<p>Проект представляет собой единую информационную площадку для взаимодействия 
руководства образовательных учреждений, педагогов, родителей и учащихся. </p>
		<br/> 
        <h4> Организационные документы </h4>
        <a href="${applicationScope.resourceServerUrl}documents/mail-barishnikov.pdf">1. Письмо заместителя начальника УГИБДД ГУ МВД России по Ростовской области С.И. Барышникова</a>
        <br>
        <a href="${applicationScope.resourceServerUrl}documents/mail-mazaeva.pdf">2. Письмо руководителям муниципальных органов, осуществляющих управление в сфере образования (Заместитель министра образования Ростовской области М.А. Мазаева)</a>
        <br>&nbsp;&nbsp;&nbsp;&nbsp;<a href="${applicationScope.resourceServerUrl}documents/prilozhenie-1.pdf">Приложение №1</a>
        <br>&nbsp;&nbsp;&nbsp;&nbsp;<a href="${applicationScope.resourceServerUrl}documents/prilozhenie-2.pdf">Приложение №2</a>
        <br>&nbsp;&nbsp;&nbsp;&nbsp;<a href="${applicationScope.resourceServerUrl}documents/prilozhenie-3.pdf">Приложение №3</a>
        <br>&nbsp;&nbsp;&nbsp;&nbsp;<a href="${applicationScope.resourceServerUrl}documents/prilozhenie-4.pdf">Приложение №4</a>
        <br><a href="${applicationScope.resourceServerUrl}documents/dogovor.pdf">3. Договор о оказании услуг (публичная оферта)</a>
		<hr>
		<h4>Контактная информация</h4>
		По всем вопросам следует обращаться по адресу <a href="mailto:info@my3o.ru">info@my3o.ru</a>
		<br>
		Контактное лицо: Афанасьев Алексей Николаевич, тел.: +7(928)126-11-70
		<br><br>

        <div class="row">
            <div class="col-sm-2"><span class="pull-right">Сегодня наш ресурс посетили</span></div>
            <div class="col-sm-10" id="qoo-counter">
                <a class="qoo" href="http://qoo.by/">
                    <img src="http://qoo.by/counter/small/008s.png">
                    <div id="qoo-counter-visits"></div>
                    <div id="qoo-counter-views" style="display:none;"></div>
                </a>
            </div>
            <script type="text/javascript" src="http://qoo.by/counter.js"></script>
        </div>
        <br>
    </div> <!-- /container -->

    <%@include file="../include/menu-bottom.jsp"%> 

</body>
</html>


