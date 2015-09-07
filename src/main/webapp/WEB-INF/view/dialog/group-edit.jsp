<%@page pageEncoding="UTF-8" session="true"%>


<div id="groupEditDlg" class="modal" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content" style="">

            <div class="modal-header">
                <button class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="headerEdit">Добавить класс</h4>
            </div>
	
            <div class="modal-body">
                <form class="form-horizontal">
                    <input type="hidden" id="groupIdDlg">

                    <div class="form-group">
                        <label for="groupNameDlg" class="col-sm-3 control-label">Класс</label>
                        <div class="col-sm-5">
                            <input type="text" id="groupNameDlg" class="form-control">
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label for="groupNumberDlg" class="col-sm-3 control-label">Номер</label>
                        <div class="col-sm-5">
                            <input type="text" id="groupNumberDlg" class="form-control">
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label for="organizationNameDlg" class="col-sm-3 control-label">Организация</label>
                        <div class="col-sm-8">
                            <select id="organizationNameDlg" class="changeOrganization selectpicker">
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
                        <label for="groupDescriptionDlg" class="col-sm-3 control-label">Описание</label>
                        <div class="col-sm-5">
                            <input type="text" id="groupDescriptionDlg" class="form-control">
                        </div>
                    </div>
 
                    <div class="form-group">
                       <label for="groupStatusDlg" class="col-sm-3 control-label">Статус</label>
                           <div class="col-sm-5">
                               <select id ="groupStatusDlg" class="selectpicker">
                                   <option value="Active" selected> Активная</option>
                                   <option value="Inactive"> Неактивная</option>
                               </select>
                           </div>
                    </div>

                     <div class="modal-footer">
                         <button id="groupEditAddDlg" data-dismiss="modal" class="btn btn-primary">Сохранить</button>
                         <button id="groupCancelDlg" data-dismiss="modal" class="btn btn-default" aria-hidden="true">Отмена</button>
                     </div>
                </form>
            </div>
        </div>
    </div>
</div>
