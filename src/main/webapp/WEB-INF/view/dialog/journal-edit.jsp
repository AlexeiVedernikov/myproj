<%@page pageEncoding="UTF-8" session="true"%>


<div id="journalEditDlg" class="modal" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content" style="">

            <div class="modal-header">
                <button class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="headerEdit" >Добавить оценку</h4>
            </div>

            <div class="modal-body">
                <form class="form-horizontal">
                    <input type="hidden" id="journalIdDlg">
                    <input type="hidden" id="markTypeDlg">

                    <div class="form-group">
                        <label class="col-sm-3 control-label">Оценка</label>
                        <div class="col-sm-8">
                            <div class="input-group">
                                <span class="input-group-btn">
                                    <button type="button" id="buttonMark1" class="buttonsMarks btn btn-default">1</button>
                                    <button type="button" id="buttonMark2" class="buttonsMarks btn btn-default">2</button>
                                    <button type="button" id="buttonMark3" class="buttonsMarks btn btn-default">3</button>
                                    <button type="button" id="buttonMark4" class="buttonsMarks btn btn-default">4</button>
                                    <button type="button" id="buttonMark5" class="buttonsMarks btn btn-default">5</button>
                                    <button type="button" id="buttonMarkN" class="buttonsMarks btn btn-default">Н</button>
                                </span>
                            </div>
                        </div>
                    </div>

                     <div class="modal-footer">
                         <button type="button" id="addDlg" class="btn btn-primary">Сохранить</button>
                         <button type="button" id="deleteDlg" class="btn btn-danger">Удалить</button>
                         <button id="cancelDlg" data-dismiss="modal" class="btn btn-default" aria-hidden="true">Отмена</button>
                     </div>
                </form>
            </div>
        </div>
    </div>
</div>
