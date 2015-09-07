<%@page pageEncoding="UTF-8" session="true"%>

<div id="dlgTownAreaEdit" class="modal" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content" style="">

            <div class="modal-header">
                <button class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="headerEdit">Добавить микрорайон</h4>
            </div>
	
            <div class="modal-body">
                <form class="form-horizontal">
                    <input type="hidden" id="inpTownAreaIdDlg">

                    <div class="form-group">
                        <label for="townAreaNameDlg" class="col-sm-3 control-label">Микрорайон</label>
                        <div class="col-sm-5">
                            <input type="text" id="townAreaNameDlg" class="form-control">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="selCountry" class="col-sm-3 control-label">Страна</label>
                        <div class="col-sm-5">
                            <select id="selCountry" class="changeCountry selectpicker">
                                <option value="disabled">--Выберите страну--</option>
                                <c:forEach var="item" items="${countryList}">
                                    <option value="${item.id}">${item.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="selRegion" class="col-sm-3 control-label">Регион</label>
                        <div class="col-sm-5">
                            <select id="selRegion" class="changeRegion selectpicker">
                                <option value="disabled">--Выберите страну--</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="selDistrict" class="col-sm-3 control-label"	>Район</label>
                        <div class="col-sm-5">
                            <select id="selDistrict" class="changeDistrict selectpicker">
                                <option value="disabled">--Выберите страну--</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="selTown" class="col-sm-3 control-label">Город</label>
                        <div class="col-sm-5">
                            <select id="selTown" class="selectpicker">
                                <option value="disabled">--Выберите страну--</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="townAreaDescriptionDlg" class="col-sm-3 control-label">Описание</label>
                        <div class="col-sm-5">
                            <input type="text" id="townAreaDescriptionDlg" class="form-control">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="townAreaStatusDlg" class="col-sm-3 control-label">Статус</label>
                        <div class="col-sm-5">
                            <select id ="townAreaStatusDlg" class="selectpicker">
                                <option value="Active"> Активная</option>
                                <option value="Inactive"> Неактивная</option>
                            </select>
                        </div>
                    </div>

                     <div class="modal-footer">
                         <button type="button" id="btnSaveTownAreaDlg" data-dismiss="modal" class="btn btn-primary">Сохранить</button>
                         <button data-dismiss="modal" class="btn btn-default" aria-hidden="true">Отмена</button>
                     </div>
                </form>
            </div>
        </div>
    </div>
</div>
