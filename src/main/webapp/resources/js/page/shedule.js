var x = null;
var groupId = null;
	
$(document).ready(function() {

    $("#timeDlg").mask("99:99");

	$("#addDlg").click(function() {
			var wm = new SheduleWebModel(
                    $("#sheduleIdDlg").val(),
					$("#selDisciplineDlg").children("option:selected").attr("value"),
					$("#selTeacherDlg").children("option:selected").attr("value"),
                    groupId,
					$("#selYearDlg").children("option:selected").attr("value"),
                    $("#cabinetDlg").val(),
                    $("#fromTimeDlg").val(),
                    $("#toTimeDlg").val(),
                    $("#descriptionDlg").val()
			);
		
		$.postJSON("sheduleAdd", wm, function(data) {
            if (data && !hasError(data)) {
            	if (x == 0) {
                    showNotification("Сохранение выполнено успешно", "success");
            	}
            	else if (x == 1) {
                    showNotification("Изменение сохранено", "success");
            	}
                viewTable();
            	$("#sheduleEditDlg").modal("hide");
                return false;
            }
        });
	});

    $("#add").click(function(){
        x = 0;
        fillSheduleEditDialog();
        $("#sheduleEditDlg").modal({
            backdrop : true
        });
    });

    $("#selYearPage").change(function() {
        viewTable();
    });

    viewTable();

});

function bindEditClick(elements) {
    elements.on("click", function() {
        x = 1;
        fillSheduleEditDialog($(this).parents("tr"));
        $("#sheduleEditDlg").modal({
            backdrop : true
        });
    });
}

function bindDeleteClick(elements) {
    elements.on("click", function() {
        var sheduleId = $(this).parents("tr").attr("trId");
        $.deleteJSON("sheduleAdd", {id:sheduleId}, function(data) {
            if (data && !hasError(data)){
                showNotification("Удаление выполнено", "warning");
                viewTable();
                return false;
            }
        });
    });
}

function SheduleWebModel(id, disciplineId, userId, groupId, year, cabinet, fromTimes, toTimes, description) {
    this.id = id;
	this.disciplineId = disciplineId;
	this.userId = userId;
    this.groupId = groupId;
    this.year = year;
    this.cabinet = cabinet;
    this.fromTimes = fromTimes;
    this.toTimes = toTimes;
    this.description = description;
}

function fillSheduleEditDialog(tr) {
    if (tr) {
        $("#headerEdit").text("Редактировать урок");

        setSelectedItemInSelectObj($("#selDisciplineDlg"), tr.children("td.discipline").attr("disciplineId"));
        setSelectedItemInSelectObj($("#selTeacherDlg"), tr.children("td.teacher").attr("teacherId"));
        setSelectedItemInSelectObj($("#selYearDlg"), tr.children("td.year").attr("year"));

        $("#cabinetDlg").val(tr.children("td.cabinet").text());
        $("#fromTimeDlg").val(tr.children("td.times").attr("fromTimes"));
        $("#toTimeDlg").val(tr.children("td.times").attr("toTimes"));
        $("#descriptionDlg").val(tr.children("td.description").text());
        $("#sheduleIdDlg").val(tr.attr("trID"));
    }
    else {
        $("#sheduleIdDlg").val(-1);
        $("#headerEdit").text("Добавить урок");

        setSelectedItemInSelectObj( $("#selDisciplineDlg"),"disabled");
        setSelectedItemInSelectObj( $("#selTeacherDlg"),"disabled");
        setSelectedItemInSelectObj( $("#selYearDlg"), $("#selYearPage").children("option:selected").attr("value"));

        $("#cabinetDlg").val("");
        $("#fromTimeDlg").val("");
        $("#toTimeDlg").val("");
        $("#descriptionDlg").val("");
    }
}

function viewTable() {
    groupId = getUrlVars()["groupId"];
    year = $("#selYearPage").children("option:selected").attr("value");
    $.getJSON("sheduleSearch", {groupId:groupId, year:year}, function(data) {
            if (data && !hasError(data)) {
                $("#tblShedule").children("tbody").html(function(){
                    var str = "";
                    for(var i = 0; i<data.value.length; i++) {
                        str += "<tr trID = " + data.value[i].id + ">" +
                            "<td class = 'edit' >" +
                            "<button type='button' class='btn btn-default editButton' title='Редактировать'>" +
                            "<span class='glyphicon glyphicon-pencil'></span>" +
                            "</button>" +
                            "&nbsp" +
                            "<button type='button' class='btn btn-default deleteButton' title='Удалить'>" +
                            "<span class='glyphicon glyphicon-trash'></span>" +
                            "</button>" +
                            "</td>" +
                            "<td class='discipline' disciplineId='" + data.value[i].disciplineDto.id + "'>" + data.value[i].disciplineDto.name + "</td>" +
                            "<td class='teacher' teacherId='" + data.value[i].userDto.id + "'>" + data.value[i].userDto.name + "</td>" +
                            "<td class='year' year='" + data.value[i].year + "'>" + data.value[i].year + "-" + (parseInt(data.value[i].year.substr(2, 4))+1) + " год</td>" +
//                            "<td class='cabinet'>" + data.value[i].cabinet + "</td>" +
//                            "<td class='times' toTimes='" + data.value[i].toTimes + "' fromTimes='" + data.value[i].fromTimes + "'>С "
//                            + data.value[i].fromTimes + " ПО " + data.value[i].toTimes + "</td>" +
                            "<td class='description'>" + data.value[i].description + "</td>" +
                            "</tr>";
                    }
                    return str;
                });
                bindEditClick($(".editButton"));
                bindDeleteClick($(".deleteButton"));
                $('#tblShedule').dataTable();
                return false;
            }}
    )
}

function getUrlVars() {
    var vars = {};
    var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) {
        vars[key] = value;
    });
    return vars;
}