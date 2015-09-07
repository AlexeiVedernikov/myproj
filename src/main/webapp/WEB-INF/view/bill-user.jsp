<%@page pageEncoding="UTF-8" session="true"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../include/header.jsp"%>
    
        <title><fmt:message key="my3o.ui.MyBill.title" /> - <fmt:message key="my3o.ui.title" /></title>

        <script src="${applicationScope.resourceServerUrl}js/page/bill-user.js"></script>
    </head>
    <body>
        <%@include file="../include/header-body.jsp"%>
        <%@include file="../include/menu-top.jsp"%>

        <div class="container min_H_400">

            <h4 class="pull-left"><fmt:message key="my3o.ui.MyBill.title" /></h4>

            <div class="clear"></div>
            <br>

            <div id="tabs">
                <ul class="nav nav-tabs" role="tablist">
                    <li class="active"><a href="#bill-user" role="tab" data-toggle="tab">Неоплаченные</a></li>
                    <li><a href="#bill-user-more" role="tab" data-toggle="tab">Все</a></li>
                </ul>
            </div>

            <br>

            <div class="tab-content">
                <div class="tab-pane active" id="bill-user">
                    <table class="table" id="tblBillUser">
                        <thead>
                        <tr>
                            <td>Ваш мобильный номер</td>
                            <td>Статус платежа</td>
                            <td>Сумма</td>
                            <td>Услуга</td>
                            <td>Оплатить до</td>
                            <td>Оплатить</td>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="item" items="${userBillPaid}">
                            <tr>
                                <td>${item.userPhone}</td>
                                <td>${item.qiwiStatus}</td>
                                <td>${item.amount}&nbsp ${item.ccy}</td>
                                <td>${item.comment}</td>
                                <td>${item.lifetime}</td>
                                <td><a href="https://qiwi.ru/payment/order.action">Оплатить</a></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>

                <div class="tab-pane" id="bill-user-more">
                    <table class="table" id="tblBillUserMore">
                        <thead>
                        <tr>
                            <td>Ваш мобильный номер</td>
                            <td>Статус платежа</td>
                            <td>Сумма</td>
                            <td>Услуга</td>
                            <td>Оплатить до</td>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="item" items="${userBill}">
                            <tr>
                                <td>${item.userPhone}</td>
                                <td>${item.qiwiStatus}</td>
                                <td>${item.amount}&nbsp ${item.ccy}</td>
                                <td>${item.comment}</td>
                                <td>${item.lifetime}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

        </div> <!-- /container -->
    
        <%@include file="../include/menu-bottom.jsp"%>
        <%@include file="dialog/country-edit.jsp"%>

    </body>
</html>


