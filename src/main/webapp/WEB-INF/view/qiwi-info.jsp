<%@page pageEncoding="UTF-8" session="true"%>
<!DOCTYPE html>
<html>
<head>
    <%@include file="../include/header.jsp"%>
    
    <title><fmt:message key="my3o.ui.qiwi.info" /></title>
</head>
<body>
	<%@include file="../include/header-body.jsp"%>
	<%@include file="../include/menu-top.jsp"%>

    <div class="container min_H_400"> 
		
<img width=30% height=30% src="${applicationScope.resourceServerUrl}images/qiwi_logotype.png" alt="ddedddd">

<p>С помощью <a href="https://w.qiwi.com">QIWI Wallet</a> вы можете оплатить наши товары и услуги моментально и без комиссии.</p>
<p>Для этого:</p>
<p>1. Сформируйте заказ на сайте или по телефону,</p>
<p>2. Выберите в качестве оплаты QIWI Wallet и введите номер своего сотового телефона,</p>
<p>3. Оплатите автоматически созданный счет на оплату: на <a href="https://w.qiwi.com">сайте</a>, QIWI Терминале, с помощью приложения для социальных сетей или <a href="https://w.qiwi.com/applications/main.action">мобильного телефона</a>.</p>
<p>QIWI Wallet легко <a href="https://w.qiwi.com/replenish/main.action">пополнить</a> в терминалах QIWI и партнеров, банковскими картами, салонах сотовой связи, супермаркетах, банкоматах или через интернет-банк.</p>
<p>Совершать платежи вы можете не только со счета QIWI Wallet, но и банковской картой, наличными, а также с лицевого счета мобильного телефона Билайн, МегаФон, МТС.</p>
<p>Если у вас еще нет QIWI Wallet, вы можете <a href="https://w.qiwi.com/register/form.action">зарегистрировать</a> его на сайте или в любом из приложений QIWI Wallet за несколько минут.</p>
<p>Остались вопросы? Задайте их в <a href="http://qiwi-in-use.livejournal.com/">блоге</a> компании.</p>

<br>

        <p>Скачать подробное описание способов оплаты вы можете <a href="https://static.qiwi.com/ru/doc/ishop/qiwi_instructions.doc">здесь</a>, а так же посмотреть <a href="http://ishopnew.qiwi.ru/files/qiwi_howtopay.mp4">видео</a> инструкцию.</p>


<br>

<p>Информация взята с официального сайта: <a href="https://w.qiwi.com">w.qiwi.com</a></p>	
		
		
		
		
    </div> <!-- /container -->

    <%@include file="../include/menu-bottom.jsp"%> 

</body>
</html>