var x = null;

$(document).ready(function() {

    bindEditClick($(".aSettingMailEdit"));
    bindDeleteClick($(".aSettingMailDelete"));
	
	$("#btnSaveSettingMailDlg").click(function(){
		var wm = new SettingMailWebModel(
            $("#inpSettingMailIdDlg").val(),
            $("#settingMailNameDlg").val(),
            $("#settingMailDescriptionDlg").val(),
            
            $("#settingMailHostDlg").val(),
            $("#settingMailPortDlg").val(),
            $("#settingMailUserNameDlg").val(),
            $("#settingMailUserPasswordDlg").val(),
            $("#settingMailDefaultSenderDlg").val(),
            $("#settingMailDefaultSubjectPrefixDlg").val(),
            
            $("#settingMailStatusDlg").children("option:selected").attr("value"),
            $("#selOrganization").children("option:selected").attr("value")
		);
		
		$.postJSON("settings-mail", wm, function(data) {
            if (data /*&& !hasError(data)*/) {
            	//password = data.value.userPassword;
            	
            	if(x==0){
            		addSettingsMailStr(data);
                    showNotification("Сохранение выполнено успешно", "success");
            	} else if (x==1){
            		editSettingsMailStr(data);
                    showNotification("Изменение сохранено", "success");
            	}
                return false;
            }
        });
		$("#dlgSettingMailEdit").modal("hide");
	});

	$("#aSettingMailAdd").click(function() {
		fillSettingMailEdit();
		x=0;
        $("#dlgSettingMailEdit").modal({
            backdrop : true
        });
	});
	
//	$('#tblSettigMail').dataTable();
});

function bindEditClick(elements){
    elements.on("click", function() {
        x = 1;
        fillSettingMailEdit( $(this).parents("tr") );
        $("#dlgSettingMailEdit").modal({
            backdrop : true
        });
    });
}

function bindDeleteClick(elements){
    elements.on("click", function() {
        var settingMailId = $(this).parents("tr").attr("trId");
        $.deleteJSON("setting-mail", {id:settingMailId}, function(data) {
            if (data && !hasError(data)) {

                $('[trId =' + settingMailId + ']').remove();
                showNotification("Удаление выполнено", "warning");
                return false;
            }
        });
    });
}

function SettingMailWebModel(id, name, description, host, port, userName, userPassword, defaultSender, defaultSubjectPrefix, status, organizationId) {
    this.id = id;
	this.name = name;
    this.description = description;
    this.host = host;
    this.port = port;
    this.userName = userName;
    this.userPassword = userPassword;
    this.defaultSender = defaultSender;
    this.defaultSubjectPrefix = defaultSubjectPrefix;
    this.status = status;
    this.organizationId = organizationId;
}

function fillSettingMailEdit(tr) {
    if (tr) {
        $("#headerEdit").text("Редактировать настройки почты");
        $("#inpSettingMailIdDlg").val(tr.children("td.edit").attr("settingMailId"));
        $("#settingMailNameDlg").val(tr.children("td.name").text());
        $("#settingMailDescriptionDlg").val(tr.children("td.description").text()); 
        
        $("#settingMailHostDlg").val(tr.children("td.host").text());
        $("#settingMailPortDlg").val(tr.children("td.port").text());
        $("#settingMailUserNameDlg").val(tr.children("td.login").text());
        $("#settingMailUserPasswordDlg").val(tr.children("td.password").text());
        $("#settingMailDefaultSenderDlg").val(tr.children("td.defaultSender").text());
        $("#settingMailDefaultSubjectPrefixDlg").val(tr.children("td.defaultSubjectPrefix").text());
        
        setSelectedItemInSelectObj($("#settingMailStatusDlg"), tr.children("td.status").attr("status"));
        setSelectedItemInSelectObj($("#selOrganization"), tr.children("td.organization").attr("organizationId"));

    } else {
        $("#headerEdit").text("Добавить настройки почты");
        $("#inpSettingMailIdDlg").val(-1);
        $("#settingMailNameDlg").val("");
        $("#settingMailDescriptionDlg").val("");
        
        $("#settingMailHostDlg").val("");
        $("#settingMailPortDlg").val("");
        $("#settingMailUserNameDlg").val("");
        $("#settingMailUserPasswordDlg").val("");
        $("#settingMailDefaultSenderDlg").val("");
        $("#settingMailDefaultSubjectPrefixDlg").val("");
        
        setSelectedItemInSelectObj( $("#settingMailStatusDlg"),"Active");
        if($("#organizationNameDlg").children("option[value='disabled']").val() == "disabled") {
            setSelectedItemInSelectObj($("#selOrganization"), "disabled");
        }
        
       }
    }

