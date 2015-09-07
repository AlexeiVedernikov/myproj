$(document).ready(function() {

    bindParentProfileClick($(".parentProfile"));
    bindParentDeleteClick($(".parentDelete"));

    $("#parentSave").click(function(){

        $.postJSON("parent", {childId:$("#userId").val(), parentId:$(".radioParent:checked").parents("tr").attr("trParentIDOut")}, function(data) {
            if (data && !hasError(data)) {

                addParent(data);
                showNotification("Сохранение выполнено успешно", "success");

                return false;
            }
        });

    });

    $("#FindParent").click(function() {

        $.getJSON("parentSearch", {name:$("#parentName").val()}, function(data) {
                if (data && !hasError(data)) {
                    $(".bodyParents").html(function(){
                        var str = "";
                        for(var i = 0; i<data.value.length; i++) {
                            str += "<tr trParentIDOut = " + data.value[i].id + ">" +
                                "<td class = 'edit'>" +
                                "<input type='radio' name='radioParent' class='radioParent'>" +
                                "&nbsp" +
                                "<button type='button' class='btn btn-default parentOutProfile'>" +
                                "<span class='glyphicon glyphicon glyphicon-user'>" + "</span>" +
                                "</button>" +
                                "</td>" +
                                "<td class='name'>" + data.value[i].name + "</td>" +
                                "<td class='email'>" + data.value[i].email + "</td>" +
                                "<td class='phone'>" + data.value[i].phone + "</td>" +
                                "<td class='description'>" + data.value[i].description + "</td>" +
                                "<td class='userType' userType=" + data.value[i].userType + ">" + data.value[i].userType + "</td>" +
                                "<td class='status' status=" + data.value[i].status + ">" + data.value[i].status + "</td>" +
                                "</tr>";
                        }
                        return str;
                    });
                    bindParentProfileClickOut( $(".parentOutProfile"));
                    return false;
                }}
        )
    });

    $("#addParent").click(function() {

        $("#parentName").val("");

        $("#addParent").hide();
        $("#findAndAddParent").show();

    });

});

function addParent(data){

    var strtable = "";

    strtable += "<tr trParentID = " + data.value.id + ">";
    strtable += "<td class = 'edit'>" +
        "<button type='button' class='btn btn-default parentDelete'>" +
        "<span class='glyphicon glyphicon-trash'>" + "</span>" +
        "</button>"
        +
        "&nbsp"
        +
        "<button type='button' class='btn btn-default parentProfile'>" +
        "<span class='glyphicon glyphicon glyphicon-user'>" + "</span>" +
        "</button>" +
        "</td>";
    strtable += "<td class = 'name'>" +  data.value.name + "</td>";
    strtable += "<td class = 'email'>" +  data.value.email + "</td>";
    strtable += "<td class = 'phone'>" +  data.value.name + "</td>";
    strtable += "<td class = 'description'>" +  data.value.description + "</td>";
    strtable += "<td class = 'userType' status = " + data.value.userType +">" + data.value.userType + "</td>";
    strtable += "<td class = 'status' status = " + data.value.status +">" + data.value.status + "</td>";
    strtable += "</tr>";

    $('#tblParent tr:last').after(strtable);

    bindParentProfileClick( $('#tblParent tr:last .parentProfile') );
    bindParentDeleteClick( $('#tblParent tr:last .parentDelete') );
}

function bindParentProfileClick(elements){
    elements.on("click", function() {
        window.location.href="user-profile?id=" + $(this).parents("tr").attr("trParentID");
    });
}

function bindParentProfileClickOut(elements){
    elements.on("click", function() {
        window.location.href="user-profile?id=" + $(this).parents("tr").attr("trParentIDOut");
    });
}

function bindParentDeleteClick(elements){
    elements.on("click", function() {
        var parentId = $(this).parents("tr").attr("trParentID");
        $.deleteJSON("parentAndChilDelete", {childId:$("#userId").val(), parentId:parentId}, function(data) {
            if (data && !hasError(data)) {

                $('[trParentID =' + parentId + ']').remove();
                showNotification("Удаление выполнено", "warning");
                return false;
            }
        });
    });
}