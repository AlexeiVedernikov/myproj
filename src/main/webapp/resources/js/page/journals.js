var keySelectGroup = 0;
var table = null;
var listNumberDays = null;
var listNameDay = null;
var tdTable = null;

$(document).ready(function() {

	$("#addDlg").click(function() {
        if ($(".buttonsMarks.btn-info").text() !="") {
            var wm = new JournalWebModel(
                $("#journalIdDlg").val(),
                $("#selDisciplinePage").children("option:selected").val(),
                tdTable.parent().attr("userId"),
                $("#tblJournal").get(0).rows[0].cells[tdTable.index()].attributes.getNamedItem("date").value,
                $(".buttonsMarks.btn-info").text(),
                $("#markTypeDlg").val()
            );
            $.postJSON("journal", wm, function (data) {
                if (data && !hasError(data)) {
                    showNotificationJournal("Сохранение выполнено успешно", "success", 1000);
                    tdTable.text(data.value.mark);
                    tdTable.attr("jour-id", data.value.id);
                    $("#journalEditDlg").modal("hide");
                    return false;
                }
            });
        }
	});

    $("#deleteDlg").click(function() {
        $.deleteJSON("journal", {id:$("#journalIdDlg").val()}, function(data) {
            if (data && !hasError(data)){
                showNotificationJournal("Удаление выполнено", "warning", 1000);
                tdTable.text("");
                tdTable.attr("jour-id", "");
                $("#journalEditDlg").modal("hide");
                return false;
            }
        });
    });

    var listMonth = { "0": "Январь", "1": "Февраль", "2": "Март",
        "3": "Апрель", "4": "Май", "5": "Июнь",
        "6": "Июль", "7": "Август", "8": "Сентябрь",
        "9": "Октябрь", "10": "Ноябрь", "11": "Декабрь"};

    listNumberDays = { "0": "31", "1": "29", "2": "31",
        "3": "30", "4": "31", "5": "30",
        "6": "31", "7": "31", "8": "30",
        "9": "31", "10": "30", "11": "31"};

    listNameDay = { "0": "Вс", "1": "Пн", "2": "Вт",
        "3": "Ср", "4": "Чт", "5": "Пт", "6": "Сб"};

    var currentMonth = new Date().getMonth();
    var currentYear = new Date().getFullYear();
    $("#selMonthPage").html(function(){
        var str = "";
        for(var i = 0; i<12; i++) {
            if (i == currentMonth) {
                str += "<option month=" + (i+1) + " numberDays=" + listNumberDays[i] + " selected>" + listMonth[i] + "</option>"
            } else {
                str += "<option month=" + (i+1) + " numberDays=" + listNumberDays[i] + ">" + listMonth[i] + "</option>"
            }
        }
        return str;
    });

    $("#selYearPage").html(function(){
        var str = "";
        for(var i = 2014; i<2018; i++) {
            if (i == currentYear) {
                str += "<option value=" + i + " selected>" + i + "</option>"
            } else {
                str += "<option value=" + i + ">" + i + "</option>"
            }
        }
        return str;
    });

    $("#selGroupPage").change(function() {

        $("#optionSelectGroup").remove();
        $('#selGroupPage').selectpicker('refresh');

        var userId = "";
        if ($("#userType").val() == "Teacher") {
            userId = $("#userId").val();
        }

        var year = $("#selYearPage").val();
        if ($("#selMonthPage").children("option:selected").attr("month") < 8) {
            year = year - 1;
        }
        $.getJSON("sheduleSearch", {groupId:$("#selGroupPage option:selected").val(), year:year, userId:userId}, function(data) {
                if (data && !hasError(data)) {

                    $("#selDisciplinePage").html(function(){
                        var str = "";
                        if (data.value.length > 0) {
                            str += "<option id='optionSelectDiscipline' value=\"disabled\">--Выберите предмет--</option>";
                        } else if (data.value.length == 0) {
                            str += "<option value=\"disabled\">Нет предметов</option>";
                        }

                        for(var i = 0; i<data.value.length; i++) {
                            str += "<option value=" + data.value[i].id + ">" + data.value[i].disciplineDto.name + "</option>"
                        }

                        return str;
                    });
                    $('#selDisciplinePage').selectpicker('refresh');

                    return false;
                }}
        );
    });

    $("#selDisciplinePage").change(function(){
        $("#optionSelectDiscipline").remove();
        $('#selDisciplinePage').selectpicker('refresh');
        viewTable();
        $("#buttonMarkTypePage").attr("disabled", false);
    });

    $(".markTypePage").css('cursor','pointer');

    $(".markTypePage").click(function() {
        if ($("#tblJournal th[markType = '" + $(this).attr("type") + "']").length == 0) {
            var numberDays = $("#selMonthPage").children("option:selected").attr("numberDays");
            var month = $("#selMonthPage").children("option:selected").attr("month");
            var curr_month = month;
            if (curr_month < 10) {
                curr_month = "0" + curr_month;
            }
            var curr_date = numberDays + "-" + curr_month + "-" + $("#selYearPage").val();

            $("#tblJournal thead tr").append('<th class="text-center warning" date="' + curr_date + '" markType="' + $(this).attr("type") + '" title="' + $(this).text() + '">' + $(this).text().substring(0, 1) + '</th>');
            $("#tblJournal tbody tr").append('<td align="center" class="warning markAbroad" markType="' + $(this).attr("type") + '" style="cursor: pointer;"></td>');

            $("#tblJournal").find("td.markAbroad").mouseover(function () {
                $(this).removeClass("warning");
                $(this).addClass("danger");
            });
            $("#tblJournal").find("td.markAbroad").mouseout(function () {
                $(this).removeClass("danger");
                $(this).addClass("warning");
            });

            $("#tblJournal").find("td.markAbroad").click(function () {
                if (!($("#userType").val() == "RegistrarAccounts" || $("#userType").val() == "Onlooker" || $("#userType").val() == "ResponsibleForPayment")) {
                    if ($(this).text() == "") {
                        fillJournalEditDialog();
                        $("#markTypeDlg").val($(this).attr("marktype"));
                    } else {
                        fillJournalEditDialog($(this));
                    }
                    tdTable = $(this);
                    $("#journalEditDlg").modal({
                        backdrop: true
                    });
                }
            });
        }
    });

    $("#selYearPage").change(function() {
        $("#selGroupPage").trigger("change");
    });

    $("#selMonthPage").change(function() {
        viewTable();
    });

    $(".buttonsMarks").click(function(){
        $("#buttonMark1").removeClass("btn-info");
        $("#buttonMark2").removeClass("btn-info");
        $("#buttonMark3").removeClass("btn-info");
        $("#buttonMark4").removeClass("btn-info");
        $("#buttonMark5").removeClass("btn-info");
        $("#buttonMarkN").removeClass("btn-info");
        $("#buttonMark1").addClass("btn-default");
        $("#buttonMark2").addClass("btn-default");
        $("#buttonMark3").addClass("btn-default");
        $("#buttonMark4").addClass("btn-default");
        $("#buttonMark5").addClass("btn-default");
        $("#buttonMarkN").addClass("btn-default");
        $(this).toggleClass('btn-info').toggleClass('btn-default');
    });

    var urlGroupId = getUrlVars()["groupId"];
    if (urlGroupId != undefined) {
        setSelectedItemInSelectObj($("#selGroupPage"), urlGroupId);
        $("#selGroupPage").trigger("change");
    }

    var keyMarks = { "49": "1", "50": "2", "51": "3",
        "52": "4", "53": "5", "121": "Н", "110": "Н"};

    $(document).keypress(function(e) {
        if ($("#journalEditDlg").hasClass("in")) {
            if (e.keyCode == 49 || e.keyCode == 50 || e.keyCode == 51 || e.keyCode == 52
                || e.keyCode == 53 || e.keyCode == 121 || e.keyCode == 110) {

                if (!((e.keyCode == 121 || e.keyCode == 110) && $("#markTypeDlg").val() != "current")) {
                    var wm = new JournalWebModel(
                        $("#journalIdDlg").val(),
                        $("#selDisciplinePage").children("option:selected").val(),
                        tdTable.parent().attr("userId"),
                        $("#tblJournal").get(0).rows[0].cells[tdTable.index()].attributes.getNamedItem("date").value,
                        keyMarks[e.keyCode],
                        $("#markTypeDlg").val()
                    );
                    $.postJSON("journal", wm, function (data) {
                        if (data && !hasError(data)) {
                            showNotificationJournal("Сохранение выполнено успешно", "success", 1000);
                            tdTable.text(data.value.mark);
                            tdTable.attr("jour-id", data.value.id);
                            $("#journalEditDlg").modal("hide");
                            return false;
                        }
                    });
                }
            }

            if ($("#journalIdDlg").val() != "-1") {
                if (e.keyCode == 127) {
                    $.deleteJSON("journal", {id: $("#journalIdDlg").val()}, function (data) {
                        if (data && !hasError(data)) {
                            showNotificationJournal("Удаление выполнено", "warning", 1000);
                            tdTable.text("");
                            tdTable.attr("jour-id", "");
                            $("#journalEditDlg").modal("hide");
                            return false;
                        }
                    });
                }
            }
        }
    });

});