function addSettingsMailStr(data){
	var strtable = ""; 
	strtable += "<tr trId = " + data.value.id + ">";
		strtable += "<td class = 'edit' settingMailId =" + data.value.id + ">" +  
		"<button type='button' class='btn btn-default aSettingMailEdit'>" +
		    "<span class='glyphicon glyphicon-pencil'>" + "</span>" +
		    "</button>" 
		    + 
		    "&nbsp"
		    +
		   "<button type='button' class='btn btn-default aSettingMailDelete'>" +
	   "<span class='glyphicon glyphicon-trash'>" + "</span>" +
	   "</button>"
		+ "</td>";
		strtable += "<td class = 'name'>" + data.value.name + "</td>";
		strtable += "<td class = 'description'>" + data.value.description + "</td>";
		strtable += "<td class = 'host'>" + data.value.host + "</td>";
		strtable += "<td class = 'port' hidden='hidden'>" + data.value.port + "</td>";
		strtable += "<td class = 'login'>" + data.value.userName + "</td>";
		strtable += "<td class = 'password' hidden='hidden'>" + data.value.userPassword + "</td>";
		strtable += "<td class = 'defaultSender'>" + data.value.defaultSender + "</td>";
		strtable += "<td class = 'defaultSubjectPrefix'>" + data.value.defaultSubjectPrefix + "</td>";
		strtable += "<td class = 'status' status = " + data.value.status + ">" +  data.value.status + "</td>";
		if (data.value.organizationDto) {
            strtable += "<td class = 'organization' hidden='hidden' organizationId = " + data.value.organizationDto.id + ">" +  data.value.organizationDto.name + "</td>";
        } else {
            strtable += "<td class = 'organization' hidden='hidden' organizationId = 'disabled'></td>";
        }

		$('#tblSettigMail tr:last').after(strtable);
        bindEditClick( $('#tblSettigMail tr:last .aSettingMailEdit') );
        bindDeleteClick( $('#tblSettigMail tr:last .aSettingMailDelete') );
}

function editSettingsMailStr(data){
	$('[trId =' + data.value.id + ']').children("td.name").text(data.value.name);
	$('[trId =' + data.value.id + ']').children("td.description").text(data.value.description);
	$('[trId =' + data.value.id + ']').children("td.host").text(data.value.host);
	$('[trId =' + data.value.id + ']').children("td.port").text(data.value.port);
	$('[trId =' + data.value.id + ']').children("td.login").text(data.value.userName);
	$('[trId =' + data.value.id + ']').children("td.password").text(data.value.userPassword);
	$('[trId =' + data.value.id + ']').children("td.defaultSender").text(data.value.defaultSender);
	$('[trId =' + data.value.id + ']').children("td.defaultSubjectPrefix").text(data.value.defaultSubjectPrefix);
	$('[trId =' + data.value.id + ']').children("td.status").text(data.value.status);
	
	
    if (data.value.organizationDto) {
        $('[trId =' + data.value.id + ']').children("td.organization").text(data.value.organizationDto.name);
        $('[trId =' + data.value.id + ']').children("td.organization").attr("organizationId", data.value.organizationDto.id);
    } else {
        $('[trId =' + data.value.id + ']').children("td.organization").text("");
        $('[trId =' + data.value.id + ']').children("td.organization").attr("organizationId", "disabled");
    }
}