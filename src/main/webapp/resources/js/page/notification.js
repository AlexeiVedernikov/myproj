var x = null;
var table = null;

$(document).ready(function() {

	$("#btnSaveNotificationDlg").click(function() {

        var countProgress = 0;
        var intervalProgress = null;
        if ($("#selUser").children("option:selected").attr("value") == "disabled") {
            intervalProgress = setInterval(function () {
//                console.log("--------");
                $.getJSON("progressNotificationsSearch", function (data) {
                    if (data && !hasError(data)) {
                        if (data.value.length == 2) {
                            countProgress = data.value[0];
//                            console.log(data.value[1], data.value[0]);
                            var width = data.value[1] / data.value[0] * 100;
                            $("#progressNotification").css('width', Math.floor(width) + '%');
                            $("#progressNotification").html(function () {
                                var str = data.value[1] + " из " + data.value[0];
                                return str;
                            });
                        }
                    }
                });
            }, 3000);
        }

        var text = "";

        if ($(".buttonsMarks.btn-info").text() == "Оценки") {
            text = "marksmarksmarks";
        } else {
            text = $("#notificationTextDlg").val();
        }
		var wm = new NotificationWebModel(
            $("#inpNotificationIdDlg").val(),
            $("#notificationNameDlg").val(),
            $("#notificationDescriptionDlg").val(),
            $("#notificationDataDlg").datepicker( "getDate" ),
            text,
            $("#notificationStatusDlg").children("option:selected").attr("value"),
            $("#notificationTypeDlg").children("option:selected").attr("value"),
            $("#notificationUserTypeDlg").children("option:selected").attr("value"),
            $("#selSettingMail").children("option:selected").attr("value"),
            $("#selUser").children("option:selected").attr("value"),
            $("#selGroup").children("option:selected").attr("value"),
            $("#selOrganization").children("option:selected").attr("value"),
            $("#notificationDataDlg").val()
		);

		$.postJSON("notification", wm, function(data) {
            if (data && !hasError(data)) {
                if ($("#selUser").children("option:selected").attr("value") == "disabled") {
                    clearInterval(intervalProgress);
                    $("#progressNotification").css('width', '100%');
                    $("#progressNotification").html(function () {
                        var str = countProgress + " из " + countProgress;
                        return str;
                    });
                    setTimeout(function () {
                        $("#progressNotification").css('width', 0 + '%');
                        $("#progressNotification").html(function () {
                            var str = "";
                            return str;
                        });
                    }, 4000);
                }
                if(x==0){
                    showNotification("Сохранение выполнено успешно", "success");
            	} else if (x==1){
                    showNotification("Изменение сохранено", "success");
            	}
                viewTable();
                $("#dlgNotificationEdit").modal("hide");
                return false;
            }
        });
	});

	$("#aNotificationAdd").click(function() {
		fillNotificationEdit();
		x=0;
        $("#dlgNotificationEdit").modal({
            backdrop : true
        });
	});

    $(".buttonsMarks").click(function(){
        $("#btnTextDlg").removeClass("btn-info");
        $("#btnMarksDlg").removeClass("btn-info");
        $("#btnTextDlg").addClass("btn-default");
        $("#btnMarksDlg").addClass("btn-default");
        $(this).toggleClass('btn-info').toggleClass('btn-default');

        if ($(".buttonsMarks.btn-info").val() == "marksmarksmarks") {
            $("#notificationTextDlg").attr("disabled", true);
        } else {
            $("#notificationTextDlg").attr("disabled", false);
        }
    });

    $("#notificationDataDlg").datepicker();
    $("#notificationDataDlg").datepicker( "option", "dateFormat", "dd-mm-yy");
    $("#notificationDataDlg").datepicker( "setDate", new Date() );
    $("#notificationDataDlg").datepicker( "option", "monthNames", [ "Январь", "Февраль", "Март", "Апрель", "Май", "Июнь",
        "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь" ] );
    $("#notificationDataDlg").datepicker( "option", "dayNamesMin", [ "Вс", "Пн", "Вт", "Ср", "Чт", "Пт", "Сб" ] );
    $("#notificationDataDlg").datepicker( "option", "firstDay", 1 );

    $("#selOrganization").change(function() {
        $.getJSON("groupSearch", {organizationIdList:$(".changeOrganization option:selected").val()}, function(data) {
                if (data && !hasError(data)) {
                    $("#selGroup").html(function(){
                        var str = "<option value=\"disabled\">--Выберите класс--</option>";
                        for(var i = 0; i<data.value.length; i++) {
                            str += "<option value=" + data.value[i].id + ">" + data.value[i].name + "</option>";
                        }
                        return str;
                    });
                    $('#selGroup').selectpicker('refresh');

                    if ($(".changeOrganization option:selected").val() == "disabled") {
                        $("#selGroup").html(function(){
                            var str = "<option value=\"disabled\">--Выберите организацию--</option>";
                            return str;
                        });
                        $('#selGroup').selectpicker('refresh');

                        $("#selUser").html(function(){
                            var str = "<option value=\"disabled\">--Выберите организацию--</option>";
                            return str;
                        });
                        $('#selUser').selectpicker('refresh');
                    } else {
                        $("#selUser").html(function () {
                            var str = "<option value=\"disabled\">--Выберите класс--</option>";
                            return str;
                        });
                        $('#selUser').selectpicker('refresh');
                    }

                    return false;
                }}
        )

    });

    $("#selGroup").change(function() {
        $.getJSON("userSearch", {groupId:$(".changeGroup option:selected").val()}, function(data) {
                if (data && !hasError(data)) {
                    $("#selUser").html(function(){
                        var str = "<option value=\"disabled\">--Выберите пользователя--</option>";
                        for(var i = 0; i<data.value.length; i++) {
                            str += "<option value=" + data.value[i].id + ">" + data.value[i].name + "</option>";
                        }
                        return str;
                    });
                    $('#selUser').selectpicker('refresh');

                    if ($(".changeGroup option:selected").val() == "disabled") {
                        $("#selUser").html(function(){
                            var str = "<option value=\"disabled\">--Выберите класс--</option>";
                            return str;
                        });
                        $('#selUser').selectpicker('refresh');
                    }
                    return false;
                }}
        );

    });

    $("#openSettingMail").click(function() {
        window.location.href="settings-mail";
    });

    viewTable();
});

