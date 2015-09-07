<%@page pageEncoding="UTF-8" session="true"%>
<!DOCTYPE html>
<html>
<head>
    <%@include file="../include/header.jsp"%>
    
    <title><fmt:message key="my3o.ui.contacts.title" /> - <fmt:message key="my3o.ui.title" /></title>
    
    <script src="${applicationScope.resourceServerUrl}js/page/contacts.js"></script>
</head>
<body>
	<%@include file="../include/header-body.jsp"%>
	<%@include file="../include/menu-top.jsp"%>

    <div class="container min_H_400"> 
		
		<%--<h4 class="pull-left"><fmt:message key="my3o.ui.users.title" /></h4>--%>

        <h3>Проект «Вся школа»</h3>

         <p>
            Проект представляет собой единую информационную площадку для взаимодействия руководства
            образовательных учреждений, педагогов, родителей и учащихся. Весь функционал проекта доступен
            в сети Интернет посредством доступа через браузер ПК или мобильных устройств. Для работы с
            сайтом проекта будет достаточно скорости доступа с мобильных устройств в режиме 3G.
            <p>
				По всем вопросам следует обращаться по адресу <a href="mailto:info@my3o.ru">info@my3o.ru</a>
				<br>
				Контактное лицо: Афанасьев Алексей Николаевич, тел.: +7(928)126-11-70
			</p>

            <hr>

            <h4 class = "">Задай Вопрос</h4>

            <form class="form-horizontal" id = "formContacts">
                <div class="form-group">
                    <label for="contactName" class="col-sm-2 control-label">Ваше Имя</label>
                    <div class="col-sm-3">
                        <input type="text" id = "contactName" class="form-control">
                    </div>
                </div>
                <div class="form-group">
                    <label for="contactEmail" class="col-sm-2 control-label"> E-Mail</label>
                    <div class="col-sm-3">
                        <input type="email" id = "contactEmail" class="form-control">
                    </div>
                </div>
                <div class="form-group">
                    <label for="contactSubject" class="col-sm-2 control-label"> Тема Сообщения</label>
                    <div class="col-sm-3">
                        <input type="text" id = "contactSubject" class="form-control">
                    </div>
                </div>
                <div class="form-group">
                    <label for="contactMessage" class="col-sm-2 control-label"> Сообщение</label>
                    <div class="col-sm-3">
                        <textarea id = "contactMessage" class="form-control"></textarea>
                    </div>
                 </div>
                <input type="button" id="contactSendMessage" class="btn-default" value="Отправить" title="Отправить">
            </form>
        </div>
<br><br>
        <div class="realperson-challenge">

        </div>

    </div> <!-- /container -->

    <%@include file="../include/menu-bottom.jsp"%>
    
</body>
</html>


