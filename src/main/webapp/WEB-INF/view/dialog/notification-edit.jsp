<%@page pageEncoding="UTF-8" session="true"%>

<div id="dlgNotificationEdit" class="modal" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content" style="">

            <div class="modal-header">
                <button class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="headerEdit">Добавить оповещение</h4>
            </div>
	
            <div class="modal-body">
				<form class="form-horizontal">
                    <input type="hidden" id="inpNotificationIdDlg">

                    <div class="form-group">
                        <label for="notificationNameDlg" class="col-sm-3 control-label">Название</label>
                        <div class="col-sm-5">
                            <input type="text" id="notificationNameDlg" class="form-control">
                        </div>
                    </div>
                    
                     <div class="form-group">
                        <label for="notificationDescriptionDlg" class="col-sm-3 control-label">Описание</label>
                        <div class="col-sm-5">
                            <input type="text" id="notificationDescriptionDlg" class="form-control">
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label for="notificationDataDlg" class="col-sm-3 control-label">Дата</label>
                        <div class="col-sm-5">
                            <input type="text" id="notificationDataDlg" class="form-control">
                        </div>
                    </div>
                    
                     <div class="form-group">
                        <label for="notificationTextDlg" class="col-sm-3 control-label">Текст</label>
                        <div class="col-sm-5">
                            <div class="btn-group btn-group-justified" role="group">
                                <div class="btn-group" role="group">
                                    <button type="button" class="btn buttonsMarks btn-info" id="btnTextDlg">Текст</button>
                                </div>
                                <div class="btn-group" role="group">
                                    <button type="button" class="btn buttonsMarks btn-default" id="btnMarksDlg" value="marksmarksmarks">Оценки</button>
                                </div>
                            </div>
                            <textarea id="notificationTextDlg" class="form-control" hidden></textarea>
                        </div>
                    </div>
                    
                     <div class="form-group">
                        <label for="notificationStatusDlg" class="col-sm-3 control-label">Статус</label>
                        <div class="col-sm-5">
                            <select id ="notificationStatusDlg" class="selectpicker">
                                <option value="Active"> Активная</option>
                                <option value="Inactive"> Неактивная</option>
                            </select>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label for="notificationTypeDlg" class="col-sm-3 control-label">Тип</label>
                        <div class="col-sm-5">
                            <select id ="notificationTypeDlg" class="selectpicker">
                                <option value="email">Email</option>
                                <option value="sms">Sms</option>
                                <option value="emailSms" disabled>Email/Sms</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="notificationUserTypeDlg" class="col-sm-3 control-label">Кому</label>
                        <div class="col-sm-5">
                            <select id ="notificationUserTypeDlg" class="selectpicker">
                                <option value="Parent">Родитель</option>
                                <option value="Scholar" disabled>Ученик</option>
                                <option value="All" disabled>Родитель/Ученик</option>
                            </select>
                        </div>
                    </div>
                    
                     <div class="form-group">
                        <label for="selSettingMail" class="col-sm-3 control-label">Настройки</label>
                        <div class="col-sm-5">
                            <select id ="selSettingMail" class="selectpicker">
                                <option value="disabled">--Выберите настройки--</option>
                                <c:forEach var="item" items="${settingMailList}">
                                    <option value="${item.id}">${item.name} (${item.organizationDto.name})</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="selOrganization" class="col-sm-3 control-label"	>Организация</label>
                        <div class="col-sm-5">
                            <select id="selOrganization" class="changeOrganization selectpicker">
                                <c:if test="${! empty requestScope.listOrg}">
                                    <c:choose>
                                        <c:when test="${cookie.organizationId.value != null && cookie.organizationId.value != 'disabled'}">
                                            <c:forEach var="item" items="${requestScope.listOrg}">
                                                <c:if test="${cookie.organizationId.value == item.key}">
                                                    <option selected value="${item.key}">${item.value}</option>
                                                </c:if>
                                            </c:forEach>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="disabled">--Выберите организацию--</option>
                                            <c:forEach var="item" items="${requestScope.listOrg}">
                                                <option value="${item.key}">${item.value}</option>
                                            </c:forEach>
                                        </c:otherwise>
                                    </c:choose>
                                </c:if>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                    <label for="selGroup" class="col-sm-3 control-label">Класс</label>
                        <div class="col-sm-5">
                            <select id="selGroup" class="changeGroup selectpicker" data-size="5" data-live-search="true">
                                <option value="disabled">--Выберите организацию--</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="selUser" class="col-sm-3 control-label">Пользователь</label>
                        <div class="col-sm-5">
                            <select id="selUser" class="changeUser selectpicker" data-size="5" data-live-search="true">
                                <option value="disabled">--Выберите организацию--</option>
                            </select>
                        </div>
                    </div>

                    <div class="modal-footer">
                        <button type="button" data-dismiss="modal" class="btn btn-primary" id="btnSaveNotificationDlg">Сохранить</button>
                        <button id="notificationCancelDlg" data-dismiss="modal" class="btn btn-default" aria-hidden="true">Отмена</button>
                    </div>
                 </form>

            </div>
        </div>
    </div>
</div>
