<%@page pageEncoding="UTF-8" session="true"%>


<div id="countryEditDlg" class="modal" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content" style="">

            <div class="modal-header">
                <button class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="headerEdit">Добавить страну</h4>
            </div>

            <div class="modal-body">
                <form class="form-horizontal">
                    <input type="hidden" id="countryIdDlg">

                    <div class="form-group">
                        <label for="countryNameDlg" class="col-sm-3 control-label">Страна</label>
                        <div class="col-sm-5">
                            <input type="text" id="countryNameDlg" class="form-control">
                        </div>
                    </div>
 
                    <div class="form-group">
                        <label for="countryDescriptionDlg" class="col-sm-3 control-label">Описание</label>
                        <div class="col-sm-5">
                            <input type="text" id="countryDescriptionDlg" class="form-control">
                        </div>
                    </div>
 
                    <div class="form-group">
                       <label for="countryStatusDlg" class="col-sm-3 control-label">Статус</label>
                           <div class="col-sm-5">
                               <select id ="countryStatusDlg" class="selectpicker">
                                   <option value="Active" selected> Активная</option>
                                   <option value="Inactive"> Неактивная</option>
                               </select>
                           </div>
                    </div>

                     <div class="modal-footer">
                         <button id="CountryEditAddDlg" data-dismiss="modal" class="btn btn-primary">Сохранить</button>
                         <button id="CountryCancelDlg" data-dismiss="modal" class="btn btn-default" aria-hidden="true">Отмена</button>
                     </div>
                </form>
            </div>
        </div>
    </div>
</div>
