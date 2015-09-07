<%@page pageEncoding="UTF-8" session="true"%>

<div id="dlgSettingMailEdit" class="modal" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content" style="">

            <div class="modal-header">
                <button class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="headerEdit">Добавить</h4>
            </div>
	
            <div class="modal-body">
				<form class="form-horizontal">
                    <input type="hidden" id="inpSettingMailIdDlg">

                    <div class="form-group">
                        <label for="settingMailNameDlg" class="col-sm-3 control-label">Название</label>
                        <div class="col-sm-5">
                            <input type="text" id="settingMailNameDlg" class="form-control">
                        </div>
                    </div>
                    
                     <div class="form-group">
                        <label for="settingMailDescriptionDlg" class="col-sm-3 control-label">Описание</label>
                        <div class="col-sm-5">
                            <input type="text" id="settingMailDescriptionDlg" class="form-control">
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label for="settingMailHostDlg" class="col-sm-3 control-label">Хост</label>
                        <div class="col-sm-5">
                            <input type="text" id="settingMailHostDlg" class="form-control">
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label for="settingMailPortDlg" class="col-sm-3 control-label">Порт</label>
                        <div class="col-sm-5">
                            <input type="text" id="settingMailPortDlg" class="form-control">
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label for="settingMailUserNameDlg" class="col-sm-3 control-label">Логин</label>
                        <div class="col-sm-5">
                            <input type="text" id="settingMailUserNameDlg" class="form-control">
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label for="settingMailUserPasswordDlg" class="col-sm-3 control-label">Пароль</label>
                        <div class="col-sm-5">
                            <input type="text" id="settingMailUserPasswordDlg" class="form-control">
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label for="settingMailDefaultSenderDlg" class="col-sm-3 control-label">Отправитель</label>
                        <div class="col-sm-5">
                            <input type="text" id="settingMailDefaultSenderDlg" class="form-control">
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label for="settingMailDefaultSubjectPrefixDlg" class="col-sm-3 control-label">Тема</label>
                        <div class="col-sm-5">
                            <input type="text" id="settingMailDefaultSubjectPrefixDlg" class="form-control">
                        </div>
                    </div>
                    
                     <div class="form-group">
                        <label for="settingMailStatusDlg" class="col-sm-3 control-label">Статус</label>
                        <div class="col-sm-5">
                            <select id ="settingMailStatusDlg" class="selectpicker">
                                <option value="Active"> Активная</option>
                                <option value="Inactive"> Неактивная</option>
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

                   
                   

                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" id="btnSaveSettingMailDlg">Сохранить</button>
                        <button id="cancelDlg" class="btn btn-default" aria-hidden="true" data-dismiss="modal">Отмена</button>
                    </div>
                 </form>

            </div>
        </div>
    </div>
</div>
