function setSelectedItemInSelectObj(selectObj, value) {
    if (selectObj) {
        if (selectObj.size() > 0) {
            selectObj.children("option[selected='selected']").removeAttr("selected");
            // selectObj.children("option[value='" + value +
            // "']").attr("selected",
            // "selected");
            selectObj.val(value);
            selectObj.selectpicker('refresh');
        }
    }
}

function hasError(data) {
    if (data.errorCode != "OK") {
        alert(data.errorCode);
        return true;
    }

    return false;
}

function showNotification(text, type){
    noty({
        text: text,
        type: type,
        dismissQueue: true,
        timeout: 5000,
        layout: 'top',
        theme: 'defaultTheme'
    });
}

function showNotificationJournal(text, type, timeout){
    noty({
        text: text,
        type: type,
        dismissQueue: true,
        timeout: timeout,
        layout: 'top',
        theme: 'defaultTheme'
    });
}

$(document).ready(function() {

    $("#selOrganizationForMenu").change(function() {
        var organizationId = $("#selOrganizationForMenu option:selected").val();
        $.postJSON("organizationSelect", {id:organizationId}, function(data) {
            window.location.reload();
        })
    });

    $(".qoo").click(function(){
    return false;
    });



});