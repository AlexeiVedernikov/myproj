<%@page pageEncoding="UTF-8" session="true"%>


<div id="sheduleEditDlg" class="modal" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content" style="">

            <div class="modal-header">
                <button class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="headerEdit">Добавить урок</h4>
            </div>

            <div class="modal-body">
                <form class="form-horizontal">
                    <input type="hidden" id="sheduleIdDlg">

                    <div class="form-group">
                        <label for="selDisciplineDlg" class="col-sm-3 control-label">Предмет</label>
                        <div class="col-sm-5">
                            <select id="selDisciplineDlg" class="selectpicker">
                                <option value="disabled">--Выберите предмет--</option>
                                <c:forEach var="item" items="${disciplineList}">
                                    <option value=${item.id}>${item.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="selTeacherDlg" class="col-sm-3 control-label">Учитель</label>
                        <div class="col-sm-5">
                            <select id="selTeacherDlg" class="selectpicker">
                                <option value="disabled">--Выберите учителя--</option>
                                <c:forEach var="item" items="${teacherList}">
                                    <option value=${item.id}>${item.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="selYearDlg" class="col-sm-3 control-label">Год</label>
                        <div class="col-sm-5">
                            <select id="selYearDlg" class="selectpicker">
                                <option value="2014" selected>2014-15 год</option>
                                <option value="2015">2015-16 год</option>
                                <option value="2016">2016-17 год</option>
                                <option value="2017">2017-18 год</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group" hidden>
                        <label for="cabinetDlg" class="col-sm-3 control-label">Кабинет</label>
                        <div class="col-sm-5">
                            <input type="text" id="cabinetDlg" class="form-control">
                        </div>
                    </div>

                    <div class="form-group" hidden>
                        <label for="fromTimeDlg" class="col-sm-3 control-label">Время с</label>
                        <div class="col-sm-5">
                            <input type="text" id="fromTimeDlg" class="form-control">
                        </div>
                    </div>

                    <div class="form-group" hidden>
                        <label for="toTimeDlg" class="col-sm-3 control-label">Время до</label>
                        <div class="col-sm-5">
                            <input type="text" id="toTimeDlg" class="form-control">
                        </div>
                    </div>
 
                    <div class="form-group">
                        <label for="descriptionDlg" class="col-sm-3 control-label">Описание</label>
                        <div class="col-sm-5">
                            <input type="text" id="descriptionDlg" class="form-control">
                        </div>
                    </div>
 
                     <div class="modal-footer">
                         <button id="addDlg" data-dismiss="modal" class="btn btn-primary">Сохранить</button>
                         <button id="cancelDlg" data-dismiss="modal" class="btn btn-default" aria-hidden="true">Отмена</button>
                     </div>
                </form>
            </div>
        </div>
    </div>
</div>
