var x = null;

$(document).ready(function() {

    bindEditClick($(".editRow"));
    bindDeleteClick($(".deleteRow"));

    $("#saveDlg").click(function(){
        var wm = new RegionWebModel(
            $("#regionIdDlg").val(),
            $("#regionNameDlg").val(),
            $("#selCountry").children("option:selected").attr("value"),
            $("#regionDescriptionDlg").val(),
            $("#regionStatusDlg").children("option:selected").attr("value")
        );

        $.postJSON("region", wm, function(data) {
            if (data && !hasError(data)) {

                if (x == 0) {
                    addRow(data);
                    showNotification("Сохранение выполнено успешно", "success");
                }
                else if (x == 1) {
                    editRow(data);
                    showNotification("Изменение сохранено", "success");
                }

                $("#dlgRegionEdit").modal("hide");

                return false;
            }
        });
    });
    $("button.addRow").click(function() {
        fillRegionEdit();
        $("#dlgRegionEdit").modal({
            backdrop : true
        });
        x = 0;
    });

    $('#tblRegions').dataTable();
});

function bindEditClick(elements){
    elements.on("click", function() {
        x = 1;
        fillRegionEdit( $(this).parents("tr") );
        $("#dlgRegionEdit").modal({
            backdrop : true
        });
    });
}

function bindDeleteClick(elements){
    elements.on("click", function() {
        var regionId = $(this).parents("tr").attr("trId");
        $.deleteJSON("region", {id:regionId}, function(data) {
            if (data && !hasError(data)) {

                $('[trId =' + regionId + ']').remove();
                showNotification("Удаление выполнено", "warning");
                return false;
            }
        });
    });
}


function RegionWebModel(id, name, countryId, description, status) {
    this.id = id;
    this.name = name;
    this.countryId = countryId;
    this.description = description;
    this.status = status;
}

function fillRegionEdit(tr) {
    if (tr) {
        $("#headerEdit").text("Редактировать регион");
        $("#regionIdDlg").val(tr.attr("trId"));
        $("#regionNameDlg").val(tr.children("td.name").text());
        $("#regionDescriptionDlg").val(tr.children("td.description").text());
        setSelectedItemInSelectObj($("#regionStatusDlg"), tr.children("td.status").attr("status"));
        $('#regionStatusDlg').selectpicker('refresh');
        fillEdit(tr);
    } else {
        $("#headerEdit").text("Добавить регион");
        $("#regionIdDlg").val(-1);
        $("#regionNameDlg").val("");
        $("#regionDescriptionDlg").val("");
        setSelectedItemInSelectObj( $("#regionStatusDlg"),"Active");
        $('#regionStatusDlg').selectpicker('refresh');
        fillEdit();
    }
}

function addRow(data) {
    var strtable =
        "<tr trId =" + data.value.id + "><td class = 'edit'>"
        + "<button type='button' class='btn btn-default editRow'>"
        + "<span class='glyphicon glyphicon-pencil'>" + "</span>"
        + "</button>"
        + "&nbsp"
        + "<button type='button' class='btn btn-default deleteRow'>"
        + "<span class='glyphicon glyphicon-trash'>" + "</span>"
        + "</button>"
        + "</td>"
        + "<td class = 'name'>" +  data.value.name + "</td>"
        + "<td class = 'countryN'  countryId = " + data.value.countryDto.id + ">" +  data.value.countryDto.name + "</td>"
        + "<td class = 'description'>" +  data.value.description + "</td>"
        + "<td class = 'status' status = " + data.value.status +">" + data.value.status + "</td>";
        + "</tr>";

    $('#tblRegions tr:last').after(strtable);
    bindEditClick( $('#tblRegions tr:last .editRow') );
    bindDeleteClick( $('#tblRegions tr:last .deleteRow') );
}

function editRow(data) {
    $('[trId =' + data.value.id + ']').children("td.name").text(data.value.name);
    $('[trId =' + data.value.id + ']').children("td.countryN").text(data.value.countryDto.name);
    $('[trId =' + data.value.id + ']').children("td.description").text(data.value.description);
    $('[trId =' + data.value.id + ']').children("td.status").text(data.value.status);
    $('[trId =' + data.value.id + ']').children("td.status").attr("status", data.value.status);
    $('[trId =' + data.value.id + ']').children("td.countryN").attr("countryId", data.value.countryDto.id);
}