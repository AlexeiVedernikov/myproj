var x = null;

$(document).ready(function() {
	
	bindEditClick($(".aTownAreaEdit"));
	bindDeleteClick($(".aTownAreaDelete"));

	$("#btnSaveTownAreaDlg").click(function(){
		var wm = new TownAreaWebModel(
            $("#inpTownAreaIdDlg").val(),
            $("#townAreaNameDlg").val(),
            $("#townAreaDescriptionDlg").val(),
            $("#townAreaStatusDlg").children("option:selected").attr("value"),
            $("#selTown").children("option:selected").attr("value")
		);

		$.postJSON("townArea", wm, function(data) {
            if (data && !hasError(data)) {
            	if(x == 0){
            		addTownArea(data);
                    showNotification("Сохранение выполнено успешно", "success");
            	}
            	else if(x == 1){
            		editTownArea(data);
                    showNotification("Изменение сохранено", "success");
            	}
                $("#dlgTownAreaEdit").modal("hide");
                return false;
            }
        });
	});
	
	$("#aTownAreaAdd").click(function() {
		x = 0;
		fillTownAreaEdit();
        $("#dlgTownAreaEdit").modal({
            backdrop : true
        });
    });

    $('#tblTownArea').dataTable();
});

function bindEditClick(elements){
	elements.on("click", function() {
		x = 1;
		fillTownAreaEdit( $(this).parents("tr") );
        $("#dlgTownAreaEdit").modal({
	        backdrop : true
	    });
	});
}

function bindDeleteClick(elements){
	elements.on("click", function() {
        var townAreaId = $(this).parents("tr").attr("trId");
        $.deleteJSON("townArea", {id:townAreaId}, function(data) {
            if (data && !hasError(data)) {
                $('[trID =' + townAreaId + ']').remove();
                showNotification("Удаление выполнено", "warning");
                return false;
            }
        });
    });
}

function TownAreaWebModel(id, name, description, status, townId) {
    this.id = id;
	this.name = name;
    this.description = description;
    this.status = status;
    this.townId = townId;
}

function fillTownAreaEdit(tr) {
    if (tr) {
        $("#headerEdit").text("Редактировать микрорайон");
        $("#inpTownAreaIdDlg").val(tr.children("td.edit").attr("townAreaId"));
        $("#townAreaNameDlg").val(tr.children("td.name").text());
        $("#townAreaDescriptionDlg").val(tr.children("td.description").text());
        setSelectedItemInSelectObj($("#townAreaStatusDlg"), tr.children("td.status").attr("status"));
        fillEdit(tr);
    } else {
        $("#headerEdit").text("Добавить микрорайон");
        $("#inpTownAreaIdDlg").val(-1);
        $("#townAreaNameDlg").val("");
        $("#townAreaDescriptionDlg").val("");
        setSelectedItemInSelectObj( $("#townAreaStatusDlg"),"Active");
        fillEdit();
    }
}
function addTownArea(data) {
    var strtable = "";
    strtable += "<tr trID = " + data.value.id + ">";
    strtable += "<td class = 'edit' townAreaId =" + data.value.id + ">" +
    "<button type='button' class='btn btn-default aTownAreaEdit'>" +
      "<span class='glyphicon glyphicon-pencil'>" + "</span>" +
    "</button>"
    +
    "&nbsp"
    +
    "<button type='button' class='btn btn-default aTownAreaDelete'>" +
      "<span class='glyphicon glyphicon-trash'>" + "</span>" +
    "</button>" +
    "</td>";
    strtable += "<td class = 'name'>" +  data.value.name + "</td>";
    strtable += "<td class = 'town' townId = " + data.value.townDto.id +">" + data.value.townDto.name + "</td>";

    if (data.value.townDto.districtDto) {
        strtable += "<td class = 'district' districtId = " + data.value.townDto.districtDto.id + ">" +  data.value.townDto.districtDto.name + "</td>";
    } else {
        strtable += "<td class = 'district' districtId></td>";
    }

    strtable += "<td class = 'region' regionId = " + data.value.townDto.regionDto.id + ">" +  data.value.townDto.regionDto.name + "</td>";
    strtable += "<td class = 'country' countryId = " + data.value.townDto.regionDto.countryDto.id + ">" +  data.value.townDto.regionDto.countryDto.name + "</td>";
    strtable += "<td class = 'description'>" +  data.value.description + "</td>";
    strtable += "<td class = 'status' status = " + data.value.status +">" + data.value.status + "</td>";
    strtable += "</tr>";

    $('#tblTownArea tr:last').after(strtable);
    bindEditClick( $('#tblTownArea tr:last .aTownAreaEdit') );
    bindDeleteClick( $('#tblTownArea tr:last .aTownAreaDelete') );
}

function editTownArea(data){
	$('[trID =' + data.value.id + ']').children("td.name").text(data.value.name);
	$('[trID =' + data.value.id + ']').children("td.description").text(data.value.description);
	$('[trID =' + data.value.id + ']').children("td.status").text(data.value.status);
	$('[trID =' + data.value.id + ']').children("td.town").text(data.value.townDto.name);
    $('[trId =' + data.value.id + ']').children("td.region").text(data.value.townDto.regionDto.name);
    if (data.value.townDto.districtDto) {
        $('[trId =' + data.value.id + ']').children("td.district").text(data.value.townDto.districtDto.name);
        $('[trId =' + data.value.id + ']').children("td.district").attr("districtId", data.value.townDto.districtDto.id);
    } else {
        $('[trId =' + data.value.id + ']').children("td.district").text("");
        $('[trId =' + data.value.id + ']').children("td.district").attr("districtId", "");
    }
    $('[trId =' + data.value.id + ']').children("td.country").text(data.value.townDto.regionDto.countryDto.name);
    $('[trId =' + data.value.id + ']').children("td.status").attr("status", data.value.status);
    $('[trId =' + data.value.id + ']').children("td.country").attr("countryId", data.value.townDto.regionDto.countryDto.id);
    $('[trId =' + data.value.id + ']').children("td.region").attr("regionId", data.value.townDto.regionDto.id);
    $('[trID =' + data.value.id + ']').children("td.town").attr("townId", data.value.townDto.id);
}

