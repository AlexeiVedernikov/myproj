var a = 0;

$(document).ready(function() {

    $("#organizationPhoneDlg").mask("+79999999999");

    bindEditClick($(".aOrganizationEdit"));
    bindDeleteClick($(".aOrganizationDelete"));

	$("#btnSaveOrganizationDlg").click(function(){
		var wm = new OrganizationWebModel(
            $("#inpOrganizationIdDlg").val(),
            $("#organizationNameDlg").val(),
            $("#organizationDescriptionDlg").val(),
            
            $("#organizationStatusDlg").children("option:selected").attr("value"),
            $("#organizationPhoneDlg").val(),
            $("#organizationAddressDlg").val(),

            $("#selCountry").children("option:selected").attr("value"),
            $("#selRegion").children("option:selected").attr("value"),
            $("#selDistrict").children("option:selected").attr("value"),
            $("#selTown").children("option:selected").attr("value"),
            $("#selTownArea").children("option:selected").attr("value")
		);
		
		$.postJSON("organization", wm, function(data) {
            if (data && !hasError(data) && data.url) {
                    showNotification("Сохранение выполнено успешно", "success");
                setTimeout(function(){window.location = data.url}, 1500);
                return false;
            }
        });
		//window.location.href="organizations";
	});

	$("#aOrganizationAdd").click(function() {
        window.location.href="dialog/organizations-edit";
	});
	
	$("#organizationCancelDlg").click(function(){
		window.location.href="organizations";
	});

//	$('#tblOrganization').dataTable();
});

function bindEditClick(elements){
    elements.on("click", function() {
        window.location.href = "dialog/organizations-edit?id=" + $(this).parents("tr").attr("trId");
    });
}

function bindDeleteClick(elements){
    elements.on("click", function() {
        var organizationId = $(this).parents("tr").attr("trId");

        $.deleteJSON("organization", {id:organizationId}, function(data) {
            if (data && !hasError(data)) {

                $('[trID =' + organizationId + ']').remove();
                showNotification("Удаление выполнено", "warning");
                return false;
            }
        });
    });
}

function OrganizationWebModel(id, name, description, status, phone, address, countryId, regionId, districtId, townId, townAreaId) {
    this.id = id;
	this.name = name;
    this.description = description;
    this.status = status;
    this.phone = phone;
    this.address = address;
    this.countryId = countryId;
    this.regionId = regionId;
    this.districtId = districtId;
    this.townId = townId;
    this.townAreaId = townAreaId;
}

function addTownStr(data){
	var strtable = ""; 
	strtable += "<tr trId = " + data.value.id + ">";
		strtable += "<td class = 'edit' organizationId =" + data.value.id + ">" +
		"<button type='button' class='btn btn-default aOrganizationEdit'>" +
		    "<span class='glyphicon glyphicon-pencil'>" + "</span>" +
		"</button>"
		    + 
		    "&nbsp"
		    +
		"<button type='button' class='btn btn-default aOrganizationDelete'>" +
	        "<span class='glyphicon glyphicon-trash'>" + "</span>" +
	    "</button>"
		+ "</td>";
		strtable += "<td class = 'name'>" + data.value.name + "</td>";
		strtable += "<td class = 'description'>" + data.value.status +  data.value.description + "</td>";
		strtable += "<td class = 'status' status = " + data.value.status + ">" +  data.value.status + "</td>";
		strtable += "<td class = 'region' regionId = " + data.value.regionDto.id + ">" +  data.value.regionDto.name + "</td>";
		strtable += "<td class = 'district' districtId = " + data.value.districtDto.id + ">" +  data.value.districtDto.name + "</td>";
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
	$('[trId =' + data.value.id + ']').children("td.district").text(data.value.districtDto.name);
}

