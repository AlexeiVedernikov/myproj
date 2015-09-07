<%@page pageEncoding="UTF-8" session="true"%>

<div id="dlgTownEdit" class="modal" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content" style="">

            <div class="modal-header">
                <button class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="headerEdit">Добавить населенный пункт</h4>
            </div>
	
            <div class="modal-body">
				<form class="form-horizontal">
                    <input type="hidden" id="inpTownIdDlg">

                    <div class="form-group">
                        <label for="townNameDlg" class="col-sm-3 control-label">Населенный пункт</label>
                        <div class="col-sm-5">
                            <input type="text" id="townNameDlg" class="form-control">
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
                            <select id="selDistrict" class="selectpicker">
                                <option value="disabled">--Выберите страну--</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="townDescriptionDlg" class="col-sm-3 control-label">Описание</label>
                        <div class="col-sm-5">
                            <input type="text" id="townDescriptionDlg" class="form-control">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="townStatusDlg" class="col-sm-3 control-label">Статус</label>
                        <div class="col-sm-5">
                            <select id ="townStatusDlg" class="selectpicker">
                                <option value="Active"> Активная</option>
                                <option value="Inactive"> Неактивная</option>
                            </select>
                        </div>
                    </div>

                    <div class="modal-footer">
                        <button type="button" data-dismiss="modal" class="btn btn-primary" id="btnSaveTownDlg">Сохранить</button>
                        <button id="townCancelDlg" data-dismiss="modal" class="btn btn-default" aria-hidden="true">Отмена</button>
                    </div>
                 </form>

            </div>
        </div>
    </div>
</div>
