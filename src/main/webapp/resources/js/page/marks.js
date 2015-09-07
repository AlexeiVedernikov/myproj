var listNumberDays = null;
var userId = null;
var listNameDay = null;

$(document).ready(function() {

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
    $("#selMonth").html(function(){
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

    $("#selYearPage").change(function() {
        viewTable($("#selMonth").children("option:selected").attr("month"), $("#selMonth").children("option:selected").attr("numberDays"));
    });

    $("#selMonth").change(function() {
        viewTable($("#selMonth").children("option:selected").attr("month"), $("#selMonth").children("option:selected").attr("numberDays"));
    });

    if ($("#userType").val() == "Parent" || $("#userType").val() == "Scholar") {
        viewTable($("#selMonth").children("option:selected").attr("month"), $("#selMonth").children("option:selected").attr("numberDays"));
    }

    $("#selGroupPage").change(function() {

        $("#optionSelectGroup").remove();
        $('#selGroupPage').selectpicker('refresh');

        $.getJSON("userSearch", {groupId:$("#selGroupPage option:selected").val(), userType:"Scholar"}, function(data) {

            if (data && !hasError(data)) {

                $("#selScholarPage").html(function(){
                    var str = "";
                    if (data.value.length > 0) {
                        str += "<option id='optionSelectScholar' value=\"disabled\">--Выберите ученика--</option>";
                    } else {
                        str += "<option value=\"disabled\">Нет учеников</option>";
                    }

                    for(var i = 0; i<data.value.length; i++) {
                        str += "<option value=" + data.value[i].id + ">" + data.value[i].name + "</option>"
                    }

                    return str;
                });
                $('#selScholarPage').selectpicker('refresh');

                return false;
            }}
        );
    });

    $("#selScholarPage").change(function(){
        $("#optionSelectScholar").remove();
        $('#selScholarPage').selectpicker('refresh');
        viewTable($("#selMonth").children("option:selected").attr("month"), $("#selMonth").children("option:selected").attr("numberDays"));
    });

});

function viewTable(month, numberDays) {

        var curr_month = month;
        if (curr_month < 10) {
            curr_month = "0" + curr_month;
        }

        var curr_date = "01-" + curr_month + "-" + $("#selYearPage").val();

        if ($("#userType").val() == "Scholar") {
            userId = $("#userId").val();
        } else {
            userId = $("#selScholarPage").children("option:selected").val();
        }

        var disciplineMap = { "test": "test2"};

        var date = new Date();
        date.setFullYear($("#selYearPage").val(), month-1, 1);
        date.setHours(0, 0, 0, 0);

        $.getJSON("markSearch", {date: curr_date, numberDays: numberDays, userId:userId}, function (data) {
                if (data && !hasError(data)) {
                    $("#tblMarks").html(function () {
                        var str = "";
                        str += "<thead>" +
                            "<tr>" +
                            "<th>Предмет</th>";
                            for (var i = 0; i < numberDays; i++) {
                                date.setDate(i+1);
                                str += "<th class='text-center'>" + listNameDay[date.getDay()] + "<br>" + (i+1) + "</th>";
                            }
                        str += "</tr>" +
                            "</thead>" +
                            "<tbody>";

                        for (var i = 0; i < data.value.sheduleDtoList.length; i++) {
                            str += "<tr>" +
                                "<td>" + data.value.sheduleDtoList[i].disciplineDto.name + "</td>";
                                for (var j = 0; j < numberDays; j++) {
                                    str += "<td></td>";
                                }
                            str += "</tr>";
                            disciplineMap[data.value.sheduleDtoList[i].id] =  i;
                        }
                        str += "</tbody>";

                        return str;
                    });

                    var map = { "standart": "label-primary", "control": "label-primary", "essay": "label-info", "summary": "label-info",
                        "homework": "label-warning", "classwork": "label-success"};

                    var keyQuater = 0;
                    var keyTrimester = 0;
                    var keySemester = 0;
                    var keyAnnual = 0;
                    var keyExam = 0;
                    var keyTotal = 0;

                    var tableMarks = $("#tblMarks").get(0);
                    for (var i = 0; i < data.value.journalDtoList.length; i++) {
                        if (data.value.journalDtoList[i].markType == "current") {
                            var day = new Date();
                            day.setFullYear($("#selYearPage").val(), month - 1, data.value.journalDtoList[i].date.substring(0, 2));
                            var discipline = disciplineMap[data.value.journalDtoList[i].sheduleDto.id];
                            var styleMark = null;
                            if (data.value.journalDtoList[i].mark == "Н") {
                                styleMark = "label-danger";
                            } else {
                                styleMark = map[data.value.journalDtoList[i].taskType];
                            }
                            tableMarks.rows[discipline + 1].cells[day.getDate()].innerHTML =
                                "<span class='label " + styleMark + "' style='font-size: 13px'>" + data.value.journalDtoList[i].mark + "</span>";
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
                    var tableMarks = $("#tblMarks").get(0);
                    for (var i = 0; i < data.value.journalDtoList.length; i++) {
                        if (data.value.journalDtoList[i].markType != "current") {
                            var discipline = disciplineMap[data.value.journalDtoList[i].sheduleDto.id];
                            var cellAbroad = $("#tblMarks th[markType = '" + data.value.journalDtoList[i].markType + "']")
                            tableMarks.rows[discipline + 1].cells[cellAbroad.index()].innerHTML =
                                "<span class='label label-warning' style='font-size: 13px'>" + data.value.journalDtoList[i].mark + "</span>";
                        }
                    }

                    date.setFullYear($("#selYearPage").val(), month-1, 1);
                    date.setHours(0, 0, 0, 0);
                    for (var i = 0; i < numberDays; i++) {
                        date.setDate(i+1);
                        if (date.getDay() == 0) {
                            $("#tblMarks tr").each(function () {
                                $('td:eq(' + (i+1) + ')', this).toggle();
                                $('th:eq(' + (i+1) + ')', this).toggle();
                            });
                        }
                    }

                    return false;
                }


            }
        );
}

function addAbroad(nameMarkAbroad, markType) {
    var numberDays = $("#selMonthPage").children("option:selected").attr("numberDays");
    var month = $("#selMonthPage").children("option:selected").attr("month");
    var curr_month = month;
    if (curr_month < 10) {
        curr_month = "0" + curr_month;
    }
    var curr_date = numberDays + "-" + curr_month + "-" + $("#selYearPage").val();

    $("#tblMarks thead tr").append('<th class="text-center warning" date="' + curr_date + '" markType="' + markType + '" title="' + nameMarkAbroad + '">' + nameMarkAbroad.substring(0, 1) + '</th>');
    $("#tblMarks tbody tr").append('<td align="center" class="warning markAbroad" markType="' + markType + '"></td>');
}

