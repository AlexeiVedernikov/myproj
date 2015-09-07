var x = null;
	
$(document).ready(function() {
	
	bindEditClick($(".countryEdit"));
	bindDeleteClick($(".countryDelete"));

	$("#CountryEditAddDlg").click(function(){
			var wm = new ReferenceBookModel(
					$("#countryIdDlg").val(),
					$("#countryNameDlg").val(),		
					$("#countryDescriptionDlg").val(),
					$("#countryStatusDlg").children("option:selected").attr("value")
			);
		
		$.postJSON("country", wm, function(data) {
            if (data && !hasError(data)) {
            	if (x == 0){
            		addCountry(data);
                    showNotification("Сохранение выполнено успешно", "success");
            	}
            	else if (x == 1){
            		editCountry(data);
                    showNotification("Изменение сохранено", "success");
            	}
            	$("#countryEditDlg").modal("hide");
                return false;
            }
        });
	});

$("#add").click(function(){	
		x = 0;
		fillCountryEditDialog();
	    $("#countryEditDlg").modal({
	        backdrop : true
	      });
});
  $('#tblCountries').dataTable();
});

function bindEditClick(elements){
	elements.on("click", function() {
		x = 1;
		fillCountryEditDialog( $(this).parents("tr") );
	       $("#countryEditDlg").modal({
	          backdrop : true
	       });
	});
}
function bindDeleteClick(elements){
	elements.on("click", function() {
        var countryId = $(this).parents("tr").attr("trId");
        $.deleteJSON("country", {id:countryId}, function(data) {
            if (data && !hasError(data)) {

            $('[trID =' + countryId + ']').remove();
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

function fillCountryEditDialog(tr) {
    if (tr) {
        $("#headerEdit").text("Редактировать страну");
    	$("#countryIdDlg").val(tr.children("td.edit").attr("countryId"));
        $("#countryNameDlg").val(tr.children("td.name").text());
        $("#countryDescriptionDlg").val(tr.children("td.description").text());
        setSelectedItemInSelectObj($("#countryStatusDlg"), tr.children("td.status").attr("status"));
    }
    else{
        $("#headerEdit").text("Добавить страну");
        $("#countryIdDlg").val(-1);
        $("#countryNameDlg").val("");
        $("#countryDescriptionDlg").val("");
        setSelectedItemInSelectObj( $("#countryStatusDlg"),"Active");
    }
}

function addCountry(data){
    var strtable = ""; 
	 	
    	strtable += "<tr trID = " + data.value.id + ">";
		strtable += "<td class = 'edit' countryId =" + data.value.id + ">" +  
		"<button type='button' class='btn btn-default countryEdit'>" +
		  "<span class='glyphicon glyphicon-pencil'>" + "</span>" +
		"</button>"  
	    + 
	    "&nbsp"
	    +
	    "<button type='button' class='btn btn-default countryDelete'>" +
	      "<span class='glyphicon glyphicon-trash'>" + "</span>" +
	    "</button>" +
		"</td>";
		strtable += "<td class = 'name'>" +  data.value.name + "</td>";
		strtable += "<td class = 'description'>" +  data.value.description + "</td>";
		strtable += "<td class = 'status' status = " + data.value.status +">" + data.value.status + "</td>";
		strtable += "</tr>";
		
		$('#tblCountries tr:last').after(strtable);
		bindEditClick( $('#tblCountries tr:last .countryEdit') );
		bindDeleteClick( $('#tblCountries tr:last .countryDelete') );
}

function editCountry(data){
	$('[trID =' + data.value.id + ']').children("td.name").text(data.value.name);
	$('[trID =' + data.value.id + ']').children("td.description").text(data.value.description);
	$('[trID =' + data.value.id + ']').children("td.status").text(data.value.status);
    $('[trID =' + data.value.id + ']').children("td.status").attr("status", data.value.status);

}