function JournalWebModel(id, sheduleId, userId, date, mark, markType) {
    this.id = id;
	this.sheduleId = sheduleId;
	this.userId = userId;
    this.date = date;
    this.mark = mark;
    this.markType = markType;
}

function viewTable() {

    var numberDays = $("#selMonthPage").children("option:selected").attr("numberDays");
    var month = $("#selMonthPage").children("option:selected").attr("month");
    var sheduleId = $("#selDisciplinePage").children("option:selected").val();

    var curr_month = month;
    if (curr_month < 10) {
        curr_month = "0" + curr_month;
    }

    var curr_date = "01-" + curr_month + "-" + $("#selYearPage").val();

    var userMap = { "test": "test2"};

    var date = new Date();
    date.setFullYear($("#selYearPage").val(), month-1, 1);
    date.setHours(0, 0, 0, 0);

    $.getJSON("journalSearch", {groupId: $("#selGroupPage option:selected").val(), date: curr_date, numberDays: numberDays, sheduleId: sheduleId}, function (data) {
            if (data && !hasError(data)) {
                $("#tblJournal").html(function () {
                    var str = "";
                    //строим заголовки
                    str += "<thead>" +
                        "<tr>" +
                        "<th>Ученик</th>";
                    var dateTh = "";
                    var monthYear = "-" + curr_month + "-" + $("#selYearPage").val();
                    for (var i = 0; i < numberDays; i++) {
                        date.setDate(i+1);
                        if ((i+1) < 10) {
                            dateTh = "0" + (i+1) + monthYear;
                        } else {
                            dateTh = (i+1) + monthYear;
                        }
                        str += "<th class='text-center' date='" + dateTh + "'>" + listNameDay[date.getDay()] + "<br>" + (i+1) + "</th>";
                    }
                    str += "</tr>" +
                        "</thead>" +
                        "<tbody>";
                    //строим таблицу
                    for (var i = 0; i < data.value.userDtoList.length; i++) {
                        str += "<tr userId='" + data.value.userDtoList[i].id + "'>" +
                            "<td>" + data.value.userDtoList[i].name + "</td>";
                        for (var j = 0; j < numberDays; j++) {
                            str += "<td align='center' class='markTable' markType='current'></td>";
                        }
                        str += "</tr>";
                        userMap[data.value.userDtoList[i].id] =  i;
                    }
                    str += "</tbody>";

                    return str;
                });

                var keyQuater = 0;
                var keyTrimester = 0;
                var keySemester = 0;
                var keyAnnual = 0;
                var keyExam = 0;
                var keyTotal = 0;

                //заполняем таблицу
                var tableMarks = $("#tblJournal").get(0);
                for (var i = 0; i < data.value.journalDtoList.length; i++) {
                    if (data.value.journalDtoList[i].markType == "current") {
                        var day = new Date();
                        day.setFullYear($("#selYearPage").val(), month - 1, data.value.journalDtoList[i].date.substring(0, 2));
                        var user = userMap[data.value.journalDtoList[i].userDto.id];
                        tableMarks.rows[user + 1].cells[day.getDate()].setAttribute("jour-id", data.value.journalDtoList[i].id);
                        tableMarks.rows[user + 1].cells[day.getDate()].innerHTML = data.value.journalDtoList[i].mark;
                    }
                    if (data.value.journalDtoList[i].markType == "quater") {
                        keyQuater = 1;
                    }
                    if (data.value.journalDtoList[i].markType == "trimester") {
                        keyTrimester = 1;
                    }
                    if (data.value.journalDtoList[i].markType == "semester") {
                        keySemester = 1;
                    }
                    if (data.value.journalDtoList[i].markType == "annual") {
                        keyAnnual = 1;
                    }
                    if (data.value.journalDtoList[i].markType == "exam") {
                        keyExam = 1;
                    }
                    if (data.value.journalDtoList[i].markType == "total") {
                        keyTotal = 1;
                    }
                }

                if (keyQuater == 1) {
                    addAbroad("Четверть", "quater");
                }
                if (keyTrimester == 1) {
                    addAbroad("Триместр", "trimester");
                }
                if (keySemester == 1) {
                    addAbroad("Полугодие", "semester");
                }
                if (keyAnnual == 1) {
                    addAbroad("Годовая", "annual");
                }
                if (keyExam == 1) {
                    addAbroad("Экзамен", "exam");
                }
                if (keyTotal == 1) {
                    addAbroad("Итоговая", "total");
                }

                //заполняем таблицу рубежными оценками
                var tableMarks = $("#tblJournal").get(0);
                for (var i = 0; i < data.value.journalDtoList.length; i++) {
                    if (data.value.journalDtoList[i].markType != "current") {
                        var user = userMap[data.value.journalDtoList[i].userDto.id];
                        var cellAbroad = $("#tblJournal th[markType = '" + data.value.journalDtoList[i].markType + "']")
                        tableMarks.rows[user + 1].cells[cellAbroad.index()].setAttribute("jour-id", data.value.journalDtoList[i].id);
                        tableMarks.rows[user + 1].cells[cellAbroad.index()].innerHTML = data.value.journalDtoList[i].mark;
                    }
                }

                $("#tblJournal").find("td.markAbroad").mouseover(function() {
                    $(this).removeClass("warning");
                    $(this).addClass("danger");
                });
                $("#tblJournal").find("td.markAbroad").mouseout(function() {
                    $(this).removeClass("danger");
                    $(this).addClass("warning");
                });

                $("#tblJournal").find("td.markAbroad").click(function() {
                    if (!($("#userType").val() == "RegistrarAccounts" || $("#userType").val() == "Onlooker" || $("#userType").val() == "ResponsibleForPayment")) {
                        if ($(this).text() == "") {
                            fillJournalEditDialog();
                            $("#markTypeDlg").val($(this).attr("marktype"));
                            $("#buttonMarkN").hide();
                        } else {
                            fillJournalEditDialog($(this));
                            $("#buttonMarkN").hide();
                        }
                        tdTable = $(this);
                        $("#journalEditDlg").modal({
                            backdrop: true
                        });
                    }
                });

                //удаляем воскресенья
                date.setFullYear($("#selYearPage").val(), month-1, 1);
                date.setHours(0, 0, 0, 0);
                for (var i = 0; i < numberDays; i++) {
                    date.setDate(i+1);
                    if (date.getDay() == 0) {
                        $("#tblJournal tr").each(function () {
                            $('td:eq(' + (i+1) + ')', this).toggle();
                            $('th:eq(' + (i+1) + ')', this).toggle();
                        });
                    }
                }

                $("#tblJournal").find("td.markTable").css('cursor','pointer');

                $("#tblJournal").find("td.markTable").click(function() {
                    if (!($("#userType").val() == "RegistrarAccounts" || $("#userType").val() == "Onlooker" || $("#userType").val() == "ResponsibleForPayment")) {
                        if ($(this).text() == "") {
                            fillJournalEditDialog();
                            $("#markTypeDlg").val($(this).attr("marktype"));
                            $("#buttonMarkN").show();
                        } else {
                            fillJournalEditDialog($(this));
                            $("#buttonMarkN").show();
                        }
                        tdTable = $(this);
                        $("#journalEditDlg").modal({
                            backdrop: true
                        });
                    }
                });

                $("#tblJournal").find("td.markTable").mouseover(function() {
                    $(this).addClass("info");
                });
                $("#tblJournal").find("td.markTable").mouseout(function() {
                    $(this).removeClass("info");
                });

                return false;
            }
        }
    );
}

