var x = null;

$(document).ready(function() {

    bindEditClick($(".editRow"));
    bindDeleteClick($(".deleteRow"));

    $("#saveDlg").click(function(){
        var wm = new DistrictWebModel(
            $("#districtIdDlg").val(),
            $("#districtNameDlg").val(),
            $("#selRegion").children("option:selected").attr("value"),
            $("#districtDescriptionDlg").val(),
            $("#districtStatusDlg").children("option:selected").attr("value")
        );

        $.postJSON("district", wm, function(data) {
            if (data && !hasError(data)) {

                if (x == 0) {
                    addRow(data);
                    showNotification("Сохранение выполнено успешно", "success");
                }
                else if (x == 1) {
                    editRow(data);
                    showNotification("Изменение сохранено", "success");
                }

                $("#dlgDistrictEdit").modal("hide");

                return false;
            }
        });
    });

    $("button.addRow").click(function() {
        fillDistrictEdit();
        $("#dlgDistrictEdit").modal({
            backdrop : true
        });
        x = 0;
    });

    $('#tblDistricts').dataTable();
});

function bindEditClick(elements) {
    elements.on("click", function() {
        x = 1;
        fillDistrictEdit( $(this).parents("tr") );
        $("#dlgDistrictEdit").modal({
            backdrop : true
        });
    });
}

function bindDeleteClick(elements) {
    elements.on("click", function() {
        var districtId = $(this).parents("tr").attr("trId");
        $.deleteJSON("district", {id:districtId}, function(data) {
            if (data && !hasError(data)) {

                $('[trId =' + districtId + ']').remove();
                showNotification("Удаление выполнено", "warning");
                return false;
            }
        });
    });
}

function DistrictWebModel(id, name, regionId, description, status) {
    this.id = id;
    this.name = name;
    this.regionId = regionId;
    this.description = description;
    this.status = status;
}

function fillDistrictEdit(tr) {
    if (tr) {
        $("#headerEdit").text("Редактировать район");
        $("#districtIdDlg").val(tr.attr("trId"));
        $("#districtNameDlg").val(tr.children("td.name").text());
        $("#districtDescriptionDlg").val(tr.children("td.description").text());
        setSelectedItemInSelectObj($("#districtStatusDlg"), tr.children("td.status").attr("status"));
        fillEdit(tr);
    } else {
        $("#headerEdit").text("Добавить район");
        $("#districtIdDlg").val(-1);
        $("#districtNameDlg").val("");
        $("#districtDescriptionDlg").val("");
        setSelectedItemInSelectObj( $("#districtStatusDlg"),"Active");
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
        + "<td class = 'region'  regionId = " + data.value.regionDto.id + ">" +  data.value.regionDto.name + "</td>"
        + "<td class = 'country'  countryId = " + data.value.regionDto.countryDto.id + ">" +  data.value.regionDto.countryDto.name + "</td>"
        + "<td class = 'description'>" +  data.value.description + "</td>"
        + "<td class = 'status' status = " + data.value.status +">" + data.value.status + "</td>";
        + "</tr>";

    $('#tblDistricts tr:last').after(strtable);
    bindEditClick( $('#tblDistricts tr:last .editRow') );
    bindDeleteClick( $('#tblDistricts tr:last .deleteRow') );
}

function editRow(data) {
    $('[trId =' + data.value.id + ']').children("td.name").text(data.value.name);
    $('[trId =' + data.value.id + ']').children("td.region").text(data.value.regionDto.name)
    $('[trId =' + data.value.id + ']').children("td.country").text(data.value.regionDto.countryDto.name);
    $('[trId =' + data.value.id + ']').children("td.description").text(data.value.description);
    $('[trId =' + data.value.id + ']').children("td.status").text(data.value.status);
    $('[trId =' + data.value.id + ']').children("td.status").attr("status", data.value.status);
    $('[trId =' + data.value.id + ']').children("td.country").attr("countryId", data.value.regionDto.countryDto.id);
    $('[trId =' + data.value.id + ']').children("td.region").attr("regionId", data.value.regionDto.id);
}