<%@page pageEncoding="UTF-8" session="true"%>


<div id="disciplinesEditDlg" class="modal" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content" style="">

            <div class="modal-header">
                <button class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="headerEdit">Добавить предмет</h4>
            </div>

            <div class="modal-body">
                <form class="form-horizontal">
                    <input type="hidden" id="disciplinesIdDlg">

                    <div class="form-group">
                        <label for="disciplinesNameDlg" class="col-sm-3 control-label">Предмет</label>
                        <div class="col-sm-5">
                            <input type="text" id="disciplinesNameDlg" class="form-control">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="disciplinesDescriptionDlg" class="col-sm-3 control-label">Описание</label>
                        <div class="col-sm-5">
                            <input type="text" id="disciplinesDescriptionDlg" class="form-control">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="disciplinesStatusDlg" class="col-sm-3 control-label">Статус</label>
                        <div class="col-sm-5">
                            <select id ="disciplinesStatusDlg" class="selectpicker">
                                <option value="Active" selected> Активный</option>
                                <option value="Inactive"> Неактивный</option>
                            </select>
                        </div>
                    </div>

                    <div class="modal-footer">
                        <button id="disciplinesEditAddDlg" data-dismiss="modal" class="btn btn-primary">Сохранить</button>
                        <button id="disciplinesCancelDlg" data-dismiss="modal" class="btn btn-default" aria-hidden="true">Отмена</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>