function bindEditClick(elements){
    elements.on("click", function() {
        x = 1;
        fillNotificationEdit( $(this).parents("tr") );
        $("#dlgNotificationEdit").modal({
            backdrop : true
        });
    });
}

function bindDeleteClick(elements){
    elements.on("click", function() {
        var notificationId = $(this).parents("tr").attr("trId");
        $.deleteJSON("notification", {id:notificationId}, function(data) {
            if (data && !hasError(data)) {
                viewTable();
//                $('[trId =' + notificationId + ']').remove();
                showNotification("Удаление выполнено", "warning");
                return false;
            }
        });
    });
}

function NotificationWebModel(id, name, description, data, text, status, type, userType, settingMailId, userId, groupId, organizationId, date) {
    this.id = id;
	this.name = name;
    this.description = description;
    this.data = data;
    this.text = text;
    this.status = status;
    this.type = type;
    this.userType = userType;
    this.settingMailId = settingMailId;
    this.userId = userId;
    this.groupId = groupId;
    this.organizationId = organizationId;
    this.date = date;
}

function fillNotificationEdit(tr) {
    if (tr) {
        $("#headerEdit").text("Редактировать оповещение");
        $("#inpNotificationIdDlg").val(tr.attr("trID"));
        $("#notificationNameDlg").val(tr.children("td.name").text());
        $("#notificationDescriptionDlg").val(tr.children("td.description").text()); 
        $("#notificationDataDlg").val(tr.children("td.dateOn").text());
        if (tr.children("td.text").attr("value") == "marksmarksmarks") {
            $("#btnTextDlg").removeClass("btn-info");
            $("#btnMarksDlg").removeClass("btn-info");
            $("#btnMarksDlg").addClass("btn-info");
            $("#btnTextDlg").addClass("btn-default");
            $("#notificationTextDlg").attr("disabled", true);
        } else {
            $("#btnTextDlg").removeClass("btn-info");
            $("#btnMarksDlg").removeClass("btn-info");
            $("#btnMarksDlg").addClass("btn-default");
            $("#btnTextDlg").addClass("btn-info");
            $("#notificationTextDlg").attr("disabled", false);
        }
        $("#notificationTextDlg").val(tr.children("td.text").text());
        setSelectedItemInSelectObj($("#notificationStatusDlg"), tr.children("td.status").attr("status"));
        setSelectedItemInSelectObj($("#notificationTypeDlg"), tr.children("td.type").attr("type"));
        setSelectedItemInSelectObj($("#notificationUserTypeDlg"), tr.children("td.type").attr("userType"));

        var settingMailId = null;
        settingMailId = tr.children("td.settingMail").attr("settingMailId");
        if (settingMailId != ""){
        	setSelectedItemInSelectObj($("#selSettingMail"), tr.children("td.settingMail").attr("settingMailId"));
        } else {
        	setSelectedItemInSelectObj( $("#selSettingMail"), "disabled");
        }

        var userId = null;
        var groupId = null;
        var organizationId = null;
        userId = tr.children("td.addressees").attr("userId");
        groupId = tr.children("td.addressees").attr("groupId");
        organizationId = tr.children("td.addressees").attr("organizationId");

        if (organizationId != ""){
        	setSelectedItemInSelectObj($("#selOrganization"), tr.children("td.addressees").attr("organizationId"));

            $.getJSON("groupSearch", {organizationIdList:organizationId}, function(data) {
                    if (data && !hasError(data)) {
                        $("#selGroup").html(function(){
                            var str = "<option value=\"disabled\">--Выберите класс--</option>";
                            for(var i = 0; i<data.value.length; i++) {
                                str += "<option value=" + data.value[i].id + ">" + data.value[i].name + "</option>";
                            }
                            return str;
                        });

                        if (groupId != "") {
                            setSelectedItemInSelectObj($("#selGroup"), tr.children("td.addressees").attr("groupId"));

                            $.getJSON("userSearch", {groupId:groupId}, function(data) {
                                    if (data && !hasError(data)) {
                                        $("#selUser").html(function(){
                                            var str = "<option value=\"disabled\">--Выберите пользователя--</option>";
                                            for(var i = 0; i<data.value.length; i++) {
                                                str += "<option value=" + data.value[i].id + ">" + data.value[i].name + "</option>";
                                            }
                                            return str;
                                        });

                                        if (userId != "") {
                                            setSelectedItemInSelectObj($("#selUser"), tr.children("td.addressees").attr("userId"));
                                        } else {
                                            setSelectedItemInSelectObj( $("#selUser"), "disabled");
                                        }

                                        return false;
                                    }}
                            );
                        } else {
                            setSelectedItemInSelectObj( $("#selGroup"), "disabled");
                            $("#selUser").html(function(){
                                var str = "<option value=\"disabled\">--Выберите класс--</option>";
                                return str;
                            });
                            setSelectedItemInSelectObj( $("#selUser"), "disabled");
                        }

                        return false;
                    }}
            );
        } else {
        	setSelectedItemInSelectObj( $("#selOrganization"), "disabled");
        }

    } else {
        $("#headerEdit").text("Добавить оповещение");
        $("#inpNotificationIdDlg").val(-1);
        $("#notificationNameDlg").val("");
        $("#notificationDescriptionDlg").val("");
        $("#notificationDataDlg").datepicker( "setDate", new Date() );
        $("#notificationTextDlg").val("");
        setSelectedItemInSelectObj( $("#notificationStatusDlg"),"Active");
        setSelectedItemInSelectObj( $("#notificationTypeDlg"),"email");
        setSelectedItemInSelectObj( $("#notificationUserTypeDlg"),"Parent");
        setSelectedItemInSelectObj( $("#selSettingMail"), "disabled");
        $("#selUser").html("<option value=\"disabled\">--Выберите организацию--</option>");
        $("#selGroup").html("<option value=\"disabled\">--Выберите организацию--</option>");
        setSelectedItemInSelectObj( $("#selUser"), "disabled");
        setSelectedItemInSelectObj( $("#selGroup"), "disabled");

        if($("#selOrganization").children("option[value='disabled']").val() == "disabled") {
            setSelectedItemInSelectObj($("#selOrganization"), "disabled");
        } else {
            $("#selOrganization").trigger("change");
        }

        $("#btnTextDlg").removeClass("btn-info");
        $("#btnMarksDlg").removeClass("btn-info");
        $("#btnMarksDlg").addClass("btn-default");
        $("#btnTextDlg").addClass("btn-info");
        $("#notificationTextDlg").attr("disabled", false);
    }
}