function fillJournalEditDialog(td) {
    if (td) {
        $("#headerEdit").text("Редактировать оценку");
        $("#journalIdDlg").val(td.attr("jour-id"));
        $("#markTypeDlg").val(td.attr("marktype"));

        $("#buttonMark1").removeClass("btn-info");
        $("#buttonMark2").removeClass("btn-info");
        $("#buttonMark3").removeClass("btn-info");
        $("#buttonMark4").removeClass("btn-info");
        $("#buttonMark5").removeClass("btn-info");
        $("#buttonMarkN").removeClass("btn-info");
        $("#buttonMark1").addClass("btn-default");
        $("#buttonMark2").addClass("btn-default");
        $("#buttonMark3").addClass("btn-default");
        $("#buttonMark4").addClass("btn-default");
        $("#buttonMark5").addClass("btn-default");
        $("#buttonMarkN").addClass("btn-default");
        if ($("#buttonMark1").text() == td.text()) {
            $("#buttonMark1").toggleClass('btn-info').toggleClass('btn-default');
        }if ($("#buttonMark2").text() == td.text()) {
            $("#buttonMark2").toggleClass('btn-info').toggleClass('btn-default');
        }
        if ($("#buttonMark3").text() == td.text()) {
            $("#buttonMark3").toggleClass('btn-info').toggleClass('btn-default');
        }
        if ($("#buttonMark4").text() == td.text()) {
            $("#buttonMark4").toggleClass('btn-info').toggleClass('btn-default');
        }
        if ($("#buttonMark5").text() == td.text()) {
            $("#buttonMark5").toggleClass('btn-info').toggleClass('btn-default');
        }
        if ($("#buttonMarkN").text() == td.text()) {
            $("#buttonMarkN").toggleClass('btn-info').toggleClass('btn-default');
        }

        $("#deleteDlg").show();
    }
    else {
        $("#headerEdit").text("Добавить оценку");
        $("#journalIdDlg").val(-1);

        $("#buttonMark1").removeClass("btn-info");
        $("#buttonMark2").removeClass("btn-info");
        $("#buttonMark3").removeClass("btn-info");
        $("#buttonMark4").removeClass("btn-info");
        $("#buttonMark5").removeClass("btn-info");
        $("#buttonMarkN").removeClass("btn-info");
        $("#buttonMark1").addClass("btn-default");
        $("#buttonMark2").addClass("btn-default");
        $("#buttonMark3").addClass("btn-default");
        $("#buttonMark4").addClass("btn-default");
        $("#buttonMark5").addClass("btn-default");
        $("#buttonMarkN").addClass("btn-default");

        $("#deleteDlg").hide();
    }
}

function getUrlVars() {
    var vars = {};
    var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) {
        vars[key] = value;
    });
    return vars;
}

function addAbroad(nameMarkAbroad, markType) {
    var numberDays = $("#selMonthPage").children("option:selected").attr("numberDays");
    var month = $("#selMonthPage").children("option:selected").attr("month");
    var curr_month = month;
    if (curr_month < 10) {
        curr_month = "0" + curr_month;
    }
    var curr_date = numberDays + "-" + curr_month + "-" + $("#selYearPage").val();

    $("#tblJournal thead tr").append('<th class="text-center warning" date="' + curr_date + '" markType="' + markType + '" title="' + nameMarkAbroad + '">' + nameMarkAbroad.substring(0, 1) + '</th>');
    $("#tblJournal tbody tr").append('<td align="center" class="warning markAbroad" markType="' + markType + '" style="cursor: pointer;"></td>');
}