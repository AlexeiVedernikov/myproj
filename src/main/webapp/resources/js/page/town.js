var x = null;

$(document).ready(function() {

    bindEditClick($(".aTownEdit"));
    bindDeleteClick($(".aTownDelete"));
	
	$("#btnSaveTownDlg").click(function(){
		var wm = new TownDataModel(
            $("#inpTownIdDlg").val(),
            $("#townNameDlg").val(),
            $("#townDescriptionDlg").val(),
            $("#townStatusDlg").children("option:selected").attr("value"),
            $("#selRegion").children("option:selected").attr("value"),
            $("#selDistrict").children("option:selected").attr("value")
		);
		
		$.postJSON("town", wm, function(data) {
            if (data && !hasError(data)) {

            	if(x==0){
                    addTownStr(data);
                    showNotification("Сохранение выполнено успешно", "success");
            	} else if (x==1){
                    editTownStr(data);
                    showNotification("Изменение сохранено", "success");
            	}
                return false;
            }
        });
		$("#dlgTownEdit").modal("hide");
	});

	$("#aTownAdd").click(function() {
		fillTownEdit();
		x=0;
        $("#dlgTownEdit").modal({
            backdrop : true
        });
	});

	$('#tblTown').dataTable();
});

function bindEditClick(elements){
    elements.on("click", function() {
        x = 1;
        fillTownEdit( $(this).parents("tr") );
        $("#dlgTownEdit").modal({
            backdrop : true
        });
    });
}

function bindDeleteClick(elements){
    elements.on("click", function() {
        var townId = $(this).parents("tr").attr("trId");
        $.deleteJSON("town", {id:townId}, function(data) {
            if (data && !hasError(data)) {

                $('[trID =' + townId + ']').remove();
                showNotification("Удаление выполнено", "warning");
                return false;
            }
        });
    });
}

function TownDataModel(id, name, description, status, regionId, districtId) {
    this.id = id;
	this.name = name;
    this.description = description;
    this.status = status;
    this.regionId = regionId;
    this.districtId = districtId;
}

function fillTownEdit(tr) {
    if (tr) {
        $("#headerEdit").text("Редактировать населенный пункт");
        $("#inpTownIdDlg").val(tr.children("td.edit").attr("townId"));
        $("#townNameDlg").val(tr.children("td.name").text());
        $("#townDescriptionDlg").val(tr.children("td.description").text());
        setSelectedItemInSelectObj($("#townStatusDlg"), tr.children("td.status").attr("status"));
        fillEdit(tr);
    } else {
        $("#headerEdit").text("Добавить населенный пункт");
        $("#inpTownIdDlg").val(-1);
        $("#townNameDlg").val("");
        $("#townDescriptionDlg").val("");
        setSelectedItemInSelectObj( $("#townStatusDlg"),"Active");
        fillEdit();
       }
    }

function addTownStr(data){
	var strtable = ""; 
	strtable += "<tr trId = " + data.value.id + ">";
		strtable += "<td class = 'edit' townId =" + data.value.id + ">" +  
		"<button type='button' class='btn btn-default aTownEdit'>" +
		    "<span class='glyphicon glyphicon-pencil'>" + "</span>" +
		    "</button>" 
		    + 
		    "&nbsp"
		    +
		   "<button type='button' class='btn btn-default aTownDelete'>" +
	   "<span class='glyphicon glyphicon-trash'>" + "</span>" +
	   "</button>"
		+ "</td>";
		strtable += "<td class = 'name'>" + data.value.name + "</td>";

        if (data.value.districtDto) {
            strtable += "<td class = 'district' districtId = " + data.value.districtDto.id + ">" +  data.value.districtDto.name + "</td>";
        } else {
            strtable += "<td class = 'district' districtId></td>";
        }

        strtable += "<td class = 'region' regionId = " + data.value.regionDto.id + ">" +  data.value.regionDto.name + "</td>";
        strtable += "<td class = 'country' countryId = " + data.value.regionDto.countryDto.id + ">" +  data.value.regionDto.countryDto.name + "</td>";
		strtable += "<td class = 'description'>" + data.value.status +  data.value.description + "</td>";
		strtable += "<td class = 'status' status = " + data.value.status + ">" +  data.value.status + "</td>";
		strtable += "</tr>";
		$('#tblTown tr:last').after(strtable);
        bindEditClick( $('#tblTown tr:last .aTownEdit') );
        bindDeleteClick( $('#tblTown tr:last .aTownDelete') );
}

function editTownStr(data){
	$('[trId =' + data.value.id + ']').children("td.name").text(data.value.name);
	$('[trId =' + data.value.id + ']').children("td.description").text(data.value.description);
	$('[trId =' + data.value.id + ']').children("td.status").text(data.value.status);
	$('[trId =' + data.value.id + ']').children("td.region").text(data.value.regionDto.name);
    if (data.value.districtDto) {
        $('[trId =' + data.value.id + ']').children("td.district").text(data.value.districtDto.name);
        $('[trId =' + data.value.id + ']').children("td.district").attr("districtId", data.value.districtDto.id);
    } else {
        $('[trId =' + data.value.id + ']').children("td.district").text("");
        $('[trId =' + data.value.id + ']').children("td.district").attr("districtId", "");
    }
    $('[trId =' + data.value.id + ']').children("td.country").text(data.value.regionDto.countryDto.name);
    $('[trId =' + data.value.id + ']').children("td.status").attr("status", data.value.status);
    $('[trId =' + data.value.id + ']').children("td.country").attr("countryId", data.value.regionDto.countryDto.id);
    $('[trId =' + data.value.id + ']').children("td.region").attr("regionId", data.value.regionDto.id);
}

