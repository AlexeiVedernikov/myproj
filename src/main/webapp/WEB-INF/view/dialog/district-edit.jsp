<%@page pageEncoding="UTF-8" session="true"%>

<div id="dlgDistrictEdit" class="modal" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content" style="">

            <div class="modal-header">
                <button class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="headerEdit">Добавить район</h4>
            </div>

            <div class="modal-body">
                <form class="form-horizontal">
                    <input type="hidden" id="districtIdDlg">

                    <div class="form-group">
                        <label for="districtNameDlg" class="col-sm-3 control-label">Район</label>
                        <div class="col-sm-5">
                            <input type="text" id="districtNameDlg" class="form-control">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="selCountry" class="col-sm-3 control-label">Страна</label>
                        <div class="col-sm-5">
                            <select id="selCountry" class="changeCountry selectpicker">
                                <option value="disabled">--Выберите страну--</option>
                                <c:forEach var="item" items="${countryList}">
                                    <option value=${item.id}>${item.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="selRegion" class="col-sm-3 control-label">Регион</label>
                        <div class="col-sm-5">
                            <select id="selRegion" class="selectpicker">
                                <option value="disabled">--Выберите страну--</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="districtDescriptionDlg" class="col-sm-3 control-label">Описание</label>
                        <div class="col-sm-5">
                            <input type="text" id="districtDescriptionDlg" class="form-control">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="districtStatusDlg" class="col-sm-3 control-label">Статус</label>
                        <div class="col-sm-5">
                            <select id ="districtStatusDlg" class="selectpicker">
                                <option value="Active">Активная</option>
                                <option value="Inactive">Неактивная</option>
                            </select>
                        </div>
                    </div>

                    <div class="modal-footer">
                        <button type="button" data-dismiss="modal" class="btn btn-primary" value="Save" id="saveDlg">Сохранить</button>
                        <button id="districtCancelDlg" data-dismiss="modal" class="btn btn-default" aria-hidden="true">Отмена</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
