var x = 0;
var table = null;

$(document).ready(function() {

	$("#disciplinesEditAddDlg").click(function(){
			var wm = new ReferenceBookModel(
					$("#disciplinesIdDlg").val(),
					$("#disciplinesNameDlg").val(),
					$("#disciplinesDescriptionDlg").val(),
					$("#disciplinesStatusDlg").children("option:selected").attr("value")
			);

		$.postJSON("disciplines", wm, function(data) {
            if (data && !hasError(data)) {
                if (x == 0){
                    showNotification("Сохранение выполнено успешно", "success");
                }
                else if (x == 1){
                    showNotification("Изменение сохранено", "success");
                }
                viewTable();
                $("#disciplinesEditDlg").modal("hide");
                return false;
            }
        });
	});

    $("#add").click(function(){
        x = 0;
        fillDisciplineEditDialog();
        $("#disciplinesEditDlg").modal({
            backdrop : true
        });
    });

    viewTable();

});

function bindEditClick(elements){
	elements.on("click", function() {
		x = 1;
		fillDisciplineEditDialog( $(this).parents("tr") );
	       $("#disciplinesEditDlg").modal({
	          backdrop : true
	       });
	});
}
function bindDeleteClick(elements){
	elements.on("click", function() {
        var disciplineId = $(this).parents("tr").attr("trId");
        $.deleteJSON("disciplines", {id:disciplineId}, function(data) {
            if (data && !hasError(data)) {
                viewTable();
                showNotification("Удаление выполнено", "warning");
            return false;
           }
        });
	});
}

function ReferenceBookModel(id, name, description, status) {
	this.id = id;
	this.name = name;
    this.description = description;
    this.status = status;
}

function fillDisciplineEditDialog(tr) {
    if (tr) {
        $("#headerEdit").text("Редактировать предмет");
    	$("#disciplinesIdDlg").val(tr.attr("trID"));
        $("#disciplinesNameDlg").val(tr.children("td.name").text());
        $("#disciplinesDescriptionDlg").val(tr.children("td.description").text());
        setSelectedItemInSelectObj($("#disciplinesStatusDlg"), tr.children("td.status").attr("status"));
}
    else{
        $("#headerEdit").text("Добавить предмет");
        $("#disciplinesIdDlg").val(-1);
        $("#disciplinesNameDlg").val("");
        $("#disciplinesDescriptionDlg").val("");
        setSelectedItemInSelectObj( $("#disciplinesStatusDlg"),"Active");
    }
}

function viewTable() {
    $.getJSON("disciplinesSearch", {}, function(data) {
            if (data && !hasError(data)) {
                $("#tblDisciplines").children("tbody").html(function() {
                    var str = "";
                    for(var i = 0; i<data.value.length; i++) {
                        str += "<tr trID = " + data.value[i].id + ">";
                        if (!($("#userType").val() == "Onlooker" || $("#userType").val() == "OperatorMarks")) {
                            str += "<td class = 'edit' >" +
                            "<div style='min-width: 90px'>" +
                            "<button type='button' class='btn btn-default editButton' title='Редактировать'>" +
                            "<span class='glyphicon glyphicon-pencil'></span>" +
                            "</button>" +
                            "&nbsp" +
                            "<button type='button' class='btn btn-default deleteButton' title='Удалить'>" +
                            "<span class='glyphicon glyphicon-trash'></span>" +
                            "</button>" +
                            "</div>" +
                            "</td>";
                        }
                        str += "<td class='name'>" + data.value[i].name + "</td>" +
                            "<td class='description'>" + data.value[i].description + "</td>" +
                            "<td class='status' status='" + data.value[i].status + "'>" + data.value[i].status + "</td>" +
                            "</tr>";
                    }
                    if (table) {
                        table.fnDestroy();
                    }
                    return str;
                });
                bindEditClick($(".editButton"));
                bindDeleteClick($(".deleteButton"));
                table = $('#tblDisciplines').dataTable();
                return false;
            }}
    )
}