function viewTable() {
    $.getJSON("notificationsSearch", {}, function(data) {
            if (data && !hasError(data)) {
                $("#tblNotification").children("tbody").html(function() {
                    var str = "";
                    for(var i = 0; i<data.value.length; i++) {
                        var today = new Date(data.value[i].dateOn);
                        var curr_date = today.getDate();
                        if (curr_date < 10) {
                            curr_date = "0" + curr_date;
                        }
                        var curr_month = today.getMonth() + 1;
                        if (curr_month < 10) {
                            curr_month = "0" + curr_month;
                        }
                        var curr_year = today.getFullYear();
                        curr_date = curr_date + "-" + curr_month + "-" + curr_year;

                        str += "<tr trID = " + data.value[i].id + ">" +
                            "<td class = 'edit' >" +
                            "<div style='min-width: 90px'>" +
                                "<button type='button' class='btn btn-default editButton' title='Редактировать'>" +
                                    "<span class='glyphicon glyphicon-pencil'></span>" +
                                "</button>" +
                                "&nbsp" +
                                "<button type='button' class='btn btn-default deleteButton' title='Удалить'>" +
                                    "<span class='glyphicon glyphicon-trash'></span>" +
                                "</button>" +
                            "</div>" +
                            "</td>" +
                            "<td class='settingMail' settingMailId='" + data.value[i].settingMailDto.id + "'>" + data.value[i].settingMailDto.name + "</td>" +
                            "<td class='name'>" + data.value[i].name + "</td>" +
                            "<td class='description'>" + data.value[i].description + "</td>";
                            if (data.value[i].text == "marksmarksmarks") {
                                str += "<td class='text' value='marksmarksmarks'></td>";
                            } else {
                                str += "<td class='text' value=''>" + data.value[i].text + "</td>";
                            }
                            str += "<td class='dateOn'>" + curr_date + "</td>" +
                            "<td class='type' type='" + data.value[i].notificationType + "' userType='" + data.value[i].userType + "'>" + data.value[i].notificationType + "</td>" +
                            "<td class='status' status='" + data.value[i].status + "'>" + data.value[i].status + "</td>" +
                            "<td class='addressees' userId='";
                            if (data.value[i].userDto != null) {
                                str += data.value[i].userDto.id;
                            }

                            str += "' groupId='";

                            if (data.value[i].groupDto != null) {
                                str += data.value[i].groupDto.id;
                            }

                            str += "' organizationId='" + data.value[i].organizationDto.id + "'>";

                            if (data.value[i].userDto != null) {
                                str += "<p class='user'>" + data.value[i].userDto.name + "</p>";
                            }

                            if (data.value[i].groupDto != null) {
                                str += "<p class='group'>Класс " + data.value[i].groupDto.name + "</p>";
                            }

                            str += "<p class='organization'>" + data.value[i].organizationDto.name + "</p>" +
                            "</td>" +
                            "</tr>";
                    }
                    if (table) {
                        table.fnDestroy();
                    }
                    return str;
                });
                bindEditClick($(".editButton"));
                bindDeleteClick($(".deleteButton"));
                table = $('#tblNotification').dataTable();
                return false;
            }}
    );
}


