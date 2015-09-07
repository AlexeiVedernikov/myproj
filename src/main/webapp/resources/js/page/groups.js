var x = null;
	
$(document).ready(function() {
	
	bindEditClick($(".groupEdit"));
	bindDeleteClick($(".groupDelete"));
    bindSheduleClick($(".groupShedule"));
	
	/*$("#selOrganizationForAdmin").change(function() {
        var colorType = $("#selOrganizationForAdmin").children("option:selected").attr("value");
       
        alert(colorType);
         if(colorType=="all"){
            $("tr.detail").show();
        }
        else{
            $("tr.detail").hide();
            $("tr.detail[colorType=" + colorType + "]").show();
        }
    });
    $( "#selOrganizationForAdmin").change();*/

	$("#groupEditAddDlg").click(function(){
			var wm = new GroupWebModel(
					$("#groupIdDlg").val(),
					$("#groupNameDlg").val(),
					$("#groupNumberDlg").val(),
					$("#organizationNameDlg").children("option:selected").attr("value"),
					$("#groupDescriptionDlg").val(),
					$("#groupStatusDlg").children("option:selected").attr("value")
			);
		
		$.postJSON("group", wm, function(data) {
            if (data && !hasError(data)) {
            	if (x == 0){
            		addGroup(data);
                    showNotification("Сохранение выполнено успешно", "success");
            	}
            	else if (x == 1){
            		editGroup(data);
                    showNotification("Изменение сохранено", "success");
            	}
            	$("#groupEditDlg").modal("hide");
                return false;
            }
        });
	});

$("#add").click(function(){	
		x = 0;
		fillGroupEditDialog();
	    $("#groupEditDlg").modal({
	        backdrop : true
	      });
	   });
  $('#tblGroups').dataTable();


});

function bindEditClick(elements){
	elements.on("click", function() {
		x = 1;
		fillGroupEditDialog( $(this).parents("tr") ); 
	       $("#groupEditDlg").modal({
	          backdrop : true
	       });
	});
}
function bindDeleteClick(elements){
	elements.on("click", function() {
			fillGroupEditDialog( $(this).parents("tr") ); 
		    var wm = new GroupWebModel(
		    		$("#groupIdDlg").val(),
					$("#groupNameDlg").val(),
					$("#groupNumberDlg").val(),
					$("#organizationNameDlg").children("option:selected").attr("value"),
					$("#groupDescriptionDlg").val(),
					$("#groupStatusDlg").children("option:selected").attr("value")
			);
		    
		    $.deleteJSON("group", wm, function(data) {
		    	if (data && !hasError(data)){
		         $('[trID =' + $("#groupIdDlg").val() + ']').remove();
                 showNotification("Удаление выполнено", "warning");
		        return false;
		       }
		  });
	});
}
function bindSheduleClick(elements){
    elements.on("click", function() {
        window.location.href="shedule?groupId=" + $(this).parents("tr").attr("trID");
    });
}

function GroupWebModel(id, name, number, organizationId, description, status) {
	this.id = id;
	this.name = name;
	this.number = number;
	this.organizationId = organizationId;
    this.description = description;
    this.status = status;
}

function fillGroupEditDialog(tr) {
    if (tr) {
        $("#headerEdit").text("Редактировать класс");
        $("#groupIdDlg").val(tr.children("td.edit").attr("groupId"));
        $("#groupNameDlg").val(tr.children("td.name").text());
        $("#groupNumberDlg").val(tr.children("td.number").text());
        $("#groupDescriptionDlg").val(tr.children("td.description").text());
        setSelectedItemInSelectObj($("#groupStatusDlg"), tr.children("td.status").attr("status"));
        setSelectedItemInSelectObj($("#organizationNameDlg"), tr.children("td.organizationName").attr("organizationId"));
    }
    else{
        $("#headerEdit").text("Добавить класс");
        $("#groupIdDlg").val(-1);
        $("#groupNameDlg").val("");
        $("#groupNumberDlg").val("");
        $("#groupDescriptionDlg").val("");
        setSelectedItemInSelectObj( $("#groupStatusDlg"),"Active");
        if($("#organizationNameDlg").children("option[value='disabled']").val() == "disabled") {
            setSelectedItemInSelectObj($("#organizationNameDlg"), "disabled");
        }
    }
}

function addGroup(data){
    var strtable = ""; 
	 	
    	strtable += "<tr trID = " + data.value.id + ">";
		strtable += "<td class = 'edit' groupId =" + data.value.id + ">" +  
		"<button type='button' class='btn btn-default groupEdit'>" +
		  "<span class='glyphicon glyphicon-pencil'>" + "</span>" +
		"</button>"  
	    + 
	    "&nbsp"
	    +
	    "<button type='button' class='btn btn-default groupDelete'>" +
	      "<span class='glyphicon glyphicon-trash'>" + "</span>" +
	    "</button>" +
        "&nbsp"
        +
        "<button type='button' class='btn btn-default groupShedule' title='Редактировать предметы'>" +
        "<span class='glyphicon glyphicon-list-alt'>" + "</span>" +
        "</button>" +
		"</td>";
		strtable += "<td class = 'name'>" +  data.value.name + "</td>";
		strtable += "<td class = 'number'>" +  data.value.number + "</td>";
		strtable += "<td class = 'organizationName'  organizationId = " + data.value.organizationDto.id + ">" +  data.value.organizationDto.name + "</td>";
		strtable += "<td class = 'description'>" +  data.value.description + "</td>";
		strtable += "<td class = 'status' status = " + data.value.status +">" + data.value.status + "</td>";
		strtable += "</tr>";
		
		$('#tblGroups tr:last').after(strtable);
		bindEditClick( $('#tblGroups tr:last .groupEdit') );
		bindDeleteClick( $('#tblGroups tr:last .groupDelete') );
        bindSheduleClick($('#tblGroups tr:last .groupShedule'));
}

function editGroup(data){
	$('[trID =' + data.value.id + ']').children("td.name").text(data.value.name);
	$('[trID =' + data.value.id + ']').children("td.number").text(data.value.number);
	$('[trID =' + data.value.id + ']').children("td.organizationName").text(data.value.organizationDto.name);
	$('[trID =' + data.value.id + ']').children("td.description").text(data.value.description);
	$('[trID =' + data.value.id + ']').children("td.status").text(data.value.status